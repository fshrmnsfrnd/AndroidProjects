package ts.btin2.blogic;

public class Raum{
    private String raumName;
    private double laenge;
    private double breite;
    private double hoehe;

    public Raum(String raumName, double laenge, double breite, double hoehe){
        this.setRaumName(raumName);
        this.setLaenge(laenge);
        this.setBreite(breite);
        this.setHoehe(hoehe);
    }

    //Methods
    public double getDeckenFlaeche(){
        return this.getLaenge() * this.getBreite();
    }

    public double getWandFlaeche(){
        double wand1 = this.getHoehe() * this.getBreite();
        double wand2 = this.getHoehe() * this.getLaenge();
        return 2*(wand1 + wand2);
    }

    //Getters
    public String getRaumName(){
        return raumName;
    }

    public double getLaenge(){
        return laenge;
    }

    public double getBreite(){
        return breite;
    }

    public double getHoehe(){
        return hoehe;
    }

    //Setters
    public void setRaumName(String raumName){
        this.raumName = raumName;
    }

    public void setLaenge(double laenge){
        this.laenge = laenge;
    }

    public void setBreite(double breite){
        this.breite = breite;
    }

    public void setHoehe(double hoehe){
        this.hoehe = hoehe;
    }
}
