package com.example.project2_rev1.eventTypes;

import com.example.project2_rev1.Adventure;

public class ChoiceEvent extends Event implements Node {

    private int[] visuals;
    private String storyText;
    private Choice choiceOne;
    private Choice choiceTwo;
    private int choiceMade;

    public ChoiceEvent(int[] visuals, String storyText, Choice choiceOne, Choice choiceTwo) {
        this.visuals = visuals;
        this.storyText = storyText;
        this.choiceOne = choiceOne;
        this.choiceTwo = choiceTwo;
    }

    public int[] getVisuals() {
        return this.visuals;
    }

    public String getStoryText() {
        return this.storyText;
    }

    public Choice getChoiceOne() {
        return this.choiceOne;
    }

    public Choice getChoiceTwo() {
        return this.choiceTwo;
    }

    public int getChoiceMade() {
        return choiceMade;
    }

    public void setChoiceMade(int choiceMade) {
        this.choiceMade = choiceMade;
    }

    public static class Choice implements Node {

        private String text;
        private Event nextEvent;
        private ChoiceAction[] actions;

        public Choice(String text, Event next, ChoiceEvent.ChoiceAction[] actions) {
            this.text = text;
            this.nextEvent = next;
            this.actions = actions;
        }

        public Event getNextEvent() {
            return nextEvent;
        }

        public String getChoiceText() {
            return text;
        }

        public ChoiceEvent.ChoiceAction[] getAction() {
            return actions;
        }
    }

    public interface ChoiceAction {}

    public interface UnlockCharacter extends ChoiceAction {
        Adventure.PartyMembers unlockCharacter();
    }

    public interface IncreaseSocialLink extends ChoiceAction {
        Adventure.PartyMembers increaseSocialLink();
    }
}
