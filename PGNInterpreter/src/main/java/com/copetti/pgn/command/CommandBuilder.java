package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public class CommandBuilder {

	private @Getter ChessPiece chessPiece;
	private @Getter ChessSquare targetSquare;
	private @Getter boolean capture;

	public void setPieceType(ChessPiece chessPiece) {
		this.chessPiece = chessPiece;
	}

	public void setDestinationSquare(ChessSquare chessSquare) {
		this.targetSquare = chessSquare;
	}

	public PGNCommand build() {

		if (chessPiece == null)
			chessPiece = ChessPiece.PAWN;
		// return new MoveCommand(chessPiece, targetSquare);
		return null;

	}

	public void setCapture() {
		capture = true;
	}

}