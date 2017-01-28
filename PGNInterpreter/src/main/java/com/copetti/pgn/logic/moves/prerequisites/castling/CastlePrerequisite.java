package com.copetti.pgn.logic.moves.prerequisites.castling;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.logic.moves.prerequisites.EmptySquarePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.MovePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.SquareNotAttackedPrerequisite;
import com.copetti.pgn.tokenizer.tokens.ChessFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class CastlePrerequisite extends MovePrerequisite {

	public enum CastleType {
		QUEEN_SIDE(-1), KING_SIDE(+1);

		private CastleType(int direction) {
			this.direction = direction;
		}

		private final @Getter int direction;
	}

	protected final @Getter CastleType type;
	protected final @Getter int kingSquareMovement = 2;

	@Override
	public boolean apply(ChessBoard board, ChessSquare self, ChessSquare target) {

		ChessColor color = board.at(self).getColor();

		if (!canCastleSide(board, color, type))
			return false;

		if (!squaresEmpty(board, self, type.getDirection()))
			return false;

		if (!castlingSquaresFreeOfAttack(board, self, type.getDirection()))
			return false;

		return true;
	}

	private boolean castlingSquaresFreeOfAttack(ChessBoard board, ChessSquare self, int direction) {

		Set<ChessSquare> kingSquares = getKingCastlingSquares(self, direction);

		return kingSquares //
				.stream() //
				.allMatch(x -> new SquareNotAttackedPrerequisite().apply(board, self, x));
	}

	private Set<ChessSquare> getKingCastlingSquares(ChessSquare self, int direction) {

		int counter = kingSquareMovement;

		Set<ChessSquare> allSquares = new HashSet<>();
		allSquares.add(self);

		ChessSquare current = self;

		while (counter > 0) //
		{
			ChessFile f = ChessFile.fromOrdinal(current.getFile().ordinal() + direction).get();
			current = new ChessSquare(f, current.getRank());

			--counter;
			allSquares.add(current);
		}

		return allSquares;
	}

	private boolean canCastleSide(ChessBoard board, ChessColor color, CastleType castleType) {

		CastleInformation info = board.getCastleInfo();

		if (color == ChessColor.WHITE) //
		{
			if (castleType == CastleType.KING_SIDE)
				return info.isWhiteKingCastle();
			return info.isWhiteQueenCastle();
		} //
		else /* if (color == ChessColor.BLACK) */
		{
			if (castleType == CastleType.KING_SIDE)
				return info.isBlackKingCastle();
			return info.isBlackQueenCastle();
		}
	}

	private boolean squaresEmpty(ChessBoard board, ChessSquare self, int direction) {

		Set<ChessSquare> sqs = new HashSet<>();

		ChessSquare current = self;

		while (true) //
		{
			ChessFile f = ChessFile.fromOrdinal(current.getFile().ordinal() + direction).get();
			current = new ChessSquare(f, current.getRank());

			if (current.getFile() == ChessFile.last() || current.getFile() == ChessFile.first())
				break;

			sqs.add(current);
		}

		return sqs.stream().allMatch(x -> new EmptySquarePrerequisite().apply(board, null, x));
	}

}
