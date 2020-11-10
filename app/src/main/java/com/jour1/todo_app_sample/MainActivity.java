package com.jour1.todo_app_sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int _todoID = -1;
    private DataBaseHelper helper;
    private SQLiteDatabase db;
    private TextView mShowText;
    private Button mDeleteButton;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText addText;
    private Button btAdd;
    private ArrayList<String> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DataBaseHelper(MainActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.showList);
        btAdd = findViewById(R.id.btAdd);
        addText = findViewById(R.id.addMemo);
        mDeleteButton = findViewById(R.id.btDelete);
        myDataset = new ArrayList<>();

        setData();

        //create adapter
        mAdapter = new MyAdapter(MainActivity.this,myDataset);
        recyclerView.setAdapter(mAdapter);

        //not to change the layout size
        recyclerView.setHasFixedSize(true);

        //use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //make line item by item
        final RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        //when push "add" button , add the text which on Edit text
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add_text.class);
                startActivity(intent);
            }
        });

       mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Caution")
                        .setMessage("Do you really delete the List?")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        helper.dbDelete();
                                        helper.readData();
                                        helper.close();
                                    }
                                })
                        .setNegativeButton("Cancel",null)
                        .show();

            }

        });


    }


    //control cursor prepared by readData *i = 0 is id
    public void setData(){
        Cursor c = helper.readData();
       while(c.moveToNext()){
           myDataset.add(c.getString(1));
       }
    }
}

