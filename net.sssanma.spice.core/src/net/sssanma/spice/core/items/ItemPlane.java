package net.sssanma.spice.core.items;

import net.sssanma.spice.core.parts.ResourcePart;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ItemPlane implements Item {

	private Image thumbnail;
	private Color color;
	private Point size;
	
	public ItemPlane(Color color, Point size) {
		this.color = color;
		this.size = size;
		createThumbnail(ResourcePart.THUMBNAIL_WIDTH, ResourcePart.THUMBNAIL_HEIGHT);
	}
	
	@Override
	public void createThumbnail(int width, int height) {
		RGB red = new RGB(255, 0, 0);
		RGB green = new RGB(0, 255, 0);
		RGB blue = new RGB(0, 0, 255);
		ImageData imageData = new ImageData(width, height, 16, new PaletteData(new RGB[] {red, green, blue}));
		this.thumbnail = new Image(Display.getCurrent(), imageData);
	}

	@Override
	public Image getThumbnail() {
		return thumbnail;
	}

	@Override
	public Point getSize() {
		return size;
	}
	
	public Color getPlaneColor() {
		return color;
	}
	
	@Override
	public void delete() {
		this.thumbnail.dispose();
	}
}
