package com.example.alienware.lab_haoweizhang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class SettingActivity extends AppCompatActivity {

    private Spinner spinner;
    private List<String> items = new ArrayList<>();
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        save = (Button) findViewById(R.id.save);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("type", (String) spinner.getSelectedItem());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
