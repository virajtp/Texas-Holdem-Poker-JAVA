public class board {
    private card[] boardCards = new card[5];
    private card[] bCards = new card[3];	//burn cards

    public board() {}

    protected void setBoardCard(card card, int num) {
        this.boardCards[num] = card;
    }

    protected card getCard(int num) {
        return this.boardCards[num];
    }

    protected void setBurnCard(card card, int num) {
        this.bCards[num] = card;
    }

    protected card getBurnCard(int num) {
        return this.bCards[num];
    }

    protected int boardSize() {
        return boardCards.length;
    }

    // display the board
    protected void dspBoard(int value) {
        System.out.println("The cards on the board ->");
        for (int i = 0; i < value; i++) {
            System.out.println("\t\t" + (i + 1) + ": " + getCard(i).dspCard());
        }
        System.out.println();
    }

    // display the burn cards
    protected void dspBurnCards() {
        System.out.println("The Burn cards ->");
        for (int i = 0; i < bCards.length; i++) {
            System.out.println("\t\t" + (i + 1) + ": " + getBurnCard(i).dspCard());
        }
        System.out.println("\n");
    }
}