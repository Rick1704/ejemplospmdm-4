package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Juego implements Runnable {

    private Pelota pelota;
    private SurfaceHolder holder;
    private float width;
    private float height;
    private Paint paint;
    private volatile boolean fin;
    private Thread gameLoop;

    public Juego() {
        pelota = new Pelota(150, 150, 50, 300, (float) Math.PI / 4, Color.RED, this);
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
    }

    public void iniciar(SurfaceHolder holder, int width, int height) {
        this.holder = holder;
        this.width = width;
        this.height = height;
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
    }

    private void pintar(Canvas canvas) {
        canvas.drawRect(0, 0, width, height, paint);
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
