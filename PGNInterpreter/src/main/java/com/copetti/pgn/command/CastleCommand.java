package com.copetti.pgn.command;

import java.util.Map;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

import lombok.Getter;

public abstract class CastleCommand extends ChessCommand {

	private @Getter ChessFile rookFile;

	public CastleCommand(ChessPiece chessPiece, CheckFlag flag, ChessFile rookFile) {
		super(chessPiece, flag);
		this.rookFile = rookFile;
	}

	protected void move(ChessBoardContextBuilder builder, ChessSquare origin, ChessSquare destination) {

		Map<ChessSquare, ColoredChessPiece> pieces = builder.getPieces();

		ColoredChessPiece piece = pieces.remove(origin);
		pieces.put(destination, piece);
	}

	@Override
	protected boolean canExecute(ChessBoard input) throws PGNInterpreterException {

		ChessFile rookFile = getRookFile();
		ChessRank rookRank = input.getKing(input.getNextToPlay()).getRank();

		ColoredChessPiece ccp = input.at(new ChessSquare(rookFile, rookRank));

		if (!checkCastleInformation(input))
			return false;

		if (null == ccp)
			return false;

		if (ccp.getColor() != input.getNextToPlay())
			return false;

		if (ccp.getPiece() != ChessPiece.ROOK)
			return false;

		return true;
	}

	protected abstract boolean checkCastleInformation(ChessBoard board) throws PGNInterpreterException;

	protected abstract ChessSquare getDestinationKingSquare(ChessSquare kingSquare);

	protected abstract ChessSquare getDestinationRookSquare(ChessSquare newKingSquare);

	@Override
	protected boolean doExecute(ChessBoardContextBuilder builder, ChessBoard input) {

		ChessSquare kingSquare = input.getKing(input.getNextToPlay());
		ChessSquare newKingSquare = getDestinationKingSquare(kingSquare);

		ChessSquare rookSquare = new ChessSquare(getRookFile(), kingSquare.getRank());
		ChessSquare newRookSquare = getDestinationRookSquare(newKingSquare);

		move(builder, kingSquare, newKingSquare);
		move(builder, rookSquare, newRookSquare);

		builder.setCastle(builder.getCastle().remove(input.getNextToPlay()));
		return true;
	}

}
