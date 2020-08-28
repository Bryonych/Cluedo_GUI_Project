package viewController;
import java.awt.*;
import java.util.*;
import java.util.Observer;
import java.util.Observable;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Model.Card;
import Model.Cell;
import Model.Character;
import Model.CharacterCard;
import Model.Game;
import Model.Cell.Type;

public class GUI extends JFrame implements Observer,ActionListener, MouseListener, KeyListener{
    //private static List<Character> characters = new ArrayList<Character>();
    private static int numPlayer;
    private static boolean boardDisplay = false;
    private ArrayList<Character> players;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int OFFSET = (screenSize.height < 1000) ? 25 : 50;
    private static final int WIDTH = screenSize.width;
    private static final int size = (screenSize.width < 1500) ? 20: 25;
    private static final int space = (screenSize.height-(100+OFFSET))/30;
    private static final int HEIGHT = screenSize.height;
 
    
    private static Image hallway = new ImageIcon("Images/yellowsquare.png").getImage();
    private static Image wall = new ImageIcon("Images/wallimage.png").getImage();
    private static Image Ballroom = new ImageIcon("Images/Ballroom.png").getImage();
    private static Image billard = new ImageIcon("Images/billard.png").getImage();
    private static Image conservatory = new ImageIcon("Images/Conservatory.png").getImage();
    private static Image Kitchen = new ImageIcon("Images/kitchenImage.png").getImage();
    private static Image study = new ImageIcon("Images/Study.png").getImage();
    private static Image Hall = new ImageIcon("Images/Hall.png").getImage();
    private static Image dinningRoom = new ImageIcon("Images/Dinningroom.png").getImage();
    private static Image door = new ImageIcon("Images/doorway.png").getImage();
    private static Image Library = new ImageIcon("Images/Library.png").getImage();
    private static Image lounge = new ImageIcon("Images/lounge.png").getImage();
    private static Image start = new ImageIcon("Images/Start.png").getImage();
    private static Image candlestick = new ImageIcon("Images/candlestick.PNG").getImage();
    private static Image dagger = new ImageIcon("Images/dagger.PNG").getImage();
    private static Image leadpipe = new ImageIcon("Images/leadpipe.PNG").getImage();
    private static Image revolver = new ImageIcon("Images/revolver.PNG").getImage();
    private static Image rope = new ImageIcon("Images/rope.PNG").getImage();
    private static Image wrench = new ImageIcon("Images/wrench.PNG").getImage();
    private static Image one = new ImageIcon("Images/one.PNG").getImage();
    private static Image two = new ImageIcon("Images/two.PNG").getImage();
    private static Image three = new ImageIcon("Images/three.PNG").getImage();
    private static Image four = new ImageIcon("Images/four.PNG").getImage();
    private static Image five = new ImageIcon("Images/five.PNG").getImage();
    private static Image six = new ImageIcon("Images/six.PNG").getImage();
    private Game game = null;
    private boolean selected = false;
    private int number = 3;
    private JPanel dicePanel;
    private JPanel cardPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel handPanel = new JPanel();
    private int playerNumber = 0;

    
    long now = System.currentTimeMillis();
    long timeCheck;

    /**
     * Constructs a GUI
     * @param game	Object controlling the game logic
     */
    public GUI(Game game){
        this.game = game;
        createMenu();

    }
    
    /**
     * Sets up the screen and displays the elements
     */
    public void display(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setTitle("Cluedo");
        setSize(WIDTH, HEIGHT);
        createMenu();
        createDice();
        cardPanel.setPreferredSize(new Dimension(700, 700));
        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(buttonPanel(), BorderLayout.NORTH);

        eastPanel.add(cardPanel, BorderLayout.EAST);
        eastPanel.add(dicePanel, BorderLayout.SOUTH);  
        boardPanel.setPreferredSize(new Dimension(750, 750));
        add(boardPanel);
        add(eastPanel, BorderLayout.EAST);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
          

    }
    
