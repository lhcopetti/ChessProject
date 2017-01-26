package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CastleLongCommand extends CastleCommand {

	public CastleLongCommand(CheckFlag flag) {
		super(ChessPiece.KING, flag);
	}

	@Override
	protected boolean canExecute(ChessBoard input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean doExecute(ChessBoardContextBuilder builder, ChessBoard input) {
		// TODO Auto-generated method stub
		return false;
	}

}
