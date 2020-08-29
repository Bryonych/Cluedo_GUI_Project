package viewController;
import java.awt.Point;
import java.io.IOException;
import java.util.*;
import java.util.Observable;

import Model.Card;
import Model.Cell;
import Model.Character;
import Model.CharacterCard;
import Model.Cell.Type;

/**
 * Represents the game of Cleudo
 */
public class Game extends Observable
{
    //------------------------
    // MEMBER VARIABLES
    //------------------------
    //Game Attributes
    private Board board;
    private static int dice = 0;
    private static List<Character> players = new ArrayList<Character>();
    private static List<Character> characters = new ArrayList<Character>();
    private List<Card> allCards = new ArrayList<Card>();
    private List<CharacterCard> charCards = new ArrayList<CharacterCard>();
    private List<RoomCard> roomCards = new ArrayList<RoomCard>();
    private List<WeaponCard> weaponCards = new ArrayList<WeaponCard>();
    private List<Room> rooms = new ArrayList<Room>();
    private List<Weapon> weapons = new ArrayList<Weapon>();
    private Tuple murderDetails;
    private CharacterCard murderer;
    private RoomCard scene;
    private WeaponCard weapon;
    private Character currentTurn;
    private Suggestion suggestion;
    private Card refute;
    private int refutePlayer;
    private Accusation accusation;
    public boolean gameWon;
    private static boolean roomplay = false;
    private static int stepsRemaining = 1;
    private static int numPlayers;
    private static int countPlayersTurn = 0;
    private boolean suggestionMade = false;
    private boolean suggestionRefuted = false;
    private boolean accusationMade = false;
    public int stepCount = 0;
    private int round = 1;
    private String characterChecker = "";



    /**
     * Creates a new game
     */
    public Game(){
        board = createBoard();
        this.numPlayers = numPlayers;
        players = new ArrayList<Character>();
        createRooms();
        createWeapons();
        createCharacters();
        createCards();
        murderDetails = setMurderDetails();
    }
    /**
     * Creates the cells that make up the initial board
     * @return  The created board
     */
    public static Board createBoard(){
        int x = 30;
        int y = 30;
        Cell[][] squares = new Cell[x][y];
        for (int col = 0; col < x; col++){
            for (int row = 0; row < y; row++){
                //Outer walls
                if (row == 0 || row == 29 || col == 0 || col == 29){
                    squares[row][col] = new Cell(Cell.Type.WALL, col, row);
                }
                //Inner horizontal walls
                else if ((row < 8 && col == 7) || (row < 9 && col == 11) || (row < 9 && col == 18) || (row < 7 && col == 21)
                        || (row > 9 && row < 19 && col == 9) || (row > 8 && row < 16 && col == 21) || (row > 16 && row < 24 && col == 21)
                        || (row > 21 && col == 8) || (row > 20 && col == 11) || (row > 25 && col == 21)
                        || (row > 20 && col == 18)){
                    squares[row][col] = new Cell(Cell.Type.WALL, col, row);
                }
                //Inner vertical walls
                else if ((row == 7 &&  col < 8) || (row == 8 && col > 10 && col < 19)|| (row == 6 && col > 21)
                        || (row == 10 && col < 10) || (row == 18 && col < 10) || (row == 9 && col > 21)
                        || (row == 15 && col > 21) || (row == 17 && col > 20) || (row == 23 && col > 20)
                        || (row == 22 && col < 9) || (row == 20 && col > 10 && col < 19) || (row == 26 && col > 20)){
                    squares[row][col] = new Cell(Cell.Type.WALL, col, row);
                }
                //Cellar
                else if (row > 10 && row < 18 && col > 11 && col < 19){
                    squares[row][col] = new Cell(Cell.Type.WALL, col, row);
                }
                else if (row < 7 && col < 7){
                    squares[row][col] = new Cell(Cell.Type.KITCHEN, col, row);
                }
                else if (row < 8 && col > 11 && col < 18){
                    squares[row][col] = new Cell(Cell.Type.BALLROOM, col, row);
                }
                else if (row < 6 && col > 21){
                    squares[row][col] = new Cell(Cell.Type.CONSERVATORY, col, row);
                }
                else if (row > 10 && row < 18 && col < 9){
                    squares[row][col] = new Cell(Cell.Type.DINING, col, row);
                }
                else if (row > 9 && row < 15 && col > 21){
                    squares[row][col] = new Cell(Cell.Type.BILLIARD, col, row);
                }
                else if (row > 17 && row < 23 && col > 20){
                    squares[row][col] = new Cell(Cell.Type.LIBRARY, col, row);
                }
                else if (row > 22 && col < 8){
                    squares[row][col] = new Cell(Cell.Type.LOUNGE, col, row);
                }
                else if (row > 20 && col > 11 && col < 18){
                    squares[row][col] = new Cell(Cell.Type.HALL, col, row);
                }
                else if (row > 25 && col > 20){
                    squares[row][col] = new Cell(Cell.Type.STUDY, col, row);
                }
                else {
                    squares[row][col] = new Cell(Cell.Type.HALLWAY, col, row);
                }
            }
        }
        squares = setDoorsAndStart(squares);
        squares = setWeapons(squares);
        return new Board(squares);
    }

