package de.ts.rindenrechenerfullsa;

import java.util.HashMap;

public class RindenRechner {
    private static HashMap<String, HashMap<Character, Double>> baumKoffizienten= new HashMap<>() {
        {
            put("Fichte", new HashMap<>(){
                {
                put('A' , 0.85149);
                put('B', 0.60934);
                put('C', -0.00228);
                }
            });

            put("Tanne", new HashMap<>(){
                {
                    put('A' , 1.76896);
                    put('B', 0.59175);
                    put('C', 0.0);
                }
            });

            put("Weißkiefer", new HashMap<>(){
                {
                    put('A' , 1.59099);
                    put('B', 0.50146);
                    put('C', 0.0);
                }
            });

            put("Schwarzkiefer", new HashMap<>(){
                {
                    put('A' , 5.27169);
                    put('B', 1.12602);
                    put('C', 0.0);
                }
            });

            put("Laerche", new HashMap<>(){
                {
                    put('A' , 3.58012);
                    put('B', 1.03147);
                    put('C', 0.0);
                }
            });

            put("Douglasie", new HashMap<>(){
                {
                    put('A' , -2.13785);
                    put('B', 0.91597);
                    put('C', -0.00375);
                }
            });

            put("Rotbuche", new HashMap<>(){
                {
                    put('A' , 2.61029);
                    put('B', 0.28522);
                    put('C', 0.0);
                }
            });

            put("Traubeneiche (Lehm)", new HashMap<>(){
                {
                    put('A' , 9.88855);
                    put('B', 0.56734);
                    put('C', 0.0);
                }
            });

            put("Traubeneiche (Muschelkalk)", new HashMap<>(){
                {
                    put('A' , 14.31589);
                    put('B', 0.72699);
                    put('C', 0.0);
                }
            });

            put("Bergahorn", new HashMap<>(){
                {
                    put('A' , -0.62466);
                    put('B', 0.73312);
                    put('C', -0.00482);
                }
            });

            put("Esche", new HashMap<>(){
                {
                    put('A' , -7.97623);
                    put('B', 1.40182);
                    put('C', -0.01011);
                }
            });
        }
    };

    public static HashMap<String, HashMap<Character, Double>> getAllBaumKoffizienten() {
        return baumKoffizienten;
    }

    public static double getRindenanteil(String baumArtName, double diameter){
        double result;
        double factor = (RindenRechner.baumKoffizienten.get(baumArtName).get('A') / diameter + RindenRechner.baumKoffizienten.get(baumArtName).get('B') + (RindenRechner.baumKoffizienten.get(baumArtName).get('C') * diameter));
        result = 20 * factor * (1 - 0.05 * factor) ;
        return result;
    }

    public static double getRindenstaerke(String baumArtName, double diameter)
    {
        double result;

        result = RindenRechner.baumKoffizienten.get(baumArtName).get('A') + RindenRechner.baumKoffizienten.get(baumArtName).get('B') * diameter + RindenRechner.baumKoffizienten.get(baumArtName).get('C') * Math.pow(diameter,2);
        result = result / 2;
        return result;
    }
}
