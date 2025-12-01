package de.ts.btin2.eanapp;

public class EanIntegrityException extends RuntimeException {
    public EanIntegrityException() {
        super("Ean ist ungültig! Bitte prüfen Sie Ihre Eingabe.");
    }
}
