import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Card, Deck, and Player classes remain the same.

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;
    private Scanner scanner;

    public Game() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        deck = new Deck(ranks, suits, values);
        player = new Player("Player");
        dealer = new Player("Dealer");
        scanner = new Scanner(System.in);
    }

    private void playerTurn() {
        boolean playerTurn = true;
        while (playerTurn && player.getPoints() < 21) {
            System.out.println("Do you want to 'hit' or 'stand'?");
            String choice = scanner.nextLine();

            if (choice.equals("hit")) {
                player.addCard(deck.deal());
                System.out.println(player);
                if (player.getPoints() > 21) {
                    System.out.println("Player busts!");
                    playerTurn = false;
                }
            } else if (choice.equals("stand")) {
                playerTurn = false;
            }
        }
    }

    private void dealerTurn() {
        while (dealer.getPoints() < 17) {
            dealer.addCard(deck.deal());
        }
        System.out.println(dealer);
    }

    private void determineWinner() {
        if (player.getPoints() > 21) {
            System.out.println("Dealer wins!");
        } else if (dealer.getPoints() > 21 || player.getPoints() > dealer.getPoints()) {
            System.out.println("Player wins!");
        } else if (player.getPoints() < dealer.getPoints()) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void playGame() {
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());

        System.out.println(player);
        System.out.println(dealer);

        playerTurn();
        if (player.getPoints() <= 21) {
            dealerTurn();
            determineWinner();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}