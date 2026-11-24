/** Mutable player character statistics selected in the character menu. */
class CharacterStatus {
    private String classes = "Swordsman";
    private float hp;
    private float attack;
    private float defense;

    public CharacterStatus() {
        configureSwordsman();
    }

    public void configureSwordsman() {
        classes = "Swordsman";
        hp = 150;
        attack = 20;
        defense = 15;
    }

    public void configureRanger() {
        classes = "Ranger";
        hp = 120;
        attack = 25;
        defense = 10;
    }

    public void configureMagus() {
        classes = "Magus";
        hp = 105;
        attack = 30;
        defense = 8;
    }

    public String getClassName() { return classes; }
    public float getHp() { return hp; }
    public float getAttack() { return attack; }
    public float getDefense() { return defense; }
}