    public Board getBoard(){
        return board;
    }

    /**
     * Sets doors and start cells in the walls
     * @param squares   Array of cells representing the board
     * @return          The amended array with doors and start cells
     */
    public static Cell[][] setDoorsAndStart(Cell[][] squares){
        squares[0][10].changeType(Cell.Type.WHITE);
        squares[0][19].changeType(Cell.Type.GREEN);
        squares[7][29].changeType(Cell.Type.PEACOCK);
        squares[20][0].changeType(Cell.Type.MUSTARD);
        squares[29][9].changeType(Cell.Type.SCARLETT);
        squares[24][29].changeType(Cell.Type.PLUM);
        squares[7][5].changeType(Cell.Type.DOOR);
        squares[5][11].changeType(Cell.Type.DOOR);
        squares[8][16].changeType(Cell.Type.DOOR);
        squares[5][18].changeType(Cell.Type.DOOR);
        squares[5][21].changeType(Cell.Type.DOOR);
        squares[13][9].changeType(Cell.Type.DOOR);
        squares[18][7].changeType(Cell.Type.DOOR);
        squares[11][21].changeType(Cell.Type.DOOR);
        squares[15][27].changeType(Cell.Type.DOOR);
        squares[17][25].changeType(Cell.Type.DOOR);
        squares[20][21].changeType(Cell.Type.DOOR);
        squares[22][7].changeType(Cell.Type.DOOR);
        squares[20][14].changeType(Cell.Type.DOOR);
        squares[20][15].changeType(Cell.Type.DOOR);
        squares[23][18].changeType(Cell.Type.DOOR);
        squares[26][22].changeType(Cell.Type.DOOR);
        return squares;
    }

    /**
     * Creates the characters and adds them to the list
     */
    public void createCharacters(){
        Character white = new Character("Mrs White",  board.getCells()[0][10], Cell.Type.WHITE);
        Character green = new Character("Mr Green", board.getCells()[0][19], Cell.Type.GREEN);
        Character peacock = new Character("Mrs Peacock", board.getCells()[7][29], Cell.Type.PEACOCK);
        Character plum = new Character("Prof Plum",board.getCells()[24][29], Cell.Type.PLUM);
        Character scarlett = new Character("Miss Scarlet", board.getCells()[29][9], Cell.Type.SCARLETT);
        Character mustard = new Character("Col Mustard", board.getCells()[20][0], Cell.Type.MUSTARD);
        characters.add(white);
        characters.add(green);
        characters.add(peacock);
        characters.add(plum);
        characters.add(scarlett);
        characters.add(mustard);
    }
    
    public void setPlayers(String character) {
    if(players.size() != 0) {
    	for(Character a : players) {
    		if(a.getName().equals(character)) {
    			throw new IllegalArgumentException("Player Already Selected");
    		}
    	}
    }
   	for (Character c : characters) {
   		if(!characterChecker.contains(c.getName())) {
    		if (c.getName().equals(character)) {
    			players.add(c);
    			characterChecker += character + " ";
    			break;
    		}
    	}
   	}
    	
    }
    public static void Print(List<Character> t) {
    	for(Character a : t) {
    		System.out.println(a.getName());
    	}
    	System.out.println("-------------");
    }
    
    public List<Card> getCards(){
    	return allCards;
    }

