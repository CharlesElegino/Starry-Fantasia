/** Mutable statistics for the enemy selected for the current stage. */
class EnemyStatus {
    private String name;
    private float hp;
    private float attack;
    private float defense;
    private final int speed;

    public EnemyStatus() {
        speed = 7;
        loadStageOne();
    }

    public void loadStageOne() {
        name = "Mystogan";
        // The first fight should take several turns and threaten every class
        // without making the opening encounter feel like a wall.
        hp = 115;
        attack = 31;
        defense = 8;
    }

    public void loadStageTwo() {
        name = "Sylvr";
        hp = 250;
        attack = 44;
        defense = 24;
    }

    public void loadStageThree() {
        name = "Vestroia";
        hp = 400;
        attack = 64;
        defense = 38;
    }

    /** Scale stage opponents modestly as the player gains levels. */
    public void scaleForPlayerLevel(int level) {
        int extraLevels = Math.max(0, level - 1);
        float hpScale = 1.0f + (0.12f * extraLevels);
        float attackScale = 1.0f + (0.08f * extraLevels);
        float defenseScale = 1.0f + (0.05f * extraLevels);
        hp *= hpScale;
        attack *= attackScale;
        defense *= defenseScale;
    }

    public String getName() { return name; }
    public float getHp() { return hp; }
    public float getAttack() { return attack; }
    public float getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public void setHp(float value) { hp = value; }
    public void setAttack(float value) { attack = value; }
    public void setDefense(float value) { defense = value; }
}
