package com.copetti.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InterpreterRequest {

	private String fenBoard;
	private String pgnCommand;

}
