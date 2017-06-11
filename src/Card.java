/*
   Course:  COMP282
   Semester:  SUMMER 2017
   Assignment:  CRAZY POKER
   File Name:  Card.java
   Author:  Joseph Silva
   Student ID:  107378412
   Email Address:  Joseph.silva.275@my.csun.edu
 */
public class Card {

   public String face;
   public String suite;
   public int value;

   public Card(String face, String suite){
      this.face = face;
      this.suite = suite;
      setValue(face);
   }

   public void printCard(){
      System.out.print(face + suite);
   }

   public void setValue(String faceCard){
      switch(faceCard){
         case "2": value = 2;
            break;
         case "3": value = 3;
            break;
         case "4": value = 4;
            break;
         case "5": value = 5;
            break;
         case "6": value = 6;
            break;
         case "7": value = 7;
            break;
         case "8": value = 8;
            break;
         case "9": value = 9;
            break;
         case "10": value = 10;
            break;
         case "j": value = 11;
            break;
         case "q": value = 12;
            break;
         case "k": value = 13;
            break;
         case "a": value = 14;
            break;
         default:
            System.out.println("This is not a valid card.");
            break;


      }
   }

}
