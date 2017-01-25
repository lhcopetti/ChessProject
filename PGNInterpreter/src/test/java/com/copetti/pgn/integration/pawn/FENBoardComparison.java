package com.copetti.pgn.integration.pawn;

import static org.junit.Assert.assertEquals;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.serializer.FEN.FENDeserializer;
import com.copetti.pgn.board.serializer.FEN.FENSerializer;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.interpreter.PGNInterpreter;

public class FENBoardComparison {

	public static void validate(String fenBoardInput, String fenBoardOutput, String command) {

		ChessBoard board = new FENDeserializer().deserialize(fenBoardInput);

		ChessCommand chessCommand = new PGNInterpreter().parse(command);
		ChessBoard outputBoard = chessCommand.execute(board);

		String outputBoardFEN = new FENSerializer().serialize(outputBoard);
		assertEquals(fenBoardOutput, outputBoardFEN);
	}

}
