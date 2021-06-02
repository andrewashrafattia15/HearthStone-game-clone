package model.heroes;
import java.util.Collections;

import model.cards.spells.*; 
import model.cards.minions.Minion;
import model.heroes.Hero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import engine .ActionValidator;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.*;
public class Hunter extends Hero{
	
public Hunter() throws IOException, CloneNotSupportedException{
	  	super("Rexxar");
	}


public void buildDeck() throws IOException, CloneNotSupportedException {
	ArrayList<Card> Hunter = new ArrayList<>();
	 Hunter.addAll(Hero.getNeutralMinions(Hero.getAllNeutralMinions("neutral_minions.csv"), 15));
	 for(int x=0; x<Hunter.size();x++){
	      ((Minion) Hunter.get(x)).setListener(this);
	 }
	 Hunter.add(new KillCommand().clone());
	 Hunter.add(new KillCommand().clone());
	 Hunter.add(new MultiShot().clone());
	 Hunter.add(new MultiShot().clone());
	 Minion Krush = new Minion("King Krush",9,Rarity.LEGENDARY,8,8,false,false,true);
	 Krush.setListener(this);
	 Hunter.add(Krush.clone());
	 Collections.shuffle(Hunter);
	 getDeck().addAll(Hunter);
     
}


public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
	super.useHeroPower();
	getListener().damageOpponent(2);
}



}
