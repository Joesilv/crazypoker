/*
   Course:  COMP282
   Semester:  SUMMER 2017
   Assignment:  CRAZY POKER
   File Name:  Player.java
   Author:  Joseph Silva
   Student ID:  107378412
   Email Address:  Joseph.silva.275@my.csun.edu
 */


import java.util.Arrays;

import static java.util.Arrays.sort;

public class Player {

   private double highestScore = 0.0;
   private double currentScore = 0.0;
   Card[] playerHand = new Card[8];


   public void receiveCards(String[] playerCards) {

      int index = 0;
      String face;
      String suite;

      //Converts String array to card object array
      for (index = 0; index < playerCards.length; index++) {
         if (playerCards[index].length() > 2) {
            face = playerCards[index].substring(0, 2);
            suite = playerCards[index].substring(2, 3);
         } else {
            face = playerCards[index].substring(0, 1);
            suite = playerCards[index].substring(1, 2);
         }
         Card card = new Card(face, suite);
         playerHand[index] = card;
      }
      printCardArray(playerHand);
   }

   public void printCardArray(Card[] arr) {
      int index = 0;
      for (index = 0; index < arr.length; index++) {
         System.out.print("Arr[" + index + "] : ");
         arr[index].printCard();
         System.out.print(" ");
      }
      System.out.println();
   }

   public void tryAllCards() {

      for (int first = 0; first < 3; first++) {
         for (int second = first + 1; second < 4; second++) {
            for (int third = second + 1; third < 5; third++) {
               for (int fourth = third + 1; fourth < 6; fourth++) {
                  for (int fifth = fourth + 1; fifth < 7; fifth++) {
                     for (int sixth = fifth + 1; sixth < 8; sixth++) {
                        //score method
                        currentScore =
                           score(playerHand[first],
                              playerHand[second],
                              playerHand[third], playerHand[fourth],
                              playerHand[fifth], playerHand[sixth]);
                        if (currentScore > highestScore) {
                           highestScore = currentScore;
                        }
                     }
                  }
               }
            }
         }
      }
      System.out.println(highestScore);
   }

   public double score(Card first, Card second, Card third, Card fourth,
                       Card fifth, Card sixth) {
      Card[] bestSix = bestSixArr(first, second, third, fourth, fifth, sixth);
      double score = 0;
      if(threePairTest(bestSix) > 10.0){
         score = threePairTest(bestSix);
      }else if(fiveStraightTest(bestSix) == 7.0){
         score = fiveStraightTest(bestSix);
      }else if(swingersTest(bestSix) == 6.0){
         score = swingersTest(bestSix);
      }else if (threeOfKindTest(bestSix) > 5.0) {
         score = threeOfKindTest(bestSix);
      } else if (twoPairTest(bestSix) > 4.0) {
         score = twoPairTest(bestSix);
      } else if (rainbowTest(bestSix) == 3.0) {
         score = rainbowTest(bestSix);
      } else if (onePairTest(bestSix) > 2.0) {
         score = onePairTest(bestSix);
         //System.out.println("There is a pair, score is: " + score);
      } else if (rainbowTest(bestSix) == 1.0) {
         score = rainbowTest(bestSix);
         //System.out.println("There is a rainbow, score is: " + score);
      }
      return score;

   }

   public Card[] bestSixArr(Card first, Card second, Card third, Card fourth,
                            Card fifth, Card sixth) {
      Card[] bestSix = new Card[6];
      bestSix[0] = first;
      bestSix[1] = second;
      bestSix[2] = third;
      bestSix[3] = fourth;
      bestSix[4] = fifth;
      bestSix[5] = sixth;
      return bestSix;
   }

   /*public int nonRainbowTest(){

   }*/

   public double onePairTest(Card[] cardArr) {
      double result = 0.0;
      for (int index = 0; index < 6; index++) {
         for (int kIndex = index + 1; kIndex < 6; kIndex++) {
            if (cardArr[index].value == cardArr[kIndex].value) {
               result = 2.0 + (cardArr[index].value * 0.01);
               return result;
            }
         }
      }
      return result;
   }

   //Checks for non-rainbow and Rainbow
   public double rainbowTest(Card[] cardArr) {
      double result = 0.0;
      boolean[] suitCheck = {true, true, true, true};

      for (int index = 0; index < 6; index++) {
         switch (cardArr[index].suite) {
            case "s":
               suitCheck[0] = false;
               break;
            case "h":
               suitCheck[1] = false;
               break;
            case "c":
               suitCheck[2] = false;
               break;
            case "d":
               suitCheck[3] = false;
               break;
            default:
               System.out.println("There is an error in RainbowTest");
               break;
         }
      }

      if (suitCheck[0] || suitCheck[1] || suitCheck[2] || suitCheck[3]) {
         result = 1.0;
      } else if (!suitCheck[0] && !suitCheck[1] && !suitCheck[2] && !suitCheck[3]) {
         result = 3.0;
      }
      return result;
   }

   public double twoPairTest(Card[] cardArr) {
      //Pairvalue holds the value of each pair for comparison
      double[] pairValues = new double[3];
      double result = 0;
      int numPairs = 0;
      int value = 0;


      pairValues[0] = 0;
      for (int index = 0; index < 6; index++) {
         for (int indexK = index + 1; indexK < 6; indexK++) {
            //Increment only if pair is unique
            if ((cardArr[index].value == cardArr[indexK].value) &&
               (value != cardArr[index].value)) {
               numPairs++;
               pairValues[numPairs] = cardArr[index].value;
               if (numPairs == 2) {
                  sort(pairValues);
                  result = 4.0 + (pairValues[2] * 0.01
                     + pairValues[1] * 0.0001);
                  return result;
               }
            }
         }
      }
      return result;
   }

