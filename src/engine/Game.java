package engine;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator,HeroListener{
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener ;
	
	
	
	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException{
		this.firstHero=p1;
		this.secondHero=p2;
		int coin = (int) (Math.random()*2);
        currentHero=coin==0?firstHero:secondHero;
        opponent=currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1); 
		currentHero.setTotalManaCrystals(1);             
		currentHero.setListener(this);
		opponent.setListener(this);
		currentHero.setValidator(this);
		opponent.setValidator(this);
	      currentHero.drawCard();
			currentHero.drawCard();
			currentHero.drawCard();
			opponent.drawCard();
			opponent.drawCard();
			opponent.drawCard();
			opponent.drawCard();
		
	}
	public Hero getCurrentHero() {
		return currentHero;
	}
	public Hero getOpponent() {
		return opponent;
	}
	public void setListener(GameListener listener) {
		this.listener = listener;
	}
	@Override
	public void validateTurn(Hero user) throws NotYourTurnException {
		if(currentHero!=user)
			throw new NotYourTurnException();
		
	}
	@Override
	public void validateAttack(Minion attacker, Minion target)throws CannotAttackException, NotSummonedException,TauntBypassException, InvalidTargetException {
		if(attacker.isSleeping()) 
			throw new CannotAttackException("this minion cannot attack on this turn");
		if(attacker.isAttacked()) 
			throw new CannotAttackException("this minion has attacked already");	
		if(currentHero.getField().contains(target)) 
			throw new InvalidTargetException("you cannot attack friendly minions");
		if(opponent.getField().contains(target)== false) 
			throw new NotSummonedException("that minion isnot on the field"); 
		if(currentHero.getField().contains(attacker)==false) 
			throw new NotSummonedException("this minion isnot on the field"); 
		if(attacker.getAttack()==0)	
			throw new CannotAttackException("this minion hasnot attack points ");
		if(target.isTaunt()==false){{
		boolean taunt = false;
			for(int i =0;i<opponent.getField().size();i++){
				if(opponent.getField().get(i).isTaunt()==true){
					taunt = true;
					break;
				}}	
			if(taunt==true)
				throw new TauntBypassException("there is a minion with taunt");
		}}
	}
	
      
	@Override
	public void validateAttack(Minion attacker, Hero target)throws CannotAttackException, NotSummonedException,TauntBypassException, InvalidTargetException {
		if(attacker.isSleeping()) 
			throw new CannotAttackException("this minion cannot attack on this turn");
		if(attacker.isAttacked()) 
			throw new CannotAttackException("this minion has attacked already");	
		if(target.equals(currentHero)) 
			throw new InvalidTargetException("you cannot attack your hero");
		if(currentHero.getField().contains(attacker)==false)
			throw new NotSummonedException("this minion isnot on the field"); 
		if(attacker.getAttack()==0)	
			throw new CannotAttackException("this minion hasnot attack points ");
		if(attacker.getName().equals("Icehowl")) 
			throw new CannotAttackException("you cannot attack heros with an Icehowl minion ");
		boolean taunt = false;
			for(int i =0;i<opponent.getField().size();i++){
				if(opponent.getField().get(i).isTaunt()==true){
					taunt = true;
					break;
				}}	
			if(taunt==true)
				throw new TauntBypassException("there is a minion with taunt");
			
	}
		
	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException {
		if(currentHero.getCurrentManaCrystals()<card.getManaCost())
			throw new NotEnoughManaException("not enough mana crystals to play this card");
	}
	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
	     if(currentHero.getField().size()==7)
	    	 throw new FullFieldException ("your field is full") ;
	}
	@Override
	public void validateUsingHeroPower(Hero hero)throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		if(hero.isHeroPowerUsed()==true)
			throw new HeroPowerAlreadyUsedException("It has been used already");
		else{
			if(hero.getCurrentManaCrystals()<2)
				throw new NotEnoughManaException("not enough mana crystals to use your hero's power") ;
		}
			
	}
	@Override
	public void onHeroDeath() {
		listener.onGameOver();
	}
	@Override
	public void damageOpponent(int amount) {
			opponent.setCurrentHP(opponent.getCurrentHP()-amount);
	}
	@Override
	public void endTurn() throws FullHandException, CloneNotSupportedException {
		Hero temp = currentHero ;
		currentHero=opponent;
		opponent = temp;
		currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
		currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
		currentHero.setHeroPowerUsed(false) ;
		int c = 0 ;
		while(c<currentHero.getField().size()){
			if(currentHero.getField().get(c).isSleeping()==true)
				currentHero.getField().get(c).setSleeping(false);
			currentHero.getField().get(c).setAttacked(false);
			c++;
		}
		currentHero.drawCard();
		listener.endTurn();

		
		
	}
	

}
