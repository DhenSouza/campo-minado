package br.com.dhentech.cm.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.dhentech.cm.exception.ExplosionException;

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

	@Test
	void testDefaultValueMarkedAttribute() {
		assertFalse(field.isMarked());
	}

	/* Teste de alterar marcação */
	@Test
	void testChangeMarking() {
		field.changeMarking();
		assertTrue(field.isMarked());
	}

	/* Teste de alterar marcação duas chamadas */
	@Test
	void testChangeMarkingTwoCalls() {
		field.changeMarking();
		field.changeMarking();
		assertFalse(field.isMarked());
	}

	// Teste abrir campo sem mina
	@Test
	void testOpenFieldWithoutMine() {
		assertTrue(field.openField());
	}

	/* teste o campo aberto sem a marca da mina */
	@Test
	void testOpenFieldWithoutMineMarked() {
		field.changeMarking();
		assertFalse(field.openField());
	}

	// abrir um campo minado e marcado
	@Test
	void testOpenFieldMineMarked() {
		field.changeMarking();
		field.mineField();
		assertFalse(field.openField());
	}

	// Teste de mina de campo aberto nao marcado
	@Test
	void unmarkedOpenFieldMineTest() {
		field.mineField();

		assertThrows(ExplosionException.class, () -> {
			field.openField();
		});
	}

	@Test
	void testOpenNeighbor1() {
		Field field11 = new Field(1, 1);
		Field field22 = new Field(2, 2);

		field22.addNeighbor(field11);

		field.addNeighbor(field22);
		field.openField();

		assertTrue(field22.isOpen() && field11.isOpen());
	}
	
	/* O campo 2,2 nao pode propagar tendo os campos vizinhos minados*/
	@Test
	void testOpenNeighbor2() {
		Field field11 = new Field(1, 1);
		Field field12 = new Field(1, 1);
		field12.mineField();
		
		Field field22 = new Field(2, 2);
		field22.addNeighbor(field11);
		field22.addNeighbor(field12);
		
		field.addNeighbor(field22);
		field.openField();
		
		assertTrue(field22.isOpen() && field11.isClose());

	}
	/* teste com o objetivo do campo alcançado*/
	@Test
	void testObjectiveAchieved1() {
		Field field32 = new Field(3,2);
		Field field34 = new Field(3,4);
		field32.openField();
		field32.changeMarking();
		
		field34.openField();
		field34.changeMarking();
		
		field.addNeighbor(field32);
		field.addNeighbor(field34);
		
		field.openField();
		assertTrue(field32.objectiveAchieved() && field34.objectiveAchieved());
	}
	
	/*Teste com o objetivo do campo nao alcançado*/
	@Test
	void testObjectiveAchieved2() {
		Field field32 = new Field(3,2);
		Field field34 = new Field(3,4);
		field32.changeMarking();
		field32.mineField();
		
		field34.mineField();
		
		field.addNeighbor(field32);
		field.addNeighbor(field34);
		
		field.openField();
		assertFalse(field32.objectiveAchieved() && field34.objectiveAchieved());
	}


}