    /**
     * Creates the players and adds them to the list
     */
    public static List<Character> setPlayers(){
    	List<Character> players = new ArrayList<Character>();
        int count = 1;
        Stack<Character> CharacterSelection = new Stack<Character>();
        CharacterSelection.addAll(characters);
        Scanner scan  = new Scanner(System.in);
        System.out.println("How many Players?");
        numPlayers = scan.nextInt();
        int counter = numPlayers;
        //For each player, allow them to select a character and add them to the players list
        while (counter != 0) {
            ArrayList<Character> temp = new ArrayList<Character>();
            temp.addAll(CharacterSelection);
            System.out.println("Player "+ count+ " Please Select Your Character");
            System.out.println("Please press Y for Yes, Any Other letter button to scroll next");
            for(int i = 0; i < temp.size();) {
                System.out.println(temp.get(i).getName());
                scan = new Scanner(System.in);
                String read = scan.nextLine().toUpperCase();
                if(read.contains("Y")) {
                    CharacterSelection.remove(temp.get(i));
                    players.add(temp.get(i));
                    counter--;
                    count++;
                    break;
                } else {
                    i++;
                }
            }
        }
        return players;
    }
    /**
     * Creates all the rooms and sets the cell where the weapon sits in the room
     */
    public void createRooms(){
        rooms.add(new Room("Kitchen", board.getCells()[2][2], Cell.Type.KITCHEN, board.getCells()[2][3]));
        rooms.add(new Room("Ball Room", board.getCells()[2][14], Cell.Type.BALLROOM, board.getCells()[2][15]));
        rooms.add(new Room("Conservatory", board.getCells()[2][27], Cell.Type.CONSERVATORY, board.getCells()[2][26]));
        rooms.add(new Room("Dining Room", board.getCells()[13][2], Cell.Type.DINING, board.getCells()[13][3]));
        rooms.add(new Room("Billiard Room", board.getCells()[13][27], Cell.Type.BILLIARD, board.getCells()[13][26]));
        rooms.add(new Room("Library", board.getCells()[20][27], Cell.Type.LIBRARY, board.getCells()[20][26]));
        rooms.add(new Room("Lounge", board.getCells()[27][2], Cell.Type.LOUNGE, board.getCells()[27][3]));
        rooms.add(new Room("Hall", board.getCells()[27][14], Cell.Type.HALL, board.getCells()[27][15]));
        rooms.add(new Room("Study", board.getCells()[27][27], Cell.Type.STUDY, board.getCells()[27][26]));
    }

