package fcplugins.tk.apply.utils;

import java.util.HashMap;
import java.util.regex.Pattern;

//
//  Not made by me :(
// 

public final class TimeUtils {

	private static final Pattern timePattern = Pattern.compile("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
	private static final HashMap<Character, Integer> timeUnit = new HashMap<>();

	static {
		timeUnit.put('d', 86400);
		timeUnit.put('h', 3600);
		timeUnit.put('m', 60);
		timeUnit.put('s', 1);
	}

	public static int toSeconds(final String time) {
		int seconds = 0;

		Integer i = null;
		Character ch;

		String[] array = timePattern.split(time);

		if (array.length % 2 != 0)
			return -1;

		for (String s : array) {
			if (i == null) {
				i = Integer.parseInt(s);
			} else {
				if (s.length() > 1) {
					return -1;
				}
				ch = s.charAt(0);
				if (!timeUnit.containsKey(ch)) {
					return -1;
				}
				seconds += (i * timeUnit.get(ch));
				ch = null;
				i = null;
			}
		}
		return (seconds <= 0 ? -1 : seconds);
	}

}