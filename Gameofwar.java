/*
 *
 * PURPOSE: Implementing A simple card game Called War using Stacks and Queues.
 *
 *
 * */

import java.util.Random;

public class A3PatelHarsh {

    public static void main(String[] args) {
        game war = new game("","Player 2"); // Start playing the War game.
       // war.play();
        String line = "length";
        String li = "width";
        System.out.println(line.compareTo(li)<0);
            }
}

class game{
    String nameOne;
    String nameTwo;
    private String[] rank  = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"}; // the Ranks of cards
    private String[] suits = {"S","D", "H","C"}; // Suits of Cards

    public game(String nameOne,String nameTwo){ // makes the game with the player names.
        this.nameOne = nameOne;
        this.nameTwo = nameTwo;
    }

    public void play(){ // Playing the Game.
        System.out.println("Playing the War card game... \n");

        Queue newDeck = new Queue();  // Creates a new deck of 52 cards.
        for (int i = 0; i < 52; i++) {
            newDeck.enqueue(i);
        }
        newDeck.shuffle();  //Shuffles the cards.

        Queue playerOne = new Queue();
        Queue playerTwo = new Queue();

        for (int i = 0; i < newDeck.getHandSize(); i+=2) { // Distributing the cards equally to both the players.
            playerOne.enqueue(newDeck.dequeue());
            playerTwo.enqueue(newDeck.dequeue());
        }//end for.



        Stack warStack = new Stack();  // Stack of cards created when There is a war.
        boolean win = false;
        while (!win) { // Loop does not exit until one Player is out of cards.
            if (playerOne.isEmpty()) { // if the first Player is out of cards. Second Player wins.
                int cardP2 = playerTwo.dequeue(); //top card.
                int rankTwo = cardP2 % rank.length; // Rank of the card
                String cardTwo = rank[rankTwo] + suits[cardP2 / rank.length]; //The Card Player 2 has on top.


                if (!warStack.isEmpty()) { //if player one is out of cards when there is a war or after the war.
                    System.out.println(nameOne + ": OUT OF CARDS." + "\t" + nameTwo + ": " + cardTwo + "\t");

                    while (!warStack.isEmpty()) { //add the winning cards to the winner.
                        playerTwo.enqueue(warStack.pop());
                    }

                    System.out.println("**" + nameTwo +" won this battle.\n");

                }
                System.out.println("** "+ nameOne +" is out of cards.\n\n"+nameTwo+" won the game!");

                playerTwo.enqueue(cardP2);  //adds back the top card as player one was out of cards.

                win = true;   // exit the loop as game is over.

            } else if (playerTwo.isEmpty()) {  // if the second Player is out of cards. First Player wins.
                int cardP1 = playerOne.dequeue(); //top card
                int rankOne = cardP1 % rank.length;  // rank of the card.
                String cardOne = rank[rankOne] + suits[cardP1 / rank.length];  //The Card Player 1 has on top.

                if (!warStack.isEmpty()) { // if player two is out of cards when there is a war or after the war.
                    System.out.println(nameOne + ": " + cardOne + "\t" + nameTwo + ": OUT OF CARDS" + "\t");

                    while (!warStack.isEmpty()) { //add the winning cards to the winner.
                        playerOne.enqueue(warStack.pop());
                    }

                    System.out.println("**" + nameOne + " won this battle.\n");
                }
                System.out.println("** "+ nameTwo +" is out of cards.\n\n"+nameOne+" won the game!");

                playerOne.enqueue(cardP1); //puts the top card back.

                win = true; //exit the loop as the game is over.

            } else {
                /* Takes the top cards of the two Players when both of them have cards.
                * Compares the ranks of the card.(suit does not matter)
                * */
                int cardP1 = playerOne.getFront();
                int cardP2 = playerTwo.getFront();
                int rankOne = cardP1 % rank.length;
                int rankTwo = cardP2 % rank.length;

                //strings used to print the cards.
                String cardOne = rank[rankOne] + suits[cardP1 / rank.length];
                String cardTwo = rank[rankTwo] + suits[cardP2 / rank.length];

                if (rankTwo > rankOne) {  // if the Second player's card is a higher rank the First player's card.

                    if (!warStack.isEmpty()) { // if it was a war second player wins the war.
                        //prints the cards of both the players.
                        System.out.println(nameOne + ": " + cardOne + "\t" + nameTwo + ": " + cardTwo + "\t");

                        while (!warStack.isEmpty()) { //adds the cards to the winner's queue. (Player two)
                            playerTwo.enqueue(warStack.pop());
                        }
                        System.out.println("**" + nameTwo + " won this battle.");

                    } else { // if it was not a war.
                        //Prints the cards and the winner.
                        System.out.println(nameOne + ": " + cardOne + "\t" + nameTwo + ": " + cardTwo + "\t" + nameTwo + " gets both the cards.");

                        //adds the cards to the winner's queue(Player Two)
                        playerTwo.enqueue(playerTwo.dequeue());
                        playerTwo.enqueue(playerOne.dequeue());

                    }
                } else if (rankOne > rankTwo) {// if the First player's card is a higher rank the Second player's card.

                    if (!warStack.isEmpty()) { // if it was a war First Player wins.
                        //prints the cards of both the players.
                        System.out.println(nameOne + ": " + cardOne + "\t" + nameTwo + ": " + cardTwo + "\t");


                        while (!warStack.isEmpty()) {// adds the cars to the winner's queue(Player one)
                            playerOne.enqueue(warStack.pop());
                        }

                        System.out.println("**" + nameOne + " won this battle.");
                    } else { //if it was not a war.
                        //Prints the cards and the winner.
                        System.out.println(nameOne + ": " + cardOne + "\t" + nameTwo + ": " + cardTwo + "\t" + nameOne + " gets both the cards.");

                        //adds the cards to the winner's queue(Player one)
                        playerOne.enqueue(playerOne.dequeue());
                        playerOne.enqueue(playerTwo.dequeue());
                    }
                } else {  // Both have the same cards. ITS A WAR!
                    //Print the two cards and add the to the stack.
                    System.out.println(nameOne + ": " + cardOne + "\t" + nameTwo + ": " + cardTwo);
                    warStack.push(playerOne.dequeue());
                    warStack.push(playerTwo.dequeue());
                    System.out.println("** Its a WAR!");
                    System.out.println(nameOne + " ante:");
                    for (int i = 0; i < 2; i++) { //adds Player one's  next two cards to the ante and prints them .
                        if (!playerOne.isEmpty()) { //only adds if cards if the player is not out of cards

                            int cardP1ante = playerOne.dequeue();
                            System.out.println(rank[cardP1ante % rank.length] + suits[cardP1ante / rank.length]);

                            warStack.push(cardP1ante);
                        } //end if
                    }//end for

                    System.out.println(nameTwo + " ante:");
                    for (int i = 0; i < 2; i++) {//adds Player Two's  next two cards to the ante and prints them .

                        if (!playerTwo.isEmpty()) { //only adds if cards if the player is not out of cards

                            int cardP2ante = playerTwo.dequeue();
                            System.out.println(rank[cardP2ante % rank.length] + suits[cardP2ante / rank.length]);

                            warStack.push(cardP2ante);
                        }//end if
                    }//end for

                }// end else ->War
            }//end else -> Both have cards remaining.
        }//end While.

    }//end Play().

}// End Game Class

