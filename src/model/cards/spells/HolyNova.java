package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell{
     
	public HolyNova (){
    	 super("Holy Nova",5,Rarity.BASIC);
     }

	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		for(int i =0; i<oppField.size();i++){
			if(oppField.get(i).isDivine())
				oppField.get(i).setDivine(false);
			else {
				if(oppField.get(i).getCurrentHP()<=2){
					oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-2);
			    i--;
			    continue;
				}
				oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-2);
		}
			}
		int y = 0 ;
		while(y<curField.size()){
			curField.get(y).setCurrentHP(curField.get(y).getCurrentHP()+2);
			y++;
	}
	}
}
