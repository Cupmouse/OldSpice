package net.sssanma.spice.core.utilities;

public class FormatUtil {
	public static String toTime(int fps, long nowframe) {
		if (nowframe / fps > 3600) 
			return toTime(fps, nowframe, Format.HOUR_MIN_SEC_FRAME);
		else
			return toTime(fps, nowframe, Format.HOURREMOVED_MIN_SEC_FRAME);
	}
	
	public static String toTime(int fps, long nowframe, Format format) {
		long allSec = (long) ((double)nowframe / fps * 1000);
		
		String hour = filString(allSec / 1000 / 60 / 60, '0', 3);
		String minutes = filString(allSec / 1000 / 60 % 60, '0', 2);
		String sec = filString(allSec / 1000 % 60, '0', 2);
		String milliSec = filString(allSec % 1000, '0', 3);
		String secFrame = filString(nowframe % fps, '0', 2);
		
		switch (format) {
		case HOUR:
			return hour+";";
		case HOUR_MIN:
			return hour+";"+minutes;
		case HOUR_MIN_SEC:
			return hour+";"+minutes+";"+sec;
		case HOUR_MIN_SEC_MILL:
			return hour+";"+minutes+";"+sec+"."+milliSec;
		case HOUR_MIN_SEC_FRAME:
			return hour+";"+minutes+";"+sec+";"+ secFrame + "F";
		case HOURREMOVED_MIN_SEC_FRAME:
			return minutes+";"+sec+";"+ secFrame + "F";
		case FRAME:
			return nowframe+"F";
		default:
			return "フォーマットエラー";
		}
	}
	
	private static String filString(String str, char fillWith, int maxlegth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < maxlegth - str.length(); i++) sb.append(fillWith);
		return sb.toString() + str;
	}
	private static String filString(long i, char fillWith, int maxlegth) {
		return filString(String.valueOf(i), fillWith, maxlegth);
	}
	
	public enum Format {
		HOUR_MIN_SEC_MILL, HOUR_MIN_SEC, HOUR_MIN, HOUR, HOUR_MIN_SEC_FRAME, FRAME, HOURREMOVED_MIN_SEC_FRAME;
	}
}
