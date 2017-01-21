package com.copetti.pgn.command.decorator;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.command.ChessCommand;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class CommandDecorator {

	@Getter
	@Setter(value = AccessLevel.PUBLIC)
	private ChessCommand command;

	public boolean validate(ChessBoard input) {
		return false;
	}

}
