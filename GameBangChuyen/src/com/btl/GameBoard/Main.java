package com.btl.GameBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.btl.GameElements.gameplay.PlayState;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

    private JFrame frame;
    private GamePanel panel;

    /**
     * Launch the application.
     * 
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
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
	frame = new JFrame();
	frame.setBounds(100, 100, 675, 413);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	panel = new GamePanel();

	frame.getContentPane().add(panel, BorderLayout.CENTER);

	JButton btnNewButton = new JButton("New button");
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		panel.setState(new PlayState(panel));
	    }
	});
	frame.getContentPane().add(btnNewButton, BorderLayout.NORTH);
    }

}
