package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CaptureCommand extends DisplacementCommand {

	public CaptureCommand(ChessPiece chessPiece, ChessSquare destSquare, CheckFlag flag) {
		super(chessPiece, destSquare, flag);
	}

	@Override
	protected boolean checkTargetSquare(ChessBoard board) {

		/*
		 * A captura En-Passant não segue a premissa de que a casa de destino
		 * esteja habitada por uma peça da cor oposta
		 */
		if (isEnpassantCapture(board))
			return true;

		ColoredChessPiece cp = board.at(getDestinationSquare());

		if (null == cp)
			return false;

		return board.getNextToPlay().opposite() == cp.getColor();
	}

	@Override
	protected ChessCommandType getType() {

		return ChessCommandType.CAPTURE_COMMAND;

	}

}
