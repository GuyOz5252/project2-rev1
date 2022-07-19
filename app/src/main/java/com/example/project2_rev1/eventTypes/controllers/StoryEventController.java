package com.example.project2_rev1.eventTypes.controllers;

import com.example.project2_rev1.AdventureController;
import com.example.project2_rev1.eventTypes.StoryEvent;

import java.util.Timer;
import java.util.TimerTask;

public class StoryEventController extends EventController {

    private StoryEvent storyEvent;

    private int currentIndex;
    private boolean advanceFlag;

    public StoryEventController(AdventureController adventureController, StoryEvent storyEvent) {
        super(adventureController);
        this.storyEvent = storyEvent;

        currentIndex = 1;
        advanceFlag = true;
    }

    public void advanceStory() {
        if (advanceFlag) {
            if (currentIndex < storyEvent.getVisuals().length) {
                adventureController.advanceStoryEvent(storyEvent.getVisuals()[currentIndex], storyEvent.getStoryText()[currentIndex]);
            }
            currentIndex++;
            if (currentIndex >= storyEvent.getVisuals().length+1) {
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
        adventureController.startNextEvent(storyEvent.getNextEvent());
    }

    public void clickAdvanceStoryEvent() {
        advanceStory();
    }
}
