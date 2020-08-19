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

public class GUI extends JFrame implements Observer{

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1000;
    private static Image hallway = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\yellowsquare.png").getImage();
    private static Image wall = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\wallimage.png").getImage();
    private static Image Ballroom = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\Ballroom.png").getImage();
    private static Image billard = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\billard.png").getImage();
    private static Image conservatory = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\Conservatory.png").getImage();
    private static Image Kitchen = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\kitchenImage.png").getImage();
    private static Image study = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\Study.png").getImage();
    private static Image Hall = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\Hall.png").getImage();
    private static Image dinningRoom = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\Dinningroom.png").getImage();
    private static Image door = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\doorway.png").getImage();
    private static Image Library = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\Library.png").getImage();
    private static Image start = new ImageIcon("C:\\Users\\Jorda\\Desktop\\Esclipse_Files\\Cludeo_GUI\\src\\Images\\Start.png").getImage();
    private Game game = null;

    public GUI(Game game){
        this.game = game;

    }
   
    	
    	
    

    public void display(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setTitle("Cluedo");
        setSize(WIDTH, HEIGHT);
        createMenu();
        
        
       // createBoardArea();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void update(Observable obs, Object obj){
        if (obs instanceof Game){
            display();
        }
        else {
            System.out.println("Error ");
        }
    }

    private JOptionPane textPane = new JOptionPane();
    private JButton quit = new JButton();
    private JMenuBar menu = new JMenuBar();
    private Container c = getContentPane();

    public void createMenu(){
        final JMenu mainMenu = new JMenu("Menu");

        final JMenuItem reset = new JMenuItem("Reset");

        menu.add(mainMenu);
        mainMenu.add(reset);

        setJMenuBar(menu);

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                resetGame();
            }
        });
        c.add(menu, BorderLayout.NORTH);
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


