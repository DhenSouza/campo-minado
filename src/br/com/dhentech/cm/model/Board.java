package br.com.dhentech.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.dhentech.cm.exception.ExplosionException;

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
	
	public void open(int line, int column) {
		try {
			fields.parallelStream()
			.filter(f -> f.getLine() == line && f.getColumn() == column)
			.findFirst()
			.ifPresent(f -> f.openField());
			
		} catch (ExplosionException ex) {
			fields.forEach(c -> c.setOpen(true));
			
			throw ex;
		}
	}
	
	public void changeMarking(int line, int column) {
		fields.parallelStream()
		.filter(f -> f.getLine() == line && f.getColumn() == column)
		.findFirst()
		.ifPresent(f -> f.changeMarking());
	}

	private void generateFields() {
		for (int line = 0; line < this.lines; line++) {
			for (int column = 0; column < this.columns; column++) {
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
			int random = (int) (Math.random() * fields.size());
			fields.get(random).mineField();
			armedMines = fields.stream().filter(mined).count();
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
		StringBuilder sb = new StringBuilder();
		
		sb.append("  ");
		for (int c = 0; c < this.columns; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		
		sb.append("\n");
		
		int i = 0;
		for(int l = 0; l < this.lines; l++) {
			sb.append(l);
			sb.append(" ");
			for(int c = 0; c < this.columns; c++) {
				sb.append(" ");
				sb.append(fields.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
