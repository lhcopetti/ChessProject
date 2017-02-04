package com.copetti.pgn.lichess;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LichessGameTester {

	private static final String rootDirectory = "src/test/resources/PGNGames/";

	@Test
	public void testPGNGames() throws Exception {

		String firstGame = "lichess_pgn_2016.11.27_lhcopetti_vs_cap1.urXzcfwv.pgn";
		String secondGame = "lichess_pgn_2017.02.04_lichess_AI_level_2_vs_lhcopetti.phybhkAw.pgn";

		List<String> pgnGamesToTest = Arrays.asList(firstGame, secondGame);

		for (String s : pgnGamesToTest) {
			String pgnGame = loadPGNGame(s);
			LichessPGNGamePlayer.testGameToConclusion(LichessPGNReader.read(pgnGame));
		}
	}

	public String loadPGNGame(String filePath) throws Exception {

		return new String(Files.readAllBytes(Paths.get(rootDirectory + filePath)));
	}

}
