package model;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Represents a board of tiles for the game
 */
public class Board
{
	//------------------------
	// MEMBER VARIABLES
	//------------------------

	public static final int HEIGHT = 25;
	public static final int WIDTH = 24;

	//Board Associations
	private Tile[][] tiles;
	private List<Weapon> weapons;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Board(String[] boardData)
	{
		tiles = new Tile[HEIGHT][WIDTH];
		//add data to each tile
		for(int i = 0; i < tiles.length; i++) {
			Tile[] row = tiles[i];
			// System.out.println("row: "+boardData[i]);
			for(int j = 0; j < row.length; j++) {
				//check which tile is it
				char c = boardData[i].charAt(j);
				switch(c) {
				case '.': //invalid tile
					tiles[i][j] = new InvalidTile(new Point(i, j), c, null);
					break;
				case '*': //for the hallway tiles and the hall tiles that are where the characters first start
					tiles[i][j] = new HallTile(new Point(i, j), null);
					break;
				case 'w': //Mrs White
					tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[0]);
					break;
				case 'g': //Mr Green
					tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[1]);
					break;
				case 'k': //Mrs Peacock
					tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[2]);
					break;
				case 'p': //Professor Plum
					tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[3]);
					break;
				case 's': //Miss Scarlett
					tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[4]);
					break;
				case 'm': //Colonel Mustard
					tiles[i][j] = new HallTile(new Point(i, j),  Game.PLAYERS[5]);
					break;
				case '|': // door can only be entered on the vertical
					tiles[i][j] = new DoorTile(new Point(i, j), c, null);
					break;
				case '-': 
					tiles[i][j] = new DoorTile(new Point(i, j), c, null);
					break;
				default:  //for the room tiles
					tiles[i][j] = new RoomTile(new Point(i, j), c, null);
					break;
				}
			}
		}
		weapons = new ArrayList<>();
		//Initialize weapons
		for(int i = 0; i < 6; i++) {
			weapons.add(Game.WEAPONS[i]);
		}
		//shuffle weapon's list and add to rooms
		Collections.shuffle(weapons);
		//for each weapon place them in the first encountered tile of the room
		for(int j = 0; j < 6; j++) {
			Point p = new Point(searchFor(Game.ROOMSYMBOL[j]));
			tiles[(int) p.getX()][(int) p.getY()] = new RoomTile(p, Game.ROOMSYMBOL[j], weapons.get(j));
		}
	}

	/**
	 * searches for first occurrence of the character in the board
	 * @param c character to search for
	 * @return
	 */
	public Point searchFor(char c) {
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(tiles[i][j].getSymbol() == c) {
					return new Point(i, j);
				}
			}
		}
		System.out.println("ERROR: character not found in board");
		return null;
	}

	/**
	 * prints current state of board
	 */
	public void printBoard(){
		for(Tile[] row : tiles) {
			for(Tile t : row) {
				System.out.print(t.getSymbol());
			}
			System.out.println();
		}
	}

	public Tile getTileAt(int x, int y) {
		return tiles[y][x];
	}

	public Tile[][] getGrid() {
		return tiles;
	}
}