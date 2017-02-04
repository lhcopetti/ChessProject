package com.copetti.pgn.lichess;

import static org.junit.Assert.*;

import org.junit.Test;

public class LichessPGNReaderTest {

	/* Game can be found at: https://en.lichess.org/urXzcfwv/white */
	private final String pgnAnnotatedGame = "[Event \"Rated game\"]\r\n" + "[Site \"https://lichess.org/urXzcfwv\"]\r\n"
			+ "[Date \"2016.11.27\"]\r\n" + "[White \"lhcopetti\"]\r\n" + "[Black \"cap1\"]\r\n"
			+ "[Result \"1-0\"]\r\n" + "[WhiteElo \"1217\"]\r\n" + "[BlackElo \"1217\"]\r\n" + "[PlyCount \"125\"]\r\n"
			+ "[Variant \"Standard\"]\r\n" + "[TimeControl \"600+0\"]\r\n" + "[ECO \"B07\"]\r\n"
			+ "[Opening \"Pirc Defense #3\"]\r\n" + "[Termination \"Normal\"]\r\n" + "[Annotator \"lichess.org\"]\r\n"
			+ "\r\n"
			+ "1. e4 Nf6 2. Nc3 d6 3. d4 g6 { B07 Pirc Defense #3 } 4. d5 Bg7 5. Bd3 O-O 6. Nf3 c6 7. O-O cxd5 8. exd5 Nbd7 9. Ng5 Nb6 10. Bb5 Nbxd5 11. Nxd5 Nxd5 12. Qxd5 Qb6 13. Be3 Qa5 14. Rfe1 Bxb2 15. Rab1 Bf6 16. Bd2 Qc7 17. c3 Be6 18. Nxe6 fxe6 19. Qxe6+ Kg7 20. h4 Bxh4 21. g3 Bf6 22. Bd7 Rad8 23. Bb5 a6 24. Ba4 Bxc3 25. Bxc3+ Qxc3 26. Qxe7+ Rf7 27. Qxd8 Qa3 28. Re7 Qxa4 29. Rxf7+ Kxf7 30. Rxb7+ Ke6 31. Qe7+ Kd5 32. Rd7 Qxa2 33. Qxd6+ Kc4 34. Qd4+ Kb5 35. Rc7 Qa5 36. Qc5+ Ka4 37. Qc4+ Qb4 38. Qxb4+ Kxb4 39. Rxh7 a5 40. f4 a4 41. g4 a3 42. Rh2 Kb3 43. Kf2 a2 44. Rh1 Kb2 45. f5 a1=Q 46. Rxa1 Kxa1 47. fxg6 Ka2 48. g7 Ka3 49. g8=Q Kb2 50. Qa8 Kc3 51. Qc8+ Kd4 52. Kf3 Ke5 53. Qe8+ Kd4 54. Kf4 Kc3 55. Qd8 Kb4 56. Qc8 Ka3 57. Ke4 Kb3 58. Kd4 Ka4 59. Kc5 Kb3 60. Qb8+ Ka2 61. Kc4 Ka3 62. Kc3 Ka4 63. Qb4# { White wins by checkmate. } 1-0";

	@Test
	public void testReading() {

		LichessPGNMatch match = LichessPGNReader.read(pgnAnnotatedGame);

		assertEquals("lhcopetti", match.getWhiteName());
		assertEquals("cap1", match.getBlackName());
		assertEquals("1-0", match.getResult());
		assertEquals(
				"e4 Nf6 Nc3 d6 d4 g6 d5 Bg7 Bd3 O-O Nf3 c6 O-O cxd5 exd5 Nbd7 Ng5 Nb6 Bb5 Nbxd5 Nxd5 Nxd5 Qxd5 Qb6 Be3 Qa5 Rfe1 Bxb2 Rab1 Bf6 Bd2 Qc7 c3 Be6 Nxe6 fxe6 Qxe6+ Kg7 h4 Bxh4 g3 Bf6 Bd7 Rad8 Bb5 a6 Ba4 Bxc3 Bxc3+ Qxc3 Qxe7+ Rf7 Qxd8 Qa3 Re7 Qxa4 Rxf7+ Kxf7 Rxb7+ Ke6 Qe7+ Kd5 Rd7 Qxa2 Qxd6+ Kc4 Qd4+ Kb5 Rc7 Qa5 Qc5+ Ka4 Qc4+ Qb4 Qxb4+ Kxb4 Rxh7 a5 f4 a4 g4 a3 Rh2 Kb3 Kf2 a2 Rh1 Kb2 f5 a1=Q Rxa1 Kxa1 fxg6 Ka2 g7 Ka3 g8=Q Kb2 Qa8 Kc3 Qc8+ Kd4 Kf3 Ke5 Qe8+ Kd4 Kf4 Kc3 Qd8 Kb4 Qc8 Ka3 Ke4 Kb3 Kd4 Ka4 Kc5 Kb3 Qb8+ Ka2 Kc4 Ka3 Kc3 Ka4 Qb4#",
				String.join(" ", match.getPgnCommands()));
	}

	@Test
	public void testExtractFromBracketsWhiteName() {
		assertEquals("lhcopetti", LichessPGNReader.extractFromBrackets(pgnAnnotatedGame, "White"));
	}

	@Test
	public void testExtractFromBracketsPlyCount() {
		assertEquals("125", LichessPGNReader.extractFromBrackets(pgnAnnotatedGame, "PlyCount"));
	}

	@Test
	public void testExtractFromBracketsResult() {
		assertEquals("1-0", LichessPGNReader.extractFromBrackets(pgnAnnotatedGame, "Result"));
	}

}
