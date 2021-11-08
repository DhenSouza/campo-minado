package br.com.dhentech.cm.model;

import java.util.ArrayList;
import java.util.List;

import br.com.dhentech.cm.exception.ExplosionException;

public class Field {

	private final Integer line;
	private final Integer column;

	private Integer idField;
	private boolean mined = false;
	private boolean open = false;
	private boolean marked = false;

	private List<Field> neighborhoods = new ArrayList<Field>();

	public Field(Integer line, Integer column) {
		this.line = line;
		this.column = column;
	}

	boolean addNeighbor(Field neighbor) {
		boolean lineDif = this.line != neighbor.line;
		boolean columnDif = this.column != neighbor.column;
		boolean diagonal = lineDif && columnDif;

		int deltaLine = Math.abs(this.line - neighbor.line);
		int deltaColumn = Math.abs(this.column - neighbor.column);
		int deltaGeneral = deltaColumn + deltaLine;

		if (deltaGeneral == 1 && !diagonal) {
			neighborhoods.add(neighbor);
			return true;
		} else if (deltaGeneral == 2 && diagonal) {
			neighborhoods.add(neighbor);
			return true;
		}

		return false;
	}

	/*
	 * Alterar a marks quando o camopo estiver fechado Change tag when field is
	 * closed
	 */
	void changeMarking() {
		if (!open) {
			marked = !marked;
		}
	}

	boolean openField() {
		if (!open && !marked) {
			open = true;

			if (mined) {
				throw new ExplosionException();
			}

			if (safeneighborhood()) {
				neighborhoods.forEach(v -> v.openField());
			}

			return true;
		}
		return false;
	}

	/*
	 * Garante que quando clica em um campo, os campos ao lado sejam seguros, caso
	 * contrario nao é aceito
	 * 
	 * Ensures that when you click on a field, the fields beside it are safe,
	 * otherwise it is not accepted
	 */

	boolean safeneighborhood() {
		return neighborhoods.stream().noneMatch(v -> v.mined);
	}
	
	void mineField() {
		if(!mined) {
			mined = true;
		}
	}
	
	public boolean isMarked() {
		return marked;
	}

}
