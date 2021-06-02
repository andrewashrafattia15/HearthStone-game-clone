package model.cards.spells;
import model.cards.Card;
import model.cards.Rarity;

abstract public class Spell extends Card implements Cloneable {
    
	public Spell(String name, int manaCost ,Rarity rarity){
		super(name,manaCost,rarity);
	}
	public String toString (){
		return super.toString();
	}
}
