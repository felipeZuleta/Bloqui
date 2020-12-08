package com.example.bloquiapp.AdaptersAndModels;

import android.graphics.Color;
import android.widget.LinearLayout;

public class ActionsModel {

    private String title;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    private int icon, background;



    public ActionsModel(String title, int icon, int background, String type) {
        this.title = title;
        this.icon = icon;
        this.background = background;
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