    /**
     * Creates all the weapons and adds them to the list
     */
    public void createWeapons(){
        weapons.add(new Weapon("Candlestick", rooms.get(0), Cell.Type.CANDLESTICK));
        weapons.add(new Weapon("Dagger", rooms.get(1), Cell.Type.DAGGER));
        weapons.add(new Weapon("Lead Pipe", rooms.get(2), Cell.Type.LEADPIPE));
        weapons.add(new Weapon("Revolver", rooms.get(3), Cell.Type.REVOLVER));
        weapons.add(new Weapon("Rope", rooms.get(4), Cell.Type.ROPE));
        weapons.add(new Weapon("Spanner", rooms.get(5), Cell.Type.SPANNER));
    }
    /**
     * Creates the cards and adds them to the lists
     */
    public void createCards(){
        for (Character c : characters){
            CharacterCard newCard = new CharacterCard(c.getName(), c);
            allCards.add(newCard);
            charCards.add(newCard);
        }
        RoomCard kitchen = new RoomCard("Kitchen", rooms.get(0));
        allCards.add(kitchen);
        roomCards.add(kitchen);
        RoomCard ballRoom = new RoomCard("Ball Room", rooms.get(1));
        allCards.add(ballRoom);
        roomCards.add(ballRoom);
        RoomCard conservatory = new RoomCard("Conservatory", rooms.get(2));
        allCards.add(conservatory);
        roomCards.add(conservatory);
        RoomCard dining = new RoomCard("Dining Room", rooms.get(3));
        allCards.add(dining);
        roomCards.add(dining);
        RoomCard billiard = new RoomCard("Billiard Room", rooms.get(4));
        allCards.add(billiard);
        roomCards.add(billiard);
        RoomCard library = new RoomCard("Library", rooms.get(5));
        allCards.add(library);
        roomCards.add(library);
        RoomCard lounge = new RoomCard("Lounge", rooms.get(6));
        allCards.add(lounge);
        roomCards.add(lounge);
        RoomCard hall = new RoomCard("Hall", rooms.get(7));
        allCards.add(hall);
        roomCards.add(hall);
        RoomCard study = new RoomCard("Study", rooms.get(8));
        allCards.add(study);
        roomCards.add(study);
        WeaponCard candlestick = new WeaponCard("Candlestick", weapons.get(0));
        allCards.add(candlestick);
        weaponCards.add(candlestick);
        WeaponCard dagger = new WeaponCard("Dagger", weapons.get(1));
        allCards.add(dagger);
        weaponCards.add(dagger);
        WeaponCard leadPipe = new WeaponCard("Lead Pipe", weapons.get(2));
        allCards.add(leadPipe);
        weaponCards.add(leadPipe);
        WeaponCard revolver = new WeaponCard("Revolver", weapons.get(3));
        allCards.add(revolver);
        weaponCards.add(revolver);
        WeaponCard rope = new WeaponCard("Rope", weapons.get(4));
        allCards.add(rope);
        weaponCards.add(rope);
        WeaponCard spanner = new WeaponCard("Spanner", weapons.get(5));
        allCards.add(spanner);
        weaponCards.add(spanner);
    }
    /**
     * Picks a random card from each list to be the murder details
     * @return  The murder details
     */
    public Tuple setMurderDetails(){
        Random rand = new Random();
        murderer = charCards.get(rand.nextInt(charCards.size()));
        scene = roomCards.get(rand.nextInt(roomCards.size()));
        weapon = weaponCards.get(rand.nextInt(weaponCards.size()));
        //Remove the cards from the pack, so the rest can be dealt
        allCards.remove(murderer);
        allCards.remove(scene);
        allCards.remove(weapon);
        return new Tuple(murderer, weapon, scene);
    }
    /**
     * Suffles the remaining cards and deals them amongst the players
     */
    public void dealCards(){
        Collections.shuffle(allCards);
        for (int i = 0; i < numPlayers; i++){
            List newHand = new ArrayList<Card>();
            for (int j = i; j < allCards.size(); j+=numPlayers){
                newHand.add(allCards.get(j));
            }
            players.get(i).setHand(new Hand(newHand, players.get(i)));
        }
        //Adds the murder cards back in, for future use
        allCards.add(murderer);
        allCards.add(scene);
        allCards.add(weapon);
    }

    /**
     * Randomly selects a number between 2 and 12 for the dice roll
     * @return  The number of the rolled dice
     */
    public int rollDice(){
        dice = 2 + (int)(Math.random()*11);
        return dice;
    }

    /**
     * Checks if an accusation is correct
     * @param accusation    The accusaiton to check against the murder details
     */
    public void checkAccusation(Accusation accusation){
        if (murderDetails.getMurderer().getCharacter().equals(accusation.murderAccused.getCharacter()) && murderDetails.getCrimeScene().getRoom().equals(accusation.roomAccused.getRoom()) && murderDetails.getWeapon().getWeapon().equals(accusation.weaponAccused.getWeapon())){
            System.out.println("Correct! You have won the game!");
            gameWon = true;
        }
        else {
            System.out.println("Incorrect Accusation - you may make no further suggestions");
            accusation.getAccuser().setSuggestionStatus(false);
        }
    }

    /**
     * Puts the weapons in the weapon cells
     * @param squares   Cells representing the board
     * @return          The amended cells with the weapons included
     */
    public static Cell[][] setWeapons(Cell[][] squares){
        squares[2][2].changeType(Cell.Type.CANDLESTICK);
        squares[2][14].changeType(Cell.Type.DAGGER);
        squares[2][27].changeType(Cell.Type.LEADPIPE);
        squares[13][2].changeType(Cell.Type.REVOLVER);
        squares[13][27].changeType(Cell.Type.ROPE);
        squares[20][27].changeType(Cell.Type.SPANNER);
        return squares;
    }
    /**
     * Moves weapon from one room to another
     * @param moving        Weapon to be moved
     * @param destination   Room to move it to
     */
    public void moveWeapon(Weapon moving, Room destination){
        Room current = moving.getLocation();
        Cell moveFrom = current.getWeaponSpot();
        Cell moveTo = destination.getWeaponSpot();
        boolean occupied = false;
        Weapon currentWeapon = null;
        //If there's already a weapon there, swap them
        if (!moveTo.getType().equals(destination.getType())){
            for (Weapon w : weapons){
                if (w.getType().equals(moveTo.getType())){
                    currentWeapon = w;
                }
            }
            occupied = true;
        }
        if (!occupied) {
            moveFrom.changeType(current.getType());
        }
        else {
            moveFrom.changeType(currentWeapon.getType());
            currentWeapon.setLocation(current);
        }
        moveTo.changeType(moving.getType());
        moving.setLocation(destination);
        //printBoard(board.getCells());
        setChanged();
        notifyObservers();

    }
    /**
     * Moves Character on the board
     * @param moving        Character to be moved
     * @param destination   Cell to move them to
     */
    public boolean moveCharacter(Character moving, Cell destination){
        //If move is valid, update both cells and character's room and return true
        if (isValidMove(destination)) {
        	countPlayersTurn++;
            Cell current = moving.getLocation();
            current.setIsEmpty(true);
            Point loc = new Point(current.getXPos(), current.getYPos());
            Cell.Type oldRoom = moving.getCurrentRoom();
            Cell.Type character = moving.getCharacterType();
            moving.setCurrentRoom(destination.getType());
            board.changeCell(new Cell(oldRoom, loc.x, loc.y), loc.x, loc.y);
            destination.changeType(character);
            moving.setLocation(destination);
            destination.setIsEmpty(false);
            //printBoard(board.getCells());
            stepCount++;
            setChanged();
            notifyObservers();
            return true;
        }
        //If invalid move
        return false;
    }

