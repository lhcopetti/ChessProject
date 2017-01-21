package com.copetti.pgn.command.decorator;

import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class PromotionDecorator {

	private @Getter ChessPiece piece;

	boolean validate() {
		return false;
	}
}
