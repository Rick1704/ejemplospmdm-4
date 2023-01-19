package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Elipse extends Figura {

    private RectF rect;

    public Elipse(float x, float y, float ancho, float alto, int color, float vAngular, Giro giro) {
        super(x, y, color, vAngular, giro);
        rect = new RectF(x - ancho / 2f, y - alto / 2, x + ancho / 2, y + alto / 2);
    }

    @Override
    public void dibujar(Canvas canvas) {
        canvas.save();
        canvas.rotate(angulo, x, y);
        canvas.drawOval(rect, paint);
        canvas.restore();
    }

    public static Elipse aleatorio(int xmin, int xmax, int ymin, int ymax,
                                   int anchomin, int anchomax, int altomin,
                                   int altomax, int vmin, int vmax) {
        return new Elipse(
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
