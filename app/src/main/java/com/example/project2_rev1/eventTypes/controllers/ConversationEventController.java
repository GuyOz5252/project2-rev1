package com.example.project2_rev1.eventTypes.controllers;

import com.example.project2_rev1.AdventureController;
import com.example.project2_rev1.eventTypes.ConversationEvent;

import java.util.Timer;
import java.util.TimerTask;

public class ConversationEventController extends EventController {

    private ConversationEvent conversationEvent;

    private boolean advanceFlag;

    private static int currentConvIndex;

    public ConversationEventController(AdventureController adventureController, ConversationEvent conversationEvent) {
        super(adventureController);
        this.conversationEvent = conversationEvent;

        hideDialog();
        currentConvIndex = 1;
        advanceFlag = true;
    }

    public void hideDialog() {
        adventureController.hideDialog();
    }

    public void advanceConversation() {
        if (advanceFlag) {
            if (currentConvIndex < conversationEvent.getConversationArrayList().length) {
                adventureController.advanceConversation(conversationEvent.getConversationArrayList()[currentConvIndex], true);
            }
            currentConvIndex++;
            if (currentConvIndex >= conversationEvent.getConversationArrayList().length+1) {
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

    private void startNextEvent() {
        adventureController.startNextEvent(conversationEvent.getNextEvent());
    }

    public void clickAdvanceConversation() {
        advanceConversation();
    }

    public void clickSkipConversation() {
        for (int i = currentConvIndex; i < conversationEvent.getConversationArrayList().length; i++) {
            adventureController.advanceConversation(conversationEvent.getConversationArrayList()[currentConvIndex], false);
            currentConvIndex++;
        }
    }

    public static int getCurrentConvIndex() {
        return currentConvIndex;
    }
}
