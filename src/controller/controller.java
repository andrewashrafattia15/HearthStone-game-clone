package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.cards.minions.Minion;
import model.cards.spells.*;
import model.heroes.*;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HearthstoneException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import view.view;

public class controller implements GameListener, ActionListener {
	private Game model;
	private view view;

	private CustomButton HeroA;
	private CustomButton HeroB;
	private boolean attacker;
	private boolean spell;
	private boolean hero;
	private ArrayList<CustomButton> CurrentHand;
	private ArrayList<CustomButton> CurrentField;

	private ArrayList<CustomButton> OpponentHand;
	private ArrayList<CustomButton> OpponentField;

	private JButton Hunter = new JButton("Hunter");
	private JButton Paladin = new JButton("Paladin");
	private JButton Priest = new JButton("Priest");
	private JButton Mage = new JButton("Mage");
	private JButton Warlock = new JButton("Warlock");
	
	private JButton Hunter1 = new JButton("Hunter");
	private JButton Paladin1 = new JButton("Paladin");
	private JButton Priest1 = new JButton("Priest");
	private JButton Mage1 = new JButton("Mage");
	private JButton Warlock1 = new JButton("Warlock");

	private Hero x = null;
	private Hero y = null;

	private JButton start = new JButton("Start");

	private JButton Endturn = new JButton("End Turn");

	public controller() throws HearthstoneException, CloneNotSupportedException, IOException {
		view = new view();
		view.getCurrent().add(Hunter);
		view.getCurrent().add(Paladin);
		view.getCurrent().add(Priest);
		view.getCurrent().add(Mage);
		view.getCurrent().add(Warlock);

		view.getOpponent().add(Hunter1);
		view.getOpponent().add(Paladin1);
		view.getOpponent().add(Priest1);
		view.getOpponent().add(Mage1);
		view.getOpponent().add(Warlock1);

		Hunter.addActionListener(this);
		Paladin.addActionListener(this);
		Priest.addActionListener(this);
		Mage.addActionListener(this);
		Warlock.addActionListener(this);

		Hunter1.addActionListener(this);
		Paladin1.addActionListener(this);
		Priest1.addActionListener(this);
		Mage1.addActionListener(this);
		Warlock1.addActionListener(this);

		view.add(start, BorderLayout.CENTER);
		start.addActionListener(this);

		view.setVisible(true);
		view.revalidate();
		view.repaint();
	}

