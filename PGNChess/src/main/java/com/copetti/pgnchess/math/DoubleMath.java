package com.copetti.pgnchess.math;

public class DoubleMath {

	public static final boolean equals(double arg1, double arg2, double delta) {
		return Math.abs(arg1 - arg2) < Math.abs(delta);
	}

}
