package br.com.dhentech.cm.model;

import java.util.ArrayList;
import java.util.List;

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
		
		if(deltaGeneral == 1 && !diagonal) {
			neighborhoods.add(neighbor);
			return true;
		} else if(deltaGeneral == 2 && diagonal) {
			neighborhoods.add(neighbor);
			return false;
		}
		
		return false;
	}

}
