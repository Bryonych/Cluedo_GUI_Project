package TestsCluedo;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Model.Game;
import junit.framework.Assert;
import viewController.Board;

class TestsCases {

	@Test
	void test_01() {
		//Testing moving out of bounds
		//Should throw an out of bounds execption because it shouldn't be able to move this way.
		Game game = new Game();
		Board board = game.createBoard();
		game.setPlayers("Mrs White");
		for(Model.Character c : game.getPlayers()) {
			int x = c.getLocation().getXPos();
			int y = c.getLocation().getYPos();
			//assertFalse(game.moveCharacter(c, board.getCells()[x][y-1]));
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {game.moveCharacter(c, board.getCells()[x][y-1]);});
		}
	}
	@Test
	void test_02() {
		//Testing the move in a taken spot 
		Game game = new Game();
		Board board = game.createBoard();
		game.setPlayers("Mr Green");
		game.setPlayers("Mrs White");
		Model.Character x  = game.getPlayers().get(0);
		Model.Character y  = game.getPlayers().get(1);
		int x1 = y.getLocation().getXPos();
		int y1 = y.getLocation().getYPos();
		game.moveCharacter(x, board.getCells()[y1+1][x1]);
		assertFalse(game.moveCharacter(y, board.getCells()[y.getLocation().getYPos()+1][y.getLocation().getXPos()]));
	}
	@Test
	void test_03() {
		Game game = new Game();
		Board board = game.createBoard();
		game.setPlayers("Mr Green");
		System.out.println(Game.Print(board));

		fail("Not yet implemented");
	}
	@Test
	void test_04() {
		fail("Not yet implemented");
	}
	@Test
	void test_05() {
		fail("Not yet implemented");
	}
	@Test
	void test_06() {
		fail("Not yet implemented");
	}
	@Test
	void test_07() {
		fail("Not yet implemented");
	}
	@Test
	void test_08() {
		fail("Not yet implemented");
	}
	@Test
	void test_09() {
		fail("Not yet implemented");
	}
	@Test
	void test_10() {
		fail("Not yet implemented");
	}

}
