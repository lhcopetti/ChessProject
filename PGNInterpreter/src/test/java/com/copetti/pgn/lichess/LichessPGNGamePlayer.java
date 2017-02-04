package com.copetti.pgn.lichess;

import static org.junit.Assert.fail;

import java.util.List;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.serializer.FEN.FENDeserializer;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.interpreter.PGNInterpreter;

public class LichessPGNGamePlayer {

	public static void testGameToConclusion(LichessPGNMatch match) {

		System.out.println("Testing PGN Game with " + match.getPgnCommands().size() + " commands");
		List<String> commands = match.getPgnCommands();

		ChessBoard board = new FENDeserializer()
				.deserialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

		int i = 1;
		for (String s : commands) {
			try {
				ChessCommand comm = new PGNInterpreter().parse(s);
				ChessBoard newBoard = comm.execute(board);

				System.out.println("FEN #" + i++ + ": " + board.toString() + "  Comm: " + s);

				if (null == newBoard)
					throw new Exception("Command returned null!");

				board = newBoard;
			} catch (Exception e) {
				fail("Command " + s + " failed for the board: " + board.toString());
			}
		}

	}

}
