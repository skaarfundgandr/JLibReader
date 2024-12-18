import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

class BackgroundImage extends JComponent {
    private Image image;

    public BackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Set the opacity (alpha value: 0.0f to 1.0f)
            float opacity = 0.98f;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            // Draw the background image
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            // Add black color to image for darkening
            g2d.setColor(new Color(0, 0, 0, 75)); // RGBA, alpha 75 for transparency
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
    }
}

class RoundedPasswordField extends JPasswordField {
    private int cornerRadius;

    public RoundedPasswordField(int cornerRadius) {
        super();
        this.cornerRadius = cornerRadius;
        setOpaque(false); // Ensures the background is handled by custom painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded background
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g2d);
        g2d.dispose();
    }

    @Override
    public Insets getInsets() {
        // Adjust the insets to add padding
        return new Insets(5, 15, 5, 15); // Top, Left, Bottom, Right padding
    }

    @Override
    public Insets getInsets(Insets insets) {
        // Adjust the insets to add padding
        insets.top = 5;
        insets.left = 15;
        insets.bottom = 5;
        insets.right = 15;
        return insets;
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded border
        g2d.setColor(getForeground());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2d.dispose();
    }

    @Override
    public void setBorder(Border border) {
        // Prevent the default border from being drawn
    }
}

class RoundedPanel extends JPanel {
    private int cornerRadius;

    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smoother corners
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the background color
        g2d.setColor(getBackground());

        // Draw a rounded rectangle
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2d.setColor(Color.BLACK); // Border color
        g2d.setStroke(new BasicStroke((float)0.2)); // Border thickness
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2d.dispose();
    }
}

class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false); // Set to false to allow custom painting
        setContentAreaFilled(false); // Prevent default background painting
        setBorderPainted(false); // Disable border painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Draw button text
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent() - fm.getDescent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2;

        g2d.setColor(getForeground());
        g2d.setStroke(new BasicStroke((float)0.4));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += cornerRadius; // Add padding for rounded corners
        size.height += cornerRadius;
        return size;
    }
}