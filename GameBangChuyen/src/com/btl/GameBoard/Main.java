package com.btl.GameBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import com.btl.GameElements.playtitle.GameTitle;
import com.btl.data.OtherImage;
import com.btl.data.SoundEffect;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JFrame frame;

	private GamePanel panel;

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Conveyor Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(OtherImage.ICON);

		panel = new GamePanel();

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.pack();
		panel.setState(new GameTitle(panel,null));

		frame.setLocationRelativeTo(null);

		panel.setState(new GameTitle(panel, null));

		SoundEffect.BACKGROUND.loop();
	}
}
