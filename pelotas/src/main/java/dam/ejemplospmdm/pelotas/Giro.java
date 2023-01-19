package dam.ejemplospmdm.pelotas;

public enum Giro {
    IZDA(-1), DCHA(1);
    int sentido;

    Giro(int sentido) {
        this.sentido = sentido;
    }

    public static Giro aleatorio() {
        return Math.random() < .5 ? Giro.IZDA : Giro.DCHA;
    }

    public int getSentido() {
        return sentido;
    }
}