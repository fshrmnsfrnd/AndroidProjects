package de.ts.btin2.eanapp;

public class EanLengthException extends RuntimeException {
    public EanLengthException(){
        super("Die Ean hat eine ungültige Länge!");
    }
}
