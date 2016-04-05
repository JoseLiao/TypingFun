import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
/*
 * 欢迎页面
 * 
 */
@SuppressWarnings("serial")
public class WelcomeFrame extends JFrame implements Runnable {
	Thread t;
	private WelcomePanel welcomePanel;

	public static void main(String[] args) {
		WelcomeFrame init = new WelcomeFrame();
		init.setVisible(true);
	}

	public WelcomeFrame() {
		t = new Thread(this);
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);// 不显示边框

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕尺寸
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/zg.gif"));
		setTitle("TypingFun");
		setSize(443, 115);
		// 窗口居中
		setLocation((screen.width - this.getWidth()) / 2, (screen.height - this.getHeight()) / 2);
		{// 画布
			welcomePanel = new WelcomePanel();
			this.add(welcomePanel);
			welcomePanel.setBackground(Color.WHITE);
		}

		t.start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			new MainFrame();
			this.setVisible(false);
			t.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
