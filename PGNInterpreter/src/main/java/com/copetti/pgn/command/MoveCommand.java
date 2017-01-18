package com.copetti.pgn.command;

import java.util.List;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.command.decorator.CommandDecorator;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public class MoveCommand extends ChessCommand {

	private @Getter ChessSquare destSquare;
	private List<CommandDecorator> decorators;

	public MoveCommand(ChessPiece chessPiece, ChessSquare destination) {
		super(chessPiece);
		this.destSquare = destination;
	}

	@Override
	public ChessBoard doExecute(ChessBoard input) {
		return null;
	}

	public void addDecorator(CommandDecorator decorator) {
		decorators.add(decorator);
	}

	@Override
	protected boolean canExecute(ChessBoard input) {
		return false;
	}

}
