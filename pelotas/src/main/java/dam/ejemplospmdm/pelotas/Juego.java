package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class Juego implements Runnable {

    private PoligonoRegular poligono;
    private Pelota pelota;
    private SurfaceHolder holder;
    private float width;
    private float height;
    private Paint paint;
    private volatile boolean fin;
    private Thread gameLoop;
    private Rect rect;
    private float giro = 0;

    public Juego() {
        pelota = new Pelota(150, 150, 50, 300, (float) Math.PI / 4, Color.RED, this);
        paint = new Paint();
    }

    public void iniciar(SurfaceHolder holder, int width, int height) {
        this.holder = holder;
        this.width = width;
        this.height = height;
        poligono = new PoligonoRegular(width / 2, height / 2, 5, 250, Color.GREEN);
        rect = new Rect(0, 0, (int) (width * .5f), (int) (height * .3f));
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    public void run() {
        fin = false;
        long t0 = System.nanoTime(), t1, lapso;
        while (!fin) {
            t1 = System.nanoTime();
            lapso = t1 - t0;
            t0 = t1;
            siguiente(lapso);
            pintar();
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void siguiente(long lapso) {
        pelota.mover(lapso);
        poligono.girar((lapso * 10) / 1000000000f);
//        giro += (lapso * 10) / 1000000000f;
    }

    private void pintar(Canvas canvas) {
//        canvas.drawRect(0, 0, width, height, paint);

        paint.setAntiAlias(true);
        canvas.drawColor(Color.BLACK);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
//        canvas.translate((width - rect.width()) / 2, (height - rect.height()) / 2);
        poligono.dibujar(canvas);
//        canvas.drawRect(rect, paint);
        canvas.restore();
        pelota.paint(canvas);
    }

    private void pintar() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            synchronized (holder) {
                pintar(canvas);
            }
        } catch (Exception e) {
        } finally {
            if (canvas != null)
                holder.unlockCanvasAndPost(canvas);
        }
    }
}
