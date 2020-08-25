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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUI extends JFrame implements Observer,ActionListener, MouseListener, KeyListener{
    //private static List<Character> characters = new ArrayList<Character>();
    private static int numPlayer;
    private ArrayList<Character> players;
    private static final int OFFSET = 50;
    private static final int WIDTH = 1400;
    private static final int HEIGHT = 1000;
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
    private Game game = null;
    
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
      // checknumPlayers();

        // createBoardArea();
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
        
    }

   // /**
     // * Pick number of players to play  
     // */
    // public void checknumPlayers() {
       // Integer[] numOptions = { 2,3,4,5,6};
        // //number of players 
        // numPlayer = (int) JOptionPane.showInputDialog(null, "How many players?", "Cluedo",
            // JOptionPane.QUESTION_MESSAGE, null, numOptions, numOptions[0]);
        // // Create player list
    // }
  //  public void setPlayers() { }

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



    public void paint(Graphics g) {
    	super.paintComponents(g);
        Cell[][] c = game.getBoard().getCells();
        //Board board = new Board(c);
        //Cell[][] cc = board.getCells();
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
                //g.drawImage(sqaure, 50*i, 50*i, 25, 25, null);
            }
        }
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
