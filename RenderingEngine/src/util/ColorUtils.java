package util;

import java.awt.Color;

public class ColorUtils {

	public static Color multiply(Color c1, Color c2) {
		return new Color(((float)c1.getRed()*c2.getRed())/255,
						 ((float)c1.getGreen()*c2.getGreen())/255,
						 ((float)c1.getBlue()*c2.getBlue())/255);
	}
	
}
