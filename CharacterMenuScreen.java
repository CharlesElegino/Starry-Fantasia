import javax.swing.*;
import java.awt.*;

/** Screen container and layout builder for the CharacterMenuScreen screen. */
public final class CharacterMenuScreen extends ScreenPanel {
    void build(Game game) {
        game.characterMenuScreen.removeAll();
        game.characterMenuScreen.install(game);
        game.characterMenuScreen.add(game.safeZonePanel);
        game.classWrite2.setText(game.characterStats.getClassName());
        game.hpWrite2.setText(String.valueOf((int) game.playerStats.getHp()));
        game.atkWrite2.setText(String.valueOf((int) game.playerStats.getAttack()));
        game.defWrite2.setText(String.valueOf((int) game.playerStats.getDefense()));
        game.levelWrite.setText(String.valueOf(game.playerStats.getLevel()));
        game.experienceWrite.setText(game.playerStats.getExperience() + " / " + game.experienceForNextLevel());
        game.experienceBar.setMaximum(game.experienceForNextLevel());
        game.experienceBar.setValue(game.playerStats.getExperience());
        game.experienceBar.setStringPainted(true);
        game.experienceBar.setString("Experience");
        game.safeZonePanel.setLayout(null);
        game.safeZonePanel.setBounds(0, 0, 960, 540);
        game.safeZonePanel.setOpaque(false);
        // STAT PANEL LAYOUT
        game.safeZonePanel.add(game.statPanel);
        game.statPanel.setLayout(null);
        game.statPanel.setBounds(70, 55, 560, 420);
        game.statPanel.setBackground(new Color(12, 20, 38, 210));
        game.statPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 183, 112, 210), 2, true),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        // SIDE PANEL
        game.safeZonePanel.add(game.sidePanel);
        game.sidePanel.setLayout(null);
        game.sidePanel.setBounds(660, 55, 230, 420);
        game.sidePanel.setBackground(new Color(12, 20, 38, 210));
        game.sidePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 183, 112, 210), 2, true),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        // STAT DISPLAY
        game.statPanel.add(game.info2);
        game.info2.setBounds(20, 12, 520, 40);
        game.info2.setForeground(new Color(255, 225, 164));
        game.info2.setFont(new Font("Georgia", Font.BOLD, 23));
        // NAME LABEL
        game.statPanel.add(game.nameDisplay2);
        game.nameDisplay2.setBounds(35, 78, 80, 26);
        game.nameDisplay2.setForeground(new Color(198, 211, 232));
        game.nameDisplay2.setFont(new Font("Arial", Font.BOLD, 15));
        // CLASS LABEL
        game.statPanel.add(game.classDisplay2);
        game.classDisplay2.setBounds(35, 116, 80, 26);
        game.classDisplay2.setForeground(new Color(198, 211, 232));
        game.classDisplay2.setFont(new Font("Arial", Font.BOLD, 15));
        // HP LABEL
        game.statPanel.add(game.HPDisplay2);
        game.HPDisplay2.setBounds(35, 166, 80, 26);
        game.HPDisplay2.setForeground(new Color(198, 211, 232));
        game.HPDisplay2.setFont(new Font("Arial", Font.BOLD, 15));
        // ATK LABEL
        game.statPanel.add(game.ATKDisplay2);
        game.ATKDisplay2.setBounds(35, 204, 80, 26);
        game.ATKDisplay2.setForeground(new Color(198, 211, 232));
        game.ATKDisplay2.setFont(new Font("Arial", Font.BOLD, 15));
        // DEF LABEL
        game.statPanel.add(game.DEFDisplay2);
        game.DEFDisplay2.setBounds(35, 242, 80, 26);
        game.DEFDisplay2.setForeground(new Color(198, 211, 232));
        game.DEFDisplay2.setFont(new Font("Arial", Font.BOLD, 15));
        // USERNAME WRITER
        game.statPanel.add(game.usernameWrite);
        game.usernameWrite.setBounds(125, 78, 175, 26);
        game.usernameWrite.setForeground(Color.white);
        game.usernameWrite.setFont(new Font("Arial", Font.BOLD, 17));
        // CLASS WRITER
        game.statPanel.add(game.classWrite2);
        game.classWrite2.setBounds(125, 116, 175, 26);
        game.classWrite2.setForeground(new Color(255, 225, 164));
        game.classWrite2.setFont(new Font("Arial", Font.BOLD, 17));
        // HP WRITER
        game.statPanel.add(game.hpWrite2);
        game.hpWrite2.setBounds(125, 166, 175, 26);
        game.hpWrite2.setForeground(Color.white);
        game.hpWrite2.setFont(new Font("Arial", Font.BOLD, 17));
        // ATK WRITER
        game.statPanel.add(game.atkWrite2);
        game.atkWrite2.setBounds(125, 204, 175, 26);
        game.atkWrite2.setForeground(Color.white);
        game.atkWrite2.setFont(new Font("Arial", Font.BOLD, 17));
        // DEF WRITER
        game.statPanel.add(game.defWrite2);
        game.defWrite2.setBounds(125, 242, 175, 26);
        game.defWrite2.setForeground(Color.white);
        game.defWrite2.setFont(new Font("Arial", Font.BOLD, 17));
        for (JLabel label : new JLabel[]{game.levelDisplay, game.experienceDisplay}) {
            label.setForeground(new Color(198, 211, 232));
            label.setFont(new Font("Arial", Font.BOLD, 15));
        }
        game.statPanel.add(game.levelDisplay);
        game.levelDisplay.setBounds(35, 280, 80, 26);
        game.statPanel.add(game.levelWrite);
        game.levelWrite.setBounds(125, 280, 175, 26);
        game.levelWrite.setForeground(new Color(255, 225, 164));
        game.levelWrite.setFont(new Font("Arial", Font.BOLD, 17));
        game.statPanel.add(game.experienceDisplay);
        game.experienceDisplay.setBounds(35, 316, 80, 26);
        game.statPanel.add(game.experienceWrite);
        game.experienceWrite.setBounds(125, 316, 175, 26);
        game.experienceWrite.setForeground(Color.WHITE);
        game.experienceWrite.setFont(new Font("Arial", Font.BOLD, 15));
        game.statPanel.add(game.experienceBar);
        game.experienceBar.setBounds(35, 357, 265, 22);
        game.experienceBar.setForeground(new Color(104, 187, 149));
        game.experienceBar.setBackground(new Color(30, 40, 58));
        // PLAYER ICON
        game.statPanel.add(game.classDisplay);
        game.classDisplay.setBounds(325, 105, 200, 220);
        // ACTIONS
        game.sidePanel.add(game.actionHeader);
        game.actionHeader.setBounds(12, 15, 202, 36);
        game.actionHeader.setForeground(new Color(255, 225, 164));
        game.actionHeader.setFont(new Font("Georgia", Font.BOLD, 20));
        // BUTTONS
        game.sidePanel.add(game.battleBTN);
        game.battleBTN.setBorderPainted(false);
        game.battleBTN.setFocusPainted(false);
        game.battleBTN.setContentAreaFilled(false);

        game.sidePanel.add(game.saveBTN);
        game.saveBTN.setBorderPainted(false);
        game.saveBTN.setFocusPainted(false);
        game.saveBTN.setContentAreaFilled(false);

        game.sidePanel.add(game.exitBTN);
        game.exitBTN.setBorderPainted(false);
        game.exitBTN.setFocusPainted(false);
        game.exitBTN.setContentAreaFilled(false);

        game.battleBTN.setBounds(59, 78, 111, 52);
        game.saveBTN.setBounds(59, 176, 111, 52);
        game.exitBTN.setBounds(59, 274, 111, 52);


        game.characterMenuScreen.add(game.restDisplay);
        game.restDisplay.setBounds(0, 0, 960, 540);
        game.restDisplay.setVisible(true);
    
    }
}
