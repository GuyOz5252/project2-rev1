package com.example.project2_rev1.eventTypes;

public class CutsceneEvent extends Event implements Node {

    private int[] visuals;
    private String[] storyText;
    private Event next;

    public CutsceneEvent(int[] visuals, String[] storyText, Event next) {
        this.visuals = visuals;
        this.storyText = storyText;
        this.next = next;
    }

    public int[] getVisuals() {
        return visuals;
    }

    public String[] getStoryText() {
        return storyText;
    }

    public Event getNextEvent() {
        return next;
    }
}
