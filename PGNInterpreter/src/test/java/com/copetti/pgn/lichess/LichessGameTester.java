package com.copetti.pgn.lichess;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class LichessGameTester {

	private static final String rootDirectory = "src/test/resources/PGNGames/";

	@Test
	public void testPGNGames() throws Exception {

		File[] pgnFiles = new File("./" + rootDirectory).listFiles();

		System.out.println("Testing " + pgnFiles.length + " PGN matches");

		for (File f : pgnFiles) {
			if (f.getName().endsWith(".pgn"))
				testLichessPGNGame(f);
			else
				System.out.println("Skipping file because it is not in PGN format. " + f.getName());
		}
	}

	private void testLichessPGNGame(File pgnFile) throws Exception {

		String pgnGame = loadPGNGame(pgnFile.getAbsolutePath());
		LichessPGNGamePlayer.testGameToConclusion(LichessPGNReader.read(pgnGame));
	}

	public String loadPGNGame(String filePath) throws Exception {

		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

}
