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
    private BlackjackViewer viewer;
    private boolean gameOver = false;

    public void setViewer(BlackjackViewer viewer) {
        this.viewer = viewer;
    }
    public Game() {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);
        player = new Player("Player");
        dealer = new Player("Dealer");
        scanner = new Scanner(System.in);
    }

    private void playerTurn() {
        boolean playerTurn = true;
        while (playerTurn && player.getPoints() < 21) {
            System.out.println("Do you want to 'hit' or 'stay'?");
            String choice = scanner.nextLine();

            if (choice.equals("hit") || choice.equals("h")) {
                player.addCard(deck.deal());
                System.out.println(player);
                viewer.updateGameView();
                if (player.getPoints() > 21) {
                    System.out.println("Player busts!");
                    playerTurn = false;
                }
            } else if (choice.equals("stay")) {
                playerTurn = false;
                viewer.updateGameView();
            }
        }
    }
    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }
    public String getStatus() {
        boolean isGameOver = dealer.getHand().size() > 0 && (player.getPoints() > 21 || dealer.getPoints() >= 17);

        if (!isGameOver) {
            return "";
        }

        boolean playerBlackjack = player.getPoints() == 21 && player.getHand().size() == 2;
        boolean dealerBlackjack = dealer.getPoints() == 21 && dealer.getHand().size() == 2;

        if (player.getPoints() > 21) {
            return "Player Busts! Dealer Wins!";
        } else if (dealer.getPoints() > 21) {
            return "Dealer Busts! Player Wins!";
        } else if (playerBlackjack && !dealerBlackjack) {
            return "Player Hits Blackjack! Player Wins!";
        } else if (!playerBlackjack && dealerBlackjack) {
            return "Dealer Hits Blackjack! Dealer Wins!";
        } else if (playerBlackjack && dealerBlackjack) {
            return "Both Hit Blackjack! It's a Tie!";
        } else if (player.getPoints() == dealer.getPoints()) {
            return "It's a Tie!";
        } else if (player.getPoints() > dealer.getPoints()) {
            return "Player Wins!";
        } else {
            return "Dealer Wins!";
        }
    }
    private void dealerTurn() {
        while (dealer.getPoints() < 17) {
            dealer.addCard(deck.deal());
            viewer.updateGameView();
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
        Scanner scanner = new Scanner(System.in); // Create a scanner for input

        while (true) { // Start an infinite loop to allow multiple games
            Game game = new Game(); // Create a new game instance
            BlackjackViewer viewer = new BlackjackViewer(game); // Create the game viewer
            game.setViewer(viewer); // Set the viewer in the game
            game.playGame(); // Start playing the game
            viewer.updateGameView(); // Update the game view

            String playAgain;
            // Loop until valid input (yes/no/y/n) is received
            do {
                System.out.println("Do you want to play again? (yes/no): ");
                playAgain = scanner.nextLine();
            } while(!playAgain.equals("yes") && !playAgain.equals("no") && !playAgain.equals("y") && !playAgain.equals("n"));

            // Check if the user doesn't want to play again
            if (!playAgain.equals("yes") && !playAgain.equals("y")) {
                System.out.println("Thanks for playing!"); // Thank the user
                break; // Exit the loop to end the game
            }
            viewer.setView(false); // Hide the viewer for the next game
        }
        System.exit(0); // Exit the program
    }

}