	public void initiate(Hero x, Hero y) {
		try {
			this.model = new Game(x, y);
			model.setListener(this);
			this.HeroA = new CustomButton(this, model.getCurrentHero());
			this.HeroB = new CustomButton(this, model.getOpponent());
		} catch (FullHandException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.view = new view();
		if (model.getCurrentHero() instanceof Hunter) {
			this.view.getCurrent().add(Hunter);
			this.Hunter.setText(x.toString());
		}
		if (model.getCurrentHero() instanceof Mage) {
			this.view.getCurrent().add(Mage);
			this.Mage.setText(x.toString());
		}
		if (model.getCurrentHero() instanceof Paladin) {
			this.view.getCurrent().add(Paladin);
			this.Paladin.setText(x.toString());
		}
		if (model.getCurrentHero() instanceof Priest) {
			this.view.getCurrent().add(Priest);
			this.Priest.setText(x.toString());
		}
		if (model.getCurrentHero() instanceof Warlock) {
			this.view.getCurrent().add(Warlock);
			this.Warlock.setText(x.toString());
		}

		if (model.getOpponent() instanceof Hunter) {
			this.view.getOpponent().add(Hunter1);
			this.Hunter1.setText(y.toString());
		}
		if (model.getOpponent() instanceof Mage) {
			this.view.getOpponent().add(Mage1);
			this.Mage1.setText(y.toString());
		}
		if (model.getOpponent() instanceof Paladin) {
			this.view.getOpponent().add(Paladin1);
			this.Paladin1.setText(y.toString());
		}
		if (model.getOpponent() instanceof Priest) {
			this.view.getOpponent().add(Priest1);
			this.Priest1.setText(y.toString());
		}
		if (model.getOpponent() instanceof Warlock) {
			this.view.getOpponent().add(Warlock1);
			this.Warlock1.setText(y.toString());
		}

		this.view.add(this.Endturn, BorderLayout.EAST);
		this.view.getCurrent().add(HeroA);
		this.view.getOpponent().add(HeroB);

		this.Endturn.addActionListener(this);
		this.HeroA.addActionListener(this);
		this.HeroB.addActionListener(this);

		CurrentHand = new ArrayList<CustomButton>();
		for (int z = 0; z < this.model.getCurrentHero().getHand().size(); z++) {
			CustomButton x1 = new CustomButton(this, this.model.getCurrentHero().getHand().get(z));
			this.view.getCurrent().add(x1);
			CurrentHand.add(x1);
		}
		OpponentHand = new ArrayList<CustomButton>();
		for (int z = 0; z < this.model.getOpponent().getHand().size(); z++) {
			CustomButton x1 = new CustomButton(this, this.model.getOpponent().getHand().get(z));
			this.view.getOpponent().add(x1);
			OpponentHand.add(x1);
			x1.setText(null);
		}
		


		view.revalidate();
		view.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( (JButton) e.getSource()instanceof JButton){
			JButton event = (JButton)e.getSource();
		if (event.equals(Hunter1) || event.equals(Mage1) || event.equals(Paladin1) || event.equals(Priest1)
				|| event.equals(Warlock1)) {
			switch (event.getActionCommand()) {
			case "Hunter":
				this.view.getOpponent().add(Hunter1, BorderLayout.CENTER);
				try {
					this.y = new Hunter();
				} catch (IOException | CloneNotSupportedException e2) {
					e2.printStackTrace();
				}
				break;

			case "Mage":
				this.view.getOpponent().add(Mage1, BorderLayout.CENTER);
				try {
					this.y = new Mage();
				} catch (IOException | CloneNotSupportedException e2) {
					e2.printStackTrace();
				}
				break;

			case "Paladin":
				this.view.getOpponent().add(Paladin1, BorderLayout.CENTER);
				try {
					this.y = new Paladin();
				} catch (IOException | CloneNotSupportedException e2) {
					e2.printStackTrace();
				}
				break;

			case "Priest":
				this.view.getOpponent().add(Priest1, BorderLayout.CENTER);
				try {
					this.y = new Priest();
				} catch (IOException | CloneNotSupportedException e2) {
					e2.printStackTrace();
				}
				break;

			case "Warlock":
				this.view.getOpponent().add(Warlock1, BorderLayout.CENTER);
				try {
					this.y = new Warlock();
				} catch (IOException | CloneNotSupportedException e2) {
					e2.printStackTrace();
				}
				break;
			}
		} else {
			if (event.equals(Hunter) || event.equals(Mage) || event.equals(Paladin) || event.equals(Priest)
					|| event.equals(Warlock)) {
				switch (event.getActionCommand()) {
				case "Hunter":
					this.view.getCurrent().add(Hunter, BorderLayout.CENTER);
					try {
						this.x = new Hunter();
					} catch (IOException | CloneNotSupportedException e2) {
						e2.printStackTrace();
					}
					break;

				case "Mage":
					this.view.getCurrent().add(Mage, BorderLayout.CENTER);
					try {
						this.x = new Mage();
					} catch (IOException | CloneNotSupportedException e2) {
						e2.printStackTrace();
					}
					break;

				case "Paladin":
					this.view.getCurrent().add(Paladin, BorderLayout.CENTER);
					try {
						this.x = new Paladin();
					} catch (IOException | CloneNotSupportedException e2) {
						e2.printStackTrace();
					}
					break;

				case "Priest":
					this.view.getCurrent().add(Priest, BorderLayout.CENTER);
					try {
						this.x = new Priest();
					} catch (IOException | CloneNotSupportedException e2) {
						e2.printStackTrace();
					}
					break;

				case "Warlock":
					this.view.getCurrent().add(Warlock, BorderLayout.CENTER);
					try {
						this.x = new Warlock();
					} catch (IOException | CloneNotSupportedException e2) {
						e2.printStackTrace();
					}
					break;
				}}
			 else {
				if (event.equals(start)) {
					this.initiate(x, y);
				} else {
					if (event.equals(Endturn)) {
						try {
							this.model.endTurn();
						} catch (FullHandException | CloneNotSupportedException e1) {
							JOptionPane.showMessageDialog(null, "Your Hand is full", "Hint",
									JOptionPane.WARNING_MESSAGE);
						}
						view.setVisible(true);
						view.revalidate();
						view.repaint();
					} 
					}
				}
			}
		}
		else {
						CustomButton event = (CustomButton)e.getSource();
						if (event.equals(HeroA)||event.equals(HeroB)) {
						if(hero ==true){
							try {
								Hero h = ((CustomButton)event).getH();
								if(!(h instanceof Mage)&&!(h instanceof Priest)) {
									h.useHeroPower();
								}else{
									if(h instanceof Mage){
										Mage j = (model.heroes.Mage) ((CustomButton)event).getH();
										if(CurrentHand.get(0).getFlag()==true)
											j.useHeroPower(this.model.getOpponent());
										else{
										  
											for(int b = 0;b<this.OpponentField.size();b++){
												if(this.OpponentField.get(b).getFlag()==true)
													j.useHeroPower((Minion)this.OpponentField.get(b).getC());
											}
										}
									}
									else{
										Priest y = (model.heroes.Priest) ((CustomButton)event).getH();
										if(CurrentHand.get(0).getFlag()==true)
											y.useHeroPower(this.model.getOpponent());
										else{
											for(int b = 0;b<this.OpponentField.size();b++){
												if(this.OpponentField.get(b).getFlag()==true)
													y.useHeroPower((Minion)this.OpponentField.get(b).getC());
											}
										}
									}
									}}
									catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
											| FullHandException | FullFieldException | CloneNotSupportedException e1) {
										e1.printStackTrace();
									}
						}
						
								else {
									((CustomButton)event).setFlag(true);
									hero = true;
								}
								
						}
						
							else {
								if(CurrentHand.contains(event)) {
									if(event.getC() instanceof Minion && (attacker == false || event.getFlag()==false)){
										try {
											model.getCurrentHero().playMinion((Minion)event.getC());
										} catch (NotYourTurnException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (NotEnoughManaException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (FullFieldException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									else{
										if(event.getC() instanceof Minion && attacker == true && event.getFlag()==true ){
											if(OpponentField.get(0).getFlag()==true)
												try {
													model.getCurrentHero().attackWithMinion((Minion)event.getC(), model.getOpponent());
												} catch (
														CannotAttackException
														| NotYourTurnException
														| TauntBypassException
														| NotSummonedException
														| InvalidTargetException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											else{
												for(int h = 0;h<OpponentField.size();h++){
													if(OpponentField.get(h).getFlag()==true && OpponentField.get(h).getC() instanceof Minion)
														try {
															model.getCurrentHero().attackWithMinion((Minion)event.getC(), (Minion)OpponentField.get(h).getC());
														} catch (
																CannotAttackException
																| NotYourTurnException
																| TauntBypassException
																| InvalidTargetException
																| NotSummonedException e1) {
															// TODO Auto-generated catch block
															e1.printStackTrace();
														}
												}
											}
										}
									else{
										if( event.getC() instanceof Spell && spell == true&& event.getFlag() == true){
											if(event.getC() instanceof AOESpell){
												try {
													model.getCurrentHero().castSpell((AOESpell)event.getC(),model.getOpponent().getField());
												} catch (
														NotEnoughManaException
														| NotYourTurnException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
											else{
												if(event.getC() instanceof HeroTargetSpell){
													try {
														model.getCurrentHero().castSpell((HeroTargetSpell)event.getC(), model.getOpponent());
													} catch (
															NotYourTurnException
															| NotEnoughManaException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
												}
												else{
													if(event.getC() instanceof MinionTargetSpell){
														for(int u =0;u<this.OpponentField.size();u++){
															if(this.OpponentField.get(u).getC()instanceof Minion &&this.OpponentField.get(u).getFlag()==true )
																try {
																	model.getCurrentHero().castSpell((MinionTargetSpell)event.getC(),(Minion) this.OpponentField.get(u).getC());
																} catch (
																		NotYourTurnException
																		| NotEnoughManaException
																		| InvalidTargetException e1) {
																	// TODO Auto-generated catch block
																	e1.printStackTrace();
																}
														}
														
													}
													else{
														if(event.getC() instanceof FieldSpell){
															try {
																model.getCurrentHero().castSpell((FieldSpell)event.getC());
															} catch (
																	NotYourTurnException
																	| NotEnoughManaException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
														}
														else{
															for(int j=0;j<model.getOpponent().getField().size();j++){
																if(OpponentField.get(j).getC()instanceof Minion)
																	try {
																		model.getCurrentHero().castSpell((LeechingSpell)event.getC(), (Minion)OpponentField.get(j).getC());
																	} catch (
																			NotYourTurnException
																			| NotEnoughManaException e1) {
																		// TODO Auto-generated catch block
																		e1.printStackTrace();
																	}
															}
														}
													}
												}
											}
										}
										else{
											if( event.getC() instanceof Spell &&( spell == false||event.getFlag()==false)){
												spell = true;
												event.setFlag(true);
											}
											}
										}
									}
									}
									
								
								
								}
							}
					}
				
			
		
	

	public void onGameOver() {
		if(this.model.getCurrentHero().getCurrentHP()!=0)
			JOptionPane.showMessageDialog(null, this.model.getCurrentHero().getName(), "Winner",
					JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, this.model.getOpponent().getName(), "Winner",
					JOptionPane.WARNING_MESSAGE);
		
		this.view.setVisible(false);

	}

	public static void main(String[] args) throws HearthstoneException, CloneNotSupportedException, IOException {
		new controller();
	}

	@Override
	public void endTurn() {
		
		ArrayList<CustomButton> temp = this.CurrentHand ;
		this.CurrentHand = this.OpponentHand ;
		this.OpponentHand = temp ;
		
		ArrayList<CustomButton> te =this.CurrentField;
		this.CurrentField =this.OpponentField;
		this.OpponentField=te;
		
		JPanel tr = this.view.getCurrent();
		this.view.setCurrent(this.view.getOpponent());
		this.view.setOpponent(tr);
		
		JPanel u = this.view.getCurrentground();
		this.view.setCurrentground(this.view.getOpponentground());
		this.view.setOpponentground(u);
		
		
		
		
		for(int y=0;y<this.OpponentHand.size();y++){
			this.OpponentHand.get(y).setText(null);
		}
		for(int y=2;y<this.CurrentHand.size();y++){
			this.CurrentHand.get(y).setText(model.getCurrentHero().getHand().get(y-2).toString());
		}
		if(this.CurrentHand.size()<10){
		CustomButton r = new CustomButton(this,this.model.getCurrentHero().getHand().get(this.model.getCurrentHero().getHand().size()-1));
		this.CurrentHand.add(r);
		this.view.getCurrent().add(r);
		}
		
		
		
		
	}

}
