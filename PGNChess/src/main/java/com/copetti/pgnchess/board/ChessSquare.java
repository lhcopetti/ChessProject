package com.copetti.pgnchess.board;

import lombok.Getter;


public class ChessSquare
{
	@Getter
	private int x;
	@Getter
	private int y;
	
	public ChessSquare(int x, int y)
	{
		if (x < 0 || x > 7)
			throw new IllegalArgumentException("Valor inválido para X " + x);
		
		if (y < 0 || y > 7)
			throw new IllegalArgumentException("Valor inválido para Y " + y);
		
		this.x = x;
		this.y = y;
	}
	
	public ChessSquare(ChessCoordinate coordinate)
	{
		this.x = coordinate.getFile().value();
		this.y = coordinate.getRank().getValue();
	}
	
	public ChessCoordinate toChessCoordinate() {
		return new ChessCoordinate(this);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof ChessSquare))
			return false;
		
		ChessSquare other = (ChessSquare) obj;
		
		return other.getX() == x && other.getY() == y;
	}
	
	@Override
	public int hashCode()
	{
		return (x + 37) * (y + 3);
	}
}
