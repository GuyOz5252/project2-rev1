package com.example.project2_rev1.menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.project2_rev1.R;

public class MainMenu extends AppCompatActivity implements View.OnTouchListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    Button btnOlderAdventure, btnFriends;
    TextView btnPlay, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);

        sharedPreferences = getSharedPreferences("sp", 0);
        editor = sharedPreferences.edit();

        btnPlay = findViewById(R.id.btnPlay);
        btnOlderAdventure = findViewById(R.id.btnOlderAdventures);
        btnFriends = findViewById(R.id.btnFriends);
        btnSettings = findViewById(R.id.btnSettings);

        btnPlay.setOnTouchListener(this);
        btnOlderAdventure.setOnTouchListener(this);
        btnFriends.setOnTouchListener(this);
        btnSettings.setOnTouchListener(this);

        if (sharedPreferences.getInt("user_currentAdvIdx", -1) != -1) {
            // TODO continue button
            System.out.println("game ongoing");
        }
    }

    public void clickPlay() {
        startActivity(new Intent(this, StartGame.class));
        this.finish();
    }

    public void clickPlay(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickPlay();
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }

    public void clickOlderAdventures() {
        // TODO start older adventures
    }

    public void clickOlderAdventures(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickOlderAdventures();
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }

    public void clickFriend() {
        // TODO start friends
    }

    public void clickFriend(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickFriend();
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }

    public void clickSettings() {
        // TODO start settings
    }

    public void clickSettings(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickSettings();
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.btnPlay:
                clickPlay(view, motionEvent);
                break;
            case R.id.btnOlderAdventures:
                clickOlderAdventures(view, motionEvent);
                break;
            case R.id.btnFriends:
                clickFriend(view, motionEvent);
                break;
            case R.id.btnSettings:
                clickSettings(view, motionEvent);
                break;
        }
        return true;
    }
}