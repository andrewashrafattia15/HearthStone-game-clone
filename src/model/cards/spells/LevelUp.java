package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class LevelUp extends Spell implements FieldSpell {
    
	public LevelUp (){
		   super("Level Up!",6 ,Rarity.EPIC);
		}

	
	public void performAction(ArrayList<Minion> field) {
		int x = 0; 
		while(x<field.size()){
			if(field.get(x).getName().equals("Silver Hand Recruit")){
			field.get(x).setAttack(field.get(x).getAttack()+1);
			field.get(x).setMaxHP(field.get(x).getMaxHP()+1);
			field.get(x).setCurrentHP(field.get(x).getCurrentHP()+1);
			}x++;
		}
		
	}

}
