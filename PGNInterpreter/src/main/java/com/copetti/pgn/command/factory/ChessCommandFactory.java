package com.copetti.pgn.command.factory;

import java.util.List;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.command.CaptureCommand;
import com.copetti.pgn.command.CastleLongCommand;
import com.copetti.pgn.command.CastleShortCommand;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.command.ChessCommand.CheckFlag;
import com.copetti.pgn.command.DisplacementCommand;
import com.copetti.pgn.command.MoveCommand;
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

		CheckFlag flag = col.getFlag();

		if (col.isLongCastleCommand())
			return new CastleLongCommand(flag);

		if (col.isShortCastleCommand())
			return new CastleShortCommand(flag);

		ChessPiece c = col.getChessPiece();
		ChessSquare cs = col.getDestinationSquare();
		DisplacementCommand comm;

		if (col.isCaptureCommand())
			comm = new CaptureCommand(c, cs, flag);
		else
			comm = new MoveCommand(c, cs, flag);

		if (col.isPromotion())
			comm.setPromotion(new PromotionDecorator(col.getTargetPromotionPiece()));

		return comm;
	}

}
