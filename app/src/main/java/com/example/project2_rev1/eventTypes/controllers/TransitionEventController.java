package com.example.project2_rev1.eventTypes.controllers;

import com.example.project2_rev1.AdventureController;
import com.example.project2_rev1.AdventureView;
import com.example.project2_rev1.eventTypes.Event;
import com.example.project2_rev1.eventTypes.Node;
import com.example.project2_rev1.eventTypes.TransitionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class TransitionEventController extends EventController {

    private TransitionEvent transitionEvent;

    private boolean continueFlag;

    public TransitionEventController(AdventureController adventureController, TransitionEvent transitionEvent) {
        super(adventureController);
        this.transitionEvent = transitionEvent;

        continueFlag = false;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                continueFlag = true;
            }
        }, 1000);
    }

    public void startNextEvent(Event event) {
        adventureController.insertToBinaryTree(transitionEvent);
        adventureController.startNextEvent(event);
    }

    public void clickContinue() {
        if (continueFlag) {
            startNextEvent(transitionEvent.getNextEvent());
        }
    }
}
