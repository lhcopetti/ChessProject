package com.copetti.pgn.command;

import com.copetti.pgncommon.chess.board.ChessSquare;
import com.copetti.pgncommon.chess.token.ChessPiece;

import lombok.Getter;

public class CommandBuilder {

	private @Getter ChessPiece chessPiece;
	private @Getter ChessSquare targetSquare;

	public void setPieceType(ChessPiece chessPiece) {
		this.chessPiece = chessPiece;
	}

	public void setDestinationSquare(ChessSquare chessSquare) {
		this.targetSquare = chessSquare;
	}

	public PGNCommand build() {

		if (chessPiece == null)
			chessPiece = ChessPiece.PAWN;
		return new MoveCommand(chessPiece, targetSquare);

	}

}
