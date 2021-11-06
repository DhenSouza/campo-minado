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

}
