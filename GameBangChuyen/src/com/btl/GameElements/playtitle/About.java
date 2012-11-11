package com.btl.GameElements.playtitle;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.btl.GameBoard.GamePanel;
import com.btl.GameBoard.GameState;
import com.btl.GameElements.playstate.Button;
import com.btl.Model.ConversionFunction;
import com.btl.data.ButtonImage;

public class About extends GameState {
	
	private Button backButton ;
	private JScrollPane aboutScroll;
	private JTextArea aboutContent;
	public About(GamePanel parent, GameState lastState,String name) {
		super(parent, lastState);
		backButton = new Button(new Point (0,0));
		backButton.setImage(ButtonImage.BACK_BUTTON_IMAGE, 40, 40);
		
		parent.setLayout(null);
		String fileName = ConversionFunction.getCurrentDirectory();
		fileName+="\\"+name;
		
		String about = null;
		try{
			about =readFile(fileName);
		}
		catch(Exception e) {
			about = "Lỗi";
		}
		Font f = new Font("UTF8",Font.PLAIN, 14);
		aboutContent = new JTextArea();
		aboutContent.setFont(f);
		
		aboutContent.setEditable(false);
		aboutContent.setText(about);
		aboutScroll = new JScrollPane(aboutContent);
		aboutScroll.setBounds(0,100,parent.width,parent.height);
		
		aboutScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		aboutScroll.setVisible(true);
		parent.add(aboutScroll);
		
		parent.revalidate();
	}
	/**
	 * đọc lội dung của file about ra
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	private String readFile(String fileName) throws IOException{
		String result = "";
		try {
			File fileDir = new File(fileName);
			 
			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));
	 
				String strLine;
			 while ((strLine = in.readLine()) != null)   {
				 result+="\n"+strLine;
			 }
		 
			 in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  return result;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (backButton.contains(new Point(e.getX(),e.getY()))){
			changeState(lastState);
		}
		
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

	@Override
	public void gameRender(Graphics g) {
		backButton.paint(g);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void changeState(GameState state) {
		parent.remove(aboutScroll);
		parent.revalidate();
		parent.setState(state);

	}
}
