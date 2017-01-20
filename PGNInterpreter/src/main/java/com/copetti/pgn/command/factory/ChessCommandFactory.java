package com.copetti.pgn.command.factory;

import java.util.List;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.command.CastleLongCommand;
import com.copetti.pgn.command.CastleShortCommand;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.command.MoveCommand;
import com.copetti.pgn.command.decorator.CaptureDecorator;
import com.copetti.pgn.command.decorator.PromotionDecorator;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class ChessCommandFactory {

	public ChessCommand create(List<LexicalState> state) {

		if (null == state)
			return null;

		LexicalStateCollection col = new LexicalStateCollection(state);

		return doCreate(col);
	}

	private ChessCommand doCreate(LexicalStateCollection col) {

		if (col.isLongCastleCommand())
			return new CastleLongCommand();

		if (col.isShortCastleCommand())
			return new CastleShortCommand();

		ChessPiece c = col.getChessPiece();
		ChessSquare cs = col.getDestinationSquare();

		MoveCommand move = new MoveCommand(c, cs);

		if (col.isCaptureCommand())
			move.addDecorator(new CaptureDecorator());

		if (col.isPromotionCommand())
			move.addDecorator(new PromotionDecorator());

		return move;
	}

}
