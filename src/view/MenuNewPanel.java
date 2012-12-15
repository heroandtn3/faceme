/**
 * Menu tao new game, cho phep thiet lap nguoi choi voi may, do kho, ben nao di truoc,...
 */
package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import control.Computer;

import model.Constant;
import model.Match;

/**
 * @author heroandtn3
 * 
 */
public class MenuNewPanel extends MyPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KEY = "MenuNewPanel";
	
	private Match match = null; // dung de set cho chessBoard
	
	// cac nut dieu khien
	private MyLabelButton lbBackHome, lbOk;

	// chon che do choi
	private JLabel lbMode = new JLabel("Select player mode:");
	private ButtonGroup modeGroup = new ButtonGroup();
	private JRadioButton radioCom, radioHum;
	private final String strCom = "Human vs. Computer";
	private final String strHum = "Human vs. Human";
	
	// chon ai di truoc
	private JLabel lbWho = new JLabel("Who will play first?");
	private ButtonGroup whoGroup = new ButtonGroup();
	private JRadioButton radioComFirst, radioHumFirst;
	private final String strComFirst = "Computer";
	private final String strHumFirst = "Human";

	// chon level
	private JLabel lbLevel = new JLabel("Select level:");
	private ButtonGroup levelGroup = new ButtonGroup();
	private JRadioButton[] radioLevel = new JRadioButton[4];
	private final String[] strLevel = {"Captain Amerian", "Iron Man", "Thor", "Hulk"};
	private JLabel picLevel = new JLabel();

	/**
	 * 
	 */
	public MenuNewPanel(MenuPanel mp) {
		// TODO Auto-generated constructor stub
		super(mp);
		initLabel();
		initRadio();
		addAll();

	}

	private void addAll() {
		// control
		setAnchor(GridBagConstraints.CENTER);
		addComponent(lbBackHome, 0, 0, 1, 1);
		addComponent(lbOk, 0, 4, 1, 1);

		// mode
		setAnchor(GridBagConstraints.WEST);
		addComponent(lbMode, 2, 0, 1, 5);
		addComponent(radioCom, 3, 0, 1, 5);
		addComponent(radioHum, 4, 0, 1, 5);
		
		// who play first
		addComponent(lbWho, 6, 0, 1, 5);
		addComponent(radioComFirst, 7, 0, 1, 5);
		addComponent(radioHumFirst, 8, 0, 1, 5);

		// level
		addComponent(lbLevel, 10, 0, 1, 5);
		for (int i=0; i<4; i++) {
			addComponent(radioLevel[i], 11+i, 0, 1, 5);
		}
		setAnchor(GridBagConstraints.CENTER);
		addComponent(picLevel, 15, 0, 5, 5);

	}

	private void initRadio() {
		// che do choi
		radioCom = new JRadioButton(strCom, true);
		radioCom.addActionListener(this);
		radioCom.setActionCommand(strCom);
		modeGroup.add(radioCom);

		radioHum = new JRadioButton(strHum, false);
		radioHum.addActionListener(this);
		radioHum.setActionCommand(strHum);
		modeGroup.add(radioHum);
		
		// ai choi truoc
		radioComFirst = new JRadioButton(strComFirst, true);
		radioComFirst.addActionListener(this);
		radioComFirst.setActionCommand(strComFirst);
		whoGroup.add(radioComFirst);
		
		radioHumFirst = new JRadioButton(strHumFirst, false);
		radioHumFirst.addActionListener(this);
		radioHumFirst.setActionCommand(strHumFirst);
		whoGroup.add(radioHumFirst);

		// level		
		for (int i=0; i<4; i++) {
			radioLevel[i] = new JRadioButton(strLevel[i]);
			radioLevel[i].addActionListener(levelAction);
			radioLevel[i].setActionCommand(strLevel[i]);
			levelGroup.add(radioLevel[i]);
		}
		radioLevel[0].setSelected(true);

	}

	private void initLabel() {
		lbBackHome = new MyLabelButton(Constant.OPT_DIR+ "/back", "Back to home menu", false);
		lbBackHome.addMouseListener(this);

		lbOk = new MyLabelButton(Constant.OPT_DIR+ "/ok", "Begin play", false);
		lbOk.addMouseListener(this);
		
		picLevel.setIcon(new ImageIcon(Constant.LEVEL_DIR + "/" + strLevel[0] + ".png"));
	}
	
	private void setEnableChoice(boolean status) {
		radioComFirst.setEnabled(status);
		radioHumFirst.setEnabled(status);
		for (int i=0; i<4; i++) {
			radioLevel[i].setEnabled(status);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel source = (JLabel) e.getSource();
		
		if (source == lbBackHome) {
			cardPanel.swapPanel(MenuHomePanel.KEY);
			
		} else if (source == lbOk) {
			// tao match de set vao chessBoardPanel
			match = new Match();
			
			// thiet lap cac che do
			if (modeGroup.getSelection().getActionCommand() == strCom) {
				
				// choi voi may
				match.setPLayWithCom(true);
				match.setNguoichoinguoi(false);
				
				if (whoGroup.getSelection().getActionCommand() == strComFirst) {
					//may choi truoc
					match.setComPlayFirst(true);
					
				} else {
					match.setComPlayFirst(false);
					
				}
				// chon level
				String choice = levelGroup.getSelection().getActionCommand();
				for (int i=0; i<4; i++) {
					if (choice.equals(strLevel[i])) {
						int lv = 2;
						if (i == 0) {
							lv = 2;
						} else if (i == 1) {
							lv = 4;
						} else if (i == 2 || i == 3) {
							lv = 6;
						}
						match.setLevel(lv);
						break;
					}
				}
			} else {
				// 2 nguoi choi
				match.setPLayWithCom(false);
				match.setNguoichoinguoi(true);
			}
			cardPanel.swapPanel(MenuPlayPanel.KEY);
			cardPanel.getMainFrame().getChessBoardPanel().setMatch(match);
			cardPanel.getMainFrame().getChessBoardPanel().initGame();
			cardPanel.getMainFrame().getChessBoardPanel().repaint();
			cardPanel.getPlayMenu().setSlLevelValue(match.getLevel());
			if (match.isPlayWithCom()) {
				cardPanel.getPlayMenu().setLbCom(match.getLevel());
			} else {
				cardPanel.getPlayMenu().setLbCom(0);
			}
			
			cardPanel.getPlayMenu().setComPlaying(match.isComPlayFirst());
			
			// Sang Hero says: doan code nay suu tam tren mang, to cung chua hieu no lam
			// to dang doc them ve Threads trong java, hy vong se hieu :)) 
			// p/s: Computer xu li cham qua
			new Thread() {
		        public void run() {
		                SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (match.isComPlayFirst()) {
									Computer com = new Computer();
									com.thinking(match, 0);
									match.tryMove(match.getNewMove());
									match.setComPlayFirst(false);
									match.setX1(match.getNewMove().getx());
									match.setY1(match.getNewMove().gety());
									match.setX2(match.getNewMove().getxx());
									match.setY2(match.getNewMove().getyy());
									cardPanel.getMainFrame().getChessBoardPanel().repaint();
									cardPanel.getPlayMenu().setComPlaying(match.isComPlayFirst());
								}
							}
		                });
		        }
		    }.run();
		 // end doan code suu tam
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JRadioButton source = (JRadioButton) e.getSource();
		if (source == radioCom) {
			setEnableChoice(true);
		} else if (source == radioHum) {
			setEnableChoice(false);
		}
	}
	
	private ActionListener levelAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JRadioButton source = (JRadioButton) e.getSource();
			picLevel.setIcon(new ImageIcon(Constant.LEVEL_DIR + "/" + source.getActionCommand() + ".png"));
		}
	};

}
