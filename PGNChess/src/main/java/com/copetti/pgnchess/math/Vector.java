package com.copetti.pgnchess.math;

import lombok.Getter;

public class Vector {

	private @Getter double x;
	private @Getter double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector subtract(Vector vector) {
		x -= vector.getX();
		y -= vector.getY();
		return this;
	}

	public Vector normalize() {

		double vectorSize = size();

		this.x /= vectorSize;
		this.y /= vectorSize;
		return this;
	}

	public double size() {

		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

	}

	public double getAngle() {
		return Math.atan2(this.y, this.x);
	}

}
