package com.jour1.todo_app_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int _todoID = -1;
    private DataBaseHelper helper;
    private SQLiteDatabase db;
    private TextView mShowText;
    private Button mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowText = findViewById(R.id.showText);
        //final ListView lvMenu = findViewById(R.id.lvMenu);
        Button btAdd = findViewById(R.id.btAdd);
        final EditText addText = findViewById(R.id.addMemo);
        mDeleteButton = findViewById(R.id.btDelete);


        //make arrayadapter to show the list on the screen


        //when push "add" button , add the text which on Edit text
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(helper == null){
                    helper = new DataBaseHelper(getApplicationContext());
                }
                if(db == null){
                    db = helper.getWritableDatabase();
                }
                //get input text
                String inputStr = addText.getText().toString();
                saveData(db, inputStr);
                readData();
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete("todoSample",null,null);
                readData();
            }
        });


    }
    public void saveData(SQLiteDatabase db, String item){
        ContentValues values = new ContentValues();
        values.put("todo", item);
        db.insert("todoSample",null,values);
    }

    public void readData(){
        Cursor cur = db.query(
                "todoSample",
                new String[] {"todo"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        cur.moveToFirst();
        StringBuilder sbuilder = new StringBuilder();
        for(int i = 0; i< cur.getCount(); i++){
            sbuilder.append(cur.getString(0));
            sbuilder.append("\n");
            cur.moveToNext();
        }
        cur.close();
        mShowText.setText(sbuilder.toString());
    }
}