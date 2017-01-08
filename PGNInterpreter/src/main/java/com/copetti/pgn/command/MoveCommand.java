package com.copetti.pgn.command;

import com.copetti.pgncommon.chess.board.ChessBoard;
import com.copetti.pgncommon.chess.board.ChessSquare;
import com.copetti.pgncommon.chess.token.ChessPiece;

public class MoveCommand extends PGNCommand {

	private ChessSquare destSquare;

	public MoveCommand(ChessPiece chessPiece, ChessSquare destination) {
		super(chessPiece);
		this.destSquare = destination;
	}

	@Override
	public ChessBoard doExecute(ChessBoard input) {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destSquare == null) ? 0 : destSquare.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MoveCommand))
			return false;
		MoveCommand other = (MoveCommand) obj;
		if (destSquare == null) {
			if (other.destSquare != null)
				return false;
		} else if (!destSquare.equals(other.destSquare))
			return false;
		return super.equals(obj);
	}

}
