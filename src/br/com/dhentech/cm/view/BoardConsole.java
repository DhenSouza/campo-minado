package br.com.dhentech.cm.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.dhentech.cm.exception.ExitException;
import br.com.dhentech.cm.exception.ExplosionException;
import br.com.dhentech.cm.model.Board;

public class BoardConsole {

	private Board board;
	private Scanner scan = new Scanner(System.in);

	public BoardConsole(Board board) {
		this.board = board;

		runGame();
	}

	private void runGame() {
		try {

			boolean doContinue = true;
			while (doContinue) {
				loopGame();

				System.out.println("Deseja outra partida? (S/n)");
				String answer = scan.nextLine();

				if ("n".equalsIgnoreCase(answer)) {
					doContinue = false;
				} else {
					board.resetGame();
				}
			}

		} catch (ExitException exit) {
			System.out.println("Adeus, otario(a)");
		} finally {
			scan.close();
		}
	}

	private void loopGame() {
		try {

			while (!board.objectiveAchieved()) {
				System.out.println(board);

				String digitado = captureTypedValue("Digite (X,Y): ");

				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();

				digitado = captureTypedValue("1 - para abrir ou 2 - (Des)Marcar: \": ");

				if ("1".equals(digitado)) {
					board.open(xy.next(), xy.next());
				} else if ("2".equals(digitado)) {
					board.changeMarking(xy.next(), xy.next());
				}

			}

			System.out.println("You win, fracassado!");
		} catch (ExplosionException ex) {
			System.out.println(board);
			// throw new ExplosionException("Fracassado");
			System.out.println("BOOOM ! Voce perdeu, Fracassado");
		}

	}

	/* Capturar o valor digitado */
	public String captureTypedValue(String text) {
		System.out.print(text);
		String digitado = scan.nextLine();

		if ("sair".equalsIgnoreCase(digitado)) {
			throw new ExitException("Valor não capturado");
		}

		return digitado;
	}

}
