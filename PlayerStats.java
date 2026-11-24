/** Runtime player stats and level progression. */
class PlayerStats {
    private float hp;
    private float maxHp;
    private float attack;
    private float defense;
    private int level = 1;
    private int experience;

    float getHp() { return hp; }
    float getMaxHp() { return maxHp; }
    float getAttack() { return attack; }
    float getDefense() { return defense; }
    int getLevel() { return level; }
    int getExperience() { return experience; }

    void setHp(float value) { hp = value; }
    void setMaxHp(float value) { maxHp = value; }
    void setAttack(float value) { attack = value; }
    void setDefense(float value) { defense = value; }
    void setLevel(int value) { level = Math.max(1, value); }
    void setExperience(int value) { experience = Math.max(0, value); }

    void resetProgress() {
        level = 1;
        experience = 0;
    }
}