    /**
     * Checks that the character is staying on the board and not trying to move into any walls
     * @param destination   Cell to be moved to
     * @return              Whether move is valid
     */
    public boolean isValidMove(Cell destination){
        //System.out.println("yPos " + destination.getYPos());
        if (destination.getType().equals(Cell.Type.WALL) || destination.getXPos() < 0 || destination.getXPos() > 29 ||
                destination.getYPos() < 0 || destination.getYPos() > 29 || destination.getType().equals(Cell.Type.START) || !destination.getIsEmpty()){
            return false;
        }
        return true;
    }

    /**
     * Moves a character to a room when a suggestion they are the murderer is made
     * @param moving    Character to be moved
     * @param newRoom   Room to move them to
     */
    public void moveCharacterToRoom(Character moving, Room newRoom){
        Cell current = moving.getLocation();
        current.setIsEmpty(true);
        Cell.Type oldRoom = moving.getCurrentRoom();
        Cell.Type character = moving.getCharacterType();
        moving.setCurrentRoom(newRoom.getType());
        current.changeType(oldRoom);
        if (newRoom.getCharacterSpot().getIsEmpty()) {
            newRoom.getCharacterSpot().changeType(character);
            newRoom.getCharacterSpot().setIsEmpty(false);
            moving.setLocation(newRoom.getCharacterSpot());
        }
        else {
            Cell spot = newRoom.getCharacterSpot();
            while (!spot.getIsEmpty()){
                spot = board.getCells()[spot.getYPos()][spot.getXPos()+1];
                if (spot.getType().equals(Cell.Type.WALL)){
                    spot = board.getCells()[spot.getYPos()-1][spot.getXPos()-1];
                }
            }
            spot.changeType(character);
            spot.setIsEmpty(false);
            moving.setLocation(spot);
        }
        //printBoard(board.getCells());
        setChanged();
        notifyObservers();

    }

    /**
     * Checks the input is valid for selecting number of players and sets field
     */
    public void checkNumPlayers(){
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("How many players?");
            numPlayers = scan.nextInt();
            
            if (numPlayers < 2 || numPlayers > 6){
                System.out.println("Number must bet 2 - 6. How many players?");
                checkNumPlayers();
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid Number. Please select a number between 2 - 6");
            checkNumPlayers();
        }
    }
    
    public void setNumPlayers(int number) {
    	numPlayers = number;
    }
    
    public int getNumPlayers() {
    	return numPlayers;
    }
    
    public List<Character> getPlayers(){
    	return Collections.unmodifiableList(players);
    }
    
    public String getPlayersS() {
    	String text = "";
    	for(Character c : players) {
    		text += c.getName() + " ";
    	}
    	System.out.println(text);
    	return text;
    }
    
    public static void resetPlayers() {
    	players = new ArrayList<Character>(); 
    }
    
    public boolean countSteps() {
    	if (stepCount == dice) {
    		stepCount = 0;
    		return true;
    	}
    	return false;
    }

    public static void main(String[] args) throws NumberFormatException, IOException{
    	Game game = new Game();
    	game.createBoard();
    	 game.dealCards();
    	TestMainGame(game,game.setPlayers());



    }
   
