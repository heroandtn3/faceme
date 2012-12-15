/**
 * 
 */
package view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Constant;

/**
 * @author heroandtn3
 * 
 */
public class MenuPlayPanel extends MyPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KEY = "MenuPlayPanel";
	
	private JLabel lbThink;
	
	private JLabel lbHuman;
	private JLabel lbCom;
	private JLabel lbLevel;
	private JSlider slLevel;
	
	private MyLabelButton butHint;
	private MyLabelButton butHome;
	private MyLabelButton butReset;
	private MyLabelButton butRedo;
	private MyLabelButton butUndo;
	private JLabel butPlayPause;
	
	// computer face
	private ImageIcon icoCaptain, icoIron, icoThor;
	private ImageIcon icoThink;
	private ImageIcon icoWarn;
	private ImageIcon icoHuman;
	private ImageIcon imgPause, imgPlay;
	private JLabel lbHumWarn;
	private JLabel lbComWarn;

	/**
	 * 
	 */
	public MenuPlayPanel(MenuPanel mp) {
		// TODO Auto-generated constructor stub
		super(mp);

		loadImage();
		initGUI();

	}

	private void loadImage() {
		imgPause = new ImageIcon(Constant.OPT_DIR + "/pause");
		imgPlay = new ImageIcon(Constant.OPT_DIR + "/play");
		
		icoCaptain = new ImageIcon(Constant.COM_DIR + "captain.png");
		icoIron = new ImageIcon(Constant.COM_DIR + "iron.png");
		icoThor = new ImageIcon(Constant.COM_DIR + "thor.png");
		
		icoThink = new ImageIcon(Constant.IMG_DIR + "/thinking.png");
		icoWarn = new ImageIcon(Constant.IMG_DIR + "/warn.png");
		
		icoHuman = new ImageIcon(Constant.COM_DIR + "human.png");
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(Constant.MENU_WIDTH, Constant.MENU_HEIGHT));
				this.setLayout(null);
				{
					butPlayPause = new JLabel(imgPause);
					this.add(butPlayPause);
					butPlayPause.setBounds(76, 313, 48, 48);
					butPlayPause.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent evt) {
							//TODO add your code for butPlayPause.mouseClicked
							if (cardPanel.getMainFrame().getChessBoardPanel().isPause()) {
								cardPanel.getMainFrame().getChessBoardPanel().setPause(false);
							} else {
								cardPanel.getMainFrame().getChessBoardPanel().setPause(true);
							}
							cardPanel.getMainFrame().getChessBoardPanel().repaint();
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							butPlayPause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							butPlayPause.setIcon(new ImageIcon(butPlayPause.getIcon().toString() + "-hover"));

							if (cardPanel.getMainFrame().getChessBoardPanel().isPause())
								butPlayPause.setToolTipText("Play");
							else
								butPlayPause.setToolTipText("Pause");
							
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							butPlayPause.setIcon(cardPanel.getMainFrame().getChessBoardPanel().isPause() ? imgPlay : imgPause);
						}
					});
				}
				{
					butUndo = new MyLabelButton(Constant.OPT_DIR + "/undo", "Undo", false);
					this.add(butUndo);
					butUndo.setBounds(14, 313, 48, 48);
					butUndo.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							//TODO add your code for butUndo.mouseClicked
							cardPanel.getMainFrame().getChessBoardPanel().showUndo();
							cardPanel.getMainFrame().getChessBoardPanel().repaint();
						}
					});
				}
				{
					butRedo = new MyLabelButton(Constant.OPT_DIR + "/redo", "Redo", false);
					this.add(butRedo);
					butRedo.setBounds(138, 313, 48, 48);
					butRedo.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							//TODO add your code for butRedo.mouseClicked
							cardPanel.getMainFrame().getChessBoardPanel().showRedo();
							cardPanel.getMainFrame().getChessBoardPanel().repaint();
						}
					});
				}
				{
					butReset = new MyLabelButton(Constant.OPT_DIR + "/reset", "Reset", false);
					this.add(butReset);
					butReset.setBounds(76, 250, 48, 48);
					butReset.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							//TODO add your code for butReset.mouseClicked
							cardPanel.getMainFrame().getChessBoardPanel().getMatch().readDefaultMatch();
							cardPanel.getMainFrame().getChessBoardPanel().getMatch().setFinish(false);
							cardPanel.getMainFrame().getChessBoardPanel().initGame();
							cardPanel.getMainFrame().getChessBoardPanel().repaint();
						}
					});
				}
				
				{
					lbLevel = new JLabel();
					this.add(lbLevel);
					lbLevel.setText("Level");
					lbLevel.setBounds(5, 380, 50, 25);
				}
				
				{
					slLevel = new JSlider();
					this.add(slLevel);
					slLevel.setBounds(70, 380, 121, 50);
					slLevel.setMaximum(6);
					slLevel.setMinimum(2);
					slLevel.setMajorTickSpacing(2);
					slLevel.setMinorTickSpacing(2);
					slLevel.setPaintLabels(true);
					slLevel.setPaintTrack(true);
					slLevel.setPaintTicks(true);
					slLevel.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							//TODO add your code for slLevel.stateChanged
							JSlider source = (JSlider) evt.getSource();
						    if (!source.getValueIsAdjusting()) {
						        int lv = (int)source.getValue();
						        if ((lv %2 ) != 0) { // la so le
						        	slLevel.setValue(lv - 1);
						        }
						        cardPanel.getMainFrame().getChessBoardPanel().getMatch().setLevel(lv);
						        setLbCom(lv);
						    }
						}
					});
				}
				{
					butHome = new MyLabelButton(Constant.OPT_DIR + "/home",
												"Back to Home Menu", false);
					this.add(butHome);
					butHome.setBounds(14, 250, 48, 48);
					butHome.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							//TODO add your code for butHome.mouseClicked
							cardPanel.swapPanel(MenuHomePanel.KEY);
							cardPanel.getMainFrame().getChessBoardPanel().setActive(false);
							cardPanel.getMainFrame().getChessBoardPanel().repaint();
							cardPanel.getMainFrame().getChessBoardPanel().getMatch().writeMatchToFile("lastmap");
						}
					});
				}
				{
					butHint = new MyLabelButton(Constant.OPT_DIR + "/hint", "Hint", false);
					this.add(butHint);
					butHint.setBounds(138, 250, 48, 48);
				}
				
				{
					lbThink = new JLabel(icoThink);
					this.add(lbThink);
					lbThink.setBounds(130, 10, 50, 50);
				}
				
				{
					lbComWarn = new JLabel(icoWarn);
					this.add(lbComWarn);
					lbComWarn.setBounds(20, 182, 48, 48);
					lbComWarn.setVisible(false);
				}
				{
					lbHumWarn = new JLabel(icoWarn);
					this.add(lbHumWarn);
					lbHumWarn.setBounds(20, Constant.MENU_HEIGHT - 58, 48, 48);
					lbHumWarn.setVisible(false);
				}
				
				{
					lbCom = new JLabel();
					this.add(lbCom);
					lbCom.setBounds(20, 10, 160, 220);
				}
				{
					lbHuman = new JLabel(icoHuman);
					this.add(lbHuman);
					lbHuman.setBounds(20, Constant.MENU_HEIGHT - 230, 160, 220);
				}
				

			}
		} catch(Exception e) {
			//e.printStackTrace();
		}
	} // ket thuc phuong thuc initGUI()
	
	/**
	 * 
	 * @param lv: level set cho jslider
	 */
	public void setSlLevelValue(int lv) {
		slLevel.setValue(lv);
	}
	
	/**
	 * 
	 * @param comIsPlaying: true if Com is Playing
	 */
	public void setComPlaying(boolean comIsPlaying) {
		if (comIsPlaying) {
			lbThink.setBounds(130, 10, 50, 50);
		} else {
			lbThink.setBounds(130, Constant.MENU_HEIGHT - 230, 50, 50);
		}
	}
	
	/**
	 * 
	 * @param lv: level of match
	 * lv = 2: captain
	 * lv = 4: iron
	 * lv = 6: thor
	 */
	public void setLbCom(int lv) {
		switch (lv) {
			case 0: // choi voi nguoi
				lbCom.setIcon(icoHuman);
				break;
			case 2: // choi voi captain
				lbCom.setIcon(icoCaptain);
				break;
			case 4: // choi voi iron
				lbCom.setIcon(icoIron);
				break;
			case 6: // choi voi Thor
			case 8: // choi voi Hulk
				lbCom.setIcon(icoThor);
				break;
		}
	}
	
	public void setWarnByCom(boolean comWarn) {
		if (comWarn) {
			lbHumWarn.setVisible(true);
		} else {
			lbHumWarn.setVisible(false);
		}
	}
	
	public void setWarnByHum(boolean humWarn) {
		if (humWarn) {
			lbComWarn.setVisible(true);
		} else {
			lbComWarn.setVisible(false);
		}
	}

}
