package com.copetti.service;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.serializer.FEN.FENDeserializer;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.interpreter.PGNInterpreter;

public class PGNChessService {

	public InterpreterResult run(String fenBoard, String pgnCommand) {

		InterpreterRequest req = new InterpreterRequest(fenBoard, pgnCommand);
		try {

			ChessBoard board = new FENDeserializer().deserialize(fenBoard);
			ChessCommand command = new PGNInterpreter().parse(pgnCommand);

			if (null == command)
				throw new PGNInterpreterException("The command [" + pgnCommand + "] is invalid.");

			ChessBoard result = command.execute(board);

			if (null == result)
				throw new PGNInterpreterException(
						"Invalid board result for the command [" + req.getPgnCommand() + "].");

			return InterpreterResult.success(req, result.toString());

		} catch (PGNInterpreterException e) {
			return InterpreterResult.fail(req, e.getMessage());
		}

	}

}
