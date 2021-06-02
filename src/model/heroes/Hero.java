package model.heroes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import engine.*;
import exceptions.*;
import model.cards.minions.*;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.Card;
import model.cards.Rarity;

public abstract class Hero extends Card implements MinionListener {
   
	private String name ;
	private int currentHP = 30;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals; 
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage;
	private HeroListener listener;
	private ActionValidator validator ;
	
	public Hero(String name) throws IOException, CloneNotSupportedException{
		this.name=name;
		this.currentHP=30;
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		field = new ArrayList<Minion>();
		buildDeck();
		
	}

	
	
	public String getName() {
		return name;
	}
	public int getCurrentHP() {
		return currentHP;
	}
	public void setCurrentHP(int currentHP) {
		if(currentHP<=0)
			getListener().onHeroDeath();
		else if(currentHP<=30)
			this.currentHP = currentHP;
		else
			this.currentHP=30;
		}
	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}
	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
	}
	public int getTotalManaCrystals() {
		return totalManaCrystals;
		}
	public void setTotalManaCrystals(int totalManaCrystals) {
		if(totalManaCrystals<=10)
		this.totalManaCrystals = totalManaCrystals;
		else
			this.totalManaCrystals=10;
	}
	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}
	public void setCurrentManaCrystals(int totalManaCrystals) {
		if(totalManaCrystals<=10)
			this.currentManaCrystals = totalManaCrystals;
		else
				this.currentManaCrystals=10;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public ArrayList<Minion> getField() {
		return field;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public static final ArrayList<Minion> getAllNeutralMinions(String neutral_minions) throws IOException{
		ArrayList<Minion>minions = new ArrayList<>();
		String temp = "";
		BufferedReader br = new BufferedReader( new FileReader(neutral_minions));
		while ((temp = br.readLine()) != null) {
		    String[] minion = temp.split(",");
		    String Name = minion[0];
		    Minion new_minion;
		    int Mana_Cost = Integer.parseInt(minion[1]);
		    Rarity rarity = null ;
		    switch(minion[2]){
		      case "b":rarity = Rarity.BASIC;break; 
		      case "c":rarity = Rarity.COMMON;break;
		      case "r":rarity = Rarity.RARE;break;
		      case "e":rarity = Rarity.EPIC;break;
		      case "l":rarity = Rarity.LEGENDARY;break; 
		     }
		    int Attack = Integer.parseInt(minion[3]);
		    int MaxHP = Integer.parseInt(minion[4]);
		    boolean Taunt = Boolean.parseBoolean(minion[5]);
		    boolean Divine = Boolean.parseBoolean(minion[6]);
		    boolean Charge = Boolean.parseBoolean(minion[7]);
		    if(Name.equals("Icehowl"))
		    new_minion = new Icehowl();
		    else
		    new_minion = new Minion(Name,Mana_Cost,rarity,Attack,MaxHP,Taunt,Divine,Charge); 
		    minions.add(new_minion);
		}
		br.close();
		return minions;
	}	
	
	
	
	public static final ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions,int count) throws CloneNotSupportedException {
		ArrayList<Minion> new_minions = new ArrayList<Minion>();int c = 0;int x =0 ;
		
		while(x<count){
			c =(int)(Math.random()*minions.size());
			Minion Legendary = minions.get(c) ;
			int copy = 0 ;
				for(int y =0;y<new_minions.size();y++){
					if(Legendary.getName()==new_minions.get(y).getName()){
						copy++;
				   }
			     }
				if(copy==0){
					new_minions.add(minions.get(c).clone());
					x++;
				   
				}
				else{
					if(copy==1&&(Legendary.getRarity()!=Rarity.LEGENDARY)){
						new_minions.add((Minion) minions.get(c).clone());
						x++;
					}
				}
				
			}
	 	 return new_minions ;
 }
	
	abstract public void buildDeck() throws IOException, CloneNotSupportedException ;



	public HeroListener getListener() {
		return listener;
	}



	public void setListener(HeroListener listener) {
		this.listener = listener;
	}

	 public void onMinionDeath(Minion m) { 
			this.field.remove(m);
		}


	public void setValidator(ActionValidator validator) {
		this.validator = validator;
	}
		 
	 public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException , FullFieldException, CloneNotSupportedException{
		 validator.validateUsingHeroPower(this);
		 validator.validateTurn(this);
		 this.setCurrentManaCrystals(this.getCurrentManaCrystals()-2);
		 this.setHeroPowerUsed(true);
		 }
     public void playMinion(Minion m) throws NotYourTurnException, NotEnoughManaException, FullFieldException{
		 validator.validatePlayingMinion(m);
		 validator.validateTurn(this);
		 validator.validateManaCost(m);
		 this.getHand().remove(m);
		 this.getField().add(m);
	 }
	 
	 public void attackWithMinion(Minion attacker, Minion target) throws CannotAttackException, NotYourTurnException, TauntBypassException
	 ,InvalidTargetException, NotSummonedException{
		 validator.validateTurn(this);
		 validator.validateAttack(attacker, target);
		attacker.attack(target);
	 }
	 
	 public void attackWithMinion(Minion attacker, Hero target) throws CannotAttackException, NotYourTurnException, TauntBypassException
	 , NotSummonedException, InvalidTargetException{
		 validator.validateTurn(this);
		 validator.validateAttack(attacker, target);
		 attacker.attack(target);
		 
	 }
	 public void castSpell(FieldSpell s) throws NotYourTurnException, NotEnoughManaException{
		 int c = 0;Boolean x = false ;
		 validator.validateTurn(this);
		 if(this instanceof Mage){
		   while(c<this.getField().size()){
			   if(this.getField().get(c).getName()=="Kalycgos"){
				   x=true;
				   break;
			   }
			   c++;
		   }
		 if(x==true)
			 ((Card) s).setManaCost(((Card) s).getManaCost()-4);
		 }
		 validator.validateManaCost((Card)s);
		 this.getHand().remove(s);
		 s.performAction(this.getField());
		 this.setCurrentManaCrystals(this.getCurrentManaCrystals()-((Card) s).getManaCost());
	 
	 }
	 public void castSpell(AOESpell s, ArrayList<Minion >oppField) throws NotYourTurnException, NotEnoughManaException{
		 int c = 0;boolean x = false ;
		 validator.validateTurn(this); 
		 if(this instanceof Mage){
			   while(c<this.getField().size()){
				   if(this.getField().get(c).getName()=="Kalycgos"){
					   x=true;
					   break;
				   }
				   c++;
			   }
			 if(x==true)
				 ((Card) s).setManaCost(((Card) s).getManaCost()-4);
			 }
		 validator.validateManaCost((Card)s);
		 this.getHand().remove(s);
		 s.performAction(oppField, this.getField());
		 this.setCurrentManaCrystals(this.getCurrentManaCrystals()-((Card) s).getManaCost());

	 }
	 public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException, InvalidTargetException{
		 int c = 0;boolean x = false ;
		 validator.validateTurn(this);
		 if(this instanceof Mage){
			   while(c<this.getField().size()){
				   if(this.getField().get(c).getName()=="Kalycgos"){
					   x=true;
					   break;
				   }
				   c++;
			   }
			 if(x==true)
				 ((Card) s).setManaCost(((Card) s).getManaCost()-4);
		 }
		 validator.validateManaCost((Card)s);
		 this.getHand().remove(s);
		 s.performAction(m);
		 this.setCurrentManaCrystals(this.getCurrentManaCrystals()-((Card) s).getManaCost());
	 
		 }
	 public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException, NotEnoughManaException{
		 int c = 0;boolean x = false ;
		 validator.validateTurn(this);
		 if(this instanceof Mage){
			   while(c<this.getField().size()){
				   if(this.getField().get(c).getName()=="Kalycgos"){
					   x=true;
					   break;
				   }
				   c++;
			   }
			 if(x==true)
				 ((Card) s).setManaCost(((Card) s).getManaCost()-4);
			 }
		 validator.validateManaCost((Card)s);
		 this.getHand().remove(s);
		 s.performAction(h);
		 this.setCurrentManaCrystals(this.getCurrentManaCrystals()-((Card) s).getManaCost());
	 }
	 public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException{
		 int c = 0;boolean x = false ;
		 validator.validateTurn(this);
		 if(this instanceof Mage ){
			   while(c<this.getField().size()){
				   if(this.getField().get(c).getName()=="Kalycgos"){
					   x=true;
					   break;
				   }
				   c++;
			   }
			 if(x==true)
				 ((Card) s).setManaCost(((Card) s).getManaCost()-4);
			 }
		 validator.validateManaCost((Card)s);
		 this.getHand().remove(s);
		 this.setCurrentHP(this.getCurrentHP()+s.performAction(m));
		 this.setCurrentManaCrystals(this.getCurrentManaCrystals()-((Card) s).getManaCost());
	 }
	 public void endTurn() throws FullHandException, CloneNotSupportedException{
		 this.listener.endTurn();
	 }
	 public Card drawCard() throws FullHandException, CloneNotSupportedException {
		 Card x = null ;
		 if(this.getDeck().isEmpty()==false)
			 x=this.getDeck().remove(0);
		 if(this.getHand().size()==10)
			 throw new FullHandException("Your Hand is full",x);
		 if(this.getDeck().isEmpty()==true){
			 this.setCurrentHP(this.getCurrentHP()-this.fatigueDamage);
			 this.fatigueDamage++;
		 }
		 boolean wilfred = false ;
		 for(int v = 0;v<this.getField().size();v++){
			 if(this.getField().get(v).getName()==("Wilfred Fizzlebang"))
				 wilfred = true ;
			 break;
		 }
		 if(wilfred == true){
			 if(x instanceof Minion)
				 x.setManaCost(0);
		 }
		 boolean Chromaggus = false ;
		 for(int v = 0;v<this.getField().size();v++){
			 if(this.getField().get(v).getName()==("Chromaggus")&&this.getHand().size()<10){
				 Chromaggus=true ;
			     break;
			 }
		 }
		 if(Chromaggus==true)
			 this.getHand().add(x.clone());
		 if(this.getHand().size()<10)
		 this.getHand().add(x);
		 return x ;
		 
	 }
	 public String toString(){
		 return "<html>"+ this.getName()+"<br>"+this.getCurrentHP()+"<br>"+this.getCurrentManaCrystals()+"<br>"+this.getTotalManaCrystals()+"<br>"+this.getDeck().size()+"<br>";
	 }
	}



	 
		



		












