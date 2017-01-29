package com.copetti.pgn.analysis;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.logic.ChessMovementResolver;

import lombok.Getter;

public class CheckmateBoardAnalyser {

	private @Getter ColoredChessPiece piece;
	private @Getter ChessSquare originSquare;
	private @Getter ChessSquare destinationSquare;

	public boolean validateMateCondition(ChessBoard board, ChessColor matedColor) {

		Map<ChessSquare, ColoredChessPiece> allPieces = board.getAllPieces(matedColor);

		for (Entry<ChessSquare, ColoredChessPiece> e : allPieces.entrySet()) //
		{
			ChessMovementResolver cmr = new ChessMovementResolver();
			Set<ChessSquare> attackingMoves = cmr.getMoves(e.getKey(), board);

			for (ChessSquare cs : attackingMoves) //
			{
				ChessBoard newBoard = getBoardWithMove(board, e.getKey(), cs);
				CheckBoardAnalyser analyser = new CheckBoardAnalyser();
				if (!analyser.validateCheckCondition(newBoard, matedColor)) {
					this.originSquare = e.getKey();
					piece = e.getValue();
					this.destinationSquare = cs;
					return false;
				}
			}
		}

		return true;
	}

	private ChessBoard getBoardWithMove(ChessBoard board, ChessSquare origin, ChessSquare destination) {
		ChessBoardContextBuilder ctx = ChessBoardContextBuilder.newBuilder(board);
		ctx.movePiece(origin, destination);
		return ctx.build();
	}

}
