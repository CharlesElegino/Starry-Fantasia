/** Application entry point. */
class Launcher {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Game application = new Game();
            application.initialize();
        });
    }
}
