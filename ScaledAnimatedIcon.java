import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/** Draws an animated image at a fixed size without creating a scaled GIF copy. */
final class ScaledAnimatedIcon extends ImageIcon {
    private final int width;
    private final int height;

    ScaledAnimatedIcon(ImageIcon source, int width, int height) {
        super(source.getImage());
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public void paintIcon(Component component, Graphics graphics, int x, int y) {
        int sourceWidth = getImage().getWidth(component);
        int sourceHeight = getImage().getHeight(component);
        if (sourceWidth <= 0 || sourceHeight <= 0) {
            return;
        }
        double scale = Math.min((double) width / sourceWidth, (double) height / sourceHeight);
        int drawWidth = Math.max(1, (int) Math.round(sourceWidth * scale));
        int drawHeight = Math.max(1, (int) Math.round(sourceHeight * scale));
        int drawX = x + (width - drawWidth) / 2;
        int drawY = y + (height - drawHeight) / 2;
        Graphics2D scaledGraphics = (Graphics2D) graphics.create();
        scaledGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        scaledGraphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);
        scaledGraphics.drawImage(getImage(), drawX, drawY, drawWidth, drawHeight, component);
        scaledGraphics.dispose();
    }
}
