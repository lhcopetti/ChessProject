package com.copetti.pgnchess.main;

import akka.actor.UntypedActor;


public class SimpleActor extends UntypedActor
{

	@Override
	public void onReceive(Object arg0) throws Exception
	{
		if (!arg0.equals("Run"))
		{
			System.out.println("Didnt get that!");
			return;
		}

		System.out.println("Hi, Forest: " + arg0);
	}

}
