package nkbh.yc;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;


public class ChessFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;
	private JMenu jMenu1 = null;
	
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JMenuItem jMenuItem3;
	
	private JPanel northPanel = null;
	private JPanel eastPanel = null;
	private JPanel southPanel = null;
	private JPanel westPanel = null;
	private JPanel chessPanel = null;

	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;

	private JTextField ipTextField = null;
	




	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuStart());
			jJMenuBar.add(getJMenuHelp());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuStart() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("开始");
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItemExit());
		}
		return jMenu;
	}
	/**
	 * This method initializes jMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuHelp() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("帮助");
			jMenu1.add(getJMenuItemHelp());
		}
		return jMenu1;
	}

	private JMenuItem getJMenuItemHelp() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("帮助信息");
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(null, "1.输入ip建立服务器；2.打开另一个运行程序输入'localhost'建立客户端；3.开始游戏。");
				}
			});
		}
		return jMenuItem3;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("背景图案");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					repaint();
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemExit() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("退出");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes northPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getNorthPanel() {
		if (northPanel == null) {
			jLabel1 = new JLabel();
			northPanel = new JPanel();
			northPanel.setLayout(new FlowLayout());
			northPanel.add(jLabel1, null);
			northPanel.add(getJButton(), null);
			northPanel.add(getJButton1(), null);
			northPanel.add(getIpTextField(), null);
			northPanel.add(getJButtonGiveUp(), null);
			northPanel.add(getJButtonBack(), null);
		}
		return northPanel;
	}

	private Component getWestPanel() {
		if (westPanel == null) {
			westPanel = new JPanel();
			westPanel.setLayout(new FlowLayout());
			
		}
		return westPanel;
	}

	/**
	 * This method initializes chessPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getChessPanel() {
		if (chessPanel == null) {
			chessPanel =  MyPanel.getInstance();
			chessPanel.setLayout(new GridBagLayout());
		}
		return chessPanel;
	}

	/**
	 * This method initializes eastPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEastPanel() {
		if (eastPanel == null) {
			eastPanel = new JPanel();
			eastPanel.setLayout(new FlowLayout());
			
		}
		return eastPanel;
	}

	/**
	 * This method initializes southPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSouthPanel() {
		if (southPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Hello!Welecom to Gobang world!");
			southPanel = new JPanel();
			southPanel.setLayout(new BorderLayout());
			southPanel.add(jLabel, BorderLayout.CENTER);
		}
		return southPanel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("服务器");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ChessControl.getInstance().pushServerButton();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("客户端");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ChessControl.getInstance().pushClientButton(ipTextField.getText());
				}
			});
		}
		return jButton1;
	}

	private JButton getJButtonGiveUp() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("认输");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(null, "一方认输！游戏结束！");
					dispose();
				}
			});
		}
		return jButton2;
	}

	private JButton getJButtonBack() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("悔棋");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int a=JOptionPane.showConfirmDialog(null, "您确定悔棋吗");
					if(a==JOptionPane.YES_OPTION)
					{					
						ChessControl.getInstance().backChess();
						repaint();
					}
				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes ipTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIpTextField() {
		if (ipTextField == null) {
			ipTextField = new JTextField();
			ipTextField.setColumns(10);
			ipTextField.setText("");
		}
		return ipTextField;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ChessFrame thisClass = new ChessFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ChessFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(680,680);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("网络对战五子棋");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getNorthPanel(), BorderLayout.NORTH);
			jContentPane.add(getEastPanel(), BorderLayout.EAST);
			jContentPane.add(getSouthPanel(), BorderLayout.SOUTH);
			jContentPane.add(getChessPanel(), BorderLayout.CENTER);
			jContentPane.add(getWestPanel(), BorderLayout.WEST);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
