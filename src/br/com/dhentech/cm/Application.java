package br.com.dhentech.cm;

import br.com.dhentech.cm.model.Board;
import br.com.dhentech.cm.view.BoardConsole;

public class Application {

	public static void main(String[] args) {

		Board board = new Board(6, 6, 3);
		new BoardConsole(board);
		
	
	}

}
