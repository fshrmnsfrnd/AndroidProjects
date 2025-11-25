/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.btin2.ean_app;


public class EanSupport {
   public void validate(String ean){
        checkLength(ean, 13);
        checkNumeric(ean);
        int readPz = Integer.valueOf(ean.substring(12, 13));
        int calcPz = calcPz(ean);
   }
   
   private int calcPz(String ean){
       int sum = 0;
       int pz = 0, rest = 0, diff = 0;

        //calc PZ
        for(int i = 0; i < ean.length()-1; i++){
            int number = Character.getNumericValue(ean.charAt(i));
            if ((i + 1) % 2 == 0) {
                sum = sum + 3 * number;
            }
            else{
                sum = sum + number;
            }
        }
        //bestimmung des Rests
        rest = sum % 10;
        diff = 10-rest;
        pz = diff % 10;
        return pz;
   }
   
   private void checkLength(String ean,int validLength){
       //hier müssen Sie die Prüfungen einbauern
       if(ean.length() != validLength){
           throw new EanIntegrityException();
       }
       
   }
   private void checkNumeric(String ean){
       //hier müssen Sie die Prüfungen einbauern
       try{
           Integer.parseInt(ean);
       }catch(NumberFormatException e){
           throw new EanNotNumericException();
       }
   }  
}
