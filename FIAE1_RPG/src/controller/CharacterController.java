package controller;

public class CharacterController {
	
	private int addHealthPoints(int healthPoints) {
		
		/*
		Im CharacterModel ist keine maximale HP-Zahl genannt, d.h. 
		fehlt ein Check (=if-Abfrage), damit die maximale HP-Zahl nicht
		überschritten wird. 
		*/
		
		this.healthPoints += healthPoints;
		return healthPoints;
		
	}
	
	private int reduceHealthPoints(int healthPoints) {
		
		this.healthPoints -= healthPoints;
		if (healthPoints <= 0) {
			// GAME OVER => Wie einbinden?
		} else {
			return healthPoints;
		}
		
	}
	
	private int addManaPoints(int manaPoints) {
		
		/*
		Im CharacterModel ist keine maximale MP-Zahl genannt, d.h. 
		fehlt ein Check (=if-Abfrage), damit die maximale MP-Zahl nicht
		überschritten wird. 
		*/
		
		this.manaPoints += manaPoints;
		return manaPoints;
		
	}
	
	private int reduceManaPoints(int manaPoints) {
		
		this.manaPoints -= manaPoints;
		if (manaPoints <= 0) {
			manaPoints = 0; 
			return manaPoints;
		} else {
			return manaPoints;
		}
		
	}
	
	private int addXP(int expPoints) {
		this.expPoints += expPoints;
		return expPoints;
	}
	
	private int levelUp() {
		// Konditionen werden in einem XpModel o.ä. definiert?
	}

}
