package br.com.dhentech.cm;

import br.com.dhentech.cm.model.Board;

public class Application {

	public static void main(String[] args) {

		Board board = new Board(6, 6, 6);
		
		
		board.selected(4, 4); 
		board.selected(4, 5); 
		board.open(3, 3);
		
		System.out.println(board);
	}

}
