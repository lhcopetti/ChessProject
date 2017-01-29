package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.exception.CantCastleQueenSideException;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CastleLongCommand extends CastleCommand {

	public CastleLongCommand(CheckFlag flag) {
		super(ChessPiece.KING, flag, ChessFile.A);
	}

	@Override
	protected ChessSquare getDestinationKingSquare(ChessSquare kingSquare) {
		return new ChessSquare(kingSquare.getFile().previous().previous(), kingSquare.getRank());
	}

	@Override
	protected ChessSquare getDestinationRookSquare(ChessSquare newKingSquare) {
		return new ChessSquare(newKingSquare.getFile().next(), newKingSquare.getRank());
	}

	@Override
	protected boolean checkCastleInformation(ChessBoard board) throws PGNInterpreterException {

		ChessColor c = board.getNextToPlay();

		if (c == ChessColor.WHITE && !board.getCastleInfo().isWhiteQueenCastle() || //
				c == ChessColor.BLACK && !board.getCastleInfo().isBlackQueenCastle())
			throw new CantCastleQueenSideException(board);
			
		return true;
	}

}
