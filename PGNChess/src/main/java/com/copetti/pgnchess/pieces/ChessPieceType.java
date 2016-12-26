package com.copetti.pgnchess.pieces;

import java.util.Set;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.moves.ChessMoveStrategy;
import com.copetti.pgnchess.pieces.moves.PawnMoveStrategy;
import com.copetti.pgnchess.pieces.moves.RookMoveStrategy;

import lombok.Getter;


public enum ChessPieceType
{
	PAWN("p", new PawnMoveStrategy()), //
	ROOK("r", new RookMoveStrategy()), //
	BISHOP("b", null), //
	KNIGHT("n", null), //
	QUEEN("q", null), //
	KING("k", null); //

	private @Getter String pgnFormat;
	private ChessMoveStrategy chessMove;

	private ChessPieceType(String pgnFormat, ChessMoveStrategy chessMove)
	{
		this.pgnFormat = pgnFormat;
		this.chessMove = chessMove;
	}

	public Set<ChessSquare> getMoves(ChessSquare self, ChessBoard board)
	{
		return chessMove.getMoves(self, board);
	}

}