   //Note: Returns highest three of a kind value as double
   public double threeOfKindTest(Card[] cardArr) {
      double[] pairValues = new double[3];
      double result = 0.0;
      int value = 0;
      int numOfThreeKind = 0;

      for (int first = 0; first < 4; first++) {
         for (int second = first + 1; second < 5; second++) {
            for (int third = second + 1; third < 6; third++) {
               if ((cardArr[first].value == cardArr[second].value) &&
                  (cardArr[first].value == cardArr[third].value)) {
                  //numOfThreeKind++;
                  //value = cardArr[first].value;
                  if (cardArr[first].value > value) {
                     value = cardArr[first].value;
                     result = 5.0 + (value * 0.01);
                  }
                  //result = 5.0 + (cardArr[first].value * 0.01);
               }
            }
         }
      }
      return result;
   }

   public double swingersTest(Card[] cardArr) {
      String[] suites = {"c","d","h","s"};
      String relatedToKing = "";
      String relatedToQueen = "";
      int kingIndex = -1;
      int queenIndex = -1;

      //King and Queen both exist as pairs
      if((countValue(cardArr, 13) == 2) && (countValue(cardArr, 12) == 2 )){
         //Retrieve index of king & queen
         kingIndex = cardIndex(cardArr, "k");
         queenIndex = cardIndex(cardArr, "q");
         //Retrieve cards of same suite
         relatedToKing = findSuit(cardArr, cardArr[kingIndex].face,
            cardArr[kingIndex].suite);
         relatedToQueen = findSuit(cardArr, cardArr[queenIndex].face,
            cardArr[kingIndex].suite);
         if(relatedToKing.contains("q") && relatedToQueen.contains("k")){
            return 6.0;
         }
      }
      return 0.0;
   }

   public double fiveStraightTest(Card[] cardArr){
      int[] cardValues;
      int repeatFlag = 0;
      int count = 0;

      cardValues = cardsToValues(cardArr);
      for(int index = 0;index < 5;index++){
         if(repeatFlag > 1){return 0.0;}
         // Sequential card is in numerical order or is a repeat
         if(cardValues[index] == cardValues[index + 1] - 1){
            count++;
            if(count == 4){
               return 7.0;
            }
         } else if(cardValues[index] == cardValues[index + 1]){
            repeatFlag++;
         }
      }
      return 0.0;
   }

   /*public int monoChromaTest(){

   }*/

   /*public int fullHouseTest(){

   }*/

   public double threePairTest(Card[] cardArr) {
      //Pairvalue holds the value of each pair for comparison
      double[] pairValues = new double[4];
      double result = 0;
      int numPairs = 0;
      int value = 0;
      int valueTwo = 0;


      pairValues[0] = 0;
      for (int index = 0; index < 6; index++) {
         for (int indexK = index + 1; indexK < 6; indexK++) {
            //Increment only if pair is first occurance
            if ((cardArr[index].value == cardArr[indexK].value) &&
               (value != cardArr[index].value) &&
               (valueTwo != cardArr[index].value)) {
               numPairs++;
               //Store the first occurance of this pair
               pairValues[numPairs] = cardArr[index].value;
               if (numPairs == 3) {
                  sort(pairValues);
                  result = 10.0 + (pairValues[3] * 0.01
                     + pairValues[2] * 0.0001
                     + pairValues[1] * 0.000001);
                  return result;
               } else if(numPairs == 1){
                  value = cardArr[index].value;
               } else if(numPairs == 2){
                  valueTwo = cardArr[index].value;
               }
            }
         }
      }
      return result;
   }

   /*public int monarchyTest(){

   }*/

   /*public int evenTest(){

   }*/

   /*public int sixStraightTest(){

   }*/

   /*public int fourKindTest(){

   }*/

   /*public int oddTest(){

   }*/

   /*public int flushTest(){

   }*/

   /*public int tripletsTest(){

   }*/

   /*public int overfullHouseTest(){

   }
*/
   /*public int homosapiensTest(){

   }*/

   /*public int kingdomTest(){

   }*/

   /*public int fiveStraightFlushTest(){

   }*/

   /*public int sixStraightFlushTest(){

   }*/

   /*public int orgyTest(){

   }
*/
   /*public int politicsTest(){

   }*/

   /*public int dinnerPartyTest(){

   }*/

   // Return number of times a value appears in an array
   public int countValue(Card[] cardArr, int value) {
      int count = 0;
      for (int index = 0; index < 6; index++) {
         if (value == cardArr[index].value) {
            count++;
         }
      }
      return count;
   }

   //Finds related cards of same suite as string
   public String findSuit(Card[] cardArr, String face, String suite) {
      String sameSuit = "";
      boolean flag = false;
      for(int index = 0;index < cardArr.length;index++){
         // Concatenate cards of same suite
         if(cardArr[index].suite.equals(suite)&&cardArr[index].face != face){
            sameSuit += cardArr[index].face;
         }
 /*        Verifies the card in argument exists
         if(face == cardArr[index].face && suite == cardArr[index].suite){
            flag = true;
         }*/
      }
      return sameSuit;
   }

   // Returns first index of given card face
   public int cardIndex(Card[] cardArr, String face){

      for(int index = 0; index < 6;index ++){
         if(cardArr[index].face.equals(face)){
            return index;
         }
      }
      return -1;
   }

   //Converts an array of card objects to an array of their sorted values
   public int[] cardsToValues(Card[] cardArr){
      int[] cardValues = new int[6];
      for(int index = 0;index < 6;index++){
         cardValues[index] = cardArr[index].value;
      } sort(cardValues);
      return cardValues;
   }

   private boolean aceCheck(int[] valuesArr){
      if
   }

}
