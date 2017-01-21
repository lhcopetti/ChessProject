package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public class MoveCommand extends DisplacementCommand {

	private @Getter ChessSquare destSquare;

	public MoveCommand(ChessPiece chessPiece, ChessSquare destination, CheckFlag flag) {
		super(chessPiece, destination, flag);
		destSquare = destination;
	}

	@Override
	public ChessBoard doExecute(ChessBoard input) {
		return null;
	}

}
