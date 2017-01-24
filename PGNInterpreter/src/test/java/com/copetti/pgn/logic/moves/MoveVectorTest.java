package com.copetti.pgn.logic.moves;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;

public class MoveVectorTest {

	@Test
	public void testMoveVectorEquals() {

		assertEquals(new MoveVector(5, 5), new MoveVector(5, 5));

		Point x = new Point(22, 33);
		Point y = new Point(22, 33);

		assertEquals(new MoveVector(x, true), new MoveVector(y, true));

	}

	@Test
	public void testMoveVectorFlip() {

		MoveVector first = new MoveVector(5, 5);
		MoveVector second = new MoveVector(5, 5);

		first.flip();
		second.flip();
		assertEquals(first, second);

		MoveVector x = new MoveVector(new Point(22, 33), false);
		x.addPrerequisite(new CapturePrerequisite());
		MoveVector y = new MoveVector(new Point(-22, -33), false);
		y.addPrerequisite(new CapturePrerequisite());
		x.flip();

		assertEquals(x, y);
	}

}
