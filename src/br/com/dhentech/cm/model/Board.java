package br.com.dhentech.cm.model;

import java.util.ArrayList;
import java.util.List;

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
		for(Field c1: fields) {
			for(Field c2: fields) {
				c1.addNeighbor(c2);
			}
		}
	}

	private void drawMinas() {

	}

}