    /**
     * Panel holding the buttons
     * @return	Button panel
     */
    JPanel buttonPanel() {
        JPanel container = new JPanel();            
        JPanel buttonPanel = new JPanel();       
        buttonPanel.setLayout(new GridLayout(1, 3));
        JButton newGame = new JButton("New Game");
        JButton test = new JButton("Test");
        JButton testTwo = new JButton("Test 2");
        newGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		//Pop up with drop down box for selecting the number of players
        		JPanel playersPopUp = new JPanel();
        		playersPopUp.add(new JLabel("How many players?"));
        		DefaultComboBoxModel<Integer> selections = new DefaultComboBoxModel<Integer>();
        		selections.addElement(2);
        		selections.addElement(3);
        		selections.addElement(4);
        		selections.addElement(5);
        		selections.addElement(6);
        		JComboBox<Integer> combo = new JComboBox(selections);
        		playersPopUp.add(combo);
        		//Checks if OK is clicked and sets up the number of players
        		int result = JOptionPane.showConfirmDialog(null, playersPopUp, "Number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        		switch (result) {
        		case JOptionPane.OK_OPTION:
        			int answer = (Integer)combo.getSelectedItem();
        			game.setNumPlayers(answer);
        			playerSelection(answer);
        			break;
        		}
        		boardDisplay = true;
        		display();
        	}
        });
        newGame.setPreferredSize(new Dimension(100, 40));
        container.setPreferredSize(new Dimension(310, 50));
        buttonPanel.add(newGame);
        buttonPanel.add(test);
        buttonPanel.add(testTwo);
        container.add(buttonPanel);
        return container;  
    }
    

    
    /**
     * Draws the initial card pack
     */
    public void drawCardPanel() {
    	
    	cardPanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			//Create a blank card for the back of the pack and displays it
    			Card pack = new CharacterCard("Card Pack", null);
    			CardImage picture = new CardImage(pack, 0, 0);
    			picture.paintComponent(g);
    		}
    	};

    	eastPanel.add(cardPanel, BorderLayout.EAST);
    }
    
    /**
     * Draws the cards in the current player's hand
     * @param player	Current player
     */
    public void drawHand(Character player) {
    	eastPanel.remove(cardPanel);
    	int total = player.getHand().getCards().size();
    	cardPanel = new JPanel() {

    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			int yCount = 0;
    			int xCount = 0;
    			for (int i = 0; i < total; i++) {
    				CardImage current = (new CardImage(player.getHand().getCards().get(i), (i-xCount)*155, (yCount*205)+30));
    				current.paintComponent(g);
    				if (i == 3 || i == 7) {
    					xCount += 4;
    					yCount++;
    				}
    			}
    		}
    	};
    	
    	cardPanel.add(new JLabel(player.getName() + "'s Hand:"));
    	display();
    }
       
    /**
     * Creates pop up for selecting the character for each player
     * @param numPlay	Number of players
     */
    public void playerSelection(int numPlay) {
    	JRadioButton scarlett = new JRadioButton("Miss Scarlett");
    	JRadioButton mustard = new JRadioButton("Colonel Mustard");
    	JRadioButton white = new JRadioButton("Mrs White");
    	JRadioButton green = new JRadioButton("Mr Green");
    	JRadioButton peacock = new JRadioButton("Mrs Peacock");
    	JRadioButton plum = new JRadioButton("Professor Plum");
    	JPanel characterPopUp = new JPanel();
    	game.resetPlayers();
    	characterPopUp.add(new JLabel("Next player, please select your character\n"));
    	characterPopUp.setLayout(new BoxLayout(characterPopUp, BoxLayout.Y_AXIS));
    	characterPopUp.add(scarlett);
    	characterPopUp.add(mustard);
    	characterPopUp.add(white);
    	characterPopUp.add(green);
    	characterPopUp.add(peacock);
    	characterPopUp.add(plum);
    	ButtonGroup bg = new ButtonGroup();
    	bg.add(scarlett);
    	bg.add(mustard);
    	bg.add(white);
    	bg.add(green);
    	bg.add(peacock);
    	bg.add(plum);
    	//Sets the players' characters according to what is selected
    	
		scarlett.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Miss Scarlet");
				selected = true;
			}
		});
		mustard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Col Mustard");
				selected = true;
			}
		});
		white.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Mrs White");
				selected = true;
			}
		});
		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Mr Green");
				selected = true;
			}
		});
		peacock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Mrs Peacock");
				selected = true;
			}
		});
		plum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Prof Plum");
				selected = true;
			}
		});
		
		//Displays the pop up until each player has selected a character
    	for (int i = 0; i < numPlay; i++) {
    		selected = false;
    		JOptionPane.showMessageDialog(null,  characterPopUp);
    		if (!selected) {
    			i--;
    		}
    		else if (bg.getSelection() != null) {
    			bg.getSelection().setEnabled(false);
    		}
    	}
    	
    	manageTurns();
    }
    
    /**
     * Runs through the turns for each player, from rolling the dice to moving, to making a suggestion and accusation
     */
    public void manageTurns() {
    	game.dealCards();
    	Character play = game.getPlayers().get(playerNumber);
    	drawHand(play);
    	JPanel diceRoll = new JPanel();
    	diceRoll.add(new JLabel(play.getName() + " press OK to roll the dice"));     
    	JOptionPane.showMessageDialog(null, diceRoll);      			
    	number = game.rollDice();
    	createDice();
    	display();
    	game.setCurrentChar(play);
    	readInput();

    }
    
    public void readInput() {
    	setFocusable(true);
        addKeyListener(new KeyAdapter() {
        	public void keyReleased(KeyEvent k) {
        		timeCheck = System.currentTimeMillis();
        		//only consider mouse events after 200 ms to reduce accidental multiple key presses
        			if((timeCheck - now) > 200) {
        				onKeyPress(k);
        			}
        			now = System.currentTimeMillis();
        	}
        });
        
        addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent e) {
        		timeCheck = System.currentTimeMillis();
        		//only consider mouse events after 300 ms to reduce accidental multiple clicks
        		if((timeCheck - now) > 300) {
        			onClick(e);
        		}
        		now = System.currentTimeMillis();
        	}
        });
        
   
        
    }
    
    /**
     * Creates the dice panel
     */
    public void createDice() {
    	dicePanel = new JPanel() {
    		@Override
    		protected void paintComponent(Graphics g) {
    			super.paintComponent(g);
    			if (number == 2) {
    				g.drawImage(one, 0, 0, 100, 100, null);
    				g.drawImage(one, 110, 0, 100, 100, null);
    			}
    			else if (number == 3) {
    				g.drawImage(one, 0, 0, 100, 100, null);
    				g.drawImage(two, 110, 0, 100, 100, null);
    			}
    			else if (number == 4) {
    				g.drawImage(two, 0, 0, 100, 100, null);
    				g.drawImage(two, 110, 0, 100, 100, null);
    			}
    			else if (number == 5) {
    				g.drawImage(two, 0, 0, 100, 100, null);
    				g.drawImage(three, 110, 0, 100, 100, null);
    			}
    			else if (number == 6) {
    				g.drawImage(four, 0, 0, 100, 100, null);
    				g.drawImage(two, 110, 0, 100, 100, null);
    			}
    			else if (number == 7) {
    				g.drawImage(six, 0, 0, 100, 100, null);
    				g.drawImage(one, 110, 0, 100, 100, null);
    			}
    			else if (number == 8) {
    				g.drawImage(three, 0, 0, 100, 100, null);
    				g.drawImage(five, 110, 0, 100, 100, null);
    			}
    			else if (number == 9) {
    				g.drawImage(four, 0, 0, 100, 100, null);
    				g.drawImage(five, 110, 0, 100, 100, null);
    			}
    			else if (number == 10) {
    				g.drawImage(five, 0, 0, 100, 100, null);
    				g.drawImage(five, 110, 0, 100, 100, null);
    			}
    			else if (number == 11) {
    				g.drawImage(six, 0, 0, 100, 100, null);
    				g.drawImage(five, 110, 0, 100, 100, null);
    			}
    			else if (number == 12) {
    				g.drawImage(six, 0, 0, 100, 100, null);
    				g.drawImage(six, 110, 0, 100, 100, null);
    			}
    		}
    	};
    	dicePanel.setPreferredSize(new Dimension(210, 110));
    }

    /**
     * Updates on broadcast from the Game class
     */
    public void update(Observable obs, Object obj){
        if (obs instanceof Game){
        	display();
        }
        else {
            System.out.println("Error on model update");
        }
    }

    /**
     * Creates the drop down menus at the top of the screen
     */
    public void createMenu(){
        setVisible(true);       
        JMenuBar menuBar = new JMenuBar();     
        //panel for menu
        JMenu turn = new JMenu("Who's Turn");
        JMenu suggest = new JMenu("Suggestion");
        JMenu accuse = new JMenu("Accusation");
        JMenu reset = new JMenu("Reset");
        //panel for submenu
        JMenuItem subTurn = new JMenuItem("Next Player");
        JMenuItem subSuggest = new JMenuItem("Make Suggestion");
        JMenuItem subAccuse = new JMenuItem("Make Accusation");
        JMenuItem reStart = new JMenuItem("Restart");       
        //action onMouse
        reStart.addActionListener(this);
        subAccuse.addActionListener(this);
        subSuggest.addActionListener(this);
        subTurn.addActionListener(this);
        //add submenu to menu 
        accuse.add(subAccuse);
        turn.add(subTurn);
        suggest.add(subSuggest);
        reset.add(reStart);        
        //adding menu to  menubar
        menuBar.add(turn);
        menuBar.add(accuse);
        menuBar.add(suggest);
        menuBar.add(reset);

        this.setJMenuBar(menuBar);

    }

    /**
     * Resets the game if selected from the drop down menu
     */
    public void resetGame(){
        this.game = new Game();
    }

    
   /**
    * Creates the panel displaying the game board
    */
    JPanel boardPanel = new JPanel() {
    	@Override
    	protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		if(boardDisplay) {
    			Cell[][] c = game.getBoard().getCells();
    			for(int i =0; i< 30; i++) {
    				int y = OFFSET + (i * space);
    				for(int j = 0;j< 30; j++) {
    					int x = OFFSET + (j * space);  				
    					if(c[j][i].getType().equals(Cell.Type.HALLWAY)) {
    						g.drawImage(hallway, y, x, size, size, null);
    						g.setColor(Color.BLACK);
    						g.drawRect(y, x, size, size);
    					} 
    					else if(c[j][i].getType().equals(Cell.Type.WALL)) {
    						g.drawImage(wall, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.BALLROOM)) {
    						g.drawImage(Ballroom, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.KITCHEN)) {
    						g.drawImage(Kitchen, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.STUDY)) {
    						g.drawImage(study, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.CONSERVATORY)) {
    						g.drawImage(conservatory, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.BILLIARD)) {
    						g.drawImage(billard, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.DOOR)) {
    						g.drawImage(door, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.DINING)) {
    						g.drawImage(dinningRoom, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.HALL)) {
    						g.drawImage(Hall, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.LIBRARY)) {
    						g.drawImage(Library, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.START)) {
    						g.drawImage(start, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.LOUNGE)) {
    						g.drawImage(lounge, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.CANDLESTICK)) {
    						g.drawImage(candlestick, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.DAGGER)) {
    						g.drawImage(dagger, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.LEADPIPE)) {
    						g.drawImage(leadpipe, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.REVOLVER)) {
    						g.drawImage(revolver, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.ROPE)) {
    						g.drawImage(rope, y, x, size, size, null);
    					}
    					else if(c[j][i].getType().equals(Cell.Type.SPANNER)) {
    						g.drawImage(wrench, y, x, size, size, null);
    					}
    					//Draws the characters in their initial places
    					if(c[i][j].getType().equals(Cell.Type.WHITE)) {

    						//System.out.println("x = " + x + ", y = " + y);
    						g.setColor(Color.WHITE);
    						g.fillOval(x,y,25,25);
    						g.setColor(Color.BLACK);
    						g.drawOval(x,y,25,25);
    					}
    					else if(c[i][j].getType().equals(Cell.Type.GREEN)) {
    						g.setColor(Color.GREEN);
    						g.fillOval(x,y,25,25);
    					}
    					else if(c[i][j].getType().equals(Cell.Type.PEACOCK)) {
    						g.setColor(Color.BLUE);
    						g.fillOval(x,y,25,25);
    					}
    					else if(c[i][j].getType().equals(Cell.Type.PLUM)) {
    						g.setColor(Color.MAGENTA);
    						g.fillOval(x,y,25,25);
    					}
    					else if(c[i][j].getType().equals(Cell.Type.SCARLETT)) {
    						g.setColor(Color.RED);
    						g.fillOval(x,y,25,25);
    					}
    					else if(c[i][j].getType().equals(Cell.Type.MUSTARD)) {
    						g.setColor(Color.YELLOW);
    						g.fillOval(x,y,25,25);
    						g.setColor(Color.BLACK);
    						g.drawOval(x,y,25,25);
    					}

    				}
    			}
    		}
    	}
    };



   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Make Accusation")) {       
            // Game.accusationMade();
            JOptionPane.showMessageDialog(this, " SORRY !! You cannot make an accusation now");
        }
        if (e.getActionCommand().equals("Make Suggestion")) {
            // Game.makeSuggestionMade();
            JOptionPane.showMessageDialog(this, "SORRY !! You cannot make a suggestion now");
        }
        if (e.getActionCommand().equals("Next Player")) {
            //GameOn();
        }
        if (e.getActionCommand().equals("Restart")) {
            JOptionPane.showMessageDialog(this, "Reset loading ....");
            resetGame();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Moving the cell
     * 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // movePlayer from cell to cell

    }

    /**
     * key events 
     * 
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //while(!game.gameWon) {}
    }

    public static void main(String[] args){
        Game game = new Game();
        GUI gui = new GUI(game);
        //game.setNumPlayers(gui.checkNumPlayers());
        game.addObserver(gui);
        SwingUtilities.invokeLater(new Runnable() {
                public void run(){
                	gui.drawCardPanel();
                	gui.display();
                }
            });
    }
    
    protected void  onClick(MouseEvent e) {
    	Cell destination = null;
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	int indX = (mouseX - OFFSET)/30;
    	int indY = (mouseY - OFFSET)/30;
    	if(mouseX > OFFSET && mouseY > OFFSET && mouseX < 945 && mouseY < 945) {
    		Cell[][] cc = game.getBoard().getCells();
    		destination = cc[indY][indX];
    		game.moveCharacter(game.getCurrentChar(), destination);
    	}
    	if(game.countSteps()) {
    		game.setCurrentChar(null);
    		if (playerNumber == game.getPlayers().size()-1) {
    			playerNumber = 0;
    			manageTurns();
    		}
    		else {
    			playerNumber ++;
    			manageTurns();
    		}
    	}
    	
    }
    
    protected void onKeyPress(KeyEvent k) {
    	if (game.getCurrentChar() == null) {
    		System.out.println("Not your turn yet");
    		return;
    	}
    	Cell currentCell = game.getCurrentChar().getLocation();
    	if(k.getKeyCode() == KeyEvent.VK_W) {
    		game.moveCharacter(game.getCurrentChar(), game.getBoard().getCells()[currentCell.getYPos() - 1][currentCell.getXPos()]);
    	}
    	else if(k.getKeyCode() == KeyEvent.VK_A) {
    		game.moveCharacter(game.getCurrentChar(), game.getBoard().getCells()[currentCell.getYPos()][currentCell.getXPos() - 1]);
    	}
    	else if(k.getKeyCode() == KeyEvent.VK_S) {
    		game.moveCharacter(game.getCurrentChar(), game.getBoard().getCells()[currentCell.getYPos() + 1][currentCell.getXPos()]);
    	}
    	else if(k.getKeyCode() == KeyEvent.VK_D) {
    		game.moveCharacter(game.getCurrentChar(), game.getBoard().getCells()[currentCell.getYPos()][currentCell.getXPos() + 1]);
    	}
    	if(game.countSteps()) {
    		game.setCurrentChar(null);
    		if (playerNumber == game.getPlayers().size()-1) {
    			playerNumber = 0;
    			manageTurns();
    		}
    		else {
    			playerNumber ++;
    			manageTurns();
    		}
    	}
    }
}


