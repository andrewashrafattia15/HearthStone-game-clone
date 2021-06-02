package model.heroes;
import java.util.Collections;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Warlock extends Hero{

	public Warlock()throws IOException, CloneNotSupportedException{
	  	super("Gul'dan");
  }
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Card> Warlock = new ArrayList<>();
		Warlock.addAll(Hero.getNeutralMinions(Hero.getAllNeutralMinions("neutral_minions.csv"), 13));
		for(int x=0; x<Warlock.size();x++){
		((Minion)Warlock.get(x)).setListener(this);
		}
		Warlock.add(new CurseOfWeakness().clone());
		Warlock.add(new CurseOfWeakness().clone());
		Warlock.add(new SiphonSoul().clone());
		Warlock.add(new SiphonSoul().clone());
		Warlock.add(new TwistingNether().clone());
		Warlock.add(new TwistingNether().clone());
		Minion Wilfred = new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false);
		Wilfred.setListener(this);
		Warlock.add(Wilfred.clone());
		Collections.shuffle(Warlock);
		getDeck().addAll(Warlock);
	}

	
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		this.setCurrentHP(this.getCurrentHP()-2);
		this.drawCard();
	}}