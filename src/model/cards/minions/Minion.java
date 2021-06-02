package model.cards.minions;
import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable{
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private boolean charge;
	private MinionListener listener;
	
	public Minion(String name,int manaCost,Rarity rarity, int attack,int maxHP,boolean
			taunt,boolean divine,boolean charge){
		super(name,manaCost,rarity);
		if(attack<0)
			this.attack=0;
		else
			this.attack=attack;
		this.maxHP=maxHP;
		this.currentHP = maxHP;
		this.taunt=taunt;
		this.divine=divine;
		this.charge=charge;
		this.sleeping = !charge;
	}
	

	public Minion() {
	}


	public void setListener(MinionListener listener) {
		this.listener = listener;
	}


	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		if(attack<0)
			this.attack=0;
		else
			this.attack=attack;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int CurrentHP) {
		if(CurrentHP<=0)
			listener.onMinionDeath(this);
		else
			this.currentHP = CurrentHP;
		
	}


	public void attack(Hero target) throws InvalidTargetException{
		if(this.getName().equals("Icehowl"))
			throw new InvalidTargetException ("you cannot attack a hero with an Icehowl minion ");
		else
		target.setCurrentHP(target.getCurrentHP()-this.getAttack());
		this.setAttacked(true);
	}
	public void attack(Minion target){
		if(this.isDivine()==true && target.isDivine()==true){
			if(target.getAttack()>0)
				this.setDivine(false);
			if(this.getAttack()>0)
				target.setDivine(false);
		}
			if(this.isDivine()==false && target.isDivine()==false){
				this.setCurrentHP(this.getCurrentHP()-target.getAttack());
				target.setCurrentHP(target.getCurrentHP()-this.getAttack());
			}
			if(this.isDivine()==true && target.isDivine()==false){
				if(target.getAttack()>0)
				this.setDivine(false);
				target.setCurrentHP(target.getCurrentHP()-this.getAttack());
			}
			if(target.isDivine()==true && this.isDivine()==false){
				if(target.getAttack()>0)
					this.setDivine(false);
				if(this.getAttack()>0)
					target.setDivine(false);
				this.setCurrentHP(this.getCurrentHP()-target.getAttack());
			}
			this.setAttacked(true);
		}
		


	public boolean isTaunt() {
		return taunt; 
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDivine() {
		return divine;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isSleeping() {
		return sleeping;
	}
	
	public String helperTaunt (){
		boolean x=this.taunt;
		if(x==false)
			return "No Taunt";
		else
			return "Taunt";
	}
    public String helperCharge (){
    	boolean x=this.charge;
    	if(x==false)
			return "No Charge";
		else
			return "Charge";

	}
    public String helperDivine (){
    	boolean x = this.divine ;
    	if(x==false)
			return "No Divine";
		else
			return "Divine";

    }

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public Minion clone() throws CloneNotSupportedException{
		 return (Minion) super.clone();
    }
	    
	public String toString (){
		return super.toString()+this.getAttack()+"<br>"+this.getCurrentHP()+"<br>"+this. helperTaunt()+"<br>"+this. helperCharge()+"<br>"+this. helperDivine() ;
	}
	
	

}
