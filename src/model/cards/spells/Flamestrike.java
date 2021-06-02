package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {
	
	public Flamestrike (){
		super("Flamestrike",7,Rarity.BASIC);
	}

	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		int x = 0 ;
		while(x<oppField.size()){
			if(oppField.get(x).isDivine()==true){
			oppField.get(x).setDivine(false);
			x++;}
			else if(oppField.get(x).getCurrentHP()<=4){
			oppField.get(x).setCurrentHP(oppField.get(x).getCurrentHP()-4);
			continue; // re enter loop without incrementation
			}
			else {
				oppField.get(x).setCurrentHP(oppField.get(x).getCurrentHP()-4);
				x++;}
		}
		
	}

}
