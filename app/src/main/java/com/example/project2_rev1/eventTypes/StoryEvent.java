package com.example.project2_rev1.eventTypes;

public class StoryEvent extends Event {

    private int[][] visuals;
    private String[] storyText;
    private Event next;

    public StoryEvent(int[][] visuals, String[] storyText, Event next) {
        this.visuals = visuals;
        this.storyText = storyText;
        this.next = next;
    }

    public int[][] getVisuals() {
        return visuals;
    }

    public String[] getStoryText() {
        return storyText;
    }

    public Event getNextEvent() {
        return next;
    }
}
