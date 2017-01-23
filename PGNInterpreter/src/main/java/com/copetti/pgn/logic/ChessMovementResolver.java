package com.copetti.pgn.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.logic.moves.BishopMoveStrategy;
import com.copetti.pgn.logic.moves.ChessMoveStrategy;
import com.copetti.pgn.logic.moves.KnightMoveStrategy;
import com.copetti.pgn.logic.moves.PawnMoveStrategy;
import com.copetti.pgn.logic.moves.RookMoveStrategy;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public class ChessMovementResolver {

	private @Getter ChessPiece piece;

	private static Map<ChessPiece, ChessMoveStrategy> moveStrategies;

	public ChessMovementResolver(ChessPiece piece) {

		this.piece = piece;

		if (null == moveStrategies)
			initMoveStrategies();
	}

	private void initMoveStrategies() {
		moveStrategies = new HashMap<>();
		moveStrategies.put(ChessPiece.PAWN, new PawnMoveStrategy());
		moveStrategies.put(ChessPiece.BISHOP, new BishopMoveStrategy());
		moveStrategies.put(ChessPiece.ROOK, new RookMoveStrategy());
		moveStrategies.put(ChessPiece.KNIGHT, new KnightMoveStrategy());
	}

	public boolean isValidMovement(ChessBoard input, ChessSquare origin, ChessSquare destination) {

		return getMoves(origin, input).contains(destination);
	}

	public Set<ChessSquare> getMoves(ChessSquare cs, ChessBoard board) {
		return moveStrategies.get(piece).getMoves(cs, board);
	}

}
