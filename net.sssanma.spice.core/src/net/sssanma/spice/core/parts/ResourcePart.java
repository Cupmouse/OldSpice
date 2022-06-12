 
package net.sssanma.spice.core.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;

public class ResourcePart {
	public static final int THUMBNAIL_WIDTH = 80;
	public static final int THUMBNAIL_HEIGHT = 80;
	private Label text1;
	private Canvas resourceThumbnail;
	private Label text2;
	private TableColumn nameColumn;
	private DropTarget dropTarget;
	private DragSource dragSource;
	private Table resources;
	
	@PostConstruct
	public void createComposite(final Composite ) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 0;
		gl_parent.marginWidth = 0;
		gl_parent.marginHeight = 0;
		parent.setLayout(gl_parent);
		
		Composite resourceInfo = new Composite(parent, SWT.NONE);
		GridLayout gl_resourceInfo = new GridLayout(2, false);
		resourceInfo.setLayout(gl_resourceInfo);
		resourceInfo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		text1 = new Label(resourceInfo, SWT.NONE);
		text1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		text1.setText("\u30DC\u30C3\u30AF\u30B9 \u30C6\u30B9\u30C8");
		
		resourceThumbnail = new Canvas(resourceInfo, SWT.NONE);
		resourceThumbnail.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		resourceThumbnail.setBounds(0, 0, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
		resourceThumbnail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 3));
		
		text2 = new Label(resourceInfo, SWT.NONE);
		text2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		text2.setText("1000*1000");
		new Label(resourceInfo, SWT.NONE);
		
		resources = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
		resources.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		resources.setHeaderVisible(true);
		resources.setLinesVisible(true);
		
		nameColumn = new TableColumn(resources, SWT.NONE);
		nameColumn.setWidth(170);
		nameColumn.setText("\u540D\u524D");
		
		dropTarget = new DropTarget(resources, DND.DROP_MOVE);
		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void dragEnter(DropTargetEvent event) {
			}
			@Override
			public void drop(DropTargetEvent event) {
				TableItem tableItem = new TableItem(resources, SWT.NONE);
				tableItem.setText("テストアイテム");
				
			}
		});
		
		dragSource = new DragSource(resources, DND.DROP_MOVE);
		
		
		// 右クリックメニュー
		Menu menu = new Menu(resources, SWT.POP_UP);
	}
}