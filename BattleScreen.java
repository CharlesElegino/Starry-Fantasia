import javax.swing.*;
import java.awt.*;

/** Screen container and layout builder for the BattleScreen screen. */
public final class BattleScreen extends ScreenPanel {
    void showStage(Game game, int stage) {
        if (game.StageBG.getParent() != game.battleScreen) {
            game.battleScreen.add(game.StageBG);
        }
        if (stage == 1) {
            game.StageBG.setIcon(game.stageBG1);
            game.playBGM(game.SBGM1);
        } else if (stage == 2) {
            game.StageBG.setIcon(game.stageBG2);
            game.playBGM(game.SBGM2);
        } else {
            game.StageBG.setIcon(game.stageBG3);
            game.playBGM(game.SBGM3);
        }
        game.StageBG.setBounds(0, 0, 960, 540);
        game.battleScreen.setComponentZOrder(game.StageBG,
                game.battleScreen.getComponentCount() - 1);
    }

    void build(Game game) {
        game.battleScreen.install(game);
        game.battleScreen.add(game.battlePanel);
        game.battlePanel.setLayout(null);
        game.battlePanel.setOpaque(false);
        game.battlePanel.setBounds(0, 0, 960, 540);
        // UPPER LAYOUT
        game.battlePanel.add(game.upperPanel);
        game.upperPanel.setLayout(null);
        game.upperPanel.setBounds(0, 0, 960, 88);
        game.upperPanel.setBackground(new Color(12, 20, 38, 225));
        game.upperPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(221, 183, 112, 210)));
        // PLAYER INFO WRITER
        game.upperPanel.add(game.playerNAMEWriter);
        game.upperPanel.add(game.playerHPWriter);
        game.upperPanel.add(game.playerATKWriter);
        game.upperPanel.add(game.playerDEFWriter);
        game.upperPanel.add(game.playerSPWriter);
        for (JLabel stat : new JLabel[]{game.playerNAMEWriter, game.playerHPWriter, game.playerATKWriter,
                game.playerDEFWriter, game.enemyNAMEWriter, game.enemyHPWriter, game.enemyATKWriter, game.enemyDEFWriter}) {
            stat.setForeground(Color.WHITE);
            stat.setFont(new Font("Arial", Font.BOLD, 14));
        }
        game.playerNAMEWriter.setForeground(new Color(255, 225, 164));
        game.playerNAMEWriter.setBounds(120, 8, 210, 24);
        game.playerHPWriter.setBounds(120, 31, 100, 22);
        game.playerATKWriter.setBounds(120, 54, 90, 22);
        game.playerDEFWriter.setBounds(215, 54, 90, 22);
        game.playerSPWriter.setBounds(340, 12, 100, 28);
        game.playerSPWriter.setForeground(new Color(255, 225, 164));
        game.playerSPWriter.setFont(new Font("Arial", Font.BOLD, 16));
        // ENEMY INFO WRITER
        game.upperPanel.add(game.enemyNAMEWriter);
        game.upperPanel.add(game.enemyHPWriter);
        game.upperPanel.add(game.enemyATKWriter);
        game.upperPanel.add(game.enemyDEFWriter);
        game.enemyNAMEWriter.setForeground(new Color(255, 190, 180));
        game.enemyNAMEWriter.setBounds(590, 8, 210, 24);
        game.enemyHPWriter.setBounds(590, 31, 100, 22);
        game.enemyATKWriter.setBounds(590, 54, 90, 22);
        game.enemyDEFWriter.setBounds(685, 54, 90, 22);
        // LOWER LAYOUT
        game.battlePanel.add(game.lowerPanel);
        game.lowerPanel.setLayout(null);
        game.lowerPanel.setBounds(0, 400, 960, 120);
        game.lowerPanel.setBackground(new Color(12, 20, 38, 230));
        game.lowerPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(221, 183, 112, 210)));
        // FIGHT BUTTON
        game.lowerPanel.add(game.fightBTN);
        game.fightBTN.setBounds(70, 15, 102, 34);
        game.fightBTN.setBorderPainted(false);
        game.fightBTN.setFocusPainted(false);
        game.fightBTN.setContentAreaFilled(false);
        // RUN BUTTON
        game.lowerPanel.add(game.runBTN);
        game.runBTN.setBounds(70, 55, 102, 34);
        game.runBTN.setBorderPainted(false);
        game.runBTN.setFocusPainted(false);
        game.runBTN.setContentAreaFilled(false);
        // SKILL PANEL LAYOUT
        game.lowerPanel.add(game.skillsPanel);
        game.skillsPanel.setVisible(false);
        game.skillsPanel.setLayout(null);
        game.skillsPanel.setBounds(210, 10, 700, 90);
        //lowerPanelInside.setBackground(Color.red);
        game.skillsPanel.setOpaque(false);
        // ATTACK BUTTON
        game.skillsPanel.add(game.skill1BTN);
        game.skill1BTN.setBounds(185, 0, 152, 41);
        game.skill1BTN.setBorderPainted(false);
        game.skill1BTN.setFocusPainted(false);
        game.skill1BTN.setContentAreaFilled(false);
        // ATTACK+ BUTTON
        game.skillsPanel.add(game.skill2BTN);
        game.skill2BTN.setBounds(350, 0, 152, 41);
        game.skill2BTN.setBorderPainted(false);
        game.skill2BTN.setFocusPainted(false);
        game.skill2BTN.setIcon(game.atk2);
        game.skill2BTN.setContentAreaFilled(false);
        // BUFF BUTTON
        game.skillsPanel.add(game.buffBTN);
        game.buffBTN.setBounds(185, 45, 152, 41);
        game.buffBTN.setBorderPainted(false);
        game.buffBTN.setFocusPainted(false);
        game.buffBTN.setContentAreaFilled(false);
        // SPECIAL BUTTON
        game.skillsPanel.add(game.ultBTN);
        game.ultBTN.setBounds(350, 45, 152, 41);
        game.ultBTN.setBorderPainted(false);
        game.ultBTN.setFocusPainted(false);
        game.ultBTN.setContentAreaFilled(false);
        // BATTLE LOG LAYOUT
        game.lowerPanel.add(game.battleLogPanel);
        game.battleLogPanel.setVisible(false);
        game.battleLogPanel.setLayout(null);
        game.battleLogPanel.setOpaque(true);
        game.battleLogPanel.setBackground(new Color(5, 10, 22));
        game.battleLogPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 183, 112, 220), 2, true),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)));
        game.battleLogPanel.setBounds(210, 7, 700, 106);
        // LOG WRITER
        game.battleLogPanel.add(game.logWriter);
        game.logWriter.setBounds(12, 5, 672, 38);
        game.logWriter.setHorizontalAlignment(JLabel.CENTER);
        game.logWriter.setForeground(new Color(255, 220, 145));
        game.logWriter.setFont(new Font("Arial", Font.BOLD, 16));
        //game.logWriter.setVerticalAlignment(JLabel.CENTER);
        // LOG WRITER
        game.battleLogPanel.add(game.logWriter2);
        game.logWriter2.setBounds(12, 48, 672, 42);
        game.logWriter2.setHorizontalAlignment(JLabel.CENTER);
        game.logWriter2.setForeground(new Color(238, 243, 252));
        game.logWriter2.setFont(new Font("Arial", Font.BOLD, 15));
        //game.logWriter2.setVerticalAlignment(JLabel.CENTER);
        // CHARACTER HOLDER
        game.battlePanel.add(game.charHolder);
        // ENEMY HOLDER
        game.battlePanel.add(game.enemyHolder);
    
    }
}
