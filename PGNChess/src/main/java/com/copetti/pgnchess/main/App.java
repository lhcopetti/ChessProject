package com.copetti.pgnchess.main;

import com.copetti.pgnchess.board.message.RequestPrint;
import com.copetti.pgncommon.chess.board.ChessBoard;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import scala.concurrent.Future;

public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("Run Forest, Run!");
		System.out.println("This is the beginning!");
		ActorSystem system = ActorSystem.create("MyActor");

		ActorRef ref = system.actorOf(Props.create(ChessBoard.class));
		ref.tell(new RequestPrint(), ref);

		Future<Terminated> terminate = system.terminate();

		while (!terminate.isCompleted()) {
			System.out.println("Waiting!");
			Thread.sleep(100);
		}

		System.out.println("This is the END!");
	}
}
