import java.util.*;
public class Player {
    private String name;
    private List<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.points = 0;
    }

    public void addCard(Card card) {
        hand.add(card);
        points += card.getValue();
    }

    public int getPoints() {
        return points;
    }

    public void resetHand() {
        hand.clear();
        points = 0;
    }

    @Override
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}