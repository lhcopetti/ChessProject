package com.copetti.pgn.integration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.copetti.pgn.exception.PGNInterpreterException;

public abstract class TestChessCommandCheckFlag {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public abstract void testWithoutFlag() throws PGNInterpreterException;

	@Test
	public abstract void testWithCheckFlag() throws PGNInterpreterException;

	@Test
	public abstract void testWithMateFlag() throws PGNInterpreterException;

}
