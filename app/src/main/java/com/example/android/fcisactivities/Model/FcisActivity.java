package com.example.android.fcisactivities.Model;

public class FcisActivity {

    String name,vision,mission,backGround,foreground;
    int year;

    public void setName(String name) {
        this.name = name;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public void setBackGround(String backGround) {
        this.backGround = backGround;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {

        return name;
    }

    public String getVision() {
        return vision;
    }

    public String getMission() {
        return mission;
    }

    public String getBackGround() {
        return backGround;
    }

    public String getForeground() {
        return foreground;
    }

    public int getYear() {
        return year;
    }
}
