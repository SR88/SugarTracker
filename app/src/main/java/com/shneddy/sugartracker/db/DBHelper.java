package com.shneddy.sugartracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shneddy.sugartracker.contract.DBContract;
import com.shneddy.sugartracker.entity.Food;
import com.shneddy.sugartracker.entity.FoodType;
import com.shneddy.sugartracker.entity.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String DATABASE_NAME = "sugartracker.db";
    public static final int DATABASE_VERSION = 1;

    public static final String createFoodTypeTable =
            "CREATE TABLE IF NOT EXISTS " + DBContract.FoodTypeC.TABLE
            + "(" + DBContract.FoodTypeC.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBContract.FoodTypeC.TYPE + " TEXT, "
            + DBContract.FoodTypeC.DESCRIP + " TEXT);";

    public static final String createFoodTable =
            "CREATE TABLE IF NOT EXISTS " + DBContract.FoodTypeC.TABLE
            + "(" + DBContract.FoodC.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBContract.FoodC.NAME + " TEXT, "
            + DBContract.FoodC.SUGAR + " REAL, "
            + DBContract.FoodC.FOODTYPE_ID + " INTEGER, "
            + "FOREIGN KEY("+ DBContract.FoodC.FOODTYPE_ID +") REFERENCES " + DBContract.FoodTypeC.TABLE + "(_id)"
            + ");";

    public static final String createEntryTable =
            "CREATE TABLE IF NOT EXISTS " + DBContract.FoodTypeC.TABLE
            + "(" + DBContract.LogEntryC.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBContract.LogEntryC.DATE + " TEXT, "
            + DBContract.LogEntryC.PORTION + " REAL, "
            + DBContract.LogEntryC.FOOD_ID + " INTEGER, "
            + "FOREIGN KEY("+ DBContract.LogEntryC.FOOD_ID +") REFERENCES " + DBContract.FoodC.TABLE + "(_id)"
            + ");";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createFoodTypeTable);
        db.execSQL(createFoodTable);
        db.execSQL(createEntryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.LogEntryC.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.FoodC.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.FoodTypeC.TABLE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true); // ensure that foreign key constraints are always on
    }

    public List<FoodType> getAllFoodTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<FoodType> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from " + DBContract.FoodTypeC.TABLE, null);
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                FoodType foodType = new FoodType();
                foodType.setId(c.getInt(c.getColumnIndex(DBContract.FoodTypeC.KEY)));
                foodType.setType(c.getString(c.getColumnIndex(DBContract.FoodTypeC.TYPE)));
                foodType.setDescription(c.getString(c.getColumnIndex(DBContract.FoodTypeC.DESCRIP)));

                list.add(foodType);
            }
        }
        return list;
    }

    public List<Food> getAllFoods(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Food> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from " + DBContract.FoodC.TABLE, null);
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                Food food = new Food();
                food.setId(c.getInt(c.getColumnIndex(DBContract.FoodC.KEY)));
                food.setName(c.getString(c.getColumnIndex(DBContract.FoodC.NAME)));
                food.setGramsSugar(c.getDouble(c.getColumnIndex(DBContract.FoodC.SUGAR)));
                food.setFoodTypeId(c.getInt(c.getColumnIndex(DBContract.FoodC.FOODTYPE_ID)));
                list.add(food);
            }
        }
        return list;
    }

    public List<LogEntry> getAllLogs(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<LogEntry> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from " + DBContract.LogEntryC.TABLE, null);
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                LogEntry logEntry = new LogEntry();
                logEntry.setId(c.getInt(c.getColumnIndex(DBContract.LogEntryC.KEY)));
                logEntry.setFoodId(c.getInt(c.getColumnIndex(DBContract.LogEntryC.FOOD_ID)));
                logEntry.setDate(c.getString(c.getColumnIndex(DBContract.LogEntryC.DATE)));
                logEntry.setPortionSize(c.getDouble(c.getColumnIndex(DBContract.LogEntryC.PORTION)));
                list.add(logEntry);
            }
        }
        return list;
    }

    public boolean insertFoodType(FoodType foodType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.FoodTypeC.TYPE, foodType.getType());
        contentValues.put(DBContract.FoodTypeC.DESCRIP, foodType.getDescription());
        long result = db.insert(DBContract.FoodTypeC.TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean insertFood(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.FoodC.NAME, food.getName());
        contentValues.put(DBContract.FoodC.SUGAR, food.getGramsSugar());
        contentValues.put(DBContract.FoodC.FOODTYPE_ID, food.getFoodTypeId());
        long result = db.insert(DBContract.FoodC.TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean insertEntry(LogEntry logEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.LogEntryC.DATE, logEntry.getDate());
        contentValues.put(DBContract.LogEntryC.FOOD_ID, logEntry.getFoodId());
        contentValues.put(DBContract.LogEntryC.PORTION, logEntry.getPortionSize());
        long result = db.insert(DBContract.LogEntryC.TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateFoodType(FoodType foodType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String[] idArg = new String[]{String.valueOf(foodType.getId())};

        contentValues.put(DBContract.FoodTypeC.DESCRIP, foodType.getDescription());
        contentValues.put(DBContract.FoodTypeC.TYPE, foodType.getType());

        long result = db
                .update(DBContract.FoodTypeC.TABLE, contentValues, "_id = ?", idArg);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateFood(Food f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String[] idArg = new String[]{String.valueOf(f.getId())};

        contentValues.put(DBContract.FoodC.FOODTYPE_ID, f.getFoodTypeId());
        contentValues.put(DBContract.FoodC.SUGAR, f.getGramsSugar());
        contentValues.put(DBContract.FoodC.NAME, f.getName());

        long result = db
                .update(DBContract.FoodC.TABLE, contentValues, "_id = ?", idArg);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }
    
    public boolean updateEntry(LogEntry l){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String[] idArg = new String[]{String.valueOf(l.getId())};

        contentValues.put(DBContract.LogEntryC.PORTION, l.getPortionSize());
        contentValues.put(DBContract.LogEntryC.FOOD_ID, l.getFoodId());
        contentValues.put(DBContract.LogEntryC.DATE, l.getDate());

        long result = db
                .update(DBContract.LogEntryC.TABLE, contentValues, "_id = ?", idArg);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Integer deleteFoodType(FoodType foodType){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{String.valueOf(foodType.getId())};
        return db.delete(DBContract.FoodTypeC.TABLE, "_id = ?", args);
    }

    public Integer deleteFood(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{String.valueOf(food.getId())};
        return db.delete(DBContract.FoodC.TABLE, "_id = ?", args);
    }

    public Integer deleteEntry(LogEntry logEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{String.valueOf(logEntry.getId())};
        return db.delete(DBContract.LogEntryC.TABLE, "_id = ?", args);
    }


}
