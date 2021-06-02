package model.heroes;
import java.util.Collections;
import java.io.IOException;
import java.util.ArrayList;
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

public class Paladin extends Hero{
	
	public Paladin()throws IOException, CloneNotSupportedException{
	  	super("Uther Lightbringer");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Card> Paladin = new ArrayList<>();
		Paladin.addAll(Hero.getNeutralMinions(Hero.getAllNeutralMinions("neutral_minions.csv"), 15));
		for(int x=0; x<Paladin.size();x++){
		((Minion) Paladin.get(x)).setListener(this);
		}
		Paladin.add(new SealOfChampions().clone());
		Paladin.add(new SealOfChampions().clone());
		Paladin.add(new LevelUp().clone());
		Paladin.add(new LevelUp().clone());
		Minion Tirion = new Minion("Tirion Fordring",4,Rarity.LEGENDARY,6,6,true,true,false);
		Tirion.setListener(this);
		Paladin.add(Tirion.clone());
		Collections.shuffle(Paladin);
		getDeck().addAll(Paladin);
	}

	
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		Minion x = new Minion( "Silver Hand Recruit",1,Rarity.BASIC,1,1,false,false,false);
		if(this.getField().size()==7) 
			throw new FullFieldException("Field is Full cannot use hero power");
		else this.getField().add(x);
		
	}
	



}
