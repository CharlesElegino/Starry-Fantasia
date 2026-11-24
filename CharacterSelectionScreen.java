import javax.swing.*;
import java.awt.*;

/** Screen container and layout builder for the CharacterSelectionScreen screen. */
public final class CharacterSelectionScreen extends ScreenPanel {
    void build(Game game) {
        game.characterSelectionScreen.removeAll();
        game.characterSelectionScreen.install(game);
        game.characterSelectionScreen.add(game.lobbyPanel);
        game.lobbyPanel.setLayout(null);
        game.lobbyPanel.setBounds(0, 0, 960, 540);
        game.lobbyPanel.setOpaque(false);

        ImageIcon displayResize = new ScaledAnimatedIcon(game.swordsmanIdleIcon, 180, 160);
        game.lobbyPanel.add(game.classDisplay);
        game.classDisplay.setIcon(displayResize);
        game.classDisplay.setBounds(535, 135, 200, 200);

        game.lobbyPanel.add(game.name);
        game.name.setBounds(558, 370, 150, 35);
        game.lobbyPanel.add(game.nameDisplay);
        game.nameDisplay.setBounds(518, 373, 50, 30);

        game.lobbyPanel.add(game.panelDisplay);
        game.panelDisplay.setIcon(game.red);
        game.panelDisplay.setBounds(500, 60, 266, 300);
        game.lobbyPanel.add(game.portrait);
        game.portrait.setBounds(160, 60, 308, 96);
        game.lobbyPanel.add(game.portrait2);
        game.portrait2.setBounds(160, 145, 308, 96);
        game.lobbyPanel.add(game.portrait3);
        game.portrait3.setBounds(160, 230, 308, 96);

        game.lobbyPanel.add(game.backBTN);
        game.backBTN.setBounds(10, 10, 120, 40);
        game.backBTN.setBorderPainted(false);
        game.backBTN.setFocusPainted(false);
        game.backBTN.setContentAreaFilled(false);

        game.lobbyPanel.add(game.confirmBTN);
        game.confirmBTN.setBounds(570, 410, 120, 40);
        game.confirmBTN.setBorderPainted(false);
        game.confirmBTN.setFocusPainted(false);
        game.confirmBTN.setContentAreaFilled(false);

        game.lobbyPanel.add(game.info);
        game.lobbyPanel.add(game.classificationDisplay);
        game.lobbyPanel.add(game.HPDisplay);
        game.lobbyPanel.add(game.ATKDisplay);
        game.lobbyPanel.add(game.DEFDisplay);
        game.lobbyPanel.add(game.classWrite);
        game.characterStats.configureSwordsman();
        game.classWrite.setText(game.characterStats.getClassName());
        game.lobbyPanel.add(game.hpWrite);
        game.hpWrite.setText("" + (int) game.characterStats.getHp());
        game.lobbyPanel.add(game.atkWrite);
        game.atkWrite.setText("" + (int) game.characterStats.getAttack());
        game.lobbyPanel.add(game.defWrite);
        game.defWrite.setText("" + (int) game.characterStats.getDefense());
        game.info.setBounds(280, 300, 100, 50);
        game.classificationDisplay.setBounds(230, 320, 100, 50);
        game.HPDisplay.setBounds(230, 340, 100, 50);
        game.ATKDisplay.setBounds(230, 360, 100, 50);
        game.DEFDisplay.setBounds(230, 380, 100, 50);
        //Write
        game.classWrite.setBounds(280, 320, 100, 50);
        game.hpWrite.setBounds(280, 340, 100, 50);
        game.atkWrite.setBounds(280, 360, 100, 50);
        game.defWrite.setBounds(280, 380, 100, 50);

        game.lobbyPanel.add(game.bookDisplay);
        game.bookDisplay.setBounds(95, 10, 748, 488);

        game.characterSelectionScreen.add(game.lobbyDisplay);
        game.lobbyDisplay.setBounds(0, 0, 960, 540);
        game.lobbyDisplay.setVisible(true);
    }
}
