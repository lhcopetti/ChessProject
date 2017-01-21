package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CastleShortCommand extends CastleCommand {

	public CastleShortCommand(CheckFlag flag) {
		super(ChessPiece.KING, flag);
	}

	@Override
	protected boolean canExecute(ChessBoard input) {
		return false;
	}

	@Override
	protected ChessBoard doExecute(ChessBoard input) {
		return null;
	}

}
