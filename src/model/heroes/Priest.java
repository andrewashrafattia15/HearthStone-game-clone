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


public class Priest extends Hero{

	public Priest()throws IOException, CloneNotSupportedException{
	  	super("Anduin Wrynn");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Card> Priest = new ArrayList<>();
		Priest.addAll(Hero.getNeutralMinions(Hero.getAllNeutralMinions("neutral_minions.csv"), 13));
		for(int x=0; x<Priest.size();x++){
		((Minion)Priest.get(x)).setListener(this);
		}
		Priest.add(new DivineSpirit().clone());
		Priest.add(new DivineSpirit().clone());
		Priest.add(new HolyNova().clone());
		Priest.add(new HolyNova().clone());
		Priest.add(new ShadowWordDeath().clone());
		Priest.add(new ShadowWordDeath().clone());
		Minion Velen = new Minion("Prophet Velen",7,Rarity.LEGENDARY,7,7,false,false,false);
		Velen.setListener(this);
		Priest.add(Velen.clone());
		Collections.shuffle(Priest);
		getDeck().addAll(Priest);
	}

   

	
	public void useHeroPower(Hero h) throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		boolean f = false  ;
		for(int x=0; x<this.getField().size();x++){
			if(this.getField().get(x).getName()=="Prophet Velen")
				f=true;
		}
		if(f==true)
		h.setCurrentHP(h.getCurrentHP()+8);
		else
			h.setCurrentHP(h.getCurrentHP()+2);
	}
	
	public void useHeroPower(Minion h) throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		boolean f = false ;
		for(int x=0; x<this.getField().size();x++){
			if(this.getField().get(x).getName()=="Prophet Velen")
				f=true;
		}
		if(f==true)
		h.setCurrentHP(h.getCurrentHP()+8);
		else
			h.setCurrentHP(h.getCurrentHP()+2);
	}
}
