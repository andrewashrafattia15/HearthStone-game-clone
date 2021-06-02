package model.cards;

import model.cards.minions.Minion;

abstract public class Card implements Cloneable{
	private String name;
	private int manaCost;
	private Rarity rarity;
	public Card(){}
	public Card(String name,int manaCost,Rarity rarity){
		this.name=name;
		this.rarity=rarity;
		if(manaCost<0)
			this.manaCost=0;
		else if(manaCost>10)
				this.manaCost=10;
		else 
				this.manaCost=manaCost;
	}
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getManaCost() {
		return manaCost;
	}
	
	public void setManaCost(int manaCost) {
		if(manaCost<0)
			this.manaCost=0;
		else if(manaCost>10)
				this.manaCost=10;
		else 
				this.manaCost=manaCost;
	}
	
	public Rarity getRarity() {
		return rarity;
	}
	public Card clone() throws CloneNotSupportedException{
	 return (Card) super.clone();
	}
    
	public String toString (){
		return "<html>"+this.getName()+"<br>"+this.getManaCost()+"<br>"+this.getRarity()+"<br>";
	}
	

}
