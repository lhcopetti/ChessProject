package com.copetti.pgn.lichess;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

public class LichessPGNMatch {

	private @Getter String whiteName;
	private @Getter String blackName;
	private @Getter String result;

	private @Getter List<String> pgnCommands;

	public LichessPGNMatch(String whiteName, String blackName, String result, List<String> pgnCommands) {

		this.whiteName = whiteName;
		this.blackName = blackName;
		this.result = result;
		this.pgnCommands = Collections.unmodifiableList(pgnCommands);
	}

}
