package com.example.project2_rev1.eventTypes.controllers;

import com.example.project2_rev1.AdventureController;
import com.example.project2_rev1.eventTypes.CutsceneEvent;

import java.util.Timer;
import java.util.TimerTask;

public class CutsceneEventController extends EventController {

    private CutsceneEvent cutsceneEvent;

    private int currentIndex;
    private boolean advanceFlag;

    public CutsceneEventController(AdventureController adventureController, CutsceneEvent cutsceneEvent) {
        super(adventureController);
        this.cutsceneEvent = cutsceneEvent;

        currentIndex = 1;
        advanceFlag = true;
    }

    public void advanceCutscene() {
        if (advanceFlag) {
            if (currentIndex < cutsceneEvent.getVisuals().length) {
                adventureController.advanceCutscene(cutsceneEvent.getVisuals()[currentIndex], cutsceneEvent.getStoryText()[currentIndex]);
            }
            currentIndex++;
            if (currentIndex >= cutsceneEvent.getVisuals().length+1) {
                startNextEvent();
            }
            advanceFlag = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    advanceFlag = true;
                }
            }, 500);
        }
    }

    public void startNextEvent() {
        adventureController.startNextEvent(cutsceneEvent.getNextEvent());
    }

    public void clickAdvanceCutscene() {
        advanceCutscene();
    }

    public void clickSkipCutscene() {
        startNextEvent();
    }
}
