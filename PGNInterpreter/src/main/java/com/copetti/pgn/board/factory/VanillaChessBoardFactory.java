package com.copetti.pgn.board.factory;

import java.util.Map;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.map.ChessBoardBuilder;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class VanillaChessBoardFactory {

	public Map<ChessSquare, ColoredChessPiece> newBoard() {
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();

		return b //
				.fill(ChessRank.of("2").get()).with(new ColoredChessPiece(ChessPiece.PAWN, ChessColor.WHITE)) //
				.fill(ChessRank.of("7").get()).with(new ColoredChessPiece(ChessPiece.PAWN, ChessColor.BLACK)) //
				.setColor(ChessColor.WHITE) //
				.at("a1", "h1").put(ChessPiece.ROOK) //
				.at("b1", "g1").put(ChessPiece.KNIGHT) //
				.at("c1", "f1").put(ChessPiece.BISHOP) //
				.at("d1").put(ChessPiece.QUEEN) //
				.at("e1").put(ChessPiece.KING) //
				.setColor(ChessColor.BLACK) //
				.at("a8", "h8").put(ChessPiece.ROOK) //
				.at("b8", "g8").put(ChessPiece.KNIGHT) //
				.at("c8", "f8").put(ChessPiece.BISHOP) //
				.at("d8").put(ChessPiece.QUEEN) //
				.at("e8").put(ChessPiece.KING) //
				.build();
	}

}
