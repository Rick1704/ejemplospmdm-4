package org.fp.dam.pmdm.ejemplos.digitalocean.ejemplohandlerthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb;
    HandlerThread hilo;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.progressBar);
        hilo = new HandlerThread("");
        hilo.start();
        h = new Handler(hilo.getLooper());
    }

    public void tarea(View v) throws InterruptedException {
        h.post(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb.setProgress(0);
                    }
                });
                while (pb.getProgress() < 100) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pb.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.incrementProgressBy(1);
                        }
                    });
                }
            }
        });
    }

    public void saluda(View v) {
        Toast.makeText(this, "Hola Mundo!", Toast.LENGTH_LONG).show();
    }
}