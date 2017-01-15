package com.copetti.pgnchess.pieces;

import java.util.Set;

import com.copetti.pgnchess.pieces.moves.BishopMoveStrategy;
import com.copetti.pgnchess.pieces.moves.ChessMoveStrategy;
import com.copetti.pgnchess.pieces.moves.PawnMoveStrategy;
import com.copetti.pgnchess.pieces.moves.RookMoveStrategy;
import com.copetti.pgncommon.chess.board.ChessBoard;
import com.copetti.pgncommon.chess.board.ChessSquare;

import lombok.Getter;


public enum ChessPieceType
{
	PAWN("p", new PawnMoveStrategy(), 1), //
	ROOK("r", new RookMoveStrategy(), 5), //
	BISHOP("b", new BishopMoveStrategy(), 3), //
	KNIGHT("n", null, 3), //
	QUEEN("q", null, 9), //
	KING("k", null, -1); //

	private @Getter String pgnFormat;
	private ChessMoveStrategy chessMove;
	private @Getter int score;

	private ChessPieceType(String pgnFormat, ChessMoveStrategy chessMove, int score)
	{
		this.pgnFormat = pgnFormat;
		this.chessMove = chessMove;
		this.score = score;
	}

	public Set<ChessSquare> getMoves(ChessSquare self, ChessBoard board)
	{
		return chessMove.getMoves(self, board);
	}

}