    public static void TestMainGame(Game g,List<Character> players) {
        //Testing for the players and the right amount
    	if(players.size() < 2 || players.size() > 6) {
    		throw new IllegalArgumentException("Wrong Amount of Players");
    	}
        //Until the game is finished, allow each player to have a turn in order
        while(!g.gameWon) {
           Turns(g,players);
        }
    }
    public static void Turns(Game game,List<Character> players) {
    	int count = 0;
    	 for (Character play : players) {
    		 if(countPlayersTurn == players.size()) {
    			 countPlayersTurn = 1;
    		 }
             count++;
             int diceRoll = game.rollDice();
             System.out.println("Player " + count + " " + play.getName() + " Your Turn");
             Scanner scan = new Scanner(System.in);
             System.out.println("Please press D to roll the dice");
             while(!scan.nextLine().equalsIgnoreCase("d")){
                 //pausing until dice rolled
             }
             System.out.println("You have rolled a: " + diceRoll);
            game.setChanged();
             //game.notifyObservers();
             game.keyRead(diceRoll, play);
             if(roomplay) {
                 System.out.println("=== Whats on your HAND for reference === ");
                 System.out.print(play.getHand());
                 game.createSuggestion(play.getHand().getCards(), play);
                 roomplay = false;
             }
             if(game.suggestionMade) {
                 System.out.println(game.suggestion.toString());
                 for (Character c : players) {
                     if (!c.equals(play)) {
                         int index = players.indexOf(c) +1;
                         System.out.println("Player " + index + " has refuted your suggestion with:\n");
                         game.refuteSuggestion(c, game.suggestion);
                     }
                 }
                 if(!game.suggestionRefuted) {
                     System.out.println("Your suggestion has not been refuted, would you like to make an accusation? (y/n)");
                     Scanner scan2 = new Scanner(System.in);
                     if(scan2.nextLine().equalsIgnoreCase("y")) {
                         game.accusation = game.suggestion.toAccusation();
                         game.accusationMade = true;
                         System.out.println("Accusation Made!!\n" + game.accusation.toString());
                     }
                 }
             }
             if(game.accusationMade) {
                 System.out.println("Checking Accusation");
                 game.checkAccusation(game.accusation);
                 if (game.gameWon) { break; }
             }
         }
         count = 0;
         game.round++;
     }
    
    
    
    public static String Print(Board board) {
    	String text = "";

    	Cell[][] cell = board.getCells();
    	for(int i = 0; i < 30; i++) {
    		for(int j = 0; j< 30; j++) {
    			text += cell[i][j].toString();
    		}
    		text +="\n";
    	}
    	return text;
    }

    /**
     * Displays the board on the screen
     * @param cell  Cells to be displayed
     */
    public static void printBoard(Cell[][] cell) {
        System.out.println("               Kitchen                          Ball Room                      Conservatory");
        for(int i = 0; i < 30; i++) {
            if (i == 13){
                System.out.print("Dining ");
            }
            else if (i == 14){
                System.out.print("Room   ");
            }
            else {
                System.out.print("       ");
            }
            System.out.print(i + " \t");
            for(int j = 0; j < 30; j++) {
                System.out.print(cell[i][j] +" ");
            }
            if (i == 11){
                System.out.print(" Billiard");
            }
            else if (i == 12){
                System.out.print( " Room");
            }
            else if (i == 20){
                System.out.print(" Library");
            }
            System.out.println("");
        }
        System.out.println("               Lounge                           Hall                           Study\n");
    }

