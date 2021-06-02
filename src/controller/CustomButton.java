package controller;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.cards.Card;
import model.heroes.Hero;

@SuppressWarnings("serial")
public class CustomButton extends JButton {

	private boolean flag;
	private Card c;
	private Hero h;

	public CustomButton(ActionListener listener, Card c) {
		addActionListener(listener);
		flag = false;
		this.c = c;
		setText(c.toString());
	}

	public CustomButton(ActionListener listener, Hero h) {

		addActionListener(listener);
		flag = false;
		this.h = h;
		setText("use hero power");

	}

	public Card getC() {
		return c;
	}

	public void setC(Card c) {
		this.c = c;
	}

	public Hero getH() {
		return h;
	}

	public void setH(Hero h) {
		this.h = h;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean f) {
		flag = f;
	}

}
