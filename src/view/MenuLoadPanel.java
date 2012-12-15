/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.Constant;

/**
 * @author heroandtn3
 * 
 */
public class MenuLoadPanel extends MyPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KEY = "MenuLoadPanel";

	private MyLabelButton lbBackHome;

	/**
	 * 
	 */
	public MenuLoadPanel(MenuPanel mp) {
		// TODO Auto-generated constructor stub
		super(mp);

		setBackground(Color.WHITE);

		initLabel();
		addAll();
	}

	private void addAll() {
		addComponent(new JLabel("Load Game"), 0, 0, 1, 3);
		addComponent(lbBackHome, 1, 0, 1, 3);
	}

	private void initLabel() {
		lbBackHome = new MyLabelButton(Constant.OPT_DIR+ "/back", "Back to home menu", false);
		lbBackHome.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel source = (JLabel) e.getSource();
		if (source == lbBackHome) {
			cardPanel.swapPanel(MenuHomePanel.KEY);
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

}