//Class Queue
class Queue {
    private int front; // pointer to the front
    private int end; // pointer to the end
    private int[] hand;  // stores the player's hand.
    private static final int HAND_SIZE = 53;  // size of the hand.
    //10 represented as 1 to compare and find the value. Here 1 means 10 in the deck .

    public Queue() { // constructor.
        front = 0;
        end = 0;
        hand = new int[HAND_SIZE];
    }

    public boolean isFull() { // checks if the array is full
        return (end + 1) % HAND_SIZE == front;
    } //checks if the queue is full.

    public boolean isEmpty() { // checks if the array is empty
        return end == front;
    } //checks if the queue is empty.

    private void swap(int []deck, int i, int j){ // Swaps the items in the queue.
        int temp = deck[ i ];
        deck[ i ] = deck[ j ];
        deck[ j ] = temp;
    }//swap

    public void shuffle() { // shuffles the deck randomly
        Random random = new Random(); // takes random indexes and shuffles the deck
        for (int i = 0; i < 5000 ; i++) {
            int k = random.nextInt(HAND_SIZE - 2);
            int j = random.nextInt(HAND_SIZE - 2);
            swap(hand, k, j);
        }
    }//shuffle

    public int dequeue() { // removes the Item from the queue.
        int removeCard = hand[front];
        if (!this.isEmpty()) { // if its not empty deletes the first item ..
            front = (front + 1) % HAND_SIZE; // sets the next item as the first one.
        }else{ // if tries to remove when the list is empty .
            System.out.println("List is already empty");
        }
        return removeCard;
    }//dequeue

    public void enqueue(int card) { // adds an item to the end
        if (!this.isFull()) { // if the queue is not full adds the card
            hand[end] = card;
            end = (end + 1) % HAND_SIZE; // sets end to the next empty spot
        }else{ // trying to insert when the array is full .
            System.out.println("you cannot insert ,its full!");
        }
    }//enqueue


    /* Getter methods*/

    public int getFront(){ // gives you the index of the front item
        return hand[front];
    }
    public int getEnd() { // gives you the index of the last item .
        return hand[end];
    }
    public int getHandSize(){
        return HAND_SIZE-1;
    }

    /*end of Getter methods*/
}

class Stack{

    private int top; //keeps a track of the top item .
    private static final int STACK_SIZE = 52; // highest possible size of the stack .
    int[] stack;

    public Stack(){ // constructor
        top = -1;
        stack = new int[STACK_SIZE];
    }

    public boolean isEmpty(){ // checks if the stack is empty
        return top == -1;
    }

    public boolean isFull(){ // checks if the stack is full
        return top == STACK_SIZE;
    }

    public void push(int card){ //adds a new card to the stack .
        if(!this.isFull()){ // if its not full adds a card at the top
            stack[top+1]= card;
            top = top + 1;
        }else{
            System.out.println("Already full\n");
        }
    }//push

    public int pop(){ //removes the card from the stack and returns it.
        int temp = 0;
        if(!this.isEmpty()){ // if its not empty removes the card at the top.
            temp = stack[top];
            top = top-1;
        }else{
            System.out.println("Already Empty, Nothing to pop.\n");
        }
        return temp;
    }//pop


    /* Getter methods*/
    public int getTop() {
        return top;
    }

    /*End of Getter methods*/

} // end Stack

