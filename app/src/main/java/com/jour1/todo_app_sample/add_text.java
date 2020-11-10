package com.jour1.todo_app_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_text extends AppCompatActivity {
    Button btSave;
    EditText edText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        btSave = findViewById(R.id.btSave);
        edText = findViewById(R.id.addMemo);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper helper = new DataBaseHelper(add_text.this);
                helper.addText(edText.getText().toString());
                Intent intent = new Intent(add_text.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}