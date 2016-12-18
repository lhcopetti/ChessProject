package com.copetti.pgnchess.pieces;

public enum ChessColor {
	WHITE {
		@Override
		public String toString() {
			return "White";
		}

		@Override
		public ChessColor opposite() {
			return BLACK;
		}

		@Override
		public String shortString() {
			return "w";
		}
	},
	BLACK {
		@Override
		public String toString() {
			return "Black";
		}

		@Override
		public ChessColor opposite() {
			return WHITE;
		}

		@Override
		public String shortString() {
			return "b";
		}
	};

	public abstract ChessColor opposite();

	public abstract String shortString();
}
