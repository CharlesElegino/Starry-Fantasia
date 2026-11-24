import javax.swing.*;
import java.awt.*;

/** Screen container and layout builder for the StageSelectionScreen screen. */
public final class StageSelectionScreen extends ScreenPanel {
    void build(Game game) {
        game.stageSelectionScreen.removeAll();
        game.stageSelectionScreen.install(game);
        game.stageSelectionScreen.add(game.dungeonPanel);
        game.dungeonPanel.setLayout(null);
        game.dungeonPanel.setBounds(0, 0, 960, 540);
        game.dungeonPanel.setOpaque(false);

        game.stageSelectionScreen.add(game.QWERTY);
        game.QWERTY.setLayout(null);
        game.QWERTY.setBounds(70, 90, 820, 350);
        game.QWERTY.setBackground(new Color(12, 20, 38, 205));
        game.QWERTY.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 183, 112, 210), 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        game.QWERTY.add(game.stageSelectionTitle);
        game.stageSelectionTitle.setBounds(30, 14, 760, 42);
        game.stageSelectionTitle.setForeground(new Color(255, 225, 164));
        game.stageSelectionTitle.setFont(new Font("Georgia", Font.BOLD, 24));

        game.QWERTY.add(game.stage1BTN);
        game.stage1BTN.setBounds(96, 95, 124, 112);
        game.stage1BTN.setBorderPainted(false);
        game.stage1BTN.setFocusPainted(false);
        game.stage1BTN.setContentAreaFilled(false);

        game.QWERTY.add(game.stage2BTN);
        game.stage2BTN.setBounds(346, 103, 128, 96);
        game.stage2BTN.setBorderPainted(false);
        game.stage2BTN.setFocusPainted(false);
        game.stage2BTN.setContentAreaFilled(false);

        game.QWERTY.add(game.stage3BTN);
        game.stage3BTN.setBounds(617, 98, 123, 106);
        game.stage3BTN.setBorderPainted(false);
        game.stage3BTN.setFocusPainted(false);
        game.stage3BTN.setContentAreaFilled(false);

        for (JLabel caption : new JLabel[]{game.stage1Caption, game.stage2Caption, game.stage3Caption}) {
            caption.setForeground(Color.WHITE);
            caption.setFont(new Font("Arial", Font.BOLD, 17));
        }
        game.QWERTY.add(game.stage1Caption);
        game.stage1Caption.setBounds(61, 222, 194, 32);
        game.QWERTY.add(game.stage2Caption);
        game.stage2Caption.setBounds(313, 222, 194, 32);
        game.QWERTY.add(game.stage3Caption);
        game.stage3Caption.setBounds(581, 222, 194, 32);

        game.stageSelectionScreen.add(game.backBTN2);
        game.backBTN2.setBounds(20, 18, 120, 40);
        game.backBTN2.setBorderPainted(false);
        game.backBTN2.setFocusPainted(false);
        game.backBTN2.setContentAreaFilled(false);

        game.stageSelectionScreen.add(game.dungeonBGDisplay);
        game.dungeonBGDisplay.setBounds(0, 0, 960, 540);
        game.dungeonBGDisplay.setVisible(true);
    
    }
}
