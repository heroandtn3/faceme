/*
Copyright 2013 heroandtn3 (@sangnd.info)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package view;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * @author heroandtn3
 * @date Jan 17, 2013
 */
public class GamePanel extends JPanel {
	
	private BoardPanel boardPanel;
	private CardLayout card = new CardLayout();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GamePanel() {
		setLayout(card);
		initGUI();
	}
	
	private void initGUI() {
		boardPanel = new BoardPanel(this);
		add(boardPanel, BoardPanel.KEY);
	}
	
	public void switchToPanel(String key) {
		card.show(this, key);
	}

}
