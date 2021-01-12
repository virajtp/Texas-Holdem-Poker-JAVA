import java.util.Arrays;

public class hand {
	
    private card[] availCards = new card[7];
    private int valOfHand;
    private final static short ONE = 1;
    private final static short TWO = 2;
    private final static short THREE = 3;
    private final static short FOUR = 4;
    
	 	public hand() {}

	    public int getHandVal() {
	        return valOfHand;
	    }

	    protected void insertCard(card card, int i) {
	        availCards[i] = card;
	    }

	    protected card readCard(int i) {
	        return availCards[i];
	    }

	    protected int cardAmounts() {
	        return availCards.length;
	    }

	    protected void sortByRank() {
	        Arrays.sort(availCards, new rankComparator());
	    }

	    protected void sortBySuit() {
	        Arrays.sort(availCards, new suitComparator());
	    }

	    protected void sortBySuitThenRank() {
	        Arrays.sort(availCards, new suitComparator());
	        Arrays.sort(availCards, new rankComparator());
	    }

	    protected void sortByRankThenSuit() {
	        Arrays.sort(availCards, new rankComparator());
	        Arrays.sort(availCards, new suitComparator());
	    }

	    protected String evaluateHand() {
	        String handResult = new String();
	        short[] rankCounter = new short[13];
	        short[] suitCounter = new short[4];

	        // initializations
	        for (int i = 0; i < rankCounter.length; i++) {
	            rankCounter[i] = 0;
	        }

	        for (int i = 4; i < suitCounter.length; i++) {
	            suitCounter[i] = 0;
	        }

	        // Loop through sorted cards and total ranks
	        for (int i = 0; i < availCards.length; i++) {
	            rankCounter[availCards[i].getRank()]++;
	            suitCounter[availCards[i].getSuit()]++;
	        }

	        // sort cards
	        this.sortByRankThenSuit();

	        // HAND RANKS
	        
	        // for royal flush
	        handResult = chkForRoyalFlush(rankCounter, suitCounter);

	        // for straight flush
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForStraightFlush(rankCounter, suitCounter);
	        }

