package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CaptureCommand extends DisplacementCommand {

	public CaptureCommand(ChessPiece chessPiece, ChessSquare destSquare, CheckFlag flag) {
		super(chessPiece, destSquare, flag);
	}

	@Override
	protected boolean canExecute(ChessBoard input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected ChessBoard doExecute(ChessBoard input) {
		// TODO Auto-generated method stub
		return null;
	}

}
