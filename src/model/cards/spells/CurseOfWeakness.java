package model.cards.spells;

import java.util.ArrayList;
import model.heroes.*;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class CurseOfWeakness extends Spell implements AOESpell{

	public CurseOfWeakness (){
	   super("Curse of Weakness",2 ,Rarity.RARE);
	}

	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
			int x = 0 ;
			while(x<oppField.size()){
				oppField.get(x).setAttack(oppField.get(x).getAttack()-2 );
				x++;
			}
		}
}
