/*
   Course:  COMP282
   Semester:  SUMMER 2017
   Assignment:  CRAZY POKER
   File Name:  Driver.java
   Author:  Joseph Silva
   Student ID:  107378412
   Email Address:  Joseph.silva.275@my.csun.edu
 */
public class Driver {

   public static void main(String args[]){

      double score = 0;
      Dealer dealer = new Dealer();
      Player player = new Player();

      //dealer.readAllCards();

      //FOR TESTING USE ONLY VALID CARDS, NO DUPLICATES OF SAME SUITE
      //OR ESLE FUNCTIONS WILL NOT PERFORM AS INTENDED
      Card card1 = new Card("Q","d");
      Card card2= new Card("Q","s");
      Card card3 = new Card("K","s");
      Card card4 = new Card("Q","h");
      Card card5 = new Card("K","h");
      Card card6 = new Card("K","d");

      Card[] cardArr = {card1,card2,card3,card4,card5,card6};

      score = player.score(card1,card2,card3,card4,card5,card6);
      System.out.println(score);

   }
}
