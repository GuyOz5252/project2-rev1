package com.example.project2_rev1.eventTypes;

public class CombatEvent extends Event {

    private Event next;

    public CombatEvent(Event next) {
        this.next = next;
    }

    public Event getNextEvent() {
        return next;
    }
}
