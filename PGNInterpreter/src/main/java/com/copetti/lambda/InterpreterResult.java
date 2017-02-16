package com.copetti.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InterpreterResult {

	private @Getter int errorCode;

	private @Getter String fenBoardInput;
	private @Getter String pgnCommand;
	private @Getter String fenBoardOutput;
	private @Getter String errorMessage;

	public static InterpreterResult success(InterpreterRequest request, String fenBoardResult) {
		return new InterpreterResult(0, request.getFenBoard(), request.getPgnCommand(), fenBoardResult, null);
	}

	public static InterpreterResult fail(InterpreterRequest request, String errorMessage) {
		return new InterpreterResult(255, request.getFenBoard(), request.getPgnCommand(), null, errorMessage);
	}

}
