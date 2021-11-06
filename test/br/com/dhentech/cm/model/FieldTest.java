package br.com.dhentech.cm.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FieldTest {
	private Field field;

	@BeforeEach
	void initField() {
		field = new Field(3, 3);
	}

	@Test
	void testNeighborRealDistanceOneLeft() {
		Field neighbor = new Field(3, 2);
		boolean result = field.addNeighbor(neighbor);

		assertTrue(result);
	}

	@Test
	void testNeighborRealDistanceOneRight() {
		Field neighbor = new Field(3, 4);
		boolean result = field.addNeighbor(neighbor);

		assertTrue(result);
	}

	@Test
	void testNeighborRealDistanceOneUp() {
		Field neighbor = new Field(2, 3);
		boolean result = field.addNeighbor(neighbor);

		assertTrue(result);
	}

	@Test
	void testNeighborRealDistanceOneDown() {
		Field neighbor = new Field(4, 3);
		boolean result = field.addNeighbor(neighbor);

		assertTrue(result);
	}

	@Test
	void testNeighborRealDistanceDiagonal() {
		Field neighbor = new Field(2, 2);
		boolean result = field.addNeighbor(neighbor);

		assertTrue(result);
	}
	
	@Test
	void testDontNeighbor() {
		Field neighbor = new Field(1, 1);
		boolean result = field.addNeighbor(neighbor);

		assertFalse(result);
	}

}
