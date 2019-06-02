package com.anagramsolver.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FormatUtilities {

	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date currentDate = new Date();
		return df.format(currentDate);
	}

	public static String timeFormatUtility(long elapsedMilliseconds) {
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(elapsedMilliseconds),
				TimeUnit.MILLISECONDS.toMinutes(elapsedMilliseconds)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(elapsedMilliseconds)),
				TimeUnit.MILLISECONDS.toSeconds(elapsedMilliseconds)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedMilliseconds)));
		return hms;
	}

	public static String prepareAnagram(String sourceAnagram) {
		char[] srcLetters = sourceAnagram.toLowerCase().replaceAll("\\s+", "").toCharArray();
		Arrays.sort(srcLetters);
		return new String(srcLetters);
	}
	
	
	public static byte[] fromHexToByte(String hexRepresentation) {
		int len = hexRepresentation.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(hexRepresentation.charAt(i), 16) << 4)
	                             + Character.digit(hexRepresentation.charAt(i+1), 16));
	    }
	    return data;
	}

}
