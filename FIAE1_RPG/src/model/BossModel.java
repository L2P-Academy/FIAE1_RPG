public class BossModel {
    private List<MobModel> minions;
    private int phase;

    public BossModel(List<MobModel> minions, int phase) {
        this.minions = minions;
        this.phase = phase;
    }

    // Methoden entsprechend dem UML-Diagramm
    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public List<MobModel> getMinions() {
        return minions;
    }

    public void setMinions(List<MobModel> minions) {
        this.minions = minions;
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
