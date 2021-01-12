import java.util.Scanner;

public class Texas_Holdem {
	public static int choice;
	
	public static void main(String[] args) throws Exception{
		
		int numOfPlayers = 3;
		deck deck = new deck();
		
		board board = new board();
		
		player[] player = new player[numOfPlayers];
		
		  // creating the players
        for (int i = 0; i < numOfPlayers; i++) {
            player[i] = new player();
        }
		
		//run for 10 rounds
		for (int y = 0; y < 10; y++) {
            System.out.println("\n------------------------Round " + (y + 1) + "------------------------");
            int cardCounter = 0;

            int burnCounter = 0;

            int count = 0;

            for (int i = 0; i < 3; i++){
                deck.shuffle();
            }
            //to cut the deck
            deck.cutDeck();

            //dealing the cards to the players
            for (int i = 0; i < 2; i++){
                for (int j = 0; j < numOfPlayers; j++) {
                    player[j].setCard(deck.getCard(cardCounter++), i);
                }
            }
            System.out.println("\nYour cards are the following in Round " + (y + 1));

            player[0].dspCards(0);
            
            System.out.print("Enter your choice - Fold - 0 / Continue - 1 : ");
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            while(choice!=0&&choice!=1){
                System.out.println("\n\n!!! WRONG INPUT : \n");
                
                System.out.print("Enter your choice - Fold - 0 / Continue - 1 : ");
                choice = input.nextInt(); 
            }

            // Burning a card before dealing
            board.setBurnCard(deck.getCard(cardCounter++), burnCounter++);

            // dealing the flop
            for (int i = 0; i < 3; i++){
                board.setBoardCard(deck.getCard(cardCounter++), count++);
            }
            System.out.println("\nAfter flop round\n");

            board.dspBoard(3);

            if (choice == 1) { // user decision 1 checks whether user prefers to continue
                System.out.print("Enter your choice - Fold - 0 / Continue - 1 : ");
                choice = input.nextInt();
                while(choice!=0&&choice!=1){
                    System.out.println("\n\n!!! WRONG INPUT : \n");
                    
                    System.out.print("Enter your choice - Fold - 0 / Continue - 1 : ");
                    choice = input.nextInt(); 
                }
            }
            // burning one card before the turn
            board.setBurnCard(deck.getCard(cardCounter++), burnCounter++);

            // dealing the turn
            board.setBoardCard(deck.getCard(cardCounter++), count++);
            System.out.println("\nAfter turn round\n");
            board.dspBoard(4);
            if (choice == 1){
                System.out.print("Enter your choice - Fold - 0 / Continue - 1 : ");
                choice = input.nextInt();
                while(choice!=0&&choice!=1){
                    System.out.println("\n\n!!! WRONG INPUT : \n");
                    
                    System.out.print("Enter your choice - Fold - 0 / Continue - 1 : ");
                    choice = input.nextInt(); 
                }
            }

            // burn a card before river stage
            board.setBurnCard(deck.getCard(cardCounter++), burnCounter++);

            // dealing the river
            board.setBoardCard(deck.getCard(cardCounter++), count++);   
            
            // print board
            System.out.println("\nAfter river round\n");
            board.dspBoard(5);                                   

            // printing the player cards
            System.out.println("\nThe player cards are the following....\n");
            if (choice != 0){
                player[0].dspCards(0);
            }

            for (int i = 1; i < numOfPlayers; i++){
                player[i].dspCards(i);
            }

            // print burn cards
            board.dspBurnCards();

            for (int i = 0; i < numOfPlayers; i++) {
                hand handtoCheck = new hand();

                // distributing the players with cards
                for (int j = 0; j < player[i].holeCardSize(); j++) {
                    handtoCheck.insertCard(player[i].getCard(j), j);
                }

                // distributing the board with the cards
                for (int j = player[i].holeCardSize(); j < (player[i].holeCardSize() + board.boardSize()); j++) {
                    handtoCheck.insertCard(board.getCard(j - player[i].holeCardSize()), j);
                }

                if(i == 0) {
                System.out.println("Your " + "\t ->\tHand value : " + handtoCheck.evaluateHand());
                
                if(choice == 1)
                player[i].addscore(handtoCheck.getHandVal());

                System.out.println("\t\tScore : " + player[i].getScore() + "\n");
                }
                else {
                    System.out.println("Player " + (i + 1) + " ->\tHand value : " + handtoCheck.evaluateHand());

                    player[i].addscore(handtoCheck.getHandVal());

                    System.out.println("\t\tScore : " + player[i].getScore() + "\n");
                }
            }
            Thread.sleep(10);
		}

        int win = 0;
        int winScore=0;
        
        for (int i = 0; i < 3; i++) {
            if (player[i].getScore() > winScore) {
            	winScore = player[i].getScore();
                win = i;
            }
        }
        if (win == 0) {
            System.out.print("\n*************   YOU ARE ");
        } else
            System.out.print("\n*************   PLAYER " + (win + 1) + " IS ");

        System.out.println("THE WINNER !!!!   *************");
	}
}
