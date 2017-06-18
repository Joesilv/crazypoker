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
            face = playerCards[index].substring(0, 1);
            suite = playerCards[index].substring(1, 2);
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

      if(dinnerPartyTest(bestSix) == 25.0){
         score = dinnerPartyTest(bestSix);
      } else if(politicsTest(bestSix) == 24.0){
         score = politicsTest(bestSix);
      }else if(orgyTest(bestSix) == 23.0){
         score = orgyTest(bestSix);
      } else if(sixStraightFlushTest(bestSix) > 22.0){
         score = sixStraightFlushTest(bestSix);
      } else if(fiveStraightFlushTest(bestSix) > 21.0){
         score = fiveStraightFlushTest(bestSix);
      }else if(kingdomTest(bestSix) == 20.0){
         score = kingdomTest(bestSix);
      }else if(homosapiensTest(bestSix) > 19.0){
         score = homosapiensTest(bestSix);
      }else if(overfullHouseTest(bestSix) > 18.0){
         score = overfullHouseTest(bestSix);
      }else if(tripletsTest(bestSix) > 17.0){
         score = tripletsTest(bestSix);
      }else if(flushTest(bestSix) > 16.0){
         score = flushTest(bestSix);
      } else if(oddTest(bestSix) == 15.0){
         score = oddTest(bestSix);
      } else if(fourKindTest(bestSix) > 14.0){
         score = fourKindTest(bestSix);
      } else if(sixStraightTest(bestSix) > 13.0){
         score = sixStraightTest(bestSix);
      } else if(evenTest(bestSix) == 12.0){
         score = evenTest(bestSix);
      } else if(monarchyTest(bestSix) == 11.0){
        score = monarchyTest(bestSix);
      } else if(threePairTest(bestSix) > 10.0){
         score = threePairTest(bestSix);
      }else if(fullHouseTest(bestSix) > 9.0){
         score = fullHouseTest(bestSix);
      }else if(monoChromaTest(bestSix) == 8.0){
         score = monoChromaTest(bestSix);
      } else if(fiveStraightTest(bestSix) > 7.0){
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
         kingIndex = cardIndex(cardArr, "K");
         queenIndex = cardIndex(cardArr, "Q");
         //Retrieve cards of same suite
         relatedToKing = findSuit(cardArr, cardArr[kingIndex].face,
            cardArr[kingIndex].suite);
         relatedToQueen = findSuit(cardArr, cardArr[queenIndex].face,
            cardArr[kingIndex].suite);
         if(relatedToKing.contains("Q") && relatedToQueen.contains("K")){
            return 6.0;
         }
      }
      return 0.0;
   }

   public double fiveStraightTest(Card[] cardArr){
      int[] cardValues;
      int repeatFlag = 0;
      int count = 0;
      int highOfStraight = 0;

      cardValues = cardsToValues(cardArr);
      for(int index = 0;index < 5;index++){
         if(repeatFlag > 1){return 0.0;}
         // Sequential card is in numerical order or is a repeat
         if(cardValues[index] == cardValues[index + 1] - 1){
            count++;
            if(count == 4){
               //Determine which card is highest of flush
               if(cardValues[4] == cardValues[5] ||
                  cardValues[4] == cardValues[5] - 1){
                  highOfStraight = cardValues[5];
               } else{
                  highOfStraight = cardValues[4];
               }
               return 7.0 + highOfStraight * .01;
            }
         } else if(cardValues[index] == cardValues[index + 1]){
            repeatFlag++;
         } else{
            count = 0;
         }
      }
      return 0.0;
   }

   public double monoChromaTest(Card[] cardArr){
      if(countColor(cardArr, "b") == 6 || countColor(cardArr, "r") == 6){
         return 8.0;
      } else {
         return 0.0;
      }
   }

   public double fullHouseTest(Card[] cardArr){
      boolean flagOne = false;
      boolean flagTwo = false;
      double threeCard = 0;
      double pair = 0 ;
      for(int index = 0;index < 6;index++){
         countValue(cardArr, cardArr[index].value);
            if(countValue(cardArr, cardArr[index].value) == 3){
               threeCard = cardArr[index].value;
               flagOne = true;
            }else if(countValue(cardArr, cardArr[index].value) == 2){
               pair = cardArr[index].value;
               flagTwo = true;
            }
      }
      if(flagOne && flagTwo){
         return 9.0 + (threeCard * .01)+ (pair * .0001) ;
      } else{
         return 0.0;
      }

   }

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

   public double monarchyTest(Card[] cardArr){
      int jackIndex = cardIndex(cardArr, "J");
      int jackCount = countValue(cardArr, 11);
      int queenCount = countValue(cardArr, 12);
      int kingCount = countValue(cardArr, 13);

      if(jackCount == 1 && queenCount == 1 && kingCount == 1) {
         //Check if king and queen of same suite as jack exist
         if (findSuit(cardArr, "J", cardArr[jackIndex].suite).contains("Q") &&
            findSuit(cardArr, "J", cardArr[jackIndex].suite).contains("K")) {
            return 11.0;
         }
      }
      return 0.0;
   }

   public double evenTest(Card[] cardArr){
      int evenCheck = 0;
      double result;
      for(int index = 0;index < 6;index++){
         if(cardArr[index].value < 11 && cardArr[index].value % 2 == 0){
            evenCheck++;
         }
      }
      if(evenCheck == 6){
         result = 12.0;
      } else{
         result = 0.0;
      }
      return result;
   }

   public double sixStraightTest(Card[] cardArr){
      int[] cardValues;
      int repeatFlag = 0;
      int count = 0;
      double highCard = 0.0;

      cardValues = cardsToValues(cardArr);
      for(int index = 0;index < 5;index++){
         if(repeatFlag > 0){return 0.0;}
         // Sequential card is in numerical order or is a repeat
         if(cardValues[index] == cardValues[index + 1] - 1){
            count++;
            if(count == 5){
               highCard = cardValues[5] * .01;
               return 13.0 + (highCard);
            }
         } else if(cardValues[index] == cardValues[index + 1]){
            repeatFlag++;
         }
      }
      return 0.0;
   }

   public double fourKindTest(Card[]cardArr){
      double value = 0;
      for(int index = 0;index < 6;index++ ){
         if(countValue(cardArr, cardArr[index].value) == 4){
              value = cardArr[index].value;
               return 14.0 + value * .01;
         }
      }
      return 0.0;
   }

   public double oddTest(Card[] cardArr){
      int oddCheck = 0;
      double result;
      for(int index = 0;index < 6;index++){
         if(cardArr[index].value < 10 && cardArr[index].value % 2 == 1){
            oddCheck++;
         }
      }
      if(oddCheck == 6){
         result = 15.0;
      } else{
         result = 0.0;
      }
      return result;
   }



   public double flushTest(Card[] cardArr){
      int[] valuesArr;
      String relatedCards = "";
      int highCard = 0;

      relatedCards = findSuit(cardArr,cardArr[0].face,cardArr[0].suite);

      valuesArr = cardsToValues(cardArr);
      sort(valuesArr);
      highCard = valuesArr[5];
      if(relatedCards.length() == 5){
         return 16.0 + highCard * 0.01;
      } else{
         return 0.0;
      }
   }

   public double tripletsTest(Card[] cardArr){
      int highThreeKind = 0;
      int lowThreeKind = 0;
      double result = 0.0;
      int[] cardValues = cardsToValues(cardArr);

      sort(cardValues);

      //Check that first 3 cards and last 3 cards are three of kind
      if(cardValues[0] == cardValues[1] && cardValues[1] == cardValues[2] &&
         cardValues[3] == cardValues[4] && cardValues[4] == cardValues[5]){
         lowThreeKind = cardValues[0];
         highThreeKind = cardValues[5];
      }

      if(highThreeKind != 0) {
         result = 17.0 + highThreeKind * .01 + lowThreeKind * .0001;
      } else {
         result = 0.0;
      }
      return result;
   }

   public double overfullHouseTest(Card[] cardArr){
      boolean flagOne = false;
      boolean flagTwo = false;
      double fourCard = 0;
      double pair = 0 ;
      for(int index = 0;index < 6;index++){
         //countValue(cardArr, cardArr[index].value);
         if(countValue(cardArr, cardArr[index].value) == 4){
            fourCard = cardArr[index].value;
            flagOne = true;
         }else if(countValue(cardArr, cardArr[index].value) == 2){
            pair = cardArr[index].value;
            flagTwo = true;
         }
      }
      if(flagOne && flagTwo){
         return 18.0 + (fourCard * .01)+ (pair * .0001) ;
      } else{
         return 0.0;
      }

   }
   public double homosapiensTest(Card[] cardArr){
      int value = 0;
      //boolean faceFlag = true;
      int[] valuesArr = cardsToValues(cardArr);

      sort(valuesArr);
      for(int index = 0;index < 6;index++){
         value = valuesArr[index];
         if( value != 11 && value != 12 &&value != 13){
            return  0.0;
         }
      }
         value = valuesArr[5];
         return 19.0 + value * 0.01;
   }

   public double kingdomTest(Card[] cardArr){
      double result = 0.0;
      if(monarchyTest(cardArr) == 11.0){
         if(flushTest(cardArr) > 16){
            result = 20.0;
         } else {
            result = 0.0;
         }
      }else{
         result = 0.0;
      }
      return result;
   }

   public double fiveStraightFlushTest(Card[] cardArr){
      double result = 0.0;
      double flushResult = 0.0;
      if(fiveStraightTest(cardArr) > 7.0){
         flushResult = flushTest(cardArr);
         if( flushResult > 16.0){
            // Expose the high card in flushResult arithmetically
            flushResult = (flushResult - 16) * 100;
            result = 21 + flushResult * 0.01;
         }
      }else{
         result = 0.0;
      }
      return result;
   }

   public double sixStraightFlushTest(Card[] cardArr){
      int[] cardValues = cardsToValues(cardArr);
      int count = 0;
      int highCard = 0;

      for(int index = 0;index < 5;index++){
         if(cardValues[index] == cardValues[index + 1]-1){
            count++;
         } else{
            return 0.0;
         }
      }
      //
      if(count == 5 && flushTest(cardArr) > 16 ){
         highCard = cardValues[5];
         return 22 + highCard *.01;
      }
      return 0.0;

   }

   public double orgyTest(Card[] cardArr){
      int[] valueArr = cardsToValues(cardArr);

      for(int index = 0; index < 6;index++){
         // Eleven and twelve represent jack and queen

         if(valueArr[index] != 11 && valueArr[index] != 12){
            return 0.0;
         }
      }
      //This will only return 23.0 if for loop runs completely through array
      return 23.0;
   }
   public double politicsTest(Card[] cardArr){
      String suits = "";
      String[] allCases = {"QK","KQ","JK","KJ","JQ","QJ"};
      boolean flag = false;
      int one = 0;

      if(countValue(cardArr, 11) == 2 &&
         countValue(cardArr, 12) == 2 &&
         countValue(cardArr, 13) == 2)
      {
         do {
            for (one = 0; one < 6; one++) {
               flag = false;
               suits = findSuit(cardArr, cardArr[one].face, cardArr[one].suite);
               for (int two = 0; two < 6; two++) {
                  if (suits.equals(allCases[two])) {
                     flag = true;
                  }
               }
               //Returns 0 if the monarchy is broken
               if(flag == false){return 0.0 ;}
            }
            if(one == 6){break;}
         }while (flag == true);
         return 24.0;
      }
      return 0.0;
   }

   public double dinnerPartyTest(Card[] cardArr){
      String suits = "";
      if(countValue(cardArr, 12) == 3 && countValue(cardArr, 13) == 3){
         for(int index = 0;index < 6;index++){
            suits =findSuit(cardArr,cardArr[index].face,cardArr[index].suite);
            if( !(suits.equals("K")||suits.equals("Q")) ){
               return 0.0;
            }
         }
         return 25.0;
      }
      return 0.0;
   }

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

   public int countColor(Card[] cardArr, String color){
      int redCount = 0;
      int blackCount = 0;

      if (color.equals("r")) {
         for(int index = 0;index < 6;index++){
            if((cardArr[index].suite.equals("d"))||
               cardArr[index].suite.equals("h")){
               redCount++;
            }
         }return redCount;
      }else if (color.equals("b")) {
         for(int index = 0;index < 6;index++){
            if((cardArr[index].suite.equals("c"))||
               cardArr[index].suite.equals("s")){
               redCount++;
            }
         }return redCount;
      } else{
         System.out.println("Color Does Not Exist");
         return -1;
      }


   }



}
