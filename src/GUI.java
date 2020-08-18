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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUI extends JFrame implements Observer{

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1000;

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
        //createBoardArea();
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

//    public void createBoardArea(){
//        GridBagConstraints board = new GridBagConstraints();
//        GridBagLayout gbl = new GridBagLayout();
//        setLayout(gbl);
//        JLabel square = new JLabel("s");
//        square.setBorder(new LineBorder(Color.BLUE));
//        int[] columns = new int[29];
//        Arrays.fill(columns, 10);
//        gbl.columnWidths = columns;
//        int[] rows = new int[29];
//        Arrays.fill(rows, 10);
//        gbl.rowHeights = rows;
//
//        //Cell[][] currentBoard = game.getBoard().getCells();
//        board.fill = GridBagConstraints.BOTH;
//        //add(square, board);
//    }

    public void addLabel(String text, GridBagConstraints board){
        JLabel label = new JLabel(text);
        label.setBorder(new LineBorder(Color.BLUE));
        add(label, board);
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


