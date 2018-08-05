package com.copetti.expose.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.copetti.service.InterpreterRequest;
import com.copetti.service.InterpreterResult;
import com.copetti.service.PGNChessService;

@RestController
@RequestMapping(path = "/pgnchess")
public class PGNChess {

	@RequestMapping(method = RequestMethod.GET)
	public InterpreterResult runCommand() {

		String fenBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String command = "e4";

		PGNChessService interpreter = new PGNChessService();
		return interpreter.run(fenBoard, command);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/nextBoard")
	public InterpreterResult nextBoard(@RequestBody InterpreterRequest req) {

		PGNChessService interpreter = new PGNChessService();
		return interpreter.run(req.getFenBoard(), req.getPgnCommand());
	}
}
