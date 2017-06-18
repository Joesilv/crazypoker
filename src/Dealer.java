/*
   Course:  COMP282
   Semester:  SUMMER 2017
   Assignment:  CRAZY POKER
   File Name:  Dealer.java
   Author:  Joseph Silva
   Student ID:  107378412
   Email Address:  Joseph.silva.275@my.csun.edu
 */
import java.util.Scanner;

public class Dealer {

   private Player[] playerScores;
   private String[] dealtCards;
   private int numPlayers = 0;

   private int cardsPerPlayer = 3;
   private int cardsCommunity = 5;

   // Reads user input, distributes players 8 cards each
   public void readAllCards(String[] argInput){
      int index = 0;
      int startCut = 5;
      int endCut = 8;

      promptInput(argInput);
      playerScores = new Player[numPlayers];
      //Loop for every player
         for(index = 0; index < numPlayers;index++) {
            //create a player object
            Player player = new Player(index + 1);
            //distribute their 3 cards
            player.receiveCards(copyArray(dealtCards,5,startCut,endCut, 8));
            startCut += 3;
            endCut +=3;
            //determine their best combination
            player.tryAllCards();
            playerScores[index] = player;

         }//end
      for(index = 0;index < numPlayers;index++){
         System.out.println("Player " + playerScores[index]
            .playerNumber + ": " + playerScores[index].highestScore);
      }


   }

 /*  public void showTableRanking(){

   }*/

   // Prompts user to input , fills array.
   public void promptInput(String[] argInput){
      //String userInput = "";
      //String[] allCards;
      int cardsAllPlayers;
/*
      Scanner scan = new Scanner(System.in);

      System.out.println("Welcome To Crazy Poker \nInput All Cards:");
      userInput = scan.nextLine();
      allCards = userInput.split(" ");*/
      do{
         dealtCards = argInput;
         cardsAllPlayers = dealtCards.length - cardsCommunity;
         numPlayers = cardsAllPlayers / cardsPerPlayer;
      } while((dealtCards.length < 11) || (cardsAllPlayers % 3) != 0);
   }

/*   public void sortPlayerScores(){
      for(int index = 0;index < playerScores.length;index++){

      }
   }*/

   //Print an Array
   public void printArray(String[] arr) {
      // if array only has one item, print and leave
      if(arr.length == 1){
         System.out.println("Arr[" + 0 + "] : " + arr[0]);
         return;
      }
      for (int i = 0; i < arr.length; i++) {
         System.out.print("Arr[" + i + "] : " + arr[i] + " ");
      }
      System.out.println();
   }

   /*Copies two portions of a single array into a new array
   Start is inclusive, endOne and endTwo are exclusive*/
   public String[] copyArray(String[] arr, int endOne, int start,
                             int endTwo,int length){

      String[] retArr = new String[length];
      // Step is an index that is not affected by loop iteration
      int step = 0;

      for(int index = 0;index < endOne;index++){
         retArr[step] = arr[index];
         step++;
      }
      for(int index = start;index < endTwo;index++){
         retArr[step] = arr[index];
         step++;
      }
      return retArr;
   }

}
