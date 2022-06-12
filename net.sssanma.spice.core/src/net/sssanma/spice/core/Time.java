package net.sssanma.spice.core;

import net.sssanma.spice.core.utilities.FormatUtil;

public class Time {
	private int fps;
	private long currentFrame;
	
	public Time(int fps, long currentFrame) {
		this.fps = fps;
		this.currentFrame = currentFrame;
	}
	
	public long getCurrentFrame() {
		return currentFrame;
	}
	
	public String toTime() {
		return FormatUtil.toTime(fps, currentFrame);
	}
	
	public Time addFrame(long frame) {
		return new Time(fps, currentFrame + frame);
	}

	public int compare(Time time) {
		return (int) (currentFrame * fps - time.currentFrame * time.fps);
	}
}
