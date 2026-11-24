import javax.swing.JPanel;

/** Base container shared by the game's independent screens. */
abstract class ScreenPanel extends JPanel {
    ScreenPanel() {
        setLayout(null);
        setBounds(0, 0, 960, 540);
        setOpaque(false);
    }

    final void install(Game game) {
        game.setContentPane(this);
        game.revalidate();
        game.repaint();
    }
}
