package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.command.decorator.PromotionDecorator;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class DisplacementCommand extends ChessCommand {

	private @Getter ChessSquare destinationSquare;

	@Setter(value = AccessLevel.PUBLIC)
	private @Getter PromotionDecorator promotion;

	public DisplacementCommand(ChessPiece chessPiece, ChessSquare destSquare, CheckFlag flag) {
		super(chessPiece, flag);
		this.destinationSquare = destSquare;
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
