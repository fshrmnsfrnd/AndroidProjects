package de.btin2.rindenabzugsrechner;

import java.util.HashMap;

public class RindenRechner{
    public static double[] getKoffizient(String baumArt){
        HashMap<String, double[]> BaumKoffizienten= new HashMap<>(){
            {
                put("Fichte", new double[]{0.85149,0.60934,-0.00228});
                put("Tanne", new double[]{1.76896,0.59175,0});
                put("Weißkiefer", new double[]{1.59099,0.50146,0});
                put("Schwarzkiefer", new double[]{5.27169,1.12602,0});
                put("Lärche", new double[]{3.58012,1.03147,0});
                put("Douglasie", new double[]{-2.13785,0.91597,-0.00375});
                put("Rotbuche", new double[]{2.61029,0.28522,0});
                put("Traubeneiche (Lehm)", new double[]{9.88855,0.56734,0});
                put("Traubeneiche (Muschelkalk)", new double[]{14.31589,0.72699,0});
                put("Bergahorn", new double[]{-0.62466,0.73312,-0.00482});
                put("Esche", new double[]{-7.97623,1.40182,-0.01011});

            }
        };

        return BaumKoffizienten.get(baumArt);
    }

    public static double calcRindeMM(String baumArt, double baumDM){
        double a = getKoffizient(baumArt)[0];
        double b = getKoffizient(baumArt)[1];
        double c = getKoffizient(baumArt)[2];
        baumDM = baumDM * 10;

        return a + b*baumDM + c*baumDM;
    }

    public static double calcRindePercent(String baumArt, double baumDM){
        double a = getKoffizient(baumArt)[0];
        double b = getKoffizient(baumArt)[1];
        double c = getKoffizient(baumArt)[2];

        return 20.0*((a/baumDM)+b+c*baumDM)*(1.0-0.5*((a/baumDM)+b+c*baumDM));
    }
}
