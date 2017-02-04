package com.copetti.pgn.lichess;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class LichessGameTester {

	@Test
	public void testPGNGames() throws Exception {

		String fileName = "src/test/resources/PGNGames/lichess_pgn_2016.11.27_lhcopetti_vs_cap1.urXzcfwv.pgn";
		String pgnGame = loadPGNGame(fileName);

		LichessPGNGamePlayer.testGameToConclusion(LichessPGNReader.read(pgnGame));
	}

	public String loadPGNGame(String filePath) throws Exception {

		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

}
