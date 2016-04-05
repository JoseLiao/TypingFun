import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

@SuppressWarnings("serial")
public class Hint extends JInternalFrame {
	MainFrame parent;
	private Editor edit;
	private KeyBoard keyBoard = new KeyBoard();
	private HandSet handSet = new HandSet();
	private JPanel p = new JPanel();
	
	public Hint(MainFrame parent) {
		this.parent = parent;
		setBounds(0, 153, 0, 0);
		this.setPreferredSize(new Dimension(658, 650 - 153));
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		this.setVisible(true);
		this.pack();
		this.getContentPane().add(p);
		
		p.setLayout(new GridLayout(2, 1));
		if (GlobalFlag.choice == 1){
			p.add(keyBoard);
			p.add(handSet);
		}
		edit = new Editor(this);
		this.parent.add(edit);
		edit.requestFocus();
	}
}