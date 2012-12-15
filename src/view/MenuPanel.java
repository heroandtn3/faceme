/**
 * Day la mot card panel, no co kha nang add nhieu panel vao va khi can show panel nao thi chi can goi 
 * phuong thuc show(String key)
 */

package view;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Constant;

public class MenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Dimension menuDim = new Dimension(Constant.MENU_WIDTH, 
													Constant.MENU_HEIGHT); // kich thuoc khung menu

	private CardLayout card = new CardLayout(0, 0);

	// cac panel
	private MenuHomePanel homeMenu;
	private MenuNewPanel newMenu;
	private MenuCreatePanel createMenu;
	private MenuLoadPanel loadMenu;
	private MenuPlayPanel playMenu;

	private MainFrame mainFrame; // frame chinh, su dung de tuong tac voi ChessBoardPanel

	public MenuPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setPreferredSize(menuDim);

		setLayout(card);

		// add cac panel vao
		homeMenu = new MenuHomePanel(this);
		add(homeMenu, MenuHomePanel.KEY);

		newMenu = new MenuNewPanel(this);
		add(newMenu, MenuNewPanel.KEY);

		createMenu = new MenuCreatePanel(this);
		add(createMenu, MenuCreatePanel.KEY);

		loadMenu = new MenuLoadPanel(this);
		add(loadMenu, MenuLoadPanel.KEY);

		playMenu = new MenuPlayPanel(this);
		add(playMenu, MenuPlayPanel.KEY);

		setVisible(true);
	}

	/**
	 * Chuyen qua lai giua cac panel
	 * @param key: chuoi String cua panel can chuyen
	 * key la String duoc dung khi cardPanel goi phuong thuc add(c, key)
	 */
	public void swapPanel(String key) {
		card.show(this, key);
	}

	/**
	 * Lay mainFrame
	 * @return
	 */
	public MainFrame getMainFrame() {
		return this.mainFrame;
	}

	/**
	 * @return the playMenu
	 */
	public MenuPlayPanel getPlayMenu() {
		return playMenu;
	}
}
