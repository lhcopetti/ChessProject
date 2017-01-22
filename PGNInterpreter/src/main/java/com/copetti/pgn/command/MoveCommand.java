package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class MoveCommand extends DisplacementCommand {

	public MoveCommand(ChessPiece chessPiece, ChessSquare destination, CheckFlag flag) {
		super(chessPiece, destination, flag);
	}

	@Override
	protected boolean checkTargetSquare(ChessBoard board) {

		return board.isEmptyAt(getDestinationSquare());
	}

}
