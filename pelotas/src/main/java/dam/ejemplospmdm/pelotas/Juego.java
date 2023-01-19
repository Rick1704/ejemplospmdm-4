package dam.ejemplospmdm.pelotas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.ArrayList;

public class Juego implements Runnable {

    private static final int NUMFIGURAS = 25;

    private final ArrayList<Figura> figuras = new ArrayList<>();
    private final Pelota pelota;
    private SurfaceHolder holder;
    private float width;
    private float height;
    private final Paint paint;
    private volatile boolean fin;
    private Thread gameLoop;
    private Giro giro = Giro.IZDA;
    private float vAngular = 35;
    private float angulo = 0;
    private float px;
    private float py;

    public Juego() {
        pelota = new Pelota(150, 150, 50, 300, (float) Math.PI / 4, Color.RED, this);
        paint = new Paint();
    }


    public void iniciar(SurfaceHolder holder, int width, int height) {
        this.holder = holder;
        this.width = width;
        this.height = height;

        px = width / 2f;
        py = height / 2f;

        int xmin = 50;
        int xmax = width - 50;
        int ymin = 50;
        int ymax = height - 50;
        int anchomin = (int) (width * 0.1f);
        int anchomax = (int) (width * 0.5f);
        int altomin = (int) (height * 0.1f);
        int altomax = (int) (height * 0.5f);
        int radiomin = width > height ? (int) (width * .1f) : (int) (height * .1f);
        int radiomax = width > height ? (int) (width * .25f) : (int) (height * .25f);

        for (int i = 0; i < NUMFIGURAS; i++) {
            switch (Aleatorio.sgte(1, 3)) {
                case 1:
                    figuras.add(Rectangulo.aleatorio(xmin, xmax, ymin, ymax, anchomin, anchomax, altomin, altomax, 30, 300));
                    break;
                case 2:
                    figuras.add(Elipse.aleatorio(xmin, xmax, ymin, ymax, anchomin, anchomax, altomin, altomax, 30, 300));
                    break;
                case 3:
                    figuras.add(PoligonoRegular.aleatorio(xmin, xmax, ymin, ymax, 3, 10, radiomin, radiomax, 30, 70));
                    break;
            }

        }
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    static float FPS = 60;
    static float NPF = 1000000000F / FPS;

    public void run() {
        fin = false;
        long t0 = System.nanoTime(), t1, lapso;
        float nanos = 0;
        while (!fin) {
            t1 = System.nanoTime();
            lapso = t1 - t0;
            t0 = t1;
            nanos += lapso;
            if (nanos >= NPF) {
                nanos -= NPF;
                siguiente(NPF);
                pintar();
            }
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void siguiente(float lapso) {
        angulo += ((lapso * vAngular) / 1000000000f) * giro.getSentido();
        pelota.mover(lapso);
        figuras.forEach(f -> f.girar(lapso));
//        giro += (lapso * 10) / 1000000000f;
    }

    private void pintar(Canvas canvas) {
//        canvas.drawRect(0, 0, width, height, paint);

        paint.setAntiAlias(true);
        canvas.drawColor(Color.BLACK);
        canvas.save();
        canvas.rotate(angulo, px, py);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        figuras.forEach(f -> f.dibujar(canvas));
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
