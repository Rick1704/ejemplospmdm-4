package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Path;

public class PoligonoRegular extends Figura {

    Path path = new Path();
    float giro = 0;

    public PoligonoRegular(float x, float y, int lados, float radio, int color) {
        super(x, y, color);
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

    public void girar(float angulo) {
        giro += angulo;
    }

    @Override
    public void dibujar(Canvas canvas) {
        canvas.save();
        canvas.translate(x, y);
        canvas.rotate(giro, 0, 0);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
