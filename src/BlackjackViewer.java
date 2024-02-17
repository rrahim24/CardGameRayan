import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.Color;

public class BlackjackViewer extends JFrame {
    private static final int WINDOW_WIDTH = 1000;  // Set the window width
    private static final int WINDOW_HEIGHT = 600; // Set the window height
    private Game game; // Game logic reference
    private Font statusFont = new Font("SansSerif", Font.BOLD, 18); // Font for status messages
    private Image  background; // Background image

    // Constructor to set up the game window
    public BlackjackViewer(Game game) {
        super("Blackjack Game"); // Window title
        this.game = game; // Initialize game logic
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Set window size
        background = new ImageIcon("Resources/background.png").getImage(); // Load background image
        setView(true); // Make window visible
    }

    // Method to set window visibility
    public void setView(boolean a){
        this.setVisible(a);
    }

    // Method to update game view
    public void updateGameView() {
        repaint(); // Redraw the window
    }

    // Override paint method for custom drawing
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call superclass paint method
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this); // Draw background
        // Draw player hand
        drawHand(g, game.getPlayer().getHand(), 50, 380, "Player");
        // Draw dealer hand
        drawHand(g, game.getDealer().getHand(), 50, 180, "Dealer");
        // Display game status
        drawStatus(g, game.getStatus());
    }

    // Draw a card hand
    private void drawHand(Graphics g, List<Card> hand, int startX, int startY, String owner) {
        int xOffset = 0; // Offset for next card

        for (Card card : hand) {
            ImageIcon icon = new ImageIcon(card.getImagePath()); // Load card image
            Image image = icon.getImage();
            g.drawImage(image, startX + xOffset, startY, 100, 100, null); // Draw card image
            xOffset += 100; // Increment offset for next card
        }
        g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20)); // Set font to bold for hand label
        g.drawString(owner + " Hand", startX, startY - 20); // Label the hand
    }

    // Display the game status
    private void drawStatus(Graphics g, String status) {
        g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20)); // Set font to bold for status
        g.drawString(status, 50, WINDOW_HEIGHT / 2 + 230);
    }
}
