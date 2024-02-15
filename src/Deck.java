import java.util.*;
public class Deck {
    private List<Card> cards;
    private int cardsLeft;


    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<>();
        int cardNumber = 1;
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit, values[i], cardNumber++));
            }
        }
        cardsLeft = cards.size();
        shuffle();
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int r = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, temp);
        }
        cardsLeft = cards.size();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        return cards.get(--cardsLeft);
    }
}
