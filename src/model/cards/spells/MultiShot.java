package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell{
   
	public MultiShot (){
		   super("Multi-Shot",4 ,Rarity.BASIC);
		}

	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		if (oppField.size()==1){
			if(oppField.get(0).isDivine())
				oppField.get(0).setDivine(false);
			else
			oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP()-3);
		}
		else {
			if(oppField.size()!=0&&oppField.size()!=1){
				int x =(int) ((Math.random()*oppField.size())+1)-1;
				if(oppField.get(x).isDivine())
					oppField.get(x).setDivine(false);
				else
				oppField.get(x).setCurrentHP(oppField.get(x).getCurrentHP()-3);
				int y=(int)((Math.random()*oppField.size())+1)-1;
				while(x==y)
				y =(int)((Math.random()*oppField.size())+1)-1;
				if(oppField.get(y).isDivine())
					oppField.get(y).setDivine(false);
				else
				oppField.get(y).setCurrentHP(oppField.get(y).getCurrentHP()-3);
			}}
	}

}
