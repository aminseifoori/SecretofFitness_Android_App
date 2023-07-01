package com.ampesoftware.secretoffitness;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper{

	private static String DB_PATH = "/data/data/com.ampesoftware.secretoffitness/databases/";
	 
	private static String DB_NAME = "Fitness";
	 
	private SQLiteDatabase myDataBase;
	 
	private final Context myContext;
	
	public dbhelper(Context context) {
		 
		super(context, DB_NAME, null, 1);
		this.myContext = context;
		}	
	public void createDataBase() throws IOException{
		 
		boolean dbExist = checkDataBase();
		 
		if(dbExist){
		//do nothing - database already exist
		}else{

		this.getReadableDatabase();
		 
		try {
		 
		copyDataBase();
		 
		} catch (IOException e) {
		 
		throw new Error("Error copying database");
		 
		}
		}
		 
		}
	private boolean checkDataBase(){
		 
		SQLiteDatabase checkDB = null;
		 
		try{
		String myPath = DB_PATH + DB_NAME;
		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		 
		}catch(SQLiteException e){
		 
		//database does't exist yet.
		 
		}
		 
		if(checkDB != null){
		 
		checkDB.close();
		 
		}
		 
		return checkDB != null ? true : false;
		}
	private void copyDataBase() throws IOException{
		 
		//Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		 
		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;
		 
		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		 
		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
		myOutput.write(buffer, 0, length);
		}
		 
		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
		 
		}
		 
		public void openDataBase() throws SQLException{
		 
		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		 
		}
		 
		@Override
		public synchronized void close() {
		 
		if(myDataBase != null)
		myDataBase.close();
		 
		super.close();
		 
		}

		public String getdatasnack(int recordid, int fielindex){
			
			 Cursor c=myDataBase.query("Snack", null,null,null,null,null,null);
			 c.moveToPosition(recordid);
			 String str=c.getString(fielindex).toString();
			 return str;
			
		}

		public int countrecordssnack(){
			Cursor c=myDataBase.query("Snack", null,null,null,null,null,null);
			return c.getCount();
		}

		public String getdatabeinshape(int recordid, int fielindex){
			
			 Cursor c=myDataBase.query("Beinshape", null,null,null,null,null,null);
			 c.moveToPosition(recordid);
			 String str=c.getString(fielindex).toString();
			 return str;
			
		}

		public int countrecordsbeinshape(){
			Cursor c=myDataBase.query("Beinshape", null,null,null,null,null,null);
			return c.getCount();
		}

		public String getdatadietfood(int recordid, int fielindex){
			
			 Cursor c=myDataBase.query("Dietfood", null,null,null,null,null,null);
			 c.moveToPosition(recordid);
			 String str=c.getString(fielindex).toString();
			 return str;
			
		}

		public int countrecordsdietfood(){
			Cursor c=myDataBase.query("Dietfood", null,null,null,null,null,null);
			return c.getCount();
		}

		public String getdatadietplam(int recordid, int fielindex){
			
			 Cursor c=myDataBase.query("Dietplan", null,null,null,null,null,null);
			 c.moveToPosition(recordid);
			 String str=c.getString(fielindex).toString();
			 return str;
			
		}

		public int countrecordsdietplan(){
			Cursor c=myDataBase.query("Dietplan", null,null,null,null,null,null);
			return c.getCount();
		}


		public String getdatasportadvice(int recordid, int fielindex){
			 Cursor c=myDataBase.query("Sportadvice", null,null,null,null,null,null);
			 c.moveToPosition(recordid);
			 String str=c.getString(fielindex).toString();
			 return str;
			
		}

		public int countrecordssportadvice(){
			Cursor c=myDataBase.query("Sportadvice", null,null,null,null,null,null);
			return c.getCount();
		}

		public void favbeinshape(int ff, int idx){
			ContentValues cv=new ContentValues();
			cv.put("Fav",ff);
			myDataBase.update("beinshape", cv, "_id="+idx , null);
		}

		public void favdietfood(int ff, int idx){
			ContentValues cv=new ContentValues();
			cv.put("Fav",ff);
			myDataBase.update("Dietfood", cv, "_id="+idx , null);
		}

		public void favdietplan(int ff, int idx){
			ContentValues cv=new ContentValues();
			cv.put("Fav",ff);
			myDataBase.update("Dietplan", cv, "_id="+idx , null);
		}

		public void favSnack(int ff, int idx){
			ContentValues cv=new ContentValues();
			cv.put("Fav",ff);
			myDataBase.update("Snack", cv, "_id="+idx , null);
		}

		public void favsportadvice(int ff, int idx){
			ContentValues cv=new ContentValues();
			cv.put("Fav",ff);
			myDataBase.update("Sportadvice", cv, "_id="+idx , null);
		}

		public String getdatadietfoodid(int recordid, int fielindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Dietfood where _id="+recordid,null);
			 c.moveToFirst();
			 String str=c.getString(fielindex).toString();
			 return str;
		}

		public String getdatadietplanid(int recordid, int fielindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Dietplan where _id="+recordid,null);
			 c.moveToFirst();
			 String str=c.getString(fielindex).toString();
			 return str;
		}

		public String getdatabeinshapeid(int recordid, int fielindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Beinshape where _id="+recordid,null);
			 c.moveToFirst();
			 String str=c.getString(fielindex).toString();
			 return str;
		}

		public String getdatasportadviceid(int recordid, int fielindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Sportadvice where _id="+recordid,null);
			 c.moveToFirst();
			 String str=c.getString(fielindex).toString();
			 return str;
		}

		public String getdatasnackid(int recordid, int fielindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Snack where _id="+recordid,null);
			 c.moveToFirst();
			 String str=c.getString(fielindex).toString();
			 return str;
		}

		public String faveddietfood(int fieldindex,int recordindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Dietfood where Fav=1",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countfavdietfood(){
			Cursor c=myDataBase.rawQuery("select * from Dietfood where Fav=1",null);
			return c.getCount();
		}

		public String faveddietplan(int fieldindex,int recordindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Dietplan where Fav=1",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countfavdietplan(){
			Cursor c=myDataBase.rawQuery("select * from Dietplan where Fav=1",null);
			return c.getCount();
		}

		public String favedbeinshape(int fieldindex,int recordindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Beinshape where Fav=1",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countfavbeinshape(){
			Cursor c=myDataBase.rawQuery("select * from Beinshape where Fav=1",null);
			return c.getCount();
		}

		public String favedsnack(int fieldindex,int recordindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Snack where Fav=1",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countfavsnack(){
			Cursor c=myDataBase.rawQuery("select * from Snack where Fav=1",null);
			return c.getCount();
		}

		public String favedsportadvice(int fieldindex,int recordindex){
			
			 Cursor c=myDataBase.rawQuery("select * from Sportadvice where Fav=1",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countfavsportadvice(){
			Cursor c=myDataBase.rawQuery("select * from Sportadvice where Fav=1",null);
			return c.getCount();
		}

		public int getfontsize(){
			 Cursor c=myDataBase.query("Fontsize", null,null,null,null,null,null);
			 c.moveToPosition(0);
			 int fs=c.getInt(1);
			 return fs;
		}

		public int getfontrev(){
			 Cursor c=myDataBase.query("Fontsize", null,null,null,null,null,null);
			 c.moveToPosition(0);
			 int fs=c.getInt(2);
			 return fs;
		}
		public void setfontsize(float fs){
			ContentValues cv=new ContentValues();
			cv.put("Size",fs);
			cv.put("Rev",1);
			myDataBase.update("Fontsize", cv, "_id=1" , null);
		}
		
		public String searchdietfood(int recordindex,int fieldindex,String condition){
			
			 Cursor c=myDataBase.rawQuery("select * from Dietfood where Foodname like '%"+condition+"%'",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countserachdietfood(String condition){
			Cursor c=myDataBase.rawQuery("select * from Dietfood where Foodname like '%"+condition+"%'",null);
			return c.getCount();
		}
		
		public String searchdietplan(int recordindex,int fieldindex,String condition){
			
			 Cursor c=myDataBase.rawQuery("select * from Dietplan where Dietname like '%"+condition+"%'",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countserachdietplan(String condition){
			Cursor c=myDataBase.rawQuery("select * from Dietplan where Dietname like '%"+condition+"%'",null);
			return c.getCount();
		}
		
		public String searchsnack(int recordindex,int fieldindex,String condition){
			
			 Cursor c=myDataBase.rawQuery("select * from Snack where Snackname like '%"+condition+"%'",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countsearchsnack(String condition){
			Cursor c=myDataBase.rawQuery("select * from Snack where Snackname like '%"+condition+"%'",null);
			return c.getCount();
		}
		
		public String searchbeinshape(int recordindex,int fieldindex,String condition){
			
			 Cursor c=myDataBase.rawQuery("select * from Beinshape where Advname like '%"+condition+"%'",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countsearchbeinshape(String condition){
			Cursor c=myDataBase.rawQuery("select * from Beinshape where Advname like '%"+condition+"%'",null);
			return c.getCount();
		}

		public String searchsportadvice(int recordindex,int fieldindex,String condition){
			
			 Cursor c=myDataBase.rawQuery("select * from Sportadvice where name like '%"+condition+"%'",null);
			 c.moveToPosition(recordindex);
			 String str=c.getString(fieldindex).toString();
			 return str;
		}

		public int countsearchsportadvice(String condition){
			Cursor c=myDataBase.rawQuery("select * from Sportadvice where name like '%"+condition+"%'",null);
			return c.getCount();
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
		 
		}
		 
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 
		}
		 
	
}
