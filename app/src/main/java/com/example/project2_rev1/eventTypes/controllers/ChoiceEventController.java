package com.example.project2_rev1.eventTypes.controllers;

import com.example.project2_rev1.Adventure;
import com.example.project2_rev1.AdventureController;
import com.example.project2_rev1.eventTypes.ChoiceEvent;
import com.example.project2_rev1.eventTypes.Event;

public class ChoiceEventController extends EventController {

    private ChoiceEvent choiceEvent;

    public ChoiceEventController(AdventureController adventureController, ChoiceEvent choiceEvent) {
        super(adventureController);
        this.choiceEvent = choiceEvent;
    }

    public void startNextEvent(Event event) {
        adventureController.startNextEvent(event);
    }

    public void unlockCharacter(Adventure.PartyMembers name) {
        adventureController.unlockCharacter(name);
    }

    public void increaseSocialLink(Adventure.PartyMembers name) {
        if (name.unlock) {
            adventureController.increaseSocialLink(name);
        }
    }

    public void clickChoiceOne() {
        choiceEvent.setChoiceMade(0);
        adventureController.insertToBinaryTree(choiceEvent);
        if (choiceEvent.getChoiceOne().getAction() != null) {
            for (ChoiceEvent.ChoiceAction action : choiceEvent.getChoiceOne().getAction()) {
                if (action instanceof ChoiceEvent.UnlockCharacter) {
                    unlockCharacter(((ChoiceEvent.UnlockCharacter) action).unlockCharacter());
                } else if (action instanceof ChoiceEvent.IncreaseSocialLink) {
                    increaseSocialLink(((ChoiceEvent.IncreaseSocialLink) action).increaseSocialLink());
                }
            }
        }
        startNextEvent(choiceEvent.getChoiceOne().getNextEvent());
    }

    public void clickChoiceTwo() {
        choiceEvent.setChoiceMade(1);
        adventureController.insertToBinaryTree(choiceEvent);
        if (choiceEvent.getChoiceTwo().getAction() != null) {
            for (ChoiceEvent.ChoiceAction action : choiceEvent.getChoiceTwo().getAction()) {
                if (action instanceof ChoiceEvent.UnlockCharacter) {
                    unlockCharacter(((ChoiceEvent.UnlockCharacter) action).unlockCharacter());
                } else if (action instanceof ChoiceEvent.IncreaseSocialLink) {
                    increaseSocialLink(((ChoiceEvent.IncreaseSocialLink) action).increaseSocialLink());
                }
            }
        }
        startNextEvent(choiceEvent.getChoiceTwo().getNextEvent());
    }
}