    /**
     * Reads character input for moving the characters
     * @param n     Number rolled on the dice
     * @param play  Player whose turn it is
     */
    public void keyRead(int n,Character play) {
        boolean acc = true;
        HashSet<Point> celltemp = new HashSet<Point>();
        System.out.println("Rolled: "+n);
        System.out.println("Press z to go up, Press q to go down, Press a to go left, Press d to right");
        while(n != 0) { // Counter, for the dice to tell how many times they can go.
            System.out.println(n);
       
            Cell currentLoc = play.getLocation();
            celltemp.add(new Point(currentLoc.getYPos(),currentLoc.getXPos()));
            int nY = currentLoc.getYPos()+1;
            int nX = currentLoc.getXPos()+1;
            Scanner scan = new Scanner(System.in);
            String character = scan.nextLine();
            if(character.contains("q")) {
                try{
                    if(celltemp.contains(new Point(nY,currentLoc.getXPos()))){
                        throw new IndexOutOfBoundsException("Invalid move. Please make another choice");
                    } else if (moveCharacter(play, board.getCells()[nY][currentLoc.getXPos()])){
                        n--;
                        
                        System.out.println("Remain moves: " + n);
                    }
                    else {
                        System.out.println("Invalid move. Please make another choice");
                    }
                } catch (IndexOutOfBoundsException e) { System.out.println("Invalid move. Please make another choice"); }

            }
            else if(character.contains("z")) {
                try{
                    if(celltemp.contains(new Point(nY-2,currentLoc.getXPos()))){
                        throw new IndexOutOfBoundsException("Invalid move. Please make another choice");
                    } else if (moveCharacter(play, board.getCells()[nY-2][currentLoc.getXPos()])) {
                        n--;
                        System.out.println("Remain moves: " + n);
                    }
                    else {
                        System.out.println("Invalid move. Please make another choice");
                    }
                } catch (IndexOutOfBoundsException e) { System.out.println("Invalid move. Please make another choice"); }

            }
            else if(character.contains("a")) {
                try{
                    if(celltemp.contains(new Point(currentLoc.getYPos(),nX-2))){
                        throw new IndexOutOfBoundsException("Invalid move. Please make another choice");
                    } else if (moveCharacter(play, board.getCells()[currentLoc.getYPos()][nX-2])) {
                        n--;
                        System.out.println("Remain moves: " + n);
                    }

                    else {
                        System.out.println("Invalid move. Please make another choice");

                    }
                } catch (IndexOutOfBoundsException e) { System.out.println("Invalid move. Please make another choice"); }

            }
            else if(character.contains("d")) {
                try{
                    if(celltemp.contains(new Point(currentLoc.getYPos(),nX))){
                        throw new IndexOutOfBoundsException("Invalid move. Please make another choice");
                    } else  if (moveCharacter(play, board.getCells()[currentLoc.getYPos()][nX])) {
                        n--;
                        System.out.println("Remain moves: " + n);
                    }
                    else {
                        System.out.println("Invalid move. Please make another choice");

                    }
                } catch (IndexOutOfBoundsException e) { System.out.println("Invalid move. Please make another choice"); }
            }

            if(acc) {
                if (Acc(play)) {
                    System.out.println("===== Do you want to Make a SUGGESTION ? Press 'Y' or any key to continue on =====");
                    String choice = scan.nextLine();
                    if (choice.equalsIgnoreCase("Y")){
                        dice = n;
                        n = 0;
                        roomplay = true;
                    }
                    else {
                        if(n != 0) {
                            System.out.println("Press z to go up, Press q to go down, Press a to go left, Press d to right");
                        }
                        acc = false;
                    }

                }
            }

        }
        if(!acc) {
            System.out.println("Next Player get ready for your turn, Press any key to contiune");
        }
    }
    public static boolean Acc(Character play) {
    	if(play.getCurrentRoom().equals(Cell.Type.KITCHEN) || play.getCurrentRoom().equals(Cell.Type.BALLROOM) || play.getCurrentRoom().equals(Cell.Type.CONSERVATORY) ||
        play.getCurrentRoom().equals(Cell.Type.DINING) || play.getCurrentRoom().equals(Cell.Type.BILLIARD) || play.getCurrentRoom().equals(Cell.Type.LIBRARY) ||
        play.getCurrentRoom().equals(Cell.Type.LOUNGE) || play.getCurrentRoom().equals(Cell.Type.HALL) || play.getCurrentRoom().equals(Cell.Type.STUDY)) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }


