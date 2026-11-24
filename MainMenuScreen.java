import javax.swing.*;
import java.awt.*;

/** Screen container and layout builder for the MainMenuScreen screen. */
public final class MainMenuScreen extends ScreenPanel {
    void build(Game game) {
        game.playBGM(game.BGMPath);
        game.mainMenuScreen.removeAll();
        game.mainMenuScreen.install(game);
        game.mainMenuScreen.add(game.mainPanel);
        //panel.setVisible(true);
        game.mainPanel.setLayout(null);
        game.mainPanel.setOpaque(false);
        game.mainPanel.setBounds(0, 0, 960, 540);

        game.mainPanel.add(game.playBTN);
        game.playBTN.setBounds((960 - 120) / 2, 285, 120, 40);
        game.playBTN.setBorderPainted(false);
        game.playBTN.setFocusPainted(false);
        game.playBTN.setContentAreaFilled(false);

        game.mainPanel.add(game.continueBTN);
        game.continueBTN.setBounds((960 - 120) / 2, 335, 120, 40);
        game.continueBTN.setBorderPainted(false);
        game.continueBTN.setFocusPainted(false);
        game.continueBTN.setContentAreaFilled(false);

        game.mainPanel.add(game.quitBTN);
        game.quitBTN.setBounds((960 - 120) / 2, 385, 120, 40);
        game.quitBTN.setBorderPainted(false);
        game.quitBTN.setFocusPainted(false);
        game.quitBTN.setContentAreaFilled(false);

        game.mainPanel.add(game.gameTitleDisplay);
        game.gameTitleDisplay.setBounds(300, 100, 332, 92);

        game.mainMenuScreen.add(game.mainDisplay);
        game.mainDisplay.setBounds(0, 0, 960, 540);
        game.mainDisplay.setVisible(true);
        game.getContentPane().revalidate();
        game.getContentPane().repaint();
    
    }
}
