package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.exception.CantCastleKingSideException;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CastleShortCommand extends CastleCommand {

	public CastleShortCommand(CheckFlag flag) {
		super(ChessPiece.KING, flag, ChessFile.H);
	}

	@Override
	protected ChessSquare getDestinationKingSquare(ChessSquare kingSquare) {

		return new ChessSquare(kingSquare.getFile().next().next(), kingSquare.getRank());
	}

	@Override
	protected ChessSquare getDestinationRookSquare(ChessSquare newKingSquare) {
		return new ChessSquare(newKingSquare.getFile().previous(), newKingSquare.getRank());
	}

	@Override
	protected boolean checkCastleInformation(ChessBoard board) throws PGNInterpreterException {
		ChessColor c = board.getNextToPlay();

		if (c == ChessColor.WHITE && !board.getCastleInfo().isWhiteKingCastle() || //
				c == ChessColor.BLACK && !board.getCastleInfo().isBlackKingCastle())
			throw new CantCastleKingSideException(board);

		return true;
	}

}
