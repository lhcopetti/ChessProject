package com.copetti.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.serializer.FEN.FENDeserializer;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.interpreter.PGNInterpreter;

public class App implements RequestHandler<InterpreterRequest, InterpreterResult> {

	@Override
	public InterpreterResult handleRequest(InterpreterRequest req, Context ctx) {

		try {

			ChessBoard board = new FENDeserializer().deserialize(req.getFenBoard());
			ChessCommand command = new PGNInterpreter().parse(req.getPgnCommand());

			if (null == command)
				throw new PGNInterpreterException("The command [" + req.getPgnCommand() + "] is invalid.");

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
