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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project2_rev1.AdventureView;
import com.example.project2_rev1.R;

import java.util.ArrayList;

public class StartGame extends AppCompatActivity implements View.OnTouchListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    Button btnPlay;
    TextView btnPrev, btnNext, btnSettings, btnBack, txtAdvDescription;
    ImageView imvVisual, imvPrevAdv, imvCurrentAdv, imvNextAdv;

    private int currentAdvIndex;
    private ArrayList<AdventureOptions> adventureOptionsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_adventure);

        sharedPreferences = getSharedPreferences("sp", 0);
        editor = sharedPreferences.edit();

        btnPlay = findViewById(R.id.btnPlay_startGame);
        btnPrev = findViewById(R.id.btnPrev_startGame);
        btnNext = findViewById(R.id.btnNext_startGame);
        btnSettings = findViewById(R.id.btnSettings_startGame);
        btnBack = findViewById(R.id.btnBack_startGame);

        btnPlay.setOnTouchListener(this);
        btnPrev.setOnTouchListener(this);
        btnNext.setOnTouchListener(this);
        btnSettings.setOnTouchListener(this);
        btnBack.setOnTouchListener(this);

        imvVisual = findViewById(R.id.imvVisual_startGame);
        txtAdvDescription = findViewById(R.id.txtAdvDescription);
        imvPrevAdv = findViewById(R.id.imvPrevAdv);
        imvCurrentAdv = findViewById(R.id.imvCurrentAdv);
        imvNextAdv = findViewById(R.id.imvNextAdv);

        editor.putInt("user_currentAdvIdx", 0);
        editor.apply();
        currentAdvIndex = sharedPreferences.getInt("user_currentAdvIdx", 0);
        imvPrevAdv.setVisibility(View.INVISIBLE);
        cycleAdv();
    }

    public void cycleAdv() {
        initAdventures();
        if (currentAdvIndex == 0) {
            imvPrevAdv.setVisibility(View.INVISIBLE);
            imvCurrentAdv.setImageResource(adventureOptionsArrayList.get(currentAdvIndex).getIcon());
            imvNextAdv.setVisibility(View.VISIBLE);
            imvNextAdv.setImageResource(adventureOptionsArrayList.get(currentAdvIndex+1).getIcon());
        } else if (currentAdvIndex == adventureOptionsArrayList.size()-1) {
            imvPrevAdv.setVisibility(View.VISIBLE);
            imvPrevAdv.setImageResource(adventureOptionsArrayList.get(currentAdvIndex-1).getIcon());
            imvCurrentAdv.setImageResource(adventureOptionsArrayList.get(currentAdvIndex).getIcon());
            imvNextAdv.setVisibility(View.INVISIBLE);
        } else {
            imvPrevAdv.setVisibility(View.VISIBLE);
            imvPrevAdv.setImageResource(adventureOptionsArrayList.get(currentAdvIndex-1).getIcon());
            imvCurrentAdv.setImageResource(adventureOptionsArrayList.get(currentAdvIndex).getIcon());
            imvNextAdv.setVisibility(View.VISIBLE);
            imvNextAdv.setImageResource(adventureOptionsArrayList.get(currentAdvIndex+1).getIcon());
        }
        imvVisual.setImageResource(adventureOptionsArrayList.get(currentAdvIndex).getVisual());
        txtAdvDescription.setText(adventureOptionsArrayList.get(currentAdvIndex).getDescription());
    }

    public void initAdventures() {
        adventureOptionsArrayList = new ArrayList<>();
        adventureOptionsArrayList.add(new AdventureOptions(
                R.drawable.placeholder_visual,
                "the first adventure",
                R.drawable.ic_launcher_background
        ));
        adventureOptionsArrayList.add(new AdventureOptions(
                R.drawable.placeholder_visual,
                "the second adventure",
                R.drawable.ic_launcher_background
        ));
        adventureOptionsArrayList.add(new AdventureOptions(
                R.drawable.placeholder_visual,
                "the third adventure",
                R.drawable.ic_launcher_background
        ));
    }

    public void clickPlay() {
        Intent intent = new Intent(this, AdventureView.class);
        startActivity(intent);
    }

    public void clickPlay(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickPlay();
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }

//    public void clickPrev() {
//        if (currentAdvIndex != 0) {
//            currentAdvIndex--;
//            editor.putInt("user_currentAdvIdx", currentAdvIndex);
//            editor.apply();
//            cycleAdv();
//        }
//    }
//
//    public void clickPrev(View view, MotionEvent motionEvent) {
//        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//            clickPrev();
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
//        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
//        }
//    }

//    public void clickNext() {
//        if (currentAdvIndex != adventureOptionsArrayList.size()-1) {
//            currentAdvIndex++;
//            editor.putInt("user_currentAdvIdx", currentAdvIndex);
//            editor.apply();
//            cycleAdv();
//        }
//    }
//
//    public void clickNext(View view, MotionEvent motionEvent) {
//        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//            clickNext();
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
//        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
//        }
//    }

//    public void clickSettings() {
//        // TODO start settings
//    }
//
//    public void clickSettings(View view, MotionEvent motionEvent) {
//        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//            clickSettings();
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
//        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
//        }
//    }

//    public void clickBack() {
//        startActivity(new Intent(this, MainMenu.class));
//        this.finish();
//    }
//
//    public void clickBack(View view, MotionEvent motionEvent) {
//        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//            clickBack();
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
//        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
//        }
//    }

    public final ClickAction clickPrevLambda = () -> {
        if (currentAdvIndex != 0) {
            currentAdvIndex--;
            editor.putInt("user_currentAdvIdx", currentAdvIndex);
            editor.apply();
            cycleAdv();
        }
    };

    public final ClickAction clickNextLambda = () -> {
        if (currentAdvIndex != adventureOptionsArrayList.size()-1) {
            currentAdvIndex++;
            editor.putInt("user_currentAdvIdx", currentAdvIndex);
            editor.apply();
            cycleAdv();
        }
    };

    public final ClickAction clickSettingsLambda = () -> {
        // TODO start settings
    };

    public final ClickAction clickBackLambda = () -> {
        System.out.println("clickBackLambda called");
        startActivity(new Intent(StartGame.this, MainMenu.class));
        StartGame.this.finish();
    };

    public void clickTextView(View view, MotionEvent motionEvent, ClickAction clickAction) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickAction.action();
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.btnPlay_startGame:
                clickPlay(view, motionEvent);
                break;
            case R.id.btnPrev_startGame:
                //clickPrev(view, motionEvent);
                clickTextView(view, motionEvent, clickPrevLambda);
                break;
            case R.id.btnNext_startGame:
                //clickNext(view, motionEvent);
                clickTextView(view, motionEvent, clickNextLambda);
                break;
            case R.id.btnSettings_startGame:
                //clickSettings(view, motionEvent);
                clickTextView(view, motionEvent, clickSettingsLambda);
                break;
            case R.id.btnBack_startGame:
                //clickBack(view, motionEvent);
                clickTextView(view, motionEvent, clickBackLambda);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //clickBack();
        clickBackLambda.action();
    }

    public class AdventureOptions {

        private int visual;
        private String description;
        private int icon;

        public AdventureOptions(int visual, String description, int icon) {
            this.visual = visual;
            this.description = description;
            this.icon = icon;
        }

        public int getVisual() {
            return visual;
        }

        public String getDescription() {
            return description;
        }

        public int getIcon() {
            return icon;
        }
    }
}