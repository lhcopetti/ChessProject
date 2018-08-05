package com.copetti.expose.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.copetti.service.InterpreterRequest;
import com.copetti.service.InterpreterResult;
import com.copetti.service.PGNChessService;

public class App implements RequestHandler<InterpreterRequest, InterpreterResult> {

	@Override
	public InterpreterResult handleRequest(InterpreterRequest req, Context ctx) {

		PGNChessService service = new PGNChessService();
		return service.run(req.getFenBoard(), req.getPgnCommand());
	}

}
