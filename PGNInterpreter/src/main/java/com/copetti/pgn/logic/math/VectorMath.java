package com.copetti.pgn.logic.math;

public class VectorMath {

	public static Vector subtract(Vector arg1, Vector arg2) {
		return new Vector(arg1.getX() - arg2.getX(), arg1.getY() - arg2.getY());
	}

	public static Vector add(Vector arg1, Vector arg2) {
		return new Vector(arg1.getX() + arg2.getX(), arg1.getY() + arg2.getY());
	}

}
