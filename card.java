import java.util.*;

public class card {
	 private int rank, suit;

	    private static String[] cardRanks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
	    private static String[] suits = { "Diamonds", "Clubs", "Hearts", "Spades" };

	    public card(int rank, int suit) {
	        this.rank = rank;
	        this.suit = suit;
	    }
	    
	    protected void setSuit(short suit) {
	        this.suit = suit;
	    }
	    
	    public int getSuit() {
	        return suit;
	    }
	    
	    protected void setRank(short rank) {
	        this.rank = rank;
	    }

	    public int getRank() {
	        return rank;
	    }

	    public @Override String toString() {
	        return rank + " of " + suit;
	    }

	    public static String suitAsString(int _suit) {
	        return suits[_suit];
	    }
	    
	    public static String rankAsString(int _rank) {
	        return cardRanks[_rank];
	    }
	    
	    // to print card as string
	    protected String dspCard() {
	        return cardRanks[rank] + " of " + suits[suit];
	    }

	    // Determine if two cards are the same
	    public static boolean sameCard(card card1, card card2) {
	        return (card1.rank == card2.rank && card1.suit == card2.suit);
	    }
}

class rankComparator implements Comparator<Object> {
    public int compare(Object card1, Object card2) throws ClassCastException {

        if (!((card1 instanceof card) && (card2 instanceof card))) {
            throw new ClassCastException("A Card object was expected.  Parameter 1 class: " + card1.getClass()
                    + " Parameter 2 class: " + card2.getClass());
        }
        int rank1 = ((card) card1).getRank();
        int rank2 = ((card) card2).getRank();

        return rank1 - rank2;
    }
}

class suitComparator implements Comparator<Object> {
    public int compare(Object card1, Object card2) throws ClassCastException {

        if (!((card1 instanceof card) && (card2 instanceof card))) {
            throw new ClassCastException("A Card object was expected.  Parameter 1 class: " + card1.getClass()
                    + " Parameter 2 class: " + card2.getClass());
        }
        int suit1 = ((card) card1).getSuit();
        int suit2 = ((card) card2).getSuit();

        return suit1 - suit2;
    }
}
