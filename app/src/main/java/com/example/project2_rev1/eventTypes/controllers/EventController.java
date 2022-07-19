package com.example.project2_rev1.eventTypes.controllers;

import com.example.project2_rev1.AdventureController;

public abstract class EventController {

    protected AdventureController adventureController;

    public EventController(AdventureController adventureController) {
        this.adventureController = adventureController;
    }
}
