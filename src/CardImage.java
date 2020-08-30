package viewController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


import Model.Card;

/**
 * Draws the image of a card
 *
 */
public class CardImage extends JComponent {

	private static final long serialVersionUID = 1L;
	private Card card;
    private static Image Ballroom = new ImageIcon("src//Images/Ballroom.png").getImage();
    private static Image billard = new ImageIcon("src//Images/billard.png").getImage();
    private static Image conservatory = new ImageIcon("src//Images/Conservatory.png").getImage();
    private static Image Kitchen = new ImageIcon("src//Images/kitchenImage.png").getImage();
    private static Image study = new ImageIcon("src//Images/Study.png").getImage();
    private static Image Hall = new ImageIcon("src//Images/Hall.png").getImage();
    private static Image dinningRoom = new ImageIcon("src//Images/Dinningroom.png").getImage();
    private static Image Library = new ImageIcon("src//Images/Library.png").getImage();
    private static Image lounge = new ImageIcon("src//Images/lounge.png").getImage();
    private static Image candlestick = new ImageIcon("src//Images/candlestick.PNG").getImage();
    private static Image dagger = new ImageIcon("src//Images/dagger.PNG").getImage();
    private static Image leadpipe = new ImageIcon("src//Images/leadpipe.PNG").getImage();
    private static Image revolver = new ImageIcon("src//Images/revolver.PNG").getImage();
    private static Image rope = new ImageIcon("src//Images/rope.PNG").getImage();
    private static Image wrench = new ImageIcon("src//Images/wrench.PNG").getImage();

    private int xPos;
    private int yPos;
    private int imageX = 10;
    private int imageY = 60;
    private int width = 130;
    private int height = 130;
    private int[] xPoints = { 75, 10, 140 };
	private int[] yPoints = { 100, 190, 190 };
	private String name;
	
	/**
	 * Constructor
	 * @param drawCard	Card being drawn
	 * @param x			X Position of card
	 * @param y			Y Position of card
	 */
	CardImage(Card drawCard, int x, int y){
		this.card = drawCard;
		this.name = card.getName();
		this.xPos = x;
		this.yPos = y;
		setPolygonPoints();
	}
	
	/**
	 * Sets the points for the triangle (base) part of a character image
	 */
	public void setPolygonPoints() {
		for (int i = 0; i < 3; i++) {
			xPoints[i]+=xPos;
			yPoints[i]+=yPos;
		}
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		add(new JLabel(card.getName()));
		g.setColor(Color.WHITE);
		g.fillRect(xPos, yPos, 150, 200);
		g.setColor(Color.BLACK);
		g.drawRect(xPos, yPos, 150, 200);	
		int letters = name.length();
		int xWord = 75-(letters*3);
		g.setFont(new Font("Dialog", Font.BOLD, 12));
		g.drawString(name, xWord+xPos, 40+yPos);
		switch (card.getName()) {
		case "Card Pack":
			break;
		case "Kitchen":
			g.drawImage(Kitchen, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Ball Room":
			g.drawImage(Ballroom, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Conservatory":
			g.drawImage(conservatory, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Dining Room":
			g.drawImage(dinningRoom, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Billiard Room":
			g.drawImage(billard, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Library":
			g.drawImage(Library, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Lounge":
			g.drawImage(lounge, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Hall":
			g.drawImage(Hall, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Study":
			g.drawImage(study, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Candlestick":
			g.drawImage(candlestick, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Dagger":
			g.drawImage(dagger, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Lead Pipe":
			g.drawImage(leadpipe, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Revolver":
			g.drawImage(revolver, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Rope":
			g.drawImage(rope, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Spanner":
			g.drawImage(wrench, imageX+xPos, imageY+yPos, width, height, null);
			break;
		case "Mrs White":		
			g.setColor(Color.BLACK);
			g.drawOval(37+xPos, 63+yPos, 75, 75);			
			g.drawPolygon(xPoints, yPoints, 3);
			g.setColor(Color.WHITE);
			g.fillOval(38+xPos, 64+yPos, 73, 73);
			int[] innerX = { xPoints[0], xPoints[1]+1, xPoints[2]-1 };
			int[] innerY = { yPoints[0]+1, yPoints[1]-1, yPoints[2]-1 };
			g.fillPolygon(innerX, innerY, 3);			
			break;
		case "Mr Green":
			g.setColor(Color.GREEN);
			g.fillOval(37+xPos, 63+yPos, 75, 75);
			g.fillPolygon(xPoints, yPoints, 3);
			break;
		case "Mrs Peacock":
			g.setColor(Color.BLUE);
			g.fillOval(37+xPos, 63+yPos, 75, 75);
			g.fillPolygon(xPoints, yPoints, 3);
			break;
		case "Prof Plum":
			g.setColor(Color.MAGENTA);
			g.fillOval(37+xPos, 63+yPos, 75, 75);
			g.fillPolygon(xPoints, yPoints, 3);
			break;
		case "Miss Scarlet":
			g.setColor(Color.RED);
			g.fillOval(37+xPos, 63+yPos, 75, 75);
			g.fillPolygon(xPoints, yPoints, 3);
			break;
		case "Col Mustard":
			g.setColor(Color.YELLOW);
			g.fillOval(37+xPos, 63+yPos, 75, 75);
			g.fillPolygon(xPoints, yPoints, 3);
			break;
		}
	}

}
