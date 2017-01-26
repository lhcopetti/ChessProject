package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class MoveCommand extends DisplacementCommand {

	public MoveCommand(ChessPiece chessPiece, ChessSquare destination, CheckFlag flag) {
		super(chessPiece, destination, flag);
	}

	@Override
	protected boolean checkTargetSquare(ChessBoard board) {

		return board.isEmptyAt(getDestinationSquare());
	}

	@Override
	protected void updateEnPassant(ChessBoardContextBuilder builder, ChessBoard cb) {

		if (getPiece() != ChessPiece.PAWN)
			return;

		int diff = getTargetCommand().getY() - getDestinationSquare().getY();

		if (Math.abs(diff) != 2)
			return;

		diff /= 2; /* Only the sign is important */

		ChessFile file = getDestinationSquare().getFile();
		ChessRank rank = ChessRank.fromIndex(getDestinationSquare().getY() + diff);
		/* A pawn moving two ranks */
		builder.setEnpassant(new ChessSquare(file, rank));
	}

}
