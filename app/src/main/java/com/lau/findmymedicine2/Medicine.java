package com.lau.findmymedicine2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Medicine{
    String Name;
    String Use;
    String Warning;

    Medicine(String Name, String Use, String Warning){
        this.Name=Name;
        this.Use=Use;
        this.Warning=Warning;

    }

    public String getName() {
        return Name;
    }

    public String getUse() {
        return Use;
    }

    public String getWarning() {
        return Warning;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setUse(String use) {
        Use = use;
    }

    public void setWarning(String warning) {
        Warning = warning;
    }
}