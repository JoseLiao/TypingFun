import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HandSet extends JPanel {
	private ImageIcon imageIcon = new ImageIcon("src/image/hand.png");
	private Image image = imageIcon.getImage();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		String s = new String(GlobalFlag.key);

		g.setColor(Color.BLUE);
		if (s.equalsIgnoreCase("q") || s.equalsIgnoreCase("a") || s.equalsIgnoreCase("z") || s.equals("~")
				|| s.equals("`") || s.equals("!") || s.equals("1"))
			g.fillOval(2, 102, 20, 20);// 1
		else if (s.equalsIgnoreCase("w") || s.equalsIgnoreCase("s") || s.equalsIgnoreCase("x") || s.equals("@")
				|| s.equals("2"))
			g.fillOval(46, 46, 20, 20);// 2
		else if (s.equalsIgnoreCase("e") || s.equalsIgnoreCase("d") || s.equalsIgnoreCase("c") || s.equals("#")
				|| s.equals("3"))
			g.fillOval(84, 23, 20, 20);// 3
		else if (s.equalsIgnoreCase("r") || s.equalsIgnoreCase("f") || s.equalsIgnoreCase("v") || s.equals("$")
				|| s.equals("4"))
			g.fillOval(144, 56, 20, 20);// 4
		else if (s.equalsIgnoreCase("t") || s.equalsIgnoreCase("g") || s.equalsIgnoreCase("b") || s.equals("%")
				|| s.equals("5"))
			g.fillOval(144, 56, 20, 20);// 4
		else if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("h") || s.equalsIgnoreCase("n") || s.equals("^")
				|| s.equals("6"))
			g.fillOval(494, 64, 20, 20);// 7
		else if (s.equalsIgnoreCase("u") || s.equalsIgnoreCase("j") || s.equalsIgnoreCase("m") || s.equals("&")
				|| s.equals("7"))
			g.fillOval(494, 64, 20, 20);// 7
		else if (s.equalsIgnoreCase("i") || s.equalsIgnoreCase("k") || s.equals(",") || s.equals("<") || s.equals("*")
				|| s.equals("8"))
			g.fillOval(545, 22, 20, 20);// 8
		else if (s.equalsIgnoreCase("o") || s.equalsIgnoreCase("l") || s.equals(".") || s.equals(">") || s.equals("(")
				|| s.equals("9"))
			g.fillOval(591, 45, 20, 20);// 9
		else if (s.equalsIgnoreCase("p") || s.equals(";") || s.equals("/") || s.equals(":") || s.equals("?")
				|| s.equals("_") || s.equals("-") || s.equals("=") || s.equals("+") || s.equals("{") || s.equals("}")
				|| s.equals("[") || s.equals("]") || s.equals("\"") || s.equals("'") || s.equals("\\") || s.equals("|"))
			g.fillOval(634, 103, 20, 20);// 10
		else if (s.equals(" ")) {
			g.fillOval(197, 204, 20, 20);// 5
			g.fillOval(432, 207, 20, 20);// 6
		}
		if (s.equals("~") || s.equals("!") || s.equals("Q") || s.equals("A") || s.equals("Z") || s.equals("@")
				|| s.equals("S") || s.equals("X") || s.equals("#") || s.equals("E") || s.equals("D") || s.equals("C")
				|| s.equals("$") || s.equals("R") || s.equals("F") || s.equals("V") || s.equals("%") || s.equals("T")
				|| s.equals("G") || s.equals("B"))
			g.fillOval(634, 103, 20, 20);// 10
		if (s.equals("^") || s.equals("Y") || s.equals("H") || s.equals("N") || s.equals("&") || s.equals("U")
				|| s.equals("J") || s.equals("M") || s.equals("*") || s.equals("I") || s.equals("K") || s.equals("<")
				|| s.equals("(") || s.equals("O") || s.equals("L") || s.equals(">") || s.equals(")") || s.equals("P")
				|| s.equals(":") || s.equals("?") || s.equals("_") || s.equals("{") || s.equals("\"") || s.equals("+")
				|| s.equals("}") || s.equals("|"))
			g.fillOval(2, 102, 20, 20);// 1
		// System.out.println(getWidth() + " " + getHeight());
	}

}
