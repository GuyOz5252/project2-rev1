package com.example.project2_rev1.eventTypes.controllers;

import com.example.project2_rev1.Adventure;
import com.example.project2_rev1.AdventureController;
import com.example.project2_rev1.eventTypes.CombatEvent;

import java.sql.Struct;
import java.util.ArrayList;

public class CombatEventController extends EventController {

    private CombatEvent combatEvent;

    private ArrayList<Adventure.PartyMembers> partyMembersArrayList;

    public CombatEventController(AdventureController adventureController, CombatEvent combatEvent) {
        super(adventureController);
        this.combatEvent = combatEvent;
        partyMembersArrayList = new ArrayList<>();

        startPartySelectDialog();
    }

    public void startNextEvent() {
        adventureController.startNextEvent(combatEvent.getNextEvent());
    }

    public void temp() { // TODO TEMP
        StringBuilder str = new StringBuilder();
        for (Adventure.PartyMembers partyMember : partyMembersArrayList) {
            str.append(partyMember.name()).append("\n");
        }
        adventureController.temp(str.toString());
    }

    // region PARTY SELECT DIALOG
    /**PARTY SELECT DIALOG**/
    public void startPartySelectDialog() {
        adventureController.startPartySelectDialog();
    }

    public boolean clickPartyMember(Adventure.PartyMembers partyMember) {
        if (partyMember.unlock && !partyMembersArrayList.contains(partyMember)) {
            if (partyMembersArrayList.size() < 3) {
                partyMembersArrayList.add(partyMember);
                adventureController.updateParty(partyMembersArrayList);
                return true;
            }
        }
        return false;
    }

    public void clickPickedPartyMember(Adventure.PartyMembers partyMember) {
        partyMembersArrayList.remove(partyMember);
        adventureController.updateParty(partyMembersArrayList);
    }

    public boolean clickContinueToCombat() {
        if (partyMembersArrayList.size() >= 1) {
            temp();
            return true;
        }
        return false;
    }
    // endregion
}
