package com.copetti.pgn.lichess;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LichessPGNReader {

	public static LichessPGNMatch read(String pgnContent) {

		String whiteName = extractFromBrackets(pgnContent, "White");
		String blackName = extractFromBrackets(pgnContent, "Black");
		String result = extractFromBrackets(pgnContent, "Result");

		String commands = getCommands(pgnContent);
		return new LichessPGNMatch(whiteName, blackName, result, Arrays.asList(commands.split(" ")));
	}

	private static String getCommands(String pgnContent) {

		return pgnContent //
				.replaceAll("\r?\\n", "") //
				.replaceAll("\\[[^\\]]*\\]", "") //
				.replaceAll("\\{[^\\}]*\\}", "") //
				.replaceAll("[0-9]*\\.", "") //
				.replaceAll("\\s+", " ") //
				.replaceAll("\\s[^\\s]*$", "") //
				.trim();
	}

	protected static String extractFromBrackets(String content, String key) {
		Pattern capturePattern = Pattern.compile("\\[" + key + " \"([^\"]*)\"\\]");

		Matcher matcher = capturePattern.matcher(content);

		if (!matcher.find())
			return null;

		return matcher.group(1);
	}

}