    /**
     * Prompts the user to make a suggestion
     * @param playerCards   Player's hand to be excluded
     * @param play          Player making the suggestion
     */
    public void createSuggestion(List<Card> playerCards, Character play) {
        CharacterCard sCharacter = null;
        WeaponCard sWeapon = null;
        RoomCard sRoom = null;
        for (RoomCard r : roomCards){
            if (r.getRoom().getType().equals(play.getCurrentRoom())){
                sRoom = r;
            }
        }
        List<CharacterCard> characterChoice = new ArrayList<CharacterCard>();
        List<WeaponCard> weaponChoice = new ArrayList<WeaponCard>();
        List<RoomCard> roomChoice = new ArrayList<RoomCard>();
        for(CharacterCard c: charCards) {
            if(c.hashCode() != play.hashCode() && !playerCards.contains(c)) {
                characterChoice.add(c);
            }
        }
        for(WeaponCard w: weaponCards) {
            if(!playerCards.contains(w)) {
                weaponChoice.add(w);
            }
        }


        System.out.println("Choose a character (enter number of character to choose):");
        int index = 1;
        for(CharacterCard c : characterChoice) {
            System.out.println(index + ". " + c.getName());
            index++;
        }
        Scanner scan2 = new Scanner(System.in);
        if (scan2.hasNextInt()) {
            sCharacter = characterChoice.get(scan2.nextInt() - 1);
            System.out.println(sCharacter.getName() + " was chosen.");
        }
        else {
            scan2.next();
            System.out.println("Use numbers to choose character.");
            sCharacter = characterChoice.get(scan2.nextInt() - 1);
            System.out.println(sCharacter.getName() + " was chosen.");
        }
        index = 1;
        System.out.println("Choose a weapon (enter number of weapon to choose):");
        for(WeaponCard w : weaponChoice) {
            System.out.println(index + ". " + w.getName());
            index++;
        }
        if (scan2.hasNextInt()) {
            sWeapon = weaponChoice.get(scan2.nextInt() - 1);
            System.out.println(sWeapon.getName() + " was chosen.");
        }
        else {
            scan2.next();
            System.out.println("Use numbers to choose weapon.");
            sWeapon = weaponChoice.get(scan2.nextInt() - 1);
            System.out.println(sWeapon.getName() + " was chosen.");
        }
        index = 1;
        if(sCharacter != null && sWeapon != null && sRoom != null) {
            suggestion = new Suggestion((players.indexOf(play) + 1), play,  sCharacter, sWeapon, sRoom);
            suggestionMade = true;
            System.out.println(suggestion.toString());
            if (!sCharacter.getCharacter().getLocation().equals(sRoom.getRoom().getType())){
                moveCharacterToRoom(sCharacter.getCharacter(), sRoom.getRoom());
            }
            if (!sWeapon.getWeapon().getLocation().equals(sRoom.getRoom())){
                moveWeapon(sWeapon.getWeapon(), sRoom.getRoom());
            }
        }
        else {
            System.out.println("Error with suggestion!");
        }
    }


    /**
     * Produces a list of matching cards from two different lists.
     * @param playerCards       Card being checked
     * @param suggestedCards    Suggestion cards to check against
     * @return
     */
    public List<Card> matches(List<Card> playerCards, List<Card> suggestedCards) {
        List<Card> matchList = new ArrayList<Card>();
        for(Card sc: suggestedCards) {
            for(Card pc : playerCards) {
                if(pc.hashCode() == sc.hashCode()) {
                    matchList.add(pc);
                }
            }
        }
        return matchList;
    }

    /**
     * Checks current player's cards against suggestion and refutes if there is a match
     * @param play          Player to check for refuting
     * @param suggestion    Suggestion that has been made
     */
    public void refuteSuggestion(Character play, Suggestion suggestion) {
        List<Card> playerCards = play.getHand().getCards();
        List<Card> suggestedCards = suggestion.getCards();
        List<Card> matches = matches(playerCards, suggestedCards);
        //only 1 card matches create a refuttal with it
        if(matches.size() == 1) {
            refute = matches.get(0);
            refutePlayer = players.indexOf(play) +1;
            suggestionRefuted = true;
            suggestionMade = false;
            System.out.println(refute.toString());
        }
        //multiple card matches, player chooses which to make a refuttal
        else if(matches.size() > 1) {
            System.out.println("Choose the card to refute with (use card number).");
            int index = 1;
            for(Card mc : matches) {
                System.out.println(index + ".\n" + mc.toString());
                index++;
            }
            Scanner refuteChoice = new Scanner(System.in);
            if(refuteChoice.hasNextInt()) {
                refute = matches.get(refuteChoice.nextInt() - 1);
                refutePlayer = players.indexOf(play) +1;
                suggestionRefuted = true;
                suggestionMade = false;
                System.out.println(play.getName() + " refuted your suggestion with:\n" + refute.toString());
            }
            else {
                System.out.println("Error with choice, please use a number between 1 and " + matches.size());
            }
        }
    }
    
    public void setCurrentChar(Character current) {
    	currentTurn = current;
    }
    
    public Character getCurrentChar() {
    	return currentTurn;
    }

}