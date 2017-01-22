package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CaptureCommand extends DisplacementCommand {

	public CaptureCommand(ChessPiece chessPiece, ChessSquare destSquare, CheckFlag flag) {
		super(chessPiece, destSquare, flag);
	}

	@Override
	protected boolean canExecute(ChessBoard input) {
		return false;
	}

	@Override
	protected ChessBoard doExecute(ChessBoard input) {
		return null;
	}

	@Override
	protected boolean checkTargetSquare(ChessBoard board) {

		ColoredChessPiece cp = board.at(getDestinationSquare());

		if (null == cp)
			return false;

		return board.getNextToPlay().opposite() == cp.getColor();
	}

}
