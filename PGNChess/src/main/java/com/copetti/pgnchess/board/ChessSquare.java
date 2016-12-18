package com.copetti.pgnchess.board;

import lombok.Getter;

public class ChessSquare {
	@Getter
	private int x;
	@Getter
	private int y;

	public ChessSquare(int x, int y) {
		if (x < 0 || x > 7)
			throw new IllegalArgumentException("Valor inválido para X " + x);

		if (y < 0 || y > 7)
			throw new IllegalArgumentException("Valor inválido para Y " + y);

		this.x = x;
		this.y = y;
	}

	public ChessSquare(ChessFile file, ChessRank rank) {
		init(file, rank);
	}

	public ChessSquare(String sq) {

		if (sq.length() > 2)
			throw new IllegalArgumentException(
					"ChessSquaren não pode ser inicializado com: " + sq + ". Tamanho: " + sq.length());

		ChessFile file = ChessFile.valueOf(("" + sq.charAt(0)).toUpperCase());
		ChessRank rank = new ChessRank(Integer.valueOf("" + sq.charAt(1)));
		init(file, rank);
	}

	private void init(ChessFile file, ChessRank rank) {
		this.x = file.ordinal();
		this.y = rank.getValue() - 1;
	}

	public ChessFile getFile() {
		return ChessFile.fromOrdinal(x);
	}

	public ChessRank getRank() {
		return new ChessRank(y + 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ChessSquare))
			return false;

		ChessSquare other = (ChessSquare) obj;

		return other.getX() == x && other.getY() == y;
	}

	@Override
	public int hashCode() {
		return (x + 37) * (y + 11);
	}

	@Override
	public String toString() {
		return getFile().toString().toLowerCase() + getRank().getValue();
	}
}
