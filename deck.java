import java.util.Random;

public class deck {
	private card[] cards = new card[52];
	
	public deck() {
        int i = 0;
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 13; k++) {
                cards[i++] = new card(k, j);
            }
        }
    }

    protected void shuffle() {
        int length = cards.length;
        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            int change = i + rnd.nextInt(length - i);
            swapCards(i, change);
        }
    }

    //to find cards
    protected int findCard(card card) {
        for (int i = 0; i < 52; i++) {
            if (card.sameCard(cards[i], card)) {
                return i;
            }
        }
        return -1;
    }
    
    // display the deck
	protected void dspDeck() {
        for (int i = 0; i < cards.length; i++) {
            System.out.println(i + 1 + ": " + cards[i].dspCard());
        }
        System.out.println("\n");
    }
	//return specified card from the deck
    protected card getCard(int cardNum) {
        return cards[cardNum];
    }

    protected void cutDeck() {

        deck tempDeck = new deck();
        Random rnd = new Random();
        int divNum = rnd.nextInt(52);
        for (int i = 0; i < divNum; i++) {
            tempDeck.cards[i] = this.cards[52 - divNum + i];
        }
        for (int j = 0; j < 52 - divNum; j++){
            tempDeck.cards[j + divNum] = this.cards[j];
        }
        this.cards = tempDeck.cards;
    }

    // Swap cards in array to 'shuffle' the deck
    private void swapCards(int n, int change) {
        card tmp = cards[n];
        cards[n] = cards[change];
        cards[change] = tmp;
    }
}