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

		assertEquals(new MoveVector(x, true, DisplacementType.BOTH), new MoveVector(y, true, DisplacementType.BOTH));

	}

	@Test
	public void testMoveVectorFlip() {

		MoveVector first = new MoveVector(5, 5);
		MoveVector second = new MoveVector(5, 5);

		first = first.flip();
		second = second.flip();
		assertEquals(first, second);

		MoveVector x = new MoveVector(new Point(22, 33), false, DisplacementType.BOTH);
		x.addPrerequisite(new CapturePrerequisite());
		MoveVector y = new MoveVector(new Point(-22, -33), false, DisplacementType.BOTH);
		y.addPrerequisite(new CapturePrerequisite());
		x = x.flip();

		assertEquals(x, y);
	}

}
