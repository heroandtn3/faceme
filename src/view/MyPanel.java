/**
 * 
 */
package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

/**
 * @author heroandtn3
 * 
 */
public class MyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	protected MenuPanel cardPanel;
	private GridBagLayout gridBag = new GridBagLayout();
	private GridBagConstraints gridBC = new GridBagConstraints();
	
	public MyPanel(MenuPanel mp) {
		// TODO Auto-generated constructor stub
		this.cardPanel = mp;
		setLayout(gridBag);
		gridBC.anchor = GridBagConstraints.WEST;
		gridBC.fill = GridBagConstraints.NONE;
		gridBC.insets = new Insets(5, 10, 5, 0);
	}
	
	protected void setAnchor(int anchor) {
		gridBC.anchor = anchor;
	}
	
	protected void setFill(int fill) {
		gridBC.fill = fill;
	}
	
	protected void setInsets(Insets insets) {
		gridBC.insets = insets;
	}
	
	protected void addComponent(Component c, int row, int col, int nrow, int ncol) {
		// TODO Auto-generated method stub
		gridBC.gridx = col;
		gridBC.gridy = row;

		gridBC.gridwidth = ncol;
		gridBC.gridheight = nrow;

		this.gridBag.setConstraints(c, gridBC);
		this.add(c);
	}
	
/*	protected void addComponent(Component c, int row, int col, int nrow, int ncol, double tx, double ty) {
		// TODO Auto-generated method stub
		gridBC.weightx = tx;
		gridBC.weighty = ty;
		
		gridBC.gridx = col;
		gridBC.gridy = row;

		gridBC.gridwidth = ncol;
		gridBC.gridheight = nrow;

		this.gridBag.setConstraints(c, gridBC);
		this.add(c);
	}*/

}
