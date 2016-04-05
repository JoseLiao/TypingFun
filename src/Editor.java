import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class Editor extends JInternalFrame implements DocumentListener, Runnable {

	Runtime r = Runtime.getRuntime();
	Hint parent;

	Document doc;
	StyledDocument styledDoc = new DefaultStyledDocument();
	Thread thread;

	private int time = 0;
	private int hour = 0;// 时
	private int min = 0;// 分
	private int sec = 0;// 秒
	private int limitTime = Integer.MAX_VALUE;

	private JLabel lblTime;
	private JTextField textCount;
	private JLabel lblError;
	private JTextPane textOld;
	private JTextPane textNew;
	private JPanel pShowData;
	private JButton btnClose;
	private JLabel lblWord;
	private JTextField textSpeed;
	private JLabel lblSpeed;
	private JTextField textError;
	private JTextField textRight;
	private JLabel lblRight;
	private JLabel lblCount;
	private JTextField textTime;
	private JPanel pAttrib;
	// private JLabel lblLimited;
	private JComboBox<String> jcboLimited;

	// 记录输入的正确和错误的个数据
	private int rightWord = 0;
	private int errorWord = 0;
	private int rightCount = 0;
	private int errorCount = 0;
	private int wordCount = 0;

	public Editor(Hint parent) {
		this.parent = parent;

		thread = new Thread(this);

		// 组建Editor用户界面
		this.setPreferredSize(new Dimension(658, 153));// 内部窗体大小658 325
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		{
			pAttrib = new JPanel();
			this.getContentPane().add(pAttrib, BorderLayout.NORTH);
			pAttrib.setBorder(BorderFactory.createTitledBorder(""));
			pAttrib.setLayout(new FlowLayout());
			pAttrib.setBounds(-2, 0, 658, 38);
			{
				btnClose = new JButton();
				pAttrib.add(btnClose);
				btnClose.setText("返回");
				btnClose.setBackground(Color.WHITE);
				btnClose.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/leave.gif")));
				btnClose.setFont(new Font("新宋体", 0, 12));
				btnClose.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						exitFrame();
					}
				});
			}
			{
				lblTime = new JLabel("时间:");
				pAttrib.add(lblTime);
				lblTime.setFont(new Font("新宋体", Font.PLAIN, 12));
			}
			{
				textTime = new JTextField(5);
				pAttrib.add(textTime);
				textTime.setText("00:00:00");
				textTime.setForeground(new Color(0x2e, 0xdf, 0xa3));
				textTime.setEditable(false);
				textTime.setHorizontalAlignment(JTextField.CENTER);
			}
			{
				lblCount = new JLabel("总字数");
				pAttrib.add(lblCount);
				lblCount.setFont(new Font("新宋体", Font.PLAIN, 12));
			}
			{
				textCount = new JTextField(4);
				pAttrib.add(textCount);
				textCount.setText("0");
				textCount.setEditable(false);
				textCount.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblRight = new JLabel();
				pAttrib.add(lblRight);
				lblRight.setText("正确:");
				lblRight.setFont(new Font("新宋体", 0, 12));
			}
			{
				textRight = new JTextField();
				pAttrib.add(textRight);
				textRight.setColumns(3);
				textRight.setText("0");
				textRight.setEditable(false);
				textRight.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblError = new JLabel();
				pAttrib.add(lblError);
				lblError.setText("错误:");
				lblError.setFont(new Font("新宋体", 0, 12));
			}
			{
				textError = new JTextField();
				pAttrib.add(textError);
				textError.setColumns(3);
				textError.setText("0");
				textError.setEditable(false);
				textError.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblSpeed = new JLabel();
				pAttrib.add(lblSpeed);
				lblSpeed.setText("速度:");
				lblSpeed.setFont(new Font("新宋体", 0, 12));
			}
			{
				textSpeed = new JTextField();
				pAttrib.add(textSpeed);
				textSpeed.setColumns(3);
				textSpeed.setText("0");
				textSpeed.setEditable(false);
				textSpeed.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				lblWord = new JLabel();
				pAttrib.add(lblWord);
				lblWord.setText("字/分");
				lblWord.setFont(new Font("新宋体", 0, 12));
			}
			{
				jcboLimited = new JComboBox<String>();
				pAttrib.add(jcboLimited);
				jcboLimited.setToolTipText("限时");
				jcboLimited.setFont(new Font("新宋体", 0, 12));
				jcboLimited.addItem("15秒");
				jcboLimited.addItem("5分钟");
				jcboLimited.addItem("10分钟");
				jcboLimited.addItem("不限时");
				jcboLimited.setSelectedItem("不限时");
				jcboLimited.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (jcboLimited.getSelectedIndex() == 0) {
							limitTime = 15;
						} else if (jcboLimited.getSelectedIndex() == 1) {
							limitTime = 5 * 60;
						} else if (jcboLimited.getSelectedIndex() == 2) {
							limitTime = 10 * 60;
						}
					}
				});
			}
		}
		{
			pShowData = new JPanel();
			this.getContentPane().add(pShowData, BorderLayout.CENTER);
			pShowData.setLayout(new GridLayout(2, 1, 5, 5));
			pShowData.setBounds(5, 38, 658, 110);
			{
				textOld = new JTextPane(styledDoc);
				pShowData.add(textOld);
				textOld.setPreferredSize(new Dimension(653, 50));
				textOld.setEditable(false);
			}
			{
				textNew = new JTextPane();
				pShowData.add(textNew);
				textNew.setPreferredSize(new Dimension(653, 50));
				Document doc = textNew.getDocument();
				doc.addDocumentListener(this);

			}
		}
		{
			textOld.setText(InputText.getData(GlobalFlag.text, GlobalFlag.textTag));
			Empty empty = new Empty();
			empty.start();
			if (textOld.getText().length() != 0) {
				if (GlobalFlag.choice == 1) {
					GlobalFlag.key = textOld.getText().substring(0, 1);
				} else if (GlobalFlag.choice == 2) {
					GlobalFlag.key = "";
				}
				this.parent.repaint();
			}
		}
		this.pack();
		textNew.requestFocus();
		thread.start();
	}

	// 判断正确和错误的字.并改变它们的颜色
	private void chDocs(String srcStr, String dstStr) {
		SimpleAttributeSet smpAtrSet = new SimpleAttributeSet();
		rightWord = 0;
		errorWord = 0;
		for (int i = 1; i <= srcStr.length(); i++) {
			if (i <= dstStr.length()) {// 数据验证
				if (srcStr.substring(i - 1, i).equals(dstStr.substring(i - 1, i))) {
					rightWord++;
					StyleConstants.setForeground(smpAtrSet, Color.BLUE);
				} else {
					errorWord++;
					StyleConstants.setForeground(smpAtrSet, Color.RED);
				}
				StyleConstants.setUnderline(smpAtrSet, true);
			} else {
				StyleConstants.setForeground(smpAtrSet, Color.BLACK);
				StyleConstants.setUnderline(smpAtrSet, false);
			}
			styledDoc.setCharacterAttributes(i - 1, 1, smpAtrSet, true);
		}

		textRight.setText((rightWord + rightCount) + "");
		textError.setText((errorWord + errorCount) + "");
		double s = (min * 60 + sec) / 60.0;
		textSpeed.setText("" + Math.round((Integer.parseInt(textCount.getText())) / s));
	}

	// 实现DocumentListener所有的方法..用于监听输入文本...
	public void changedUpdate(DocumentEvent e) {
		if (GlobalFlag.choice == 1) {
			GlobalFlag.key = textOld.getText().substring(textNew.getText().length(), textNew.getText().length() + 1);
		} else if (GlobalFlag.choice == 2) {
			GlobalFlag.key = "";
		}
		this.parent.repaint();
	}

	public void insertUpdate(DocumentEvent e) {
		if (GlobalFlag.choice == 1) {
			try {
				GlobalFlag.key = textOld.getText().substring(textNew.getText().length(),
						textNew.getText().length() + 1);
			} catch (StringIndexOutOfBoundsException str) {
				// str.printStackTrace();
			}
		} else if (GlobalFlag.choice == 2) {
			GlobalFlag.key = "";
		}
		this.parent.repaint();
	}

	public void removeUpdate(DocumentEvent e) {
		if (GlobalFlag.choice == 1) {
			GlobalFlag.key = textOld.getText().substring(textNew.getText().length(), textNew.getText().length() + 1);
		}
		if (GlobalFlag.choice == 2) {
			GlobalFlag.key = "";
		}
		this.parent.repaint();
	}

	// 数据验证
	private void dataValidate() {
		chDocs(textOld.getText(), textNew.getText());
		if (textOld.getText().length() > textNew.getText().length()) {
			if (GlobalFlag.choice == 1) {
				GlobalFlag.key = textOld.getText().substring(textNew.getText().length(),
						textNew.getText().length() + 1);
			} else if (GlobalFlag.choice == 2) {
				GlobalFlag.key = "";
			}
			textCount.setText((textNew.getText().length() + wordCount) + "");
		} else {
			wordCount += textOld.getText().length();
			rightCount += rightWord;
			errorCount += errorWord;
			textOld.setText(InputText.getData(GlobalFlag.text, GlobalFlag.textTag));
			if (GlobalFlag.choice == 1) {
				GlobalFlag.key = textOld.getText().substring(0, 1);
			} else if (GlobalFlag.choice == 2) {
				GlobalFlag.key = "";
			}
			this.parent.repaint();
		}
	}

	// 退出时所要关闭的窗体
	private void exitFrame() {
		GlobalFlag.key = "";
		GlobalFlag.text = "";
		this.parent.repaint();
		this.parent.doDefaultCloseAction();
		this.doDefaultCloseAction();
		GlobalFlag.onChoice = true;
		GlobalFlag.textTag = 1;
		r.gc();
		System.gc();
	}

	// 计时器
	public void run() {
		try {
			for (;;)
				if (textNew.getText().length() > 0)
					break;

			while (true) {
				Thread.sleep(1000);
				time++;
				sec++;
				if (sec >= 60) {
					sec = 0;
					min++;
					if (min >= 60) {
						min = 0;
						hour++;
						if (hour >= 1000) {
							sec = 0;
							min = 0;
							hour = 0;
						}
					}
				}
				textTime.setText(((hour <= 9) ? "0" : "") + hour + ":" + ((min <= 9) ? "0" : "") + min + ":"
						+ ((sec <= 9) ? "0" : "") + sec);
				if (time >= limitTime) {
					String message = "正确率：" + ((int) (Double.parseDouble(textRight.getText())
							/ Double.parseDouble(textCount.getText()) * 10000) / 100.0) + " 速度：" + textSpeed.getText()
							+ "字/分钟";
					JOptionPane.showMessageDialog(null, message, "TypingFun", JOptionPane.INFORMATION_MESSAGE);
					exitFrame();
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class Empty extends Thread {
		public void run() {
			while (true) {
				try {
					Thread.sleep(100);
					dataValidate();
					if (textNew.getText().length() >= textOld.getText().length()) {
						textNew.setText("");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
