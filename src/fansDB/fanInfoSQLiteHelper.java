package fansDB;

import java.util.LinkedList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class fanInfoSQLiteHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME="FansDB";
	private static final String TABLE_NAME="FanInfo";

	private static final String COLUMNID="ID";
	private static final String COLUMNFANNAME="FanNAME";
	private static final String COLUMNFANEMAIL="FanEMAIL";
	
	public fanInfoSQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String createTable = "CREATE TABLE "+TABLE_NAME+" ( "+COLUMNID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+COLUMNFANNAME+" TEXT, "+COLUMNFANEMAIL+" TEXT )";
		db.execSQL(createTable);


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		onCreate(db);


	}

	public void addFan(FanInfo fan){

		Log.d("add fan", fan.toString());
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(COLUMNFANNAME, fan.getName());
		values.put(COLUMNFANEMAIL, fan.getEmailAD());
		db.insert(TABLE_NAME, null, values);
		db.close();


	}

	public LinkedList<FanInfo> getAllFans(){
		LinkedList<FanInfo> fans = new LinkedList<FanInfo>();
		String query = "SELECT * FROM "+TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		FanInfo fan = null;
		if(cursor.moveToFirst()){
			do{
				fan = this.cursorToFan(cursor);
				fans.add(fan);
				cursor.moveToNext();
			}while(!cursor.isAfterLast());


		}
		Log.d("get all fans information",fans.toString());
		return fans;

	}

	private FanInfo cursorToFan(Cursor cursor){
		FanInfo fan = new FanInfo();

		fan.setId(Integer.parseInt(cursor.getString(0)));
		fan.setName(cursor.getString(1));
		fan.setEmailAD(cursor.getString(2));
		return fan;
	}
}
