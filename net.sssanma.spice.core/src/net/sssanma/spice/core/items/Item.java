package net.sssanma.spice.core.items;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

public interface Item {
	public Image getThumbnail();
	public void createThumbnail(int width, int height);
	public Point getSize();
	public void delete();
}
