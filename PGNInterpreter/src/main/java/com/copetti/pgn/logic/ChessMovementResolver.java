package com.copetti.pgn.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.strategies.BishopMoveStrategy;
import com.copetti.pgn.logic.moves.strategies.ChessMoveExecutor;
import com.copetti.pgn.logic.moves.strategies.KingMoveStrategy;
import com.copetti.pgn.logic.moves.strategies.KnightMoveStrategy;
import com.copetti.pgn.logic.moves.strategies.PawnMoveStrategy;
import com.copetti.pgn.logic.moves.strategies.QueenMoveStrategy;
import com.copetti.pgn.logic.moves.strategies.RookMoveStrategy;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public class ChessMovementResolver {

	private @Getter ChessPiece piece;

	private static Map<ChessPiece, MoveContainer> moveStrategies;

	public ChessMovementResolver() {

		if (null == moveStrategies)
			initMoveStrategies();
	}

	private void initMoveStrategies() {
		moveStrategies = new HashMap<>();
		moveStrategies.put(ChessPiece.PAWN, new PawnMoveStrategy());
		moveStrategies.put(ChessPiece.BISHOP, new BishopMoveStrategy());
		moveStrategies.put(ChessPiece.ROOK, new RookMoveStrategy());
		moveStrategies.put(ChessPiece.KNIGHT, new KnightMoveStrategy());
		moveStrategies.put(ChessPiece.QUEEN, new QueenMoveStrategy());
		moveStrategies.put(ChessPiece.KING, new KingMoveStrategy());
	}

	public boolean isValidMovement(ChessBoard input, ChessSquare origin, ChessSquare destination) {

		return getMoves(origin, input).contains(destination);
	}

	public Set<ChessSquare> getMoves(ChessSquare cs, ChessBoard board) {

		ChessPiece piece = board.at(cs).getPiece();
		ChessMoveExecutor cme = new ChessMoveExecutor(moveStrategies.get(piece));

		try {
			return cme.getMoves(cs, board);
		} catch (PGNInterpreterException e) {
			e.printStackTrace();
			return null;
		}
	}

}
