package com.example.alienware.lab_haoweizhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alienware on 2017/2/9.
 */

public class ScheduleAdapter extends ArrayAdapter<Team> {
    ScheduleAdapter (Context context, int layoutResourceId, ArrayList<Team> schedule) {
        super(context, layoutResourceId, schedule);
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);
        Team team = getItem(position);

      //  System.out.println(matchItem.get(0));
        TextView teamName = (TextView) scheduleView.findViewById(R.id.scheduleText);
        TextView date = (TextView) scheduleView.findViewById(R.id.timeText);
        teamName.setText(team.getTeamName());
        date.setText(team.getTime());

        ImageView teamLogo = (ImageView) scheduleView.findViewById(R.id.teamLogo);
        String mDrawableName = team.getTeamLogo();
        int resID = getContext().getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
        teamLogo.setImageResource(resID );

        return scheduleView;
    }

    public String getPackageName() {
        return "com.example.alienware.lab_haoweizhang" ;
    }
}

/*class ScheduleAdapter extends ArrayAdapter<String> {
    ScheduleAdapter (Context context, int layoutResourceId, String[] schedule) {
        super(context, layoutResourceId, schedule);
    }
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater scheduleInflater = LayoutInflater.from(getContext());
        View scheduleView = scheduleInflater.inflate(R.layout.schedule_item, parent, false);

        String matchItem = getItem(position);
        TextView teamName = (TextView) scheduleView.findViewById(R.id.scheduleText);
        TextView date = (TextView) scheduleView.findViewById(R.id.timeText);
        teamName.setText("Test");
        date.setText("Testtime");
        return scheduleView;
    }
}*/