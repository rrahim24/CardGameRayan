import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.Color;

public class BlackjackViewer extends JFrame {
    private static final int WINDOW_WIDTH = 1000;  // Width of the window
    private static final int WINDOW_HEIGHT = 600; // Height of the window
    private Game game; // Reference to the game logic
    private Font statusFont = new Font("SansSerif", Font.BOLD, 18);
    private Image  background;

    public BlackjackViewer(Game game) {
        super("Blackjack Game");
        this.game = game;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        background = new ImageIcon("Resources/background.png").getImage();
        setView(true);
    }

    public void setView(boolean a){
        this.setVisible(a);
    }
    public void updateGameView() {
        repaint(); // Trigger the paintComponent method to redraw the game view
    }

    @Override
    public void paint(Graphics g) {
        Color Background = new Color(51, 113, 0);
        super.paint(g);
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setColor(Color.BLACK);
        drawHand(g, game.getPlayer().getHand(), 50, 380, "Player");

        drawHand(g, game.getDealer().getHand(), 50, 180, "Dealer");

        drawStatus(g, game.getStatus());
    }

    private void drawHand(Graphics g, List<Card> hand, int startX, int startY, String owner) {
        int xOffset = 0; // Horizontal offset for drawing cards side by side

        for (Card card : hand) {
            ImageIcon icon = new ImageIcon(card.getImagePath());
            Image image = icon.getImage();
            g.drawImage(image, startX + xOffset, startY, 100, 100, null);
            xOffset += 100; // Move to the right for the next card
        }
        g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
        g.drawString(owner + " Hand", startX, startY - 20);
    }

    private void drawStatus(Graphics g, String status) {
        g.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 20));
        g.drawString(status, 50, WINDOW_HEIGHT / 2 + 230);
    }
}
