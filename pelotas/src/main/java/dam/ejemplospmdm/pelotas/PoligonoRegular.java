package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;

public class PoligonoRegular extends Figura {

    Path path = new Path();

    public PoligonoRegular(float x, float y, int lados, float radio, int color, float vAngular, Giro giro) {
        super(x, y, color, vAngular, giro);
        if (lados < 3)
            throw new IllegalArgumentException("número de lados incorrecto");
        float xIni = radio;
        float yIni = 0;
        float angulo = 2 * (float) Math.PI / lados;
        path.moveTo(xIni, yIni);
        for (int i=1; i<lados; i++)
            path.lineTo(radio * (float) Math.cos(i * angulo), radio * (float) Math.sin(i * angulo));
        path.lineTo(xIni, yIni);
    }

    public static PoligonoRegular aleatorio(int minx, int maxx, int miny, int maxy, int minlados,
                                     int maxlados, int minradio, int maxRadio, int vmin,
                                            int vmax) {
        return new PoligonoRegular(
                Aleatorio.sgte(minx, maxx),
                Aleatorio.sgte(miny, maxy),
                (int) Aleatorio.sgte(minlados, maxlados),
                Aleatorio.sgte(minradio, maxRadio),
                Color.rgb((float) Math.random(), (float) Math.random(), (float) Math.random()),
                Aleatorio.sgte(vmin, vmax),
                Giro.aleatorio()
        );
    }

    @Override
    public void dibujar(Canvas canvas) {
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(angulo, 0, 0);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
