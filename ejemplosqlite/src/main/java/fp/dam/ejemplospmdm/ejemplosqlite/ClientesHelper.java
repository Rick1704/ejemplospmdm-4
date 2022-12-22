package fp.dam.ejemplospmdm.ejemplosqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.InputStream;
import java.util.Scanner;

public class ClientesHelper extends SQLiteOpenHelper {

    private Context context;

    public ClientesHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ejecutarScritpSQL(sqLiteDatabase, R.raw.clientes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            switch (oldVersion) {
                case 1:
                    ejecutarScritpSQL(sqLiteDatabase, R.raw.dbupgradev2);
                case 2:
                    ejecutarScritpSQL(sqLiteDatabase, R.raw.dbupgradev3);
            }
        }
    }

    private void ejecutarScritpSQL(SQLiteDatabase db, int resource) {
        InputStream inputStream = context.getResources().openRawResource(resource);
        try (Scanner scanner = new Scanner(inputStream)) {
            scanner.useDelimiter(";");
            db.beginTransaction();
            while (scanner.hasNext())
                db.execSQL(scanner.next());
            db.setTransactionSuccessful();
        } catch (SQLException e) {

        } finally {
            db.endTransaction();
        }
    }
}
