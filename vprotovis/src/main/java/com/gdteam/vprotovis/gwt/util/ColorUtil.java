package com.gdteam.vprotovis.gwt.util;

import java.awt.Color;

public class ColorUtil {

	/**
	 * Make a color lighter.
	 * 
	 * @param color
	 *            Color to make lighter.
	 * @param fraction
	 *            Darkness fraction.
	 * @return Lighter color.
	 */
	public static Color lighter(Color color, double fraction) {
		int red = (int) Math.round(color.getRed() * (1.0 + fraction));
		int green = (int) Math.round(color.getGreen() * (1.0 + fraction));
		int blue = (int) Math.round(color.getBlue() * (1.0 + fraction));

		if (red < 0)
			red = 0;
		else if (red > 255)
			red = 255;
		if (green < 0)
			green = 0;
		else if (green > 255)
			green = 255;
		if (blue < 0)
			blue = 0;
		else if (blue > 255)
			blue = 255;

		int alpha = color.getAlpha();

		return new Color(red, green, blue, alpha);
	}
}
