package de.ts.btin2.eanapp;

public class EanSupport {
    public void validate(String ean){
        //checks
        checkLength(ean,13);
        checkNumeric(ean);

        // check PZ
        int calcPz = calcPz(ean);
        int readPz = Integer.valueOf(ean.substring(12));

        if (calcPz != readPz){
            throw new EanIntegrityException();
        }
    }

    public int calcPz(String ean){
        int sum = 0;
        int pz = 0, rest = 0, diff = 0;

        //checks
        checkLength(ean,12);
        checkNumeric(ean);

        //calc PZ
        for(int i = 0; i < 12; i++){
            int number = Character.getNumericValue(ean.charAt(i));

            if ((i + 1) % 2 == 0) {
                sum = sum + 3 * number;
            }else{
                sum = sum + number;
            }
        }
        rest = sum % 10;
        diff = 10-rest;
        pz = diff % 10;

        return pz;
    }

    private void checkLength(String ean,int validLength){
        if (ean.length() != validLength){
            throw new EanLengthException();
        }
    }

    private void checkNumeric(String ean){
        for(int i = 0; i < ean.length(); i++){
            char c = ean.charAt(i);

            if (!Character.isDigit(c)){
                throw new EanNotNumericException();
            }
        }
    }
}
