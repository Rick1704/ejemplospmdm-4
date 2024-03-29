package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Color;
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

    public static Rectangulo aleatorio(int xmin, int xmax, int ymin, int ymax,
                                       int anchomin, int anchomax, int altomin,
                                       int altomax, int vmin, int vmax) {
        return new Rectangulo(
            Aleatorio.sgte(xmin, xmax),
            Aleatorio.sgte(ymin, ymax),
            Aleatorio.sgte(anchomin, anchomax),
            Aleatorio.sgte(altomin, altomax),
            Color.rgb((float) Math.random(), (float) Math.random(), (float) Math.random()),
            Aleatorio.sgte(vmin, vmax),
            Giro.aleatorio()
        );
    }

}
