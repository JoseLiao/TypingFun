import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class MainFrame extends JDesktopPane implements ActionListener {
	private JMenuBar fMenu;
	private JMenu fMenuSys;
	private JMenu fMenuPractise;
	private JMenu fMenuHelp;
	private JMenuItem fMenuSoft;
	private JMenuItem fMenuAbout;
	private JMenuItem[] fMenuArticle = new JMenuItem[6];
	private JMenuItem[] fMenuKey = new JMenuItem[5];
	private JMenuItem fMenuExit;

	JFrame frame;
	private JMenu fMenuPractise1;
	private JFileChooser chooser;
	
	private int x = 658;
	private int y = 650;

	public MainFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(x, y));//y = 550
		frame.setTitle("TypingFun");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/zg.gif"));
		frame.setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screen.width - x) / 2, (screen.height - y - 50) / 2);// ���ڶ�λ
		frame.pack();
		frame.setVisible(true);

		// ��Ӳ˵�
		fMenu = new JMenuBar();
		frame.setJMenuBar(fMenu);
		fMenu.setPreferredSize(new Dimension(392, 23));
		{
			fMenuSys = new JMenu();
			fMenu.add(fMenuSys);
			fMenuSys.setText("ϵͳ(S)");
			fMenuSys.setFont(new Font("SansSerif", Font.PLAIN, 12));
			fMenuSys.setMnemonic(KeyEvent.VK_S);
			fMenuSys.setRolloverEnabled(true);
			{
				fMenuExit = new JMenuItem();
				fMenuSys.add(fMenuExit);
				fMenuExit.setText("�˳�");
				fMenuExit.addActionListener(this);
				fMenuExit.setFont(new Font("SansSerif", Font.PLAIN, 12));
				fMenuExit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("image/leave.gif")));
			}
		}
		{
			fMenuPractise = new JMenu();
			fMenu.add(fMenuPractise);
			fMenuPractise.setText("������ϰ(E)");
			fMenuPractise.setFont(new Font("SansSerif", Font.PLAIN, 12));
			fMenuPractise.setMnemonic(KeyEvent.VK_E);
			fMenuPractise.setRolloverEnabled(true);
			{

				for (int i = 0; i < 5; i++) {
					fMenuKey[i] = new JMenuItem();
					fMenuPractise.add(fMenuKey[i]);
					fMenuKey[i].setText("English_" + (i + 1));
					fMenuKey[i].addActionListener(this);
					fMenuKey[i].setFont(new Font("SansSerif", Font.PLAIN, 12));
				}
			}
		}
		{
			fMenuPractise1 = new JMenu();
			fMenu.add(fMenuPractise1);
			fMenuPractise1.setText("������ϰ(A)");
			fMenuPractise1.setFont(new Font("SansSerif", Font.PLAIN, 12));
			fMenuPractise1.setMnemonic(KeyEvent.VK_A);
			fMenuPractise1.setRolloverEnabled(true);
			{
				for (int i = 0; i < 5; i++) {
					fMenuArticle[i] = new JMenuItem();
					fMenuPractise1.add(fMenuArticle[i]);
					fMenuArticle[i].setText("Chinese_" + (i + 1));
					fMenuArticle[i].addActionListener(this);
					fMenuArticle[i].setFont(new Font("SansSerif", Font.PLAIN, 12));
				}
				{
					fMenuArticle[5] = new JMenuItem();
					fMenuPractise1.add(fMenuArticle[5]);
					fMenuArticle[5].setText("����...");
					fMenuArticle[5].addActionListener(this);
					fMenuArticle[5].setFont(new Font("SansSerif", Font.PLAIN, 12));
				}
			}
		}
		{
			fMenuHelp = new JMenu();
			fMenu.add(fMenuHelp);
			fMenuHelp.setText("����(H)");
			fMenuHelp.setFont(new Font("SansSerif", Font.PLAIN, 12));
			fMenuHelp.setMnemonic(KeyEvent.VK_H);
			fMenuHelp.setRolloverEnabled(true);
			{
				fMenuSoft = new JMenuItem();
				fMenuHelp.add(fMenuSoft);
				fMenuSoft.setText("����");
				fMenuSoft.addActionListener(this);
				fMenuSoft.setFont(new Font("SansSerif", Font.PLAIN, 12));
				fMenuSoft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/h.gif")));
			}

			{
				fMenuAbout = new JMenuItem();
				fMenuHelp.add(fMenuAbout);
				fMenuAbout.setText("��������");
				fMenuAbout.addActionListener(this);
				fMenuAbout.setFont(new Font("SansSerif", Font.PLAIN, 12));
				fMenuAbout.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/about.gif")));
			}
		}
	}

	// ʵ��ActionListener�ӿڵķ���
	public void actionPerformed(ActionEvent e) {
		if (GlobalFlag.onChoice == true) {
			if (e.getSource() == fMenuExit) {
				int i = JOptionPane.showConfirmDialog(this, "ȷ���˳���", "TypingFun", JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (i == 0) {
					System.exit(0);
				}
			}
			for (int i = 0; i < 5; i++)
				if (e.getSource() == fMenuKey[i]) {
					GlobalFlag.onChoice = false;
					GlobalFlag.text = "src/Text/English_" + (i + 1) + ".txt";
					GlobalFlag.choice = 1;
					showFrame();
				}
			for (int i = 0; i < 5; i++)
				if (e.getSource() == fMenuArticle[i]) {
					GlobalFlag.onChoice = false;
					GlobalFlag.text = "src/Text/Chinese_" + (i + 1) + ".txt";
					GlobalFlag.choice = 2;
					showFrame();
				}
			if (e.getSource() == fMenuArticle[5]) {
				GlobalFlag.onChoice = false;
				GlobalFlag.choice = 2;
				chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("�ı��ĵ�(*.txt)", "txt"));
				int choosed = chooser.showOpenDialog(null);
				if (choosed == JFileChooser.APPROVE_OPTION)
					GlobalFlag.text = chooser.getSelectedFile().getAbsolutePath();
				showFrame();
			}

			if (e.getSource() == fMenuAbout) {

			}
			if (e.getSource() == fMenuSoft) {

			}

		}
	}

	private void showFrame() {
		Hint hint = new Hint(this);
		this.add(hint);
	}
}