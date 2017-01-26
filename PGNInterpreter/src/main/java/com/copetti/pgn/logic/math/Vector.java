package com.copetti.pgn.logic.math;

import lombok.Getter;

public final class Vector {

	private static final double DELTA = 0.0001;

	private @Getter double x;
	private @Getter double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector(Vector counter) {
		this.x = counter.getX();
		this.y = counter.getY();
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

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Vector))
			return false;

		Vector v = (Vector) obj;
		return DoubleMath.equals(x, v.getX(), DELTA) && DoubleMath.equals(y, v.getY(), DELTA);
	}

	@Override
	public int hashCode() {
		return new Double((x + 5) * 17 + (y + 7) * 13).intValue();
	}

	public double getAngle() {

		double angle = Math.toDegrees(Math.atan2(this.y, this.x));

		if (DoubleMath.equals(angle, 360.d, 0.01d))
			return 0.d;

		if (angle < 0.d)
			return angle + 360.d;

		return angle;
	}

	public Vector add(Vector vector) {
		x += vector.getX();
		y += vector.getY();
		return this;
	}

	public Vector unitify() {

		double factor = Math.max(Math.abs(this.x), Math.abs(this.y));
		this.x /= factor;
		this.y /= factor;
		return this;
	}

	@Override
	public String toString() {
		return "[" + this.x + "," + this.y + "]";
	}
}
