package br.com.dhentech.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {

	private int lines;
	private int columns;
	private int mines;

	private final List<Field> fields = new ArrayList<>();

	public Board(int lines, int columns, int mines) {
		this.lines = lines;
		this.columns = columns;
		this.mines = mines;

		generateFields();
		AssociateNeighbors();
		drawMinas();

	}

	private void generateFields() {
		for (int line = 0; line < lines; line++) {
			for (int column = 0; column < columns; column++) {
				fields.add(new Field(line, column));
			}
		}
	}

	private void AssociateNeighbors() {
		for (Field c1 : fields) {
			for (Field c2 : fields) {
				c1.addNeighbor(c2);
			}
		}
	}

	/* Metodo que sorteia as minas no tabuleiro */
	private void drawMinas() {
		long armedMines = 0;

		Predicate<Field> mined = m -> m.isMined();

		do {
			armedMines = fields.stream().filter(mined).count();

			int random = (int) (Math.random() * fields.size());

			fields.get(random).mineField();
		} while (armedMines < mines);
	}

	/*
	 * Garantir que o objetivo seja alcançado quando todos os campos forem acionados
	 */
	public boolean objectiveAchieved() {
		return fields.stream().allMatch(f -> f.objectiveAchieved());
	}

	public void resetGame() {
		fields.stream().forEach(f -> f.resetGame());
		drawMinas();
	}
	
	public String toString() {
		return "";
	}

}
