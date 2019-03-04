package com.shneddy.sugartracker.contract;

import android.provider.BaseColumns;

public class DBContract {

    public static final class FoodTypeC implements BaseColumns {

        public static final String TABLE = "foodtype";

        public static final String KEY = "_id";
        public static final String TYPE = "type";
        public static final String DESCRIP = "description";

        public static final String[] Projection = new String[]{
                FoodTypeC.KEY,
                FoodTypeC.TYPE,
                FoodTypeC.DESCRIP
        };
    }

    public static final class FoodC implements BaseColumns {

        public static final String TABLE = "food";

        public static final String KEY = "_id";
        public static final String NAME = "name";
        public static final String SUGAR = "sugar";
        public static final String FOODTYPE_ID = "foodtype_id";

        public static final String[] Projection = new String[]{
                FoodC.KEY,
                FoodC.NAME,
                FoodC.SUGAR,
                FoodC.FOODTYPE_ID
        };
    }

    public static final class LogEntryC implements BaseColumns {

        public static final String TABLE = "log";

        public static final String KEY = "_id";
        public static final String DATE = "date";
        public static final String PORTION = "portion";
        public static final String FOOD_ID = "food_id";

        public static final String[] Projection = new String[]{
                LogEntryC.KEY,
                LogEntryC.DATE,
                LogEntryC.PORTION,
                LogEntryC.FOOD_ID
        };
    }

}
