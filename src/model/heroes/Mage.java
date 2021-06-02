package model.heroes;
import java.util.Collections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Mage extends Hero{
	
	public Mage()throws IOException, CloneNotSupportedException{
	  	super("Jaina Proudmoore");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Card> Mage = new ArrayList<>();
		Mage.addAll(Hero.getNeutralMinions(Hero.getAllNeutralMinions("neutral_minions.csv"), 13));
		for(int x=0; x<Mage.size();x++){
		((Minion) Mage.get(x)).setListener(this);
		}
		Mage.add(new Polymorph().clone());
		Mage.add(new Polymorph().clone());
		Mage.add(new Flamestrike().clone());
		Mage.add(new Flamestrike().clone());
		Mage.add(new Pyroblast().clone());
		Mage.add(new Pyroblast().clone());
		Minion Kal = new Minion("Kalycgos",10,Rarity.LEGENDARY,4,12,false,false,false);
		Kal.setListener(this);
		Mage.add(Kal.clone());
		Collections.shuffle(Mage);
		getDeck().addAll(Mage);
	    
	}
	public void useHeroPower(Hero h) throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		h.setCurrentHP(h.getCurrentHP()-1);
	}
	public void useHeroPower(Minion h) throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		if(h.isDivine())
			h.setDivine(false);
		else
		h.setCurrentHP(h.getCurrentHP()-1);
	}





}
