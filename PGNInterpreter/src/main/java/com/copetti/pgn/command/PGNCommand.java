package com.copetti.pgn.command;

public abstract class PGNCommand {

	// private @Getter ChessPiece piece;
	//
	// public PGNCommand(ChessPiece chessPiece) {
	// this.piece = chessPiece;
	// }
	//
	// public ChessBoard execute(ChessBoard input, ChessColor color) {
	// if (!checkColor()) {
	// System.out.println("A pe�a n�o possui a mesma cor que foi
	// especificada.");
	// return null;
	// }
	//
	// if (!checkDepartureSquare()) {
	// System.out.println("N�o h� nenhuma pe�a a ser movida no square
	// especificado.");
	// return null;
	// }
	//
	// if (!checkTargetSquare()) {
	// System.out.println("Square de destino n�o atende aos pr�-requisitos");
	// return null;
	// }
	// return null;
	// }
	//
	// private boolean checkTargetSquare() {
	// return false;
	// }
	//
	// private boolean checkColor() {
	// return false;
	// }
	//
	// private boolean checkDepartureSquare() {
	// // TODO Auto-generated method stub
	// return false;
	// }
	//
	// public abstract ChessBoard doExecute(ChessBoard input);
	//
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((piece == null) ? 0 : piece.hashCode());
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (!(obj instanceof PGNCommand))
	// return false;
	// PGNCommand other = (PGNCommand) obj;
	// if (piece != other.piece)
	// return false;
	// return true;
	// }
}
