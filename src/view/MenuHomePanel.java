/**
 * Menu chao mung khi bat dau game
 */
package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.Constant;
import model.Match;

/**
 * @author heroandtn3
 * 
 */
public class MenuHomePanel extends MyPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KEY = "MenuHomePanel";

	private MyLabelButton lbNewGame, lbCreateGame, lbLoadGame, lbExit;

	/**
	 * 
	 */
	public MenuHomePanel(MenuPanel mp) {
		// TODO Auto-generated constructor stub
		super(mp);
		this.setBackground(Color.GREEN);
		setPreferredSize(cardPanel.getPreferredSize());

		initLabel();
		addAll();
	}

	private void addAll() {
		addComponent(lbNewGame, 0, 0, 1, 3);
		addComponent(lbCreateGame, 1, 0, 1, 3);
		addComponent(lbLoadGame, 2, 0, 1, 3);
		addComponent(lbExit, 3, 0, 1, 3);;
	}

	private void initLabel() {

		lbNewGame = new MyLabelButton(Constant.OPT_DIR+ "/newgame.gif", "Play new game", true);
		lbNewGame.addMouseListener(this);

		lbCreateGame = new MyLabelButton(Constant.OPT_DIR+ "/creategame.gif", "Create a game", true);
		lbCreateGame.addMouseListener(this);

		lbLoadGame = new MyLabelButton(Constant.OPT_DIR+ "/loadgame.gif", "Load a game", true);
		lbLoadGame.addMouseListener(this);

		lbExit = new MyLabelButton(Constant.OPT_DIR+ "/exit.gif", "Exit game", true);
		lbExit.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel source = (JLabel) e.getSource();
		if (source == lbNewGame) {
			cardPanel.swapPanel(MenuNewPanel.KEY);
		} else if (source == lbCreateGame) {
			cardPanel.swapPanel(MenuCreatePanel.KEY);
		} else if (source == lbLoadGame) {
			//cardPanel.swapPanel(MenuLoadPanel.KEY);
			Match match = new Match();
			if (match.readeMatchFromFile("lastmap")) {
			} else {
				match.readDefaultMatch();
			}
			cardPanel.swapPanel(MenuPlayPanel.KEY);
			cardPanel.getMainFrame().getChessBoardPanel().setMatch(match);
			cardPanel.getMainFrame().getChessBoardPanel().initGame();
			cardPanel.getMainFrame().getChessBoardPanel().repaint();
		} else if (source == lbExit) {
			System.exit(0);
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
;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
