
public class Card {
    private String suit;
    private String rank;
    private int value;
    private String imagePath;

    public Card(String rank, String suit, int value, int cardNumber) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.imagePath = "Resources/Cards/" + cardNumber + ".png";
    }

    public int getValue() {
        return value;
    }
    public String getImagePath() {
        return imagePath;
    }
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}