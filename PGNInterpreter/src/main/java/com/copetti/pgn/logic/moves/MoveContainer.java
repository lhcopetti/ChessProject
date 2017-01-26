package com.copetti.pgn.logic.moves;

import java.util.Set;

@FunctionalInterface
public interface MoveContainer {

	public Set<MoveVector> getMoveCollection();
}
