package com.example.alienware.lab_haoweizhang;

import java.io.Serializable;

/**
 * Created by sunhuahui on 2017/2/18.
 */

public class Team implements Serializable {
    String teamName;
    String teamLogo;
    String time;
    String address;
    String slogan;
    String gameType;
    int tScore;
    int pScore;
    int ts1;
    int ps1;
    int ts2;
    int ps2;
//{"image101", "Ohio State", "Feb 11", "Purcell Pavilion at the Joyce Center, Notre Dame, Indiana", "Seminoles", "31:41", "31:41"};

    public Team(String[] teamInfo) {
        teamName = teamInfo[1];
        teamLogo = teamInfo[0];
        time = teamInfo[2];
        address = teamInfo[3];
        slogan = teamInfo[4];
        ts1 = Integer.parseInt(teamInfo[5].split(":")[0].trim());
        ps1 = Integer.parseInt(teamInfo[5].split(":")[1].trim());
        ts2 = Integer.parseInt(teamInfo[6].split(":")[0].trim());
        ps2 = Integer.parseInt(teamInfo[6].split(":")[1].trim());
        tScore = ts1 + ts2;
        pScore = ps1 + ps2;
        gameType = teamInfo[7];
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getTs1() {
        return ts1;
    }

    public int getPs1() {
        return ps1;
    }

    public int getTs2() {
        return ts2;
    }

    public int getPs2() {
        return ps2;
    }
}
