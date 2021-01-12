public class player {
    public card[] holeCards = new card[2];
    private int score;
    
    public player() {}

    public player(card c1, card c2) {
        holeCards[0] = c1;
        holeCards[1] = c2;
    }

    public int getScore() {
        return score;
    }

    public void addscore(int tmp) {
        score = score + tmp;
    }
    
    protected void setCard(card card, int cardNum) {
        holeCards[cardNum] = card;
    }

    protected card getCard(int cardNum) {
        return holeCards[cardNum];
    }

    protected int holeCardSize() {
        return holeCards.length;
    }

    protected void dspCards(int playerNum) {
    	if(playerNum!=0)
            System.out.println("Player " + (playerNum+1) + " hole cards ->");
            else
             System.out.println("Your hole cards ->");

        for (int i = 0; i < 2; i++) {
            System.out.println("\t\t" + holeCards[i].dspCard());
        }
        System.out.println();
    }
}