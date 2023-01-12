package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Figura {
    protected float x;
    protected float y;
    protected final Paint paint = new Paint();

    public Figura(float x, float y, int color) {
        this.x = x;
        this.y = y;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(5f);
    }

    public abstract void dibujar(Canvas canvas);
}
