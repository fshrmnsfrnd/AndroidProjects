package de.ts.btin2.eanapp;

public class EanNotNumericException extends RuntimeException {
    public EanNotNumericException(){
        super("Ean ist keine Zahl! Bitte prüfen Sie Ihre Eingabe.");
    }
}
