package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Model.Card;
import Model.Game;
import Model.Board;
import Model.Hand;
import Model.Accusation;
import junit.framework.Assert;


class TestsCases2 {

	@Test
	void test_01() {
		//Testing moving out of bounds
		//Should throw an out of bounds execption because it shouldn't be able to move this way.
		Game game = new Game();
		Board board = game.getBoard();
		game.setPlayers("Mrs White");
		Model.Character c = game.getPlayers().get(0);
			int x = c.getLocation().getXPos();
			int y = c.getLocation().getYPos();
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {game.moveCharacter(c, board.getCells()[x][y-1]);});
	}
	@Test
	void test_02() {
		//Testing the move in a taken spot.
		//Should return false because it the spot is taken so its a invalid move
		Game game = new Game();
		Board board = game.getBoard();
		game.setPlayers("Mrs White");
		game.setPlayers("Mr Green");
		Model.Character x  = game.getPlayers().get(0);
		Model.Character y  = game.getPlayers().get(1);
		int x1 = y.getLocation().getXPos();
		int y1 = y.getLocation().getYPos();
		game.moveCharacter(x, board.getCells()[y.getLocation().getYPos()+1][y.getLocation().getXPos()]);
		assertFalse(game.moveCharacter(y, board.getCells()[y.getLocation().getYPos()][y.getLocation().getXPos()]));
	}
	@Test
	void test_03() {
		//Testing moving into the walls
		//Should return false because it is not a valid move.
		Game game = new Game();
		Board board = game.getBoard();
		game.setPlayers("Mrs White");
		Model.Character c = game.getPlayers().get(0);
			int x = c.getLocation().getXPos();
			int y = c.getLocation().getYPos();
			assertFalse(game.moveCharacter(c, board.getCells()[y][x+1]));
	}
	@Test
	void test_04() {
		//Testing for playing the game as only one character
		//Should return an the illegal argumentException because the wrong amount of players are trying to play the game
		Game g = new Game();
		g.resetPlayers();
		Board board = g.getBoard();
		g.setPlayers("Mrs White");
		System.out.println(g.getNumPlayers());
		Model.Character c = g.getPlayers().get(0);
		assertFalse(Game.TestMainGame(g,g.getPlayers()));
	}
	@Test
	void test_05() {
		//Testing for players making suggestion outside of rooms
		Game game = new Game();
		Board board = game.getBoard();
		game.setPlayers("Mrs White");
		Model.Character c = game.getPlayers().get(0);
		assertFalse(game.Acc(c));
	}
	@Test
	void test_06() {
		//Testing for players being in a room to make a suggestion
		Game game = new Game();
		Board board = game.getBoard();
		game.setPlayers("Mrs White");
		Model.Character c = game.getPlayers().get(0);
		game.moveCharacter(c, board.getCells()[5][15]);
		assertTrue(game.Acc(c));
	}

	@Test
	void test_07() {
		//Testing for valid movement, being able to move once.
		Game game = new Game();
		Board board = game.getBoard();
		game.setPlayers("Mrs White");
		game.setPlayers("Mr Green");
		Model.Character x  = game.getPlayers().get(0);
		Model.Character y  = game.getPlayers().get(1);
		//assertTrue(game.moveCharacter(x,board.getCells()[y.getLocation().getXPos()][y.getLocation().getYPos()+1]));
		assertTrue(game.moveCharacter(x,board.getCells()[x.getLocation().getYPos()][x.getLocation().getXPos()+1]));
	}
	@Test
	void test_08() {
		//Testing that you can't make an empty Accusation
		//Throwing a nullPointerException when checkAccstion is empty
		Game game = new Game();
		Board board = game.getBoard();
		game.setPlayers("Mrs White");
		assertThrows(NullPointerException.class,() ->{game.checkAccusation(null);});
	}
	


}

