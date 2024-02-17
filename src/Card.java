public class Card {
    private String suit;
    private String rank;
    private int value;
    private String imagePath;


    public Card(String rank, String suit, int value, int cardNumber) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        // Construct the image path based on card number
        this.imagePath = "Resources/Cards/" + cardNumber + ".png";
    }

    // Returns the card's value
    public int getValue() {
        return value;
    }

    // Returns the path to the card's image
    public String getImagePath() {
        return imagePath;
    }

    // Returns the card's rank
    public String getRank() {
        return rank;
    }

    // Returns the card's suit
    public String getSuit() {
        return suit;
    }

    // Returns a string representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
