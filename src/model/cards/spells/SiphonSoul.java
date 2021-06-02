package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class SiphonSoul extends Spell implements LeechingSpell{
   
	public SiphonSoul (){
		   super("Siphon Soul",6 ,Rarity.RARE);
		}

	
	public int performAction(Minion m) {
		if(m.isDivine()== true){
			m.setDivine(false);
			m.setCurrentHP(0);
		}
		else 
				m.setCurrentHP(0);
		
		return 3;
	}

}
