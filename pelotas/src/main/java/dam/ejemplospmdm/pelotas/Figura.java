package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Figura {

    public enum Giro {
        IZDA(-1), DCHA(1);
        int sentido;

        Giro(int sentido) {
            this.sentido = sentido;
        }

        public int getSentido() {
            return sentido;
        }
    }

    protected float x;
    protected float y;
    protected final Paint paint = new Paint();
    protected float angulo = 0;
    private float vAngular;
    private Giro giro;

    public Figura(float x, float y, int color, float vAngular, Giro giro) {
        this.x = x;
        this.y = y;
        this.vAngular = vAngular;
        this.giro = giro;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(5f);
    }

    public void girar(float lapso) {
        angulo += ((lapso * vAngular) / 1000000000f) * giro.getSentido();
    }

    public abstract void dibujar(Canvas canvas);
}
