 
package net.sssanma.spice.core.parts;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class PreviewPart {

	@PostConstruct
	public void createComposite(final Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Canvas canvas = new Canvas(composite, SWT.NONE);
		GridData gd_canvas = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_canvas.heightHint = 900;
		gd_canvas.widthHint = 900;
		canvas.setLayoutData(gd_canvas);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
	}
}