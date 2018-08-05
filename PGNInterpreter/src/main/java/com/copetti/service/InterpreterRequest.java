package com.copetti.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InterpreterRequest {

	private String fenBoard;
	private String pgnCommand;

}
