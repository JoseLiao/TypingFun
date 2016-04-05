import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WelcomePanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		g.setColor(new Color(0x2e,0xdf,0xa3));
		FontMetrics fm = g.getFontMetrics();
		
		int stringWidth = fm.stringWidth("Welcome to TypingFun");
		int stringAscent = fm.getAscent();
		
		int xCoordinate = getWidth() / 2 - stringWidth / 2;
		int yCoordinate = getHeight() /2 + stringAscent / 2;
		
		g.drawString("Welcome to TypingFun", xCoordinate, yCoordinate);
		
		g.setFont(new Font("SansSerif", Font.PLAIN, 12));
		g.setColor(Color.BLACK);
		g.drawString("by ljnpng", (getWidth() - 54), getHeight() - 4);
	}
}
