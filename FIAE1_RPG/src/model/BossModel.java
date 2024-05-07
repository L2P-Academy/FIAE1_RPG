package model;


public class BossModel extends MobModel {
	//   private List<MobModel> minions;
 private int phase;
 
 
	// Constructor
 	public BossModel (String name, String race, int healthPoints, int baseDmg, int baseArmour, boolean isFriendly,
			int level, boolean isFlying, int phase) {
 		super(name, race, healthPoints, baseDmg, baseArmour, isFriendly, level, isFlying);
 		this.phase = phase;
 	}
 	// Beispiel - Bosse
 	
 	BossModel GoblinKing = new BossModel("Ralphus Bauchuss","Goblin" , 500 , 75, 50, false, 10, false, 3 );
 	// Phase 1: bis 60% normal, Phase 2: bis 20% ruft Minions, Phase 3: Unter 20% versucht er zu fliehen
 	
 	BossModel Dullahan = new BossModel("Todesritter, Dullahan","Untoter", 2000, 150, 150, true, 20, false, 2);
 	// Special Minion: Geisterpferd(halb so Stark wie Dullahan)
 	// Phase 1: bis 30% kämpft er auf dem Pferd. Phase 2: unter 30% steigt er aus dem Pferd und kämpft bis zum bitteren Ende.
 	
 	BossModel ErwachsenerDrache = new BossModel("Cadmus Drache der Finsternis", "Drache", 10000, 200, 500, false, 40, true, 3);
 	// Phase 1: bis 70% kämpft er auf dem Boden.
 	// Phase 2: er fliegt und macht ranged Atacks bis seine Fluegel kapput sind.
 	// phase 3: kämpft er in der Rage bis zum Tod. (2x Schaden)
 	
 	
 	
    // Getter und Setter
    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

   
    // Beispielmethoden
    public void attack() {
        // Implementierung für den Angriff des Bosses
    }

    public int calculateDamage() {
        // Implementierung für die Schadensberechnung des Bosses
        return 0;
    }
}
