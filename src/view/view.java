package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class view extends JFrame{
	private JPanel Current ;
	private JPanel Opponent ;
	private JPanel Currentground ;
	private JPanel Opponentground ;

	
	@SuppressWarnings("deprecation")
	public view () {
		this.setBounds(500, 300, 1200, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(0,1));
		Current=new JPanel();
		Opponent=new JPanel();
		Currentground=new JPanel();
		Opponentground=new JPanel();
		
		Current.setPreferredSize(new Dimension(200,this.getHeight()/4));
		Opponent.setPreferredSize(new Dimension(200,this.getHeight()/4));
		Currentground.setPreferredSize(new Dimension(200,this.getHeight()/4));
		Opponentground.setPreferredSize(new Dimension(200,this.getHeight()/4));
		
		
		this.add(Opponent);
		this.add(Opponentground);
		this.add(Currentground);
		this.add(Current);
		
		
		Current.setBackground(Color.RED);
		Opponent.setBackground(Color.ORANGE);
		Currentground.setBackground(Color.DARK_GRAY);
		Opponentground.setBackground(Color.LIGHT_GRAY);
		
		Current.setLayout(new GridLayout(0,10));
		Opponent.setLayout(new GridLayout(0,10));
		Currentground.setLayout(new GridLayout(0,7));
		Opponentground.setLayout(new GridLayout(0,7));
		
	    this.setVisible(true);
	    this.revalidate();
		this.repaint();
	}
	
	
	
	
	public JPanel getCurrent() {
		return Current;
	}




	public JPanel getCurrentground() {
		return Currentground;
	}




	public JPanel getOpponentground() {
		return Opponentground;
	}




	public void setCurrent(JPanel current) {
		Current = current;
	}




	public void setOpponent(JPanel opponent) {
		Opponent = opponent;
	}




	public void setCurrentground(JPanel currentground) {
		Currentground = currentground;
	}




	public void setOpponentground(JPanel opponentground) {
		Opponentground = opponentground;
	}




	public JPanel getOpponent() {
		return Opponent;
	}




	public static void main (String[]args) {
	 new view ();
	}

}
