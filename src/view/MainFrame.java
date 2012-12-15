package view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import control.ComponentMover;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuPanel menuPanel;
	private ChessBoardPanel chessBoardPanel;

	public MainFrame() {
		
		
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// cho phep di chuyen cua so bang chuot
		ComponentMover cm = new ComponentMover();
		cm.registerComponent(this);
		
		// add 2 panel chinh
		chessBoardPanel = new ChessBoardPanel(this);
		getContentPane().add(chessBoardPanel);
		
		menuPanel = new MenuPanel(this);
		getContentPane().add(menuPanel);
	
		setResizable(false); // khong thay doi kich thuoc duoc
		setUndecorated(true); // khong co vien (border)
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public ChessBoardPanel getChessBoardPanel() {
		return this.chessBoardPanel;
	}
	
	public MenuPanel getMenuPanel() {
		return this.menuPanel;
	}
	
	/*public Match getMatch() {
		return this.match;
	}*/
	
}
