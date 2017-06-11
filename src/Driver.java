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


      Card card1 = new Card("10","c");
      Card card2= new Card("8","s");
      Card card3 = new Card("4","c");
      Card card4 = new Card("2","s");
      Card card5 = new Card("2","s");
      Card card6 = new Card("6","h");

      Card[] cardArr = {card1,card2,card3,card4,card5,card6};

      score = player.score(card1,card2,card3,card4,card5,card6);
      System.out.println(score);

   }
}
