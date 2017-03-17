package com.example.alienware.lab_haoweizhang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ArrayList<String[]> al = new ArrayList<String[]>();
    private ArrayList<Team> teams = new ArrayList<>();
    private Toolbar toolbar;
    private static final int REQUEST_CODE = 1000;
    private ScheduleAdapter scheduleApater;
    private ListView scheduleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        /*String[] str = {"Ohio State", "Florida State","Wake Forest","Boston College",
                "North Carolina State","Georgia Tech","North Virgina","Chicago State "};
        String[] str2 = {"Feb11", "Feb 14","Feb 18","Feb 26","Mar 1", "Mar 7","Mar 16"};
        ArrayList<String[]> teamName = new ArrayList<String[]>();
        teamName.add(str);
        teamName.add(str2);
        */
        //List<String[]> record = new ArrayList<String[]>();
        //String[] list  = new String[8];

        String[] list = {"Ohio State", "Feb 11"};
        /*list[1] ="Florida State Feb 14";
        list[2] ="Wake Forest Feb 18";
        list[3] ="Boston College Feb 26";
        list[4] ="North Carolina State Mar 1";
        list[5] ="Georgia Tech Mar 4";
        list[6] ="North Virgina Mar 7";
        list[7] ="Chicago State Mar 16";*/


        String[] ff = {"image101", "Ohio State", "Feb 11", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "31:41", "39:50"};
        String[] ff1 = {"image102", "Florida State", "Feb 14", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "20:54", "65:43"};
        String[] ff2 = {"image103", "Wake Forest", "Feb 18", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "65:56", "87:89"};
        String[] ff3 = {"image104", "Boston College", "Feb 26", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "45:41", "58:41"};
        String[] ff4 = {"image105", "North Carolina State", "Mar 1", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "76:49", "76:98"};
        String[] ff5 = {"image106", "Georgia Tech", "Mar 4", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "65:59", "48:76"};
        String[] ff6 = {"image107", "North Virgina", "Mar 7", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "76:47", "69:41"};
        String[] ff7 = {"image108", "Chicago State", "Mar 16", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "31:41", "31:41"};
        al.add(ff);
        al.add(ff1);
        al.add(ff2);
        al.add(ff3);
        al.add(ff4);
        al.add(ff5);
        al.add(ff6);
        al.add(ff7);

        MyCsvFileReader reader = new MyCsvFileReader(getApplicationContext());
        ArrayList<String[]> items = reader.readCsvFile(R.raw.schedule);
        for (String[] item : items) {
            teams.add(new Team(item));
        }

        ScheduleAdapter scheduleApater = new ScheduleAdapter(this, R.layout.schedule_item, teams);

        //ListAdapter scheduleAdapter
        // = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list );

        scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleApater);
        // scheduleListView.setAdapter(scheduleAdapter);


        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("team", teams.get(i));
                startActivity(intent);
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share:

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, teams.get(0).getTeamName() + " ： Notre Dame");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;
                    case R.id.sync:
                        Snackbar.make(getWindow().getDecorView(), "sync", Snackbar.LENGTH_LONG)
                                .setAction("Try Again", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                }).show();
                        break;
                    case R.id.settings:
                        startActivityForResult(new Intent(MainActivity.this, SettingActivity.class), REQUEST_CODE);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE && data != null) {
                String type = data.getStringExtra("type");
                ArrayList<Team> items = new ArrayList<>();

                for (Team team : teams) {
                    if (type.equals(team.gameType.trim()) || type.equals("All")) {
                        items.add(team);
                    }
                }

                ScheduleAdapter scheduleApater = new ScheduleAdapter(this, R.layout.schedule_item, items);
                scheduleListView.setAdapter(scheduleApater);
            }
        }
    }
}
