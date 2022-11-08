package dam.ejemplospmdm.ejemplothread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Runnable {

    ProgressBar pb;
    Thread hilo;
    TextView texto;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.progressBar);
        texto = findViewById(R.id.textView);
    }

    @Override
    public void run() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                texto.setText("Ejecutando tarea en segundo plano");
                pb.setProgress(0);
            }
        });
        while (pb.getProgress() < 100) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            h.post(new Runnable() {
                @Override
                public void run() {
                       pb.incrementProgressBy(1);
                    }
            });
        }
    }

    public void tarea(View v) throws InterruptedException {
        hilo = new Thread(this);
        hilo.start();
        /*
        pb.setProgress(0);

        while (pb.getProgress() < 100) {
            Thread.sleep(300);
            pb.incrementProgressBy(1);
        }
        */
    }

    public void saluda(View v) {
        Toast.makeText(this, "Hola Mundo!", Toast.LENGTH_LONG).show();
    }

}