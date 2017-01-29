package com.copetti.pgn.analysis;

import java.util.Map;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.logic.ChessMovementResolver;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CheckBoardAnalyser {

	public boolean validateCheckCondition(ChessBoard board, ChessColor inCheckColor) {

		Map<ChessSquare, ColoredChessPiece> all = board.getAllPieces(inCheckColor.opposite());
		ChessMovementResolver cmr = new ChessMovementResolver();

		ChessSquare oppositeKingSquare = board.getKing(inCheckColor);

		return all //
				.entrySet() //
				.stream() //
				.filter(x -> cmr.getMoves(x.getKey(), board).contains(oppositeKingSquare)) //
				.findFirst() //
				.isPresent();
	}

}
