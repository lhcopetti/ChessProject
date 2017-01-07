package com.copetti.pgnchess.command;


public abstract class ChessCommand
{

	public boolean execute()
	{
		if (!canExecute())
			return false;
		
		return doExecute();
	}
	
	public abstract boolean canExecute();
	public abstract boolean doExecute();
	
	public abstract boolean undo();
}
