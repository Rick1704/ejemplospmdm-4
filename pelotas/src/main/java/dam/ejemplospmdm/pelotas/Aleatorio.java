package dam.ejemplospmdm.pelotas;

import java.util.Random;

public class Aleatorio {

    public static int sgte(int min, int max) {
        int l = max - min + 1;
        return ((int) (Math.random() * l * 1000) % l) + min;
    }

}
