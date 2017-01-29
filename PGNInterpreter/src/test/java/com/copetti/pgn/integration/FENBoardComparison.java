package com.copetti.pgn.integration;

import static org.junit.Assert.assertEquals;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.serializer.FEN.FENDeserializer;
import com.copetti.pgn.board.serializer.FEN.FENSerializer;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.interpreter.PGNInterpreter;

public class FENBoardComparison {

	public static void validate(String fenBoardInput, String fenBoardOutput, String command)
			throws PGNInterpreterException {

		ChessBoard board = new FENDeserializer().deserialize(fenBoardInput);

		ChessCommand chessCommand = new PGNInterpreter().parse(command);
		ChessBoard outputBoard = chessCommand.execute(board);

		String outputBoardFEN = new FENSerializer().serialize(outputBoard);
		assertEquals(fenBoardOutput, outputBoardFEN);
	}

	public static void tryCommand(String initialBoard, String pgnCommand) throws PGNInterpreterException {

		ChessBoard board = new FENDeserializer().deserialize(initialBoard);
		ChessCommand chessCommand = new PGNInterpreter().parse(pgnCommand);
		chessCommand.execute(board);
	}

}
