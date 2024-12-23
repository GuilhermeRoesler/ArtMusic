package utils;

import javax.swing.text.MaskFormatter;

public class MaskUtil {

	public static MaskFormatter createMaskFormatter(String mask) {
		try {
			return new MaskFormatter(mask);
		} catch (Exception e) {
			return null;
		}
	}
}
