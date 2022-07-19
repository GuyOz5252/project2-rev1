package com.example.project2_rev1.eventTypes;

public class TransitionEvent extends Event implements Node {

    private String transitionText;
    private Event nextEvent;

    public TransitionEvent(String transitionText, Event nextEvent) {
        this.transitionText = transitionText;
        this.nextEvent = nextEvent;
    }

    public String getTransitionText() {
        return transitionText;
    }

    public Event getNextEvent() {
        return nextEvent;
    }
}
