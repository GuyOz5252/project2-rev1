package com.example.project2_rev1.utils;

import android.util.DisplayMetrics;

public class Display {

    public Size size;

    public Display(DisplayMetrics displayMetrics) {
        this.size = new Size(
                displayMetrics.widthPixels,
                displayMetrics.heightPixels
        );
    }
}
