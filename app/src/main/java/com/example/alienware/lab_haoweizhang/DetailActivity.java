package com.example.alienware.lab_haoweizhang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView time;
    private TextView address;
    private Team team;
    private ImageView teamLogo;
    private TextView teamName, name;
    private TextView slogan;
    private TextView score;
    private TextView ts1, ts2, ps1, ps2, total, pTotal;
    private Button camera;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        team = (Team) getIntent().getSerializableExtra("team");
        time = (TextView) findViewById(R.id.time);
        address = (TextView) findViewById(R.id.address);
        teamLogo = (ImageView) findViewById(R.id.teamLogo);
        teamName = (TextView) findViewById(R.id.teamName);
        slogan = (TextView) findViewById(R.id.slogan);
        score = (TextView) findViewById(R.id.score);
        ts1 = (TextView) findViewById(R.id.ts1);
        ts2 = (TextView) findViewById(R.id.ts2);
        ps1 = (TextView) findViewById(R.id.ps1);
        ps2 = (TextView) findViewById(R.id.ps2);
        total = (TextView) findViewById(R.id.total);
        pTotal = (TextView) findViewById(R.id.ptotal);
        camera = (Button) findViewById(R.id.camera);
        name = (TextView) findViewById(R.id.name);


        time.setText(team.getTime());
        address.setText(team.getAddress());
        int resID = getResources().getIdentifier(team.getTeamLogo(), "drawable", getPackageName());
        teamLogo.setImageResource(resID);
        teamName.setText(team.getTeamName());
        name.setText(team.getTeamName());
        slogan.setText(team.getSlogan());
        score.setText(team.tScore + " - " + team.pScore);

        ts1.setText(String.valueOf(team.ts1));
        ts2.setText(team.ts2 + "");
        ps1.setText(team.ps1 + "");
        ps2.setText(team.ps2 + "");
        total.setText(team.tScore + "");
        pTotal.setText(team.pScore + "");


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, GalleryActivity.class));
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, team.getTeamName() + " ： Notre Dame");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
