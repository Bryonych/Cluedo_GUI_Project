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
    private static final int WIDTH = 1200;
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
    private static Image Library = new ImageIcon("/Images/Library.png").getImage();
    private static Image start = new ImageIcon("/Images/Start.png").getImage();
    private Game game = null;

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

    public void createBoardArea(){
        GridBagConstraints board = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        JLabel square = new JLabel("s");
        square.setBorder(new LineBorder(Color.BLUE));
        int[] columns = new int[29];
        Arrays.fill(columns, 10);
        gbl.columnWidths = columns;
        int[] rows = new int[29];
        Arrays.fill(rows, 10);
        gbl.rowHeights = rows;

        Cell[][] currentBoard = game.getBoard().getCells();
        board.fill = GridBagConstraints.BOTH;
        add(square, board);
        //Game.createWeapons();
    }

    public void addLabel(String text, GridBagConstraints board){
        JLabel label = new JLabel(text);
        label.setBorder(new LineBorder(Color.BLUE));
        add(label, board);
    }

    public void paint(Graphics g) {
        System.out.println(game.getBoard().toString());
        Cell[][] c = game.getBoard().getCells();
        Board board = new Board(c);
        Cell[][] cc = board.getCells();
        for(int i =0; i< 30; i++) {
            for(int j = 0;j< 30; j++) {
                if(cc[i][j].getType().equals(Cell.Type.HALLWAY)) {
                    g.drawImage(hallway, 50+(i*30), 50+(j*30), 25, 25, null);
                } 
                if(cc[i][j].getType().equals(Cell.Type.WALL)) {
                    g.drawImage(wall, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.BALLROOM)) {
                    g.drawImage(Ballroom, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.KITCHEN)) {
                    g.drawImage(Kitchen, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.STUDY)) {
                    g.drawImage(study, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.CONSERVATORY)) {
                    g.drawImage(conservatory, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.BILLIARD)) {
                    g.drawImage(billard, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.DOOR)) {
                    g.drawImage(door, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.DINING)) {
                    g.drawImage(dinningRoom, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.HALL)) {
                    g.drawImage(Hall, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.LIBRARY)) {
                    g.drawImage(Library, 50+(i*30), 50+(j*30), 25, 25, null);
                }
                if(cc[i][j].getType().equals(Cell.Type.START)) {
                    g.drawImage(start, 50+(i*30), 50+(j*30), 25, 25, null);
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

        game.addObserver(gui);
        SwingUtilities.invokeLater(new Runnable() {
                public void run(){
                    gui.display();
                }
            });

    }
}

