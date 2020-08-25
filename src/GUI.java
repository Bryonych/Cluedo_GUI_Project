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

public class GUI extends JFrame implements Observer,ActionListener, MouseListener, KeyListener{
    //private static List<Character> characters = new ArrayList<Character>();
    private static int numPlayer;
    private ArrayList<Character> players;
    private static final int OFFSET = 50;
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 1020;
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
    
    long now = System.currentTimeMillis();
    long timeCheck;


    public GUI(Game game){
        this.game = game;
        createMenu();

    }

    public void display(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setTitle("Cluedo");
        setSize(WIDTH, HEIGHT);
        createMenu();
        setLocationRelativeTo(null);
        setVisible(true);
        
        addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent e) {
        		timeCheck = System.currentTimeMillis();
        		//only consider mouse events after 500 ms to reduce accidental multiple clicks
        		if((timeCheck - now) > 500) {
        			onClick(e);
        		}
        		now = System.currentTimeMillis();
        	}
        });
        
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
              
        add(buttonPanel(), BorderLayout.LINE_END);
        add(boardPanel); 

    }
    
    JPanel buttonPanel() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));            
        JPanel buttonPanel = new JPanel();       
        buttonPanel.setLayout(new GridLayout(1, 3));
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
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
        		int result = JOptionPane.showConfirmDialog(null, playersPopUp, "Number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        		switch (result) {
        		case JOptionPane.OK_OPTION:
        			int answer = (Integer)combo.getSelectedItem();
        			game.setNumPlayers(answer);
        			playerSelection(answer);
        			break;
        		}
        	}
        });
        newGame.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(newGame);
        container.add(buttonPanel);
        container.setMaximumSize(new Dimension(500, 50));
        return container;  
    }
    
    public void playerSelection(int number) {
    	JRadioButton scarlett = new JRadioButton("Miss Scarlett");
    	JRadioButton mustard = new JRadioButton("Colonel Mustard");
    	JRadioButton white = new JRadioButton("Mrs White");
    	JRadioButton green = new JRadioButton("Mr Green");
    	JRadioButton peacock = new JRadioButton("Mrs Peacock");
    	JRadioButton plum = new JRadioButton("Professor Plum");
    	JPanel characterPopUp = new JPanel();
    	characterPopUp.add(new JLabel("Next player, please select your character\n"));
    	characterPopUp.setLayout(new BoxLayout(characterPopUp, BoxLayout.Y_AXIS));
    	characterPopUp.add(scarlett);
    	characterPopUp.add(mustard);
    	characterPopUp.add(white);
    	characterPopUp.add(green);
    	characterPopUp.add(peacock);
    	characterPopUp.add(plum);
		scarlett.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Miss Scarlet");
				scarlett.setEnabled(false);
				selected = true;
			}
		});
		mustard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Col Mustard");
				mustard.setEnabled(false);
				selected = true;
			}
		});
		white.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Mrs White");
				white.setEnabled(false);
				selected = true;
			}
		});
		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Mr Green");
				green.setEnabled(false);
				selected = true;
			}
		});
		peacock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Mrs Peacock");
				peacock.setEnabled(false);
				selected = true;
			}
		});
		plum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setPlayers("Prof Plum");
				plum.setEnabled(false);
				selected = true;
			}
		});
		
    	for (int i = 0; i < number; i++) {
    		selected = false;
    		JOptionPane.showMessageDialog(null,  characterPopUp);
    		if (!selected) i--;
    	}
    	
    	manageTurns();
    }
    
    public void manageTurns() {
    	game.dealCards();
        while(!game.gameWon) {
        	for (Character play : game.getPlayers()) {
        		JPanel diceRoll = new JPanel();
                diceRoll.add(new JLabel(play.getName() + " press OK to roll the dice"));       
        		JOptionPane.showMessageDialog(null, diceRoll);      			
        		number = game.rollDice();
        		display();
        	}
        }
    }
    

    JPanel dicePanel = new JPanel() {
    	@Override
    	protected void paintComponent(Graphics g) {
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


    public void update(Observable obs, Object obj){
        if (obs instanceof Game){
            display();
        }
        else {
            System.out.println("Error ");
        }
    }

    // private JOptionPane textPane = new JOptionPane();
    // private JButton quit = new JButton();
    //  private JMenuBar menu = new JMenuBar();
    // private Container c = getContentPane();

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

    public void resetGame(){
        this.game = new Game();
    }


    JPanel boardPanel = new JPanel() {
    	@Override
    	protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		Cell[][] c = game.getBoard().getCells();
    		for(int i =0; i< 30; i++) {
    			int y = OFFSET + (i * 30);
    			for(int j = 0;j< 30; j++) {
    				int x = OFFSET + (j * 30);
                    if(c[j][i].getType().equals(Cell.Type.HALLWAY)) {
                        g.drawImage(hallway, y, x, 25, 25, null);
                    } 
                    else if(c[j][i].getType().equals(Cell.Type.WALL)) {
                        g.drawImage(wall, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.BALLROOM)) {
                        g.drawImage(Ballroom, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.KITCHEN)) {
                        g.drawImage(Kitchen, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.STUDY)) {
                        g.drawImage(study, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.CONSERVATORY)) {
                        g.drawImage(conservatory, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.BILLIARD)) {
                        g.drawImage(billard, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.DOOR)) {
                        g.drawImage(door, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.DINING)) {
                        g.drawImage(dinningRoom, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.HALL)) {
                        g.drawImage(Hall, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.LIBRARY)) {
                        g.drawImage(Library, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.START)) {
                        g.drawImage(start, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.LOUNGE)) {
                        g.drawImage(lounge, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.CANDLESTICK)) {
                        g.drawImage(candlestick, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.DAGGER)) {
                        g.drawImage(dagger, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.LEADPIPE)) {
                        g.drawImage(leadpipe, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.REVOLVER)) {
                        g.drawImage(revolver, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.ROPE)) {
                        g.drawImage(rope, y, x, 25, 25, null);
                    }
                    else if(c[j][i].getType().equals(Cell.Type.SPANNER)) {
                        g.drawImage(wrench, y, x, 25, 25, null);
                    }
                    
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
    };
    


    
    public int checkNumPlayers() {
    	return 0;
    }
   
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
        game.setNumPlayers(gui.checkNumPlayers());
        game.addObserver(gui);
        SwingUtilities.invokeLater(new Runnable() {
                public void run(){
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
    	
    }
    
    protected void onKeyPress(KeyEvent k) {
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
    }
}


