package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Rectangulo extends Figura {

    private RectF rect;

    public Rectangulo(float x, float y, float ancho, float alto, int color, float vAngular, Giro giro) {
        super(x, y, color, vAngular, giro);
        rect = new RectF(x - ancho / 2f, y - alto / 2, x + ancho / 2, y + alto / 2);
    }

    @Override
    public void dibujar(Canvas canvas) {
        canvas.save();
        canvas.rotate(angulo, x, y);
        canvas.drawRect(rect, paint);
        canvas.restore();
    }

    public static Rectangulo rectanguloAleatorio(float xmin, float xmax, float ymin, float ymax,
                                                 float anchomin, float anchomax, float altomin,
                                                 float altomax, float vmin, float vmax) {
        float x = ;
        float y = ;
        float ancho = ;
        float alto = ;
        int color = ;
        float v = ;
        Giro giro = ;
        return new Rectangulo(x, y, ancho, alto, color, v, giro);
    }

}