	        // for four of a kind
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForFourOfAKind(rankCounter);
	        }

	        // for full house
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForFullHouse(rankCounter);
	        }

	        // for flush
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForFlush(rankCounter, suitCounter);
	        }

	        // for straight
	        if (handResult == null || handResult.length() == 0) {
	            this.sortByRank();
	            handResult = chkForStraight(rankCounter);
	        }

	        // for three of a kind
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForThreeOfAKind(rankCounter);
	        }

	        // for two pair
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForTwoPair(rankCounter);
	        }

	        // for one pair
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForOnePair(rankCounter);
	        }

	        // for HighCard
	        if (handResult == null || handResult.length() == 0) {
	            handResult = chkForHighCard(rankCounter);
	        }
	        return handResult;
	    }
	    
	    private String chkForRoyalFlush(short[] rankCounter, short[] suitCounter) {
	        String result = "";

	        if ((rankCounter[9] >= 1 && // 10
	                rankCounter[10] >= 1 && // Jack
	                rankCounter[11] >= 1 && // Queen
	                rankCounter[12] >= 1 && // King
	                rankCounter[0] >= 1) // Ace
	                && (suitCounter[0] > 4 || suitCounter[1] > 4 || suitCounter[2] > 4 || suitCounter[3] > 4)) {

	            royalSearch: for (int i = 0; i < 3; i++) {
	                // Ace must be in position 0, 1 or 2
	                if (availCards[i].getRank() == 0) {
	                    for (int j = 1; j < 4 - i; j++) {
	                        if ((availCards[i + j].getRank() == 9 && availCards[i + j + 1].getRank() == 10
	                                && availCards[i + j + 2].getRank() == 11 && availCards[i + j + 3].getRank() == 12)
	                                && (availCards[i].getSuit() == availCards[i + j].getSuit()
	                                        && availCards[i].getSuit() == availCards[i + j + 1].getSuit()
	                                        && availCards[i].getSuit() == availCards[i + j + 2].getSuit()
	                                        && availCards[i].getSuit() == availCards[i + j + 3].getSuit())) {
	                            // Found royal flush, break and return.
	                            result = "Royal Flush!! Suit: " + card.suitAsString(availCards[i].getSuit());
	                            valOfHand = 10;
	                            break royalSearch;
	                        }
	                    }
	                }
	            }
	        }
	        return result;
	    }

	    // Straight flush is 5 consecutive cards of the same suit.
	    private String chkForStraightFlush(short[] rankCounter, short[] suitCounter) {
	        String result = "";

	        if (suitCounter[0] > 4 || suitCounter[1] > 4 || suitCounter[2] > 4 || suitCounter[3] > 4) {

	            for (int i = availCards.length - 1; i > 3; i--) {
	                if ((availCards[i].getRank() - ONE == availCards[i - ONE].getRank()
	                        && availCards[i].getRank() - TWO == availCards[i - TWO].getRank()
	                        && availCards[i].getRank() - THREE == availCards[i - THREE].getRank()
	                        && availCards[i].getRank() - FOUR == availCards[i - FOUR].getRank())
	                        && (availCards[i].getSuit() == availCards[i - ONE].getSuit()
	                                && availCards[i].getSuit() == availCards[i - TWO].getSuit()
	                                && availCards[i].getSuit() == availCards[i - THREE].getSuit()
	                                && availCards[i].getSuit() == availCards[i - FOUR].getSuit())) {
	                    // Found royal flush, break and return.
	                    result = "Straight Flush!! " + card.rankAsString(availCards[i].getRank()) + " high of "
	                            + card.suitAsString(availCards[i].getSuit());
	                    valOfHand = 9;
	                    break;
	                }
	            }
	        }
	        return result;
	    }

	    // Four of a kind is 4 cards with the same rank
	    private String chkForFourOfAKind(short[] rankCounter) {
	        String result = "";

	        for (int i = 0; i < rankCounter.length; i++) {
	            if (rankCounter[i] == FOUR) {
	                result = "Four of a Kind, " + card.rankAsString(i) + "'s";
	                valOfHand = 8;
	                break;
	            }
	        }
	        return result;
	    }
	    
	    private String chkForFullHouse(short[] rankCounter) {
	        String result = "";
	        short threeOfKindRank = -1;
	        short twoOfKindRank = -1;

	        for (int i = rankCounter.length; i > 0; i--) {
	            if ((threeOfKindRank < (short) 0) || (twoOfKindRank < (short) 0)) {
	                if ((rankCounter[i - ONE]) > 2) {
	                    threeOfKindRank = (short) (i - ONE);
	                } else if ((rankCounter[i - ONE]) > 1) {
	                    twoOfKindRank = (short) (i - ONE);
	                }
	            } else {
	                break;
	            }
	        }
	        if ((threeOfKindRank >= (short) 0) && (twoOfKindRank >= (short) 0)) {
	            result = "Full House: " + card.rankAsString(threeOfKindRank) + "'s full of "
	                    + card.rankAsString(twoOfKindRank) + "'s";
	            valOfHand = 7;
	        }
	        return result;
	    }

	    // 5 cards of the same suit.
	    private String chkForFlush(short[] rankCounter, short[] suitCounter) {
	        String result = "";

	        if (suitCounter[0] > 4 || suitCounter[1] > 4 || suitCounter[2] > 4 || suitCounter[3] > 4) {

	            for (int i = availCards.length - 1; i > 3; i--) {
	                if (availCards[i].getSuit() == availCards[i - ONE].getSuit()
	                        && availCards[i].getSuit() == availCards[i - TWO].getSuit()
	                        && availCards[i].getSuit() == availCards[i - THREE].getSuit()
	                        && availCards[i].getSuit() == availCards[i - FOUR].getSuit()) {
	                    // Found royal flush, break and return.
	                    result = "Flush!! " + card.rankAsString(availCards[i].getRank()) + " high of "
	                            + card.suitAsString(availCards[i].getSuit());
	                    valOfHand = 6;
	                    break;
	                }
	            }
	        }
	        return result;
	    }

	    // 5 consecutive cards, regardless of suit.
	    private String chkForStraight(short[] rankCounter) {
	        String result = "";

	        // index with a value greater than 0
	        for (int i = rankCounter.length; i > 4; i--) {
	            if ((rankCounter[i - 1] > 0) && (rankCounter[i - 2] > 0) && (rankCounter[i - 3] > 0)
	                    && (rankCounter[i - 4] > 0) && (rankCounter[i - 5] > 0)) {
	                result = "Straight " + card.rankAsString(i - 1) + " high";
	                valOfHand = 5;
	                break;
	            }
	        }
	        return result;
	    }

	    // Three of a kind is 3 cards of the same rank.
	    private String chkForThreeOfAKind(short[] rankCounter) {
	        String result = "";

	        // index with a value greater than 0
	        for (int i = rankCounter.length; i > 0; i--) {
	            if (rankCounter[i - 1] > 2) {
	                result = "Three of a Kind " + card.rankAsString(i - 1) + "'s";
	                valOfHand = 4;
	                break;
	            }
	        }
	        return result;
	    }

	    // different cards of the same rank
	    private String chkForTwoPair(short[] rankCounter) {
	        String result = "";
	        short firstPairRank = -1;
	        short secondPairRank = -1;

	        for (int i = rankCounter.length; i > 0; i--) {
	            if ((firstPairRank < (short) 0) || (secondPairRank < (short) 0)) {
	                if (((rankCounter[i - ONE]) > 1) && (firstPairRank < (short) 0)) {
	                    firstPairRank = (short) (i - ONE);
	                } else if ((rankCounter[i - ONE]) > 1) {
	                    secondPairRank = (short) (i - ONE);
	                }
	            } else {
	                // two pair found, break loop.
	                break;
	            }
	        }

	        // populate output
	        if ((firstPairRank >= (short) 0) && (secondPairRank >= (short) 0)) {
	            if (secondPairRank == (short) 0) {
	                result = "Two Pair: " + card.rankAsString(secondPairRank) + "'s and " + card.rankAsString(firstPairRank)
	                        + "'s";
	            } else {
	                result = "Two Pair: " + card.rankAsString(firstPairRank) + "'s and " + card.rankAsString(secondPairRank)
	                        + "'s";
	            }
	        }
	        valOfHand = 3;
	        return result;
	    }

	    // One is is two cards of the same rank.
	    private String chkForOnePair(short[] rankCounter) {
	        String result = "";

	        for (int i = rankCounter.length; i > 0; i--) {
	            if ((rankCounter[i - ONE]) > 1) {
	                result = "One Pair: " + card.rankAsString(i - ONE) + "'s";
	                break;
	            }
	        }
	        valOfHand = 2;
	        return result;
	    }

	    // highest card out of the 7 possible cards to be used.
	    private String chkForHighCard(short[] rankCounter) {
	        String result = "";

	        for (int i = rankCounter.length; i > 0; i--) {
	            if ((rankCounter[i - ONE]) > 0) {
	                result = "High card: " + card.rankAsString(i - ONE);
	                break;
	            }
	        }
	        valOfHand = 1;
	        return result;
	    }
}
