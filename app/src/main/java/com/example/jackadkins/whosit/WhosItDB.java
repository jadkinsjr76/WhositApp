package com.example.jackadkins.whosit;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nathaniel on 4/6/16.
 *
 * This class will be used to serve as the database class for the entirety of the WhosIt application.
 */
public class WhosItDB {

    // Database constants:
    public static final String DB_NAME = "whosit.db";
    public static final int    DB_VERSION = 1;

    // User Table Constants:
    public static final String USER_TABLE = "user";

    public static final String USER_ID = "_id";
    public static final int    USER_ID_COL = 0;

    public static final String USER_USERNAME = "user_username";
    public static final int    USER_NAME_COL = 1;

    public static final String USER_PASSWORD = "user_password";
    public static final int    USER_PASSWORD_COL = 2;

    // Quiz Table Constants:
    public static final String QUIZ_TABLE = "quiz";

    public static final String QUIZ_ID = "_id";
    public static final int    QUIZ_ID_COL = 0;

    public static final String QUIZ_USER_ID = "_user_id";
    public static final int    QUIZ_USER_ID_COL = 1;

    public static final String QUIZ_NAME = "_name";
    public static final int    QUIZ_NAME_COL = 2;

    // Question Table Constants:
    public static final String QUESTION_TABLE = "question";

    public static final String QUESTION_ID = "_id";
    public static final int    QUESTION_ID_COL = 0;

    public static final String QUESTION_QUIZ_ID = "_quiz_id";
    public static final int    QUESTION_QUIZ_ID_COL = 1;

    public static final String QUESTION_TEXT = "_name";
    public static final int    QUESTION_TEXT_COL = 2;

    // Answer Table Constants:
    public static final String ANSWER_TABLE = "question";

    public static final String ANSWER_ID = "_id";
    public static final int    ANSWER_ID_COL = 0;

    public static final String ANSWER_QUESTION_ID = "_question_id";
    public static final int    ANSWER_QUESTION_ID_COL = 1;

    public static final String ANSWER_TEXT = "_text";
    public static final int    ANSWER_TEXT_COL = 2;

    public static final String ANSWER_RESULT_TEXT = "_result_text";
    public static final int    ANSWER_RESULT_TEXT_COL = 3;

    // CREATE and DROP TABLE statements:
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " (" +
            USER_ID       + " INTEGER AS PRIMARY KEY AUTOINCREMENT, " +
            USER_USERNAME + " TEXT   NOT NULL UNIQUE, " +
            USER_PASSWORD + " TEXT   NOT NULL);";

    public static final String CREATE_QUIZ_TABLE = "CREATE TABLE " + QUIZ_TABLE + "(" +
            QUIZ_ID      + " INTEGER AS PRIMARY KEY AUTOINCREMENT, " +
            QUIZ_USER_ID + " INTEGER   NOT NULL, " +
            QUIZ_NAME    + " TEXT   NOT NULL);";

    public static final String CREATE_QUESTION_TABLE = "CREATE TABLE " + QUESTION_TABLE + "(" +
            QUESTION_ID      + " INTEGER AS PRIMARY KEY AUTOINCREMENT, " +
            QUESTION_QUIZ_ID + " INTEGER   NOT NULL, " +
            QUESTION_TEXT    + " TEXT   NOT NULL);";

    public static final String CREATE_ANSWER_TABLE =  "CREATE TABLE " + ANSWER_TABLE + "(" +
            ANSWER_ID          + " INTEGER AS PRIMARY KEY AUTOINCREMENT, " +
            ANSWER_QUESTION_ID + " INTEGER   NOT NULL, " +
            ANSWER_TEXT        + " TEXT   NOT NULL);" +
            ANSWER_RESULT_TEXT + " TEXT   NOT NULL);";

    public static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE;

    public static final String DROP_QUIZ_TABLE = "DROP TABLE IF EXISTS " + QUIZ_TABLE;

