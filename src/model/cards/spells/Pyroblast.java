package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements HeroTargetSpell,MinionTargetSpell{
   
	public Pyroblast (){
		   super("Pyroblast",10 ,Rarity.EPIC);
		}

	
	public void performAction(Minion m) throws InvalidTargetException {
		if(m.isDivine())
			m.setDivine(false);
		else
		m.setCurrentHP(m.getCurrentHP()-10);
	}

	
	public void performAction(Hero h) {
		if(h.getCurrentHP()<=10){
			   h.setCurrentHP(0);
		   }
		   else 
			  h.setCurrentHP(h.getCurrentHP()-10);
		}

}
