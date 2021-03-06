package net.sssanma.spice.core.parts;

import javax.annotation.PostConstruct;
import net.sssanma.spice.core.Time;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DND;

public class TimelinePart {
	private Canvas timeLine;
	private int maxX = 100;
	private Time max;
	private int leftMargin = 100;
	private int rightMargin = 100;
	private int zoomLevel = 1;
	private int maxZoomLevel = 6;
	private Slider verticalSlider;
	private Slider horizonalSlider;
	private int fps = 60;
	private Time currentTime = new Time(fps, 0);
	private boolean movingCurrentTime;
	private long movingCurrentTimeCoolTime;
	private int contentsStartX = 65;
	private int contentsStartY;
	private int fontHeight;
	private DropTarget dropTarget;

	@PostConstruct
	public void createComposite(final Composite parent) {
		parent.setLayout(new BorderLayout(0, 0));
		
		timeLine = new Canvas(parent, SWT.NONE);
		
		dropTarget = new DropTarget(timeLine, DND.DROP_MOVE);
		timeLine.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {
				if (e.y < contentsStartY && e.x > contentsStartX) movingCurrentTime = true;
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				movingCurrentTime = false;
				movingCurrentTimeCoolTime = 0;
			}
		});
		timeLine.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				if (movingCurrentTime) {
					Time clickTime = pxToTime(horizonalSlider.getSelection() + e.x);
					
					if (clickTime.getCurrentFrame() > maxX)
						clickTime = new Time(fps, maxX);
					else if (clickTime.getCurrentFrame() < 0)
						clickTime = new Time(fps, 0);

					if (clickTime.compare(currentTime) == 0) return;
					
					Point maxsize = timeLine.getSize();
					int currentLineX = timeToPx(clickTime);
					int canvasCurrentLineX = currentLineX - horizonalSlider.getSelection();
					
					if (canvasCurrentLineX + getFrameSpace() > maxsize.x || canvasCurrentLineX <= contentsStartX) {
						if (movingCurrentTimeCoolTime > System.currentTimeMillis()) return;
						if (canvasCurrentLineX <= contentsStartX) {
							if (clickTime.getCurrentFrame() == 0) {
								horizonalSlider.setSelection(0);
							} else {
								horizonalSlider.setSelection(currentLineX - getFrameSpace());
							}
						} else {
							if (clickTime.compare(max) == 0) {
								horizonalSlider.setSelection(horizonalSlider.getMaximum());
							} else {
								horizonalSlider.setSelection(currentLineX - maxsize.x + getFrameSpace());
							}
						}
						movingCurrentTimeCoolTime = System.currentTimeMillis() + 70;
					}

					currentTime = clickTime;
					
					timeLine.redraw();
				}
			}
		});
		
		horizonalSlider = new Slider(parent, SWT.NONE);
		horizonalSlider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				timeLine.redraw();
			}
		});
		horizonalSlider.setLayoutData(BorderLayout.SOUTH);
		
		verticalSlider = new Slider(parent, SWT.VERTICAL);
		verticalSlider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				timeLine.redraw();
			}
		});
		verticalSlider.setLayoutData(BorderLayout.EAST);
		timeLine.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				changeSliderMax();
				redraw(e.gc);
			}
		});
		parent.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseScrolled(MouseEvent e) {
				if (e.count == 0) return;
				
				// ?Y?[???O???}?E?X?????u
				int beforeMouseX = horizonalSlider.getSelection() + e.x;
				
//				
//				
//				// ?}?E?X?????????u???t???[??
//				double beforeSelectFrame = (double)beforeMouseX / metorSpace;

				int afterSliderX = 0;
				if (e.count > 0 && zoomLevel + 1 <= maxZoomLevel) {
					afterSliderX = beforeMouseX * 2 - e.x;
					zoomLevel += 1;
				} else if (e.count < 0 && zoomLevel - 1 >= 1){
					afterSliderX = beforeMouseX / 2 - e.x;
					zoomLevel -= 1;
				} else {
					afterSliderX = 0;
				}
				
//				// ?Y?[???O???I?????????????t???[?????Y?[?????????u
//				double afterSelectFrameX = beforeSelectFrame * metorSpace;
				// ?Y?[???????X???C?_?[?????u
//				int afterSliderX = (int) (afterSelectFrameX - e.x);
				
				changeSliderMax();
				horizonalSlider.setSelection(afterSliderX);
				
				horizonalSlider.setIncrement(e.count > 0 ? horizonalSlider.getIncrement() + 1 : horizonalSlider.getIncrement() - 1);
				timeLine.redraw();
			}
		});
		
		this.max = new Time(fps, maxX);
	}
	private void changeSliderMax() {
		horizonalSlider.setMaximum(timeToPx(max) + rightMargin - timeLine.getSize().x);
	}
	private int getFrameSpace() {
		return 70;
	}
	
	private int timeToPx(Time time) {
		return (int) (time.getCurrentFrame() * getFrameSpace()) + leftMargin;
	}
	private Time pxToTime(long px) {
		return new Time(fps, (px - leftMargin) / getFrameSpace());
	}
	private void redraw(GC gc) {
		Point max = timeLine.getSize();
		Image temp = new Image(gc.getDevice(), max.x, max.y);
		GC tempGC = new GC(temp);
		fontHeight = tempGC.getFontMetrics().getHeight();
		contentsStartY = tempGC.getFontMetrics().getHeight() + 3;
		drawMetor(tempGC);
		drawTimeLine(tempGC);
		gc.drawImage(temp , 0, 0);
		tempGC.dispose();
		temp.dispose();
	}
	
	private void drawMetor(GC gc) {
		Point max = timeLine.getSize();
		int canvasLeftPosX = horizonalSlider.getSelection();
		int canvasRightPosX = canvasLeftPosX + max.x;
		
		Time renderStartFrame = pxToTime(canvasLeftPosX - getFrameSpace());
		Time renderEndFrame = pxToTime(canvasRightPosX + getFrameSpace() * 2);
		for (int i = timeToPx(renderStartFrame); i < timeToPx(renderEndFrame); i += getFrameSpace()) {
			Time time = pxToTime(i);
			if (time.compare(new Time(fps, maxX)) > 0) break;
			int x = i - canvasLeftPosX;
			String formattedTime = time.toTime();
			Point strSize = gc.textExtent(formattedTime);
			gc.drawString(formattedTime, x - strSize.x / 2, 0);
			gc.drawLine(x, fontHeight - 2, x, fontHeight + 3);
		}
		
		
//		
//		int margin = (int) (timeToPx(pxToTime(canvasLeftPosX + 100)) - canvasLeftPosX);
//		System.out.println(pxToTime(margin).toTime());
//		int renderMaxX = canvasRightPosX + 100;
//		Time baseTime = pxToTime(margin + horizonalSlider.getSelection());
//		switch(zoomLevel) {
//		default:
//			for (int i = 0; i < maxX; i++) {
//				Time time = new Time(fps, i);
//				int timeY = timeToPx(time)- canvasLeftPosX + margin;
//				String formattedTime = time.toTime();
//				Point strSize = gc.textExtent(formattedTime);
//				gc.drawString(formattedTime, timeY - strSize.x / 2, 0);
//				gc.drawLine(timeY, fontMaxY - 2, timeY, fontMaxY + 3);
//			}
//			for (int i = margin; i < renderMaxX - canvasLeftPosX; i+=metorSpace) {
//				System.out.println(i + horizonalSlider.getSelection());
//				Time time = pxToTime(i + horizonalSlider.getSelection());
//				if (time.compare(new Time(fps, maxX)) > 0) break;
//				String formattedTime = time.toTime();
//				Point strSize = gc.textExtent(formattedTime);
//				gc.drawString(formattedTime, i - strSize.x / 2, 0);
//				gc.drawLine(i, fontMaxY - 2, i, fontMaxY + 3);
//			}
//		}

		
		
		
		//		int j = 3;
//		int x;
//		for (int i = horizonalSlider.getSelection() / metorSpace; (x = i * metorSpace  - horizonalSlider.getSelection()) < max.x ; i++) {
//			if (j % 3 == 0) {
//				String formattedTime = pxToTime(x + horizonalSlider.getSelection()).toTime();
//				Point strSize = gc.textExtent(formattedTime);
//				gc.drawString(formattedTime, x - strSize.x / 2, 0);
//				gc.drawLine(x, fontMaxY - 2, x, fontMaxY + 3);
//			} else {
//				gc.drawLine(x, fontMaxY + 1, x, fontMaxY + 3);
//			}
//			j++;
//		}
//		int x;
//		int j = 0;
//		for (int i = horizonalSlider.getSelection() / getZoom(); (x = i * metorSpace - horizonalSlider.getSelection()) < max.x ; i++) {
//			if (i % metorSpace == 0) {
//				if (j % 3 == 0) {
//					gc.drawLine(x, fontMaxY - 1, x, fontMaxY + 3);
//					String formattedTime = pxToTime(x + horizonalSlider.getSelection()).toTime();
//					Point strSize = gc.textExtent(formattedTime);
//					gc.drawString(formattedTime, x - strSize.x / 2, 0);
//				} else {
//					gc.drawLine(x, fontMaxY + 1, x, fontMaxY + 3);
//				}
//				j++;
//			}
//		}
	}
	private int getZoom() {
		return (int) Math.round(Math.pow(2., zoomLevel));
	}
	private void drawTimeLine(GC gc) {
		Point max = timeLine.getSize();

		int canvasLeftPosX = horizonalSlider.getSelection();
		int currentLine = timeToPx(currentTime) - canvasLeftPosX;
		
		// ?I???????t???[?????w?i???F?t??????
		gc.setBackground(new Color(gc.getDevice(), new RGB(200, 200, 200)));
		gc.fillRectangle(currentLine, contentsStartY, getFrameSpace(), max.y - contentsStartY);

		gc.setForeground(new Color(gc.getDevice(), new RGB(255, 0, 0)));
		gc.drawLine(currentLine, fontHeight - 2, currentLine, max.y);
		
		// ??????????????
		gc.setBackground(new Color(gc.getDevice(), new RGB(255, 255, 255)));
		gc.fillRectangle(0, 0, contentsStartX, max.y);
		
		gc.setForeground(new Color(gc.getDevice(), new RGB(0, 0, 0)));
		// ?R???e???c????????????
		gc.drawLine(0, contentsStartY, max.x, contentsStartY);
		
		gc.drawLine(contentsStartX, 0, contentsStartX, max.y);
		
		// ?R???e???c?????C???[??
		for (int i = 0; i * 25 < max.y; i++) {
			int y = i * 25 + contentsStartY;
			gc.setForeground(new Color(gc.getDevice(), new RGB(0, 0, 0)));
			gc.drawLine(0, y, max.x, y);
			gc.setBackground(new Color(gc.getDevice(), new RGB(255, 255, 255)));
			gc.drawString("???C??"+i, 5, y + gc.textExtent("???C??"+i).y / 4);
		}
	}
}
