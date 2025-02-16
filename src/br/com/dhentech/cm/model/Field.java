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
				throw new ExplosionException("Mina encontrada!");
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
	 * contrario nao � aceito
	 * 
	 * Ensures that when you click on a field, the fields beside it are safe,
	 * otherwise it is not accepted
	 */

	boolean safeneighborhood() {
		return neighborhoods.stream().noneMatch(v -> v.mined);
	}

	void mineField() {
		mined = true;
	}

	public boolean isMined() {
		return mined;
	}

	public boolean isMarked() {
		return marked;
	}
	
	 void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isOpen() {
		return open;
	}

	public boolean isClose() {
		return !open;
	}

	public Integer getLine() {
		return line;
	}

	public Integer getColumn() {
		return column;
	}

	/* Garantir que o objetivo seja alcan�ado nos campos */
	boolean objectiveAchieved() {
		boolean desvendado = !mined && open;
		boolean protegido = mined && marked;
		return desvendado || protegido;
	}

	/* Para saber a quantidade de minas que tem na vizinhan�a */
	long minesInTheNeighborhood() {
		return neighborhoods.stream().filter(v -> v.mined).count();
	}

	void resetGame() {
		open = false;
		mined = false;
		marked = false;
	}

	public String toString() {
		if (this.marked) {
			return "x";
		} else if (this.open && this.mined) {
			return "*";
		} else if (this.open && minesInTheNeighborhood() > 0) {
			return Long.toString(minesInTheNeighborhood());
		} else if (this.open) {
			return " ";
		} else {
			return "?";
		}
	}

}
