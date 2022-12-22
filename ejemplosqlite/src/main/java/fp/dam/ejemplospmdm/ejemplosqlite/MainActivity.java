package fp.dam.ejemplospmdm.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final int DBVERSION = 3;

    ClientesHelper bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bdHelper = new ClientesHelper(this, "clientes.db", DBVERSION);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();
    }
}