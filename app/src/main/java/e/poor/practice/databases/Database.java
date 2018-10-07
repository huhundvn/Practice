package e.poor.practice.databases;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import e.poor.practice.DAO.ICategoryDAO;
import e.poor.practice.models.Category;

@android.arch.persistence.room.Database(entities = {Category.class}, version = 2)
public abstract class Database extends RoomDatabase {

    private static Database sInstance;

    public static synchronized Database getInstance(Context context) {
        if (sInstance == null) {
//            copyEncloseDatabase(context, "db");
            sInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "db.db")
                    .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public static void copyEncloseDatabase(Context context, String databaseName) {
        File dbPath = context.getDatabasePath(databaseName);
        if (dbPath.exists()) {
            dbPath.delete();
            Log.i("database", "Database deleted");
        }
        dbPath.getParentFile().mkdir();

        try {
            InputStream inputStream = context.getAssets().open(databaseName);
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
                Log.i("database", "Writing...");
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public abstract ICategoryDAO categoryDAO();
}
