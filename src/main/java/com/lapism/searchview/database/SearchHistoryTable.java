package com.lapism.searchview.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.lapism.searchview.R;
import com.lapism.searchview.widget.SearchItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class SearchHistoryTable {

    private final WeakReference<Context> mContext;
    private SearchHistoryDatabase dbHelper;
    private SQLiteDatabase db;

    public SearchHistoryTable(Context context) {
        mContext = new WeakReference<>(context);
    }

    private void open() {
        dbHelper = new SearchHistoryDatabase(mContext.get());
        db = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public void addItem(SearchItem item) {
        ContentValues values = new ContentValues();
        if (!checkText(item.getTitle().toString())) {
            values.put(SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_TITLE, item.getTitle().toString());
            if (!TextUtils.isEmpty(item.getSubtitle())) {
                values.put(SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_SUBTITLE, item.getSubtitle().toString());
            }
            open();
            db.insert(SearchHistoryDatabase.SEARCH_HISTORY_TABLE, null, values);
            close();
        } else {
            values.put(SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_ID, getLastItemId() + 1);
            open();
            db.update(SearchHistoryDatabase.SEARCH_HISTORY_TABLE, values, SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_ID + " = ? ", new String[]{Integer.toString(getItemId(item))});
            close();
        }
    }

    private int getItemId(SearchItem item) {
        open();
        String query = "SELECT " + SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_ID +
                " FROM " + SearchHistoryDatabase.SEARCH_HISTORY_TABLE +
                " WHERE " + SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_TITLE + " = ?";
        Cursor res = db.rawQuery(query, new String[]{item.getTitle().toString()});
        res.moveToFirst();
        int id = res.getInt(0);
        close();
        res.close();
        return id;
    }

    private int getLastItemId() {
        open();
        String sql = "SELECT " + SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_ID + " FROM " + SearchHistoryDatabase.SEARCH_HISTORY_TABLE;
        Cursor res = db.rawQuery(sql, null);
        res.moveToLast();
        int count = res.getInt(0);
        close();
        res.close();
        return count;
    }

    private boolean checkText(String text) {
        open();

        String query = "SELECT * FROM " + SearchHistoryDatabase.SEARCH_HISTORY_TABLE + " WHERE " + SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_TITLE + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{text});

        boolean hasObject = false;

        if (cursor.moveToFirst()) {
            hasObject = true;
        }

        cursor.close();
        close();
        return hasObject;
    }

    public List<SearchItem> getAllItems() {
        List<SearchItem> list = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + SearchHistoryDatabase.SEARCH_HISTORY_TABLE;
        /*if (databaseKey != null) {
            selectQuery += " WHERE " + SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_KEY + " = " + databaseKey;
        }*/
        selectQuery += " ORDER BY " + SearchHistoryDatabase.SEARCH_HISTORY_COLUMN_ID + " DESC LIMIT " + 2;

        open();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SearchItem item = new SearchItem(mContext.get());
                item.setIcon1Drawable(mContext.get().getResources().getDrawable(R.drawable.search_ic_history_black_24dp, mContext.get().getTheme()));
                item.setTitle(cursor.getString(1));
                item.setSubtitle(cursor.getString(2));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return list;
    }

    public void clearDatabase() {
        open();
        db.delete(SearchHistoryDatabase.SEARCH_HISTORY_TABLE, null, null);
        close();
    }

}
