package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLOffscreenAutoDrawable;
import javax.media.opengl.GLProfile;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.opengl.util.awt.AWTGLReadBufferUtil;

public class Tesr {

	public static void main(String[] args) {
//		GLProfile glp = GLProfile.getDefault();
//		GLDrawableFactory glf = GLDrawableFactory.getFactory(glp);
//		AbstractGraphicsDevice glagd = GLProfile.getDefaultDevice();
//		System.out.println(glf.canCreateFBO(glagd, glp));
//		GLCapabilities glcap = new GLCapabilities(glp);
//		glcap.setFBO(true);
//		final GLAutoDrawable gloffd = glf.createOffscreenAutoDrawable(glagd, glcap, null, 300, 300);
//		gloffd.display();
//		
//		GL2 gl2 = gloffd.getGL().getGL2();
//
//		gl2.glPointSize(4f);
//		gl2.glColor3f(1f,0f,0f);    
//		gl2.glEnableClientState(GL2.GL_VERTEX_ARRAY);
//		
//		BufferedImage im = new AWTGLReadBufferUtil(gloffd.getGLProfile(), false).readPixelsToBufferedImage(gloffd.getGL(), 0, 0, 300, 300, true);
//		try {
//			ImageIO.write(im,"png",new File("im.png"));
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
		
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setOnscreen(false);

		// create the offscreen drawable
		GLDrawableFactory factory = GLDrawableFactory.getFactory(glp);
		GLOffscreenAutoDrawable drawable = factory.createOffscreenAutoDrawable(null,caps,null,300,300);
		drawable.display();
		drawable.getContext().makeCurrent();

		GL2 gl = drawable.getGL().getGL2();

		// use pixel coordinates
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();    
		gl.glOrtho(0d, 300, 300, 0d, -1d, 1d);

		// draw some points to the drawable
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3d( 1.0, 1.0, 1.0 );
		gl.glVertex2d( -0.9, -0.9 );
		gl.glVertex2d( 0.9, 0.9 );
		gl.glVertex2d( 0.9, -0.9 );
		gl.glEnd();
		gl.glFlush();
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);

		BufferedImage im = new AWTGLReadBufferUtil(drawable.getGLProfile(), false).readPixelsToBufferedImage(drawable.getGL(), 0, 0, 300, 300, true /* awtOrientation */);
		try {
			ImageIO.write(im,"png",new File("im.png"));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
//		gloffd.addGLEventListener(new GLEventListener() {
//			
//			@Override
//			public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
//				// TODO 自動生成されたメソッド・スタブ
//				
//			}
//			
//			@Override
//			public void init(GLAutoDrawable arg0) {
//				int width = gloffd.getWidth();
//				int height = gloffd.getHeight();
//				GL gl = gloffd.getGL();
//				ByteBuffer buff = ByteBuffer.allocate(width * height * 4); //4= rgba
//				gl.glReadPixels(0, 0, width, height, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, buff);
//				
//				BufferedImage bfi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
//				for (int i = 0; i < ; i += 3) {
//					pixels[i] =
//							((fb.get(bindex) << 16)) +
//							((fb.get(bindex+1) << 8)) +
//							((fb.get(bindex+2) << 0));
//				}
//				
//				bfi.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
//				bfi.set;
//				try {
//					BufferedImage bi = ImageIO.wrread(in);
//					System.out.println(bi);
//					ImageIO.write(bi, "png", new File("test.png"));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//			}
//			
//			@Override
//			public void dispose(GLAutoDrawable arg0) {
//				// TODO 自動生成されたメソッド・スタブ
//				
//			}
//			
//			@Override
//			public void display(GLAutoDrawable arg0) {
//				// TODO 自動生成されたメソッド・スタブ
//				
//			}
//		});

//		gloffd.display();
	}

	public static String toTimeFormat(long time) {
		long allSec = time / 1000;
		
		String hour = filString(allSec / 60 / 60, '0', 3);
		String minutes = filString(allSec / 60 % 60, '0', 2);
		String sec = filString(allSec % 60, '0', 2);
		String milliSec = filString(time % 1000, '0', 3);
		
		return hour+":"+minutes+":"+sec+"."+milliSec;
	}
	
	private static String filString(String str, char fillWith, int maxlegth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < maxlegth - str.length(); i++) sb.append(fillWith);
		return sb.toString() + str;
	}
	private static String filString(long i, char fillWith, int maxlegth) {
		return filString(String.valueOf(i), fillWith, maxlegth);
	}
}
