/**
 * 
 */
package view;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.ImageService;

/**
 * @author heroandtn3
 *
 */
public class MyLabelButton extends JLabel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ImageIcon image;
	private String imgUrl;
	private boolean highlight;
	
	
	public MyLabelButton(String imgUrl, String tooltip, boolean highlight) {
		// TODO Auto-generated constructor stub
		this.imgUrl = imgUrl;
		this.highlight = highlight;
		
		image = new ImageIcon(this.imgUrl);
		setIcon(image);
		setToolTipText(tooltip);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		if (highlight) {
			setIcon(ImageService.hightlight(image));
		} else {
			setIcon(new ImageIcon(imgUrl+ "-hover"));
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setIcon(image);
	}

}