    public static final String DROP_QUESTION_TABLE = "DROP TABLE IF EXISTS " + QUESTION_TABLE;

    public static final String DROP_ANSWER_TABLE = "DROP TABLE IF EXISTS " + ANSWER_TABLE;

    // Create inner SQLiteOpenHelper class:
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Run SQL statements to create tables:
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_QUIZ_TABLE);
            db.execSQL(CREATE_QUESTION_TABLE);
            db.execSQL(CREATE_ANSWER_TABLE);

            // Insert dummy account:
            db.execSQL("INSERT INTO user VALUES (1, 'Nchudson95', 'password')");

            // Insert dummy quiz:
            db.execSQL("INSERT INTO quiz VALUES (1, 1, 'What Disney Princess Are You?')");

            // Insert dummy questions:
            db.execSQL("INSERT INTO question VALUES (1, 1, 'What is your favorite color?')");
            db.execSQL("INSERT INTO question VALUES (2, 1, 'Which is better, dogs or cats?')");
            db.execSQL("INSERT INTO question VALUES (3, 1, 'What is your favorite school subject?')");
            db.execSQL("INSERT INTO question VALUES (4, 1, 'Would you rather be inside or outside?')");
            db.execSQL("INSERT INTO question VALUES (5, 1, 'What is your clothing style?')");

            // Insert dummy answers:
            db.execSQL("INSERT INTO answer VALUES (1,  1, 'Red', 'Mulan')");
            db.execSQL("INSERT INTO answer VALUES (2,  1, 'Green', 'Pocahontas')");
            db.execSQL("INSERT INTO answer VALUES (3,  1, 'Blue', 'Ariel')");
            db.execSQL("INSERT INTO answer VALUES (4,  1, 'Yellow', 'Jasmine')");

            db.execSQL("INSERT INTO answer VALUES (5,  2, 'Dogs', 'Mulan')");
            db.execSQL("INSERT INTO answer VALUES (6,  2, 'Cats', 'Pocahontas')");
            db.execSQL("INSERT INTO answer VALUES (7,  2, 'Both', 'Ariel')");
            db.execSQL("INSERT INTO answer VALUES (8,  2, 'Neither', 'Jasmine')");

            db.execSQL("INSERT INTO answer VALUES (9,  3, 'Math', 'Mulan')");
            db.execSQL("INSERT INTO answer VALUES (10, 3, 'English', 'Pocahontas')");
            db.execSQL("INSERT INTO answer VALUES (11, 3, 'Science', 'Ariel')");
            db.execSQL("INSERT INTO answer VALUES (12, 3, 'History', 'Jasmine')");

            db.execSQL("INSERT INTO answer VALUES (13, 4, 'Inside', 'Mulan')");
            db.execSQL("INSERT INTO answer VALUES (14, 4, 'Both', 'Pocahontas')");
            db.execSQL("INSERT INTO answer VALUES (15, 4, 'Either', 'Ariel')");
            db.execSQL("INSERT INTO answer VALUES (16, 4, 'Neither', 'Jasmine')");

            db.execSQL("INSERT INTO answer VALUES (17, 5, 'Vintage', 'Mulan')");
            db.execSQL("INSERT INTO answer VALUES (18, 5, 'Parent', 'Pocahontas')");
            db.execSQL("INSERT INTO answer VALUES (19, 5, 'Goth', 'Ariel')");
            db.execSQL("INSERT INTO answer VALUES (20, 5, 'What style?', 'Jasmine')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("WhosIt", "Upgrading db from version " + oldVersion + " to " + newVersion + ".");

            // Drop the tables:
            db.execSQL(WhosItDB.DROP_USER_TABLE);
            db.execSQL(WhosItDB.DROP_QUIZ_TABLE);
            db.execSQL(WhosItDB.DROP_QUESTION_TABLE);
            db.execSQL(WhosItDB.DROP_ANSWER_TABLE);

            // Create new database:
            onCreate(db);
        }

    }

    // Database object and database helper object:
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // Constructor for the class:
    public WhosItDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // PRIVATE METHODS FOR THE CLASS:
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public ArrayList<Quiz> getQuizzes(int userID) {
        String where = QUIZ_ID + " = ?";
        String[] whereArgs = { Integer.toString(userID) };

        this.openReadableDB();
        Cursor cursor = db.query(QUIZ_TABLE, null, where, whereArgs, null, null, null);
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        while (cursor.moveToNext()) {
            quizzes.add(getQuizFromCursor(cursor));
        }

        if (cursor != null)
            cursor.close();
        this.closeDB();

        return quizzes;
    }

    public ArrayList<Question> getQuestions(int quizID) {
        String where = QUESTION_ID + " = ?";
        String[] whereArgs = { Integer.toString(quizID) };

        this.openReadableDB();
        Cursor cursor = db.query(QUESTION_TABLE, null, where, whereArgs, null, null, null);
        ArrayList<Question> questions = new ArrayList<Question>();
        while (cursor.moveToNext()) {
            questions.add(getQuestionFromCursor(cursor));
        }

        if (cursor != null)
            cursor.close();
        this.closeDB();

        return questions;
    }

    public ArrayList<Answer> getAnswers(int questionID) {
        String where = ANSWER_ID + " = ?";
        String[] whereArgs = { Integer.toString(questionID) };

        this.openReadableDB();
        Cursor cursor = db.query(ANSWER_TABLE, null, where, whereArgs, null, null, null);
        ArrayList<Answer> answers = new ArrayList<Answer>();
        while (cursor.moveToNext()) {
            answers.add(getAnswerFromCursor(cursor));
        }

        if (cursor != null)
            cursor.close();
        this.closeDB();

        return answers;
    }

    public Quiz getQuiz(int userID) {
        String   where = QUIZ_ID + " = ?";
        String[] whereArgs = { Integer.toString(userID) };

        this.openReadableDB();
        Cursor cursor = db.query(QUIZ_TABLE, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Quiz quiz = getQuizFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return quiz;
    }

    public Question getQuestion(int quizID) {
        String   where = QUESTION_ID + " = ?";
        String[] whereArgs = { Integer.toString(quizID) };

        this.openReadableDB();
        Cursor cursor = db.query(QUESTION_TABLE, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Question question = getQuestionFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return question;
    }

    public Object getAnswer(int questionID) {
        String   where = ANSWER_ID + " = ?";
        String[] whereArgs = { Integer.toString(questionID) };

        this.openReadableDB();
        Cursor cursor = db.query(ANSWER_TABLE, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Answer answer = getAnswerFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return answer;
    }

    // CURSOR PRIVATE METHODS:
    private static Quiz getQuizFromCursor(Cursor cursor) {
        // If the cursor is null or there are no more quizzes, return null:
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }

        // Otherwise, proceed:
        try {
            Quiz quiz = new Quiz(
                    cursor.getString(QUIZ_NAME_COL),
                    cursor.getInt(QUIZ_ID_COL),
                    cursor.getInt(QUIZ_USER_ID_COL));
            return quiz;
        } catch (Exception e) {
            return null;
        }
    }

    private static Question getQuestionFromCursor(Cursor cursor) {
        // If the cursor is null or there are no more quizzes, return null:
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }

        // Otherwise, proceed:
        try {
            Question question = new Question(
                    cursor.getString(QUESTION_TEXT_COL),
                    cursor.getInt(QUESTION_ID_COL),
                    cursor.getInt(QUESTION_QUIZ_ID_COL));
            return question;
        } catch (Exception e) {
            return null;
        }
    }

    private static Answer getAnswerFromCursor(Cursor cursor) {
        // If the cursor is null or there are no more quizzes, return null:
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }

        // Otherwise, proceed:
        try {
            Answer answer = new Answer(
                    cursor.getString(ANSWER_TEXT_COL),
                    cursor.getString(ANSWER_RESULT_TEXT_COL),
                    cursor.getInt(ANSWER_ID_COL),
                    cursor.getInt(ANSWER_QUESTION_ID_COL));
            return answer;
        } catch (Exception e) {
            return null;
        }
    }


    // INSERT/UPDATE/DELETE METHODS:
    //Every insert method returns the ID of the row of data being entered
    //Every Update/Delete method returns the number of rows affected in the table
    public long insertUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(USER_USERNAME, user.getUserName());
        cv.put(USER_PASSWORD, user.getPassword());

        this.openWriteableDB();
        long rowID = db.insert(USER_TABLE, null, cv);

        return rowID;
    }

    public int updateUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(USER_USERNAME, user.getUserName());
        cv.put(USER_ID, user.getId());
        cv.put(USER_PASSWORD, user.getPassword());
        String   where = USER_ID + " = ?";
        String[] whereArgs = { String.valueOf(user.getId()) };

        this.openWriteableDB();
        int rowCount = db.update(USER_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteUser(long id) {
        String   where = USER_ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(USER_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public long insertQuiz(Quiz quiz) {
        ContentValues cv = new ContentValues();
        cv.put(QUIZ_NAME, quiz.getName());
        cv.put(QUIZ_USER_ID, quiz.getUserID());

        this.openWriteableDB();
        long rowID = db.insert(QUIZ_TABLE, null, cv);

        return rowID;
    }

    public int updateQuiz(Quiz quiz) {
        ContentValues cv = new ContentValues();
        cv.put(QUIZ_NAME, quiz.getName());
        cv.put(QUIZ_ID, quiz.getQuizID());
        cv.put(QUIZ_USER_ID, quiz.getUserID());

        String   where = QUIZ_ID + " = ?";
        String[] whereArgs = { String.valueOf(quiz.getQuizID()) };

        this.openWriteableDB();
        int rowCount = db.update(QUIZ_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteQuiz(long id) {
        String   where = QUIZ_ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(QUIZ_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }


    public long insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QUESTION_TEXT, question.getQuestionText());
        cv.put(QUESTION_QUIZ_ID, question.getQuizID());

        this.openWriteableDB();
        long rowID = db.insert(QUESTION_TABLE, null, cv);

        return rowID;
    }

    public int updateQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QUESTION_TEXT, question.getQuestionText());
        cv.put(QUESTION_ID, question.getQuestionID());
        cv.put(QUESTION_QUIZ_ID, question.getQuizID());

        String   where = QUESTION_ID + " = ?";
        String[] whereArgs = { String.valueOf(question.getQuestionID()) };

        this.openWriteableDB();
        int rowCount = db.update(QUIZ_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteQuestion(long id) {
        String   where = QUESTION_ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(QUESTION_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public long insertAnswer(Answer answer) {
        ContentValues cv = new ContentValues();
        cv.put(ANSWER_TEXT, answer.getAnswerName());
        cv.put(ANSWER_QUESTION_ID, answer.getQuestionID());

        this.openWriteableDB();
        long rowID = db.insert(ANSWER_TABLE, null, cv);

        return rowID;
    }

    public int updateAnswer(Answer answer) {
        ContentValues cv = new ContentValues();
        cv.put(ANSWER_TEXT, answer.getAnswerName());
        cv.put(ANSWER_ID, answer.getAnswerID());
        cv.put(ANSWER_QUESTION_ID, answer.getQuestionID());

        String   where = ANSWER_ID + " = ?";
        String[] whereArgs = { String.valueOf(answer.getQuestionID()) };

        this.openWriteableDB();
        int rowCount = db.update(ANSWER_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteAnswer(long id) {
        String   where = ANSWER_ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(ANSWER_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

}