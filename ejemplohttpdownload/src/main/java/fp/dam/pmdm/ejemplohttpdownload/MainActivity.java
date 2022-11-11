package fp.dam.pmdm.ejemplohttpdownload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permiso != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 0);

        editText = findViewById(R.id.editText);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(this::download);
    }

    void download(View v) {
        new Thread() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection con = null;
                try {
                    url = new URL(editText.getText().toString());
                    con = (HttpURLConnection) url.openConnection();
                    InputStream in = con.getInputStream();
                    String fichero = FilenameUtils.getName(url.getPath());
                    try (BufferedOutputStream out = new BufferedOutputStream(openFileOutput(fichero, Activity.MODE_PRIVATE))) {
                        int b;
                        while ((b = in.read()) != -1)
                            out.write(b);
                    } catch (IOException e) {

                    }
                } catch (MalformedURLException e) {
                    Log.e("URL error", e.getLocalizedMessage());
                } catch (IOException e) {
                    Log.e("Error de conexión", e.getLocalizedMessage());
                } finally {
                    if (con != null)
                        con.disconnect();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "FIN", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }.start();
    }
}