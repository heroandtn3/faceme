package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.ChessPosition;
import model.Constant;
import model.Match;
import model.MyQueue;
import model.MyStack;
import model.Node;
import model.Player;
import control.Computer;

public class ChessBoardPanel extends JPanel implements MouseMotionListener,
		MouseListener {

	private static final long serialVersionUID = 1L;

	// some images
	private final Image imgBoard = new ImageIcon(Constant.BOARD_DIR
			+ "/banco.png").getImage();
	private final Image imgSelect = new ImageIcon(Constant.CHESS_DIR
			+ "/select.png").getImage();
	private final Image imgWelcome = new ImageIcon(Constant.IMG_DIR
			+ "/welcome2.jpg").getImage();
	private final Image imgPause = new ImageIcon(Constant.IMG_DIR
			+ "/waiting.jpg").getImage();
	private final Image imgDiduoc = new ImageIcon(Constant.CHESS_DIR
			+ "/diduoc.png").getImage();
	private final Image imgAnduoc = new ImageIcon(Constant.CHESS_DIR
			+ "/anduoc.png").getImage();

	private int recentX = -1, recentY = -1, piece = 0, type = 0, x = -1,
			y = -1;

	private boolean selected = false, okXY = false;
	private int hienChieu = 1;
	private MyQueue queue = new MyQueue();
	private MyStack stack = new MyStack();

	private MainFrame mainFrame;
	private Match match = null;
	private Computer com = new Computer();
	private Player player = new Player();
	private ChessPosition posCurrent = null;
	private List<ChessPosition> posCanMove = new ArrayList<ChessPosition>();
	
	// game control
	private boolean active = false;
	private boolean pause = true;
	
	// hien thong bao chieu tuong
	private JLabel lbChieuTuong = new JLabel("CHIEU TUONG");
	
	/**
	 * Constructor
	 * @param mainFrame
	 */
	public ChessBoardPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		setPreferredSize(new Dimension(Constant.BOARD_WIDTH,
				Constant.BOARD_HEIGHT));
		add(new JLabel("Chess"));
		addMouseListener(this);
		setLayout(null);
		lbChieuTuong.setOpaque(true);
		lbChieuTuong.setBackground(Color.RED);
		lbChieuTuong.setBounds(0, 302, 600, 67);
		add(lbChieuTuong);
		lbChieuTuong.setVisible(false);
		validate();
	}

	/**
	 * Khoi tao game
	 */
	public void initGame() {
		recentX = recentY = -1;
		piece = type = 0;
		x = y = -1;
		hienChieu = 1;

		selected = false;
		okXY = false;
		queue = new MyQueue();

		com = new Computer();
		player = new Player();

		posCurrent = null;
		posCanMove = new ArrayList<ChessPosition>();
		
		active = true;
		pause = false;
		
		if (match != null) {
			mainFrame.getMenuPanel().getPlayMenu().setSlLevelValue(match.getLevel());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (active) {
			if (!pause) {
				drawBoard(g);
				drawChess(g);
			} else {
				drawPauseScreen(g);
			}
		} else {
			drawWelcome(g);
		}
	}

	private void drawPauseScreen(Graphics g) {
		g.drawImage(imgPause, Constant.BOARD_X, Constant.BOARD_Y,
				Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT, null);
	}

	private void drawWelcome(Graphics g) {
		g.drawImage(imgWelcome, Constant.BOARD_X, Constant.BOARD_Y,
				Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT, null);
	}

	private void drawBoard(Graphics g) {
		g.drawImage(imgBoard, Constant.BOARD_X, Constant.BOARD_Y,
				Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT, null);
	}

	private void drawChess(Graphics g) {
		int x = 0, i = 0, j = 0;
		for (i = 0; i < 10; i++)
			for (j = 0; j < 9; j++) {
				piece = match.getTablePos()[i][j];
				type = 1;
				if (piece > 0)
					type = 0;
				x = Math.abs(piece);
				if (x > 0) {
					g.drawImage(match.getPieceChess()[type][x].getShape(),
								Constant.OX + j * Constant.length, 
								Constant.OY + i	* Constant.length, 
								42, 42, null);
				}
				g.drawImage(imgSelect,
							Constant.OX + Constant.length * match.getX1(),
							Constant.OY + Constant.length * match.getY1(), 
							42, 42,	null);
				g.drawImage(imgSelect,
						Constant.OX + Constant.length * match.getX2(),
						Constant.OY + Constant.length * match.getY2(), 42, 42,
						null);
			}
		for (ChessPosition h : posCanMove) {
			g.drawImage(imgSelect, Constant.OX + Constant.length * recentX,
					Constant.OY + Constant.length * recentY, 42, 42, null);
			if (h.getCanBeEaten()) {
				g.drawImage(imgAnduoc, Constant.OX + h.getCol()
						* Constant.length + 7, Constant.OY + h.getRow()
						* Constant.length + 7, 25, 25, null);
			} else
				g.drawImage(imgDiduoc, Constant.OX + h.getCol()
						* Constant.length + 7, Constant.OY + h.getRow()
						* Constant.length + 7, 25, 25, null);
		}
		// repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (active && !pause) {
			mainFrame.getMenuPanel().getPlayMenu().setComPlaying(match.isComPlayFirst());
			x = (e.getX() - Constant.OX) / Constant.length;
			y = (e.getY() - Constant.OY) / Constant.length;
			if (!match.isFinish()) {
				
				if (((x >= 0) && (x < 9)) && ((y >= 0) && (y < 10))) {
					if (!selected) {
						if (match.getTablePos()[y][x]<0 || (match.getTablePos()[y][x]>0 && match.nguoichoinguoi())) {
							piece = match.getTablePos()[y][x];
							int side = 1;
							if (y <= 4)
								side = -1;
							type = 1;
							posCurrent = new ChessPosition(x, y, false);
							posCanMove = match.getPieceChess()[type][Math
									.abs(piece)].getPosCanMove(match, posCurrent,
									side);
							recentX = x;
							recentY = y;
							selected = true;
						}
					} else {
						okXY = false;
						for (int count = 0; count < posCanMove.size(); count++) {
							ChessPosition pos = posCanMove.get(count);
							if ((x == pos.getCol()) && (y == pos.getRow())) {
								okXY = true;
							}
						}
						if (okXY) {
							piece = match.getTablePos()[y][x];
							if ((piece == 0)
									|| ((piece
											* match.getTablePos()[recentY][recentX] != 0) || (match.getTablePos()[y][x]>0 && match.nguoichoinguoi()))) {
								if (Math.abs(match.getTablePos()[y][x])==7) {
									match.setFinish(true);
									showDlgYou();// hien thong bao nguoi thang
								}
								int cuoi = match.getTablePos()[y][x];
								match.getTablePos()[y][x] = match.getTablePos()[recentY][recentX];
								int dau = match.getTablePos()[recentY][recentX];
								stack.push(new Node(recentY, recentX, y, x, dau,
										cuoi));
								match.getTablePos()[recentY][recentX] = 0;

								selected = false;
								if (match.isComPlayFirst()) {
									match.setComPlayFirst(false);
								} else {
									match.setComPlayFirst(true);
								}
								
								hienChieu = 1;
							}
							posCanMove.clear();
						} else {
							if (match.getTablePos()[y][x] != 0  || (match.getTablePos()[y][x]>0 && match.nguoichoinguoi())) {
								piece = match.getTablePos()[y][x];
								int side = 1;
								if (y <= 4)
									side = -1;
								type = 1;
								posCurrent = new ChessPosition(x, y, false);
								posCanMove = match.getPieceChess()[type][Math
										.abs(piece)].getPosCanMove(match,
										posCurrent, side);
								recentX = x;
								recentY = y;
								selected = true;
							}

						}
					}

					int a[][] = new int[9][8];
					a = match.getTablePos();
					repaint();
					if (!player.kiemtraquandenchieutuong(a)) {
						if (hienChieu < 2)
							showChieu();
					}

				}
			}
			repaint();
			mainFrame.getMenuPanel().getPlayMenu().setComPlaying(match.isComPlayFirst());

			// Sang Hero says: doan code nay suu tam tren mang, to cung chua hieu no
			// lam
			// to dang doc them ve Threads trong java, hy vong se hieu :))
			// p/s: Computer xu li cham qua
			if (!match.isFinish() && match.isPlayWithCom() && match.isComPlayFirst())
				new Thread() {
					public void run() {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								if (match.isComPlayFirst()) {
									com.thinking(match, 0);
									if (Math.abs(match.getTablePos()[match
											.getNewMove().getyy()][match
											.getNewMove().getxx()]) == 7) {
										match.setFinish(true);
										showDlgCom();// hien thong bao may thang
									}
									int dau = match.getTablePos()[match
											.getNewMove().gety()][match
											.getNewMove().getx()];
									int cuoi = match.getTablePos()[match
											.getNewMove().getyy()][match
											.getNewMove().getxx()];
									match.tryMove(match.getNewMove());
									match.setComPlayFirst(false);
									// x1,y1,x2,y2 toa do can select khi maydi
									match.setX1(match.getNewMove().getx());
									match.setY1(match.getNewMove().gety());
									match.setX2(match.getNewMove().getxx());
									match.setY2(match.getNewMove().getyy());

									stack.push(new Node(match.getNewMove().gety(),
											match.getNewMove().getx(), match
													.getNewMove().getyy(), match
													.getNewMove().getxx(), dau,
											cuoi));
									int a[][] = new int[9][8];
									a = match.getTablePos();
									repaint();
									mainFrame.getMenuPanel().getPlayMenu().setComPlaying(match.isComPlayFirst());
									if (!player.kiemtraquandochieutuong(a)) {
										if (hienChieu < 2)
											showChieu();
									}

									hienChieu = 1;
								}
							}
						});
					}
				}.run();
			// end doan code suu tam
		}
	} // ket thuc phuong thuc mouseClicked()

	public void showUndo() {
		match.setX1(-1);
		match.setY1(-1);
		match.setX2(-1);
		match.setY2(-1);
		Node node = stack.pop();
		if (node != null) {
			match.getTablePos()[node.getXdau()][node.getYdau()] = node
					.getGtDau();
			match.getTablePos()[node.getXcuoi()][node.getYcuoi()] = node
					.getGtCuoi();
			queue.insert(new Node(node.getXdau(), node.getYdau(), node
					.getXcuoi(), node.getYcuoi(), node.getGtDau(), node
					.getGtCuoi()));

		}
		Node node1 = stack.pop();
		if (node1 != null) {
			match.getTablePos()[node1.getXdau()][node1.getYdau()] = node1
					.getGtDau();
			match.getTablePos()[node1.getXcuoi()][node1.getYcuoi()] = node1
					.getGtCuoi();
			queue.insert(new Node(node1.getXdau(), node1.getYdau(), node1
					.getXcuoi(), node1.getYcuoi(), node1.getGtDau(), node1
					.getGtCuoi()));
		}
	}

	public void showRedo() {
		match.setX1(-1);
		match.setY1(-1);
		match.setX2(-1);
		match.setY2(-1);
		Node node = queue.remove();
		if (node != null) {
			match.getTablePos()[node.getXdau()][node.getYdau()] = node
					.getGtDau();
			match.getTablePos()[node.getXcuoi()][node.getYcuoi()] = node
					.getGtCuoi();
			stack.push(new Node(node.getXcuoi(), node.getYcuoi(), node
					.getXdau(), node.getYdau(), node.getGtCuoi(), node
					.getGtDau()));
		}
		Node node1 = queue.remove();
		if (node1 != null) {
			match.getTablePos()[node1.getXdau()][node1.getYdau()] = node1
					.getGtDau();
			match.getTablePos()[node1.getXcuoi()][node1.getYcuoi()] = node1
					.getGtCuoi();
			stack.push(new Node(node1.getXcuoi(), node1.getYcuoi(), node1
					.getXdau(), node1.getYdau(), node1.getGtCuoi(), node1
					.getGtDau()));

		}
	}

	public void showDlgYou() {
		JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát Game?",
				"You Win", JOptionPane.OK_OPTION);
	}

	public void showDlgCom() {
		JOptionPane.showConfirmDialog(this, "Ban Co Muon Thoat Game?",
				"Com Win", JOptionPane.OK_OPTION);
	}

	public void showChieu() {
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				if (match.isComPlayFirst()) {
					mainFrame.getMenuPanel().getPlayMenu().setWarnByHum(true);
				} else {
					mainFrame.getMenuPanel().getPlayMenu().setWarnByCom(true);
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				mainFrame.getMenuPanel().getPlayMenu().setWarnByHum(false);
				mainFrame.getMenuPanel().getPlayMenu().setWarnByCom(false);
			}
		});
		
		t.start();
		
		
		hienChieu++;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 * @param match the match to set
	 */
	public void setMatch(Match match) {
		this.match = match;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the pause
	 */
	public boolean isPause() {
		return pause;
	}

	/**
	 * @param pause the pause to set
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}

}