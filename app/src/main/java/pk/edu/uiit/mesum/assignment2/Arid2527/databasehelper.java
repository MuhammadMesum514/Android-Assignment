package pk.edu.uiit.mesum.assignment2.Arid2527;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehelper extends SQLiteOpenHelper {
    private static final String database_name = "Studentdb";
    private static final String table_name = "students";
//    private static final String table_col_name = "username";
    private static final int db_version = 1;
    SQLiteDatabase sqLiteDatabase;

    public databasehelper(Context context) {
        super(context, database_name, null, db_version);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table_name + "( user_id INTEGER PRIMARY KEY AUTOINCREMENT,user_name VARCHAR,user_email VARCHAR,Arid_no VARCHAR, user_pass VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
    public long signUp(String name,String email, String Arid_no, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", name);
        contentValues.put("user_email", email);
        contentValues.put("Arid_no", Arid_no);
        contentValues.put("user_pass", password);
        long user_data = sqLiteDatabase.insert(table_name, null, contentValues);
        return user_data;

    }
    public Boolean checkUser (String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where user_email = ? and user_pass = ?", new String[] {email, password} );
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor getData(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from users where user_email = ? and user_pass = ?", new String[] {email,password});
        return res;
    }

}

