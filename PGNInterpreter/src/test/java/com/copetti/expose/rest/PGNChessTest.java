package com.copetti.expose.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.copetti.service.InterpreterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PGNChessTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testRequestNextBoard() throws Exception {
		String inputBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String pgnCommand = "e4";
		InterpreterRequest request = new InterpreterRequest(inputBoard, pgnCommand);
		String json = mapper.writeValueAsString(request);
		mvc.perform(MockMvcRequestBuilders //
				.post("/pgnchess/nextBoard") //
				.content(json) //
				.contentType(MediaType.APPLICATION_JSON) //
				.accept(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$.errorCode", equalTo(0))) //
				.andExpect(jsonPath("$.fenBoardInput", equalTo(inputBoard))) //
				.andExpect(jsonPath("$.pgnCommand", equalTo(pgnCommand))) //
				.andExpect(jsonPath("fenBoardOutput",
						equalTo("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"))) //
				.andExpect(jsonPath("$.errorMessage", equalTo(null)));
	}
}
