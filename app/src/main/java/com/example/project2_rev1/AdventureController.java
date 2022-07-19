package com.example.project2_rev1;

import android.provider.Telephony;

import com.example.project2_rev1.eventTypes.ChoiceEvent;
import com.example.project2_rev1.eventTypes.CombatEvent;
import com.example.project2_rev1.eventTypes.ConversationEvent;
import com.example.project2_rev1.eventTypes.CutsceneEvent;
import com.example.project2_rev1.eventTypes.Event;
import com.example.project2_rev1.eventTypes.Node;
import com.example.project2_rev1.eventTypes.StoryEvent;
import com.example.project2_rev1.eventTypes.TransitionEvent;
import com.example.project2_rev1.eventTypes.controllers.ChoiceEventController;
import com.example.project2_rev1.eventTypes.controllers.CombatEventController;
import com.example.project2_rev1.eventTypes.controllers.ConversationEventController;
import com.example.project2_rev1.eventTypes.controllers.CutsceneEventController;
import com.example.project2_rev1.eventTypes.controllers.StoryEventController;
import com.example.project2_rev1.eventTypes.controllers.TransitionEventController;

import java.util.ArrayList;

public class AdventureController {

    private Adventure adventure;
    private AdventureView adventureView;

    private ChoiceEventController choiceEventController;
    private TransitionEventController transitionEventController;
    private ConversationEventController conversationEventController;
    private CutsceneEventController cutsceneEventController;
    private StoryEventController storyEventController;
    private CombatEventController combatEventController;

    public AdventureController(AdventureView adventureView, int currentAdventureIndex) {
        this.adventureView = adventureView;
        this.adventure = new Adventure();

        startFirstEvent(currentAdventureIndex);
    }

    public Event getCurrentEvent() {
        return adventure.getCurrentEvent();
    }

    private void startFirstEvent(int idx) {
        Event event = Event.EventPool.getFirstEventInAdventureByIndex(idx);
        if (event instanceof ChoiceEvent) {
            choiceEventController = new ChoiceEventController(this, (ChoiceEvent)event);
        } else if (event instanceof TransitionEvent) {
            transitionEventController = new TransitionEventController(this, (TransitionEvent)event);
        } else if (event instanceof ConversationEvent) {
            conversationEventController = new ConversationEventController(this, (ConversationEvent)event);
        } else if (event instanceof CutsceneEvent) {
            cutsceneEventController = new CutsceneEventController(this, (CutsceneEvent)event);
        } else if (event instanceof StoryEvent) {
            storyEventController = new StoryEventController(this, (StoryEvent)event);
        } else if (event instanceof CombatEvent) {
            combatEventController = new CombatEventController(this, (CombatEvent)event);
        }
        adventure.setCurrentEvent(event);
    }

    public void startNextEvent(Event event) {
        adventure.setCurrentEvent(event);
        adventureView.loadEvent();
        if (event == null) {
            adventure.endAdventure(adventureView);
        }
        if (event instanceof ChoiceEvent) {
            choiceEventController = new ChoiceEventController(this, (ChoiceEvent)event);
        } else if (event instanceof TransitionEvent) {
            transitionEventController = new TransitionEventController(this, (TransitionEvent)event);
        } else if (event instanceof ConversationEvent) {
            conversationEventController = new ConversationEventController(this, (ConversationEvent)event);
        } else if (event instanceof CutsceneEvent) {
            cutsceneEventController = new CutsceneEventController(this, (CutsceneEvent)event);
        } else if (event instanceof StoryEvent) {
            storyEventController  = new StoryEventController(this, (StoryEvent)event);
        } else if (event instanceof  CombatEvent) {
            combatEventController = new CombatEventController(this, (CombatEvent)event);
        }
    }

    public void insertToBinaryTree(Node node) {
        adventure.insertToBinaryTree(node);
    }

    public void unlockCharacter(Adventure.PartyMembers name) {
        adventure.unlockCharacter(name);
    }

    public void increaseSocialLink(Adventure.PartyMembers name) {
        adventure.increaseSocialLink(name);
    }

    // region CHOICE EVENT
    /**CHOICE EVENT**/
    public void clickChoiceOne() {
        choiceEventController.clickChoiceOne();
    }

    public void clickChoiceTwo() {
        choiceEventController.clickChoiceTwo();
    }
    // endregion

    // region TRANSITION EVENT
    /**TRANSITION EVENT**/
    public void clickContinue() {
        transitionEventController.clickContinue();
    }
    // endregion

    // region CONVERSATION EVENT
    /**CONVERSATION EVENT**/
    public void hideDialog() {
        adventureView.hideDialog();
    }

    public void advanceConversation(ConversationEvent.ConversationNode conversationNode, boolean animate) {
        adventureView.advanceConversation(conversationNode, animate);
    }

    public void clickAdvanceConversation() {
        conversationEventController.clickAdvanceConversation();
    }

    public void clickSkipConversation() {
        conversationEventController.clickSkipConversation();
    }
    // endregion

    // region CUTSCENE EVENT
    /**CUTSCENE EVENT**/
    public void advanceCutscene(int visual, String text) {
        adventureView.advanceCutscene(visual, text);
    }

    public void clickAdvanceCutscene() {
        cutsceneEventController.clickAdvanceCutscene();
    }

    public void clickSkipCutscene() {
        cutsceneEventController.clickSkipCutscene();
    }
    // endregion

    // region STORY EVENT
    /**STORY EVENT**/
    public void advanceStoryEvent(int[] visuals, String text) {
        adventureView.advanceStoryEvent(visuals, text);
    }

    public void clickAdvanceStoryEvent() {
        storyEventController.clickAdvanceStoryEvent();
    }
    /***************/
    // endregion

    // region COMBAT
    /**COMBAT EVENT**/
    public void temp(String str) {
        adventureView.temp(str);
    } // TODO TEMP

    // party select dialog
    public void startPartySelectDialog() {
        adventureView.startPartySelectDialog();
    }

    public void updateParty(ArrayList<Adventure.PartyMembers> partyMembersArrayList) {
        adventureView.updateParty(partyMembersArrayList);
    }

    public boolean clickPartyMember(Adventure.PartyMembers partyMember) {
        return combatEventController.clickPartyMember(partyMember);
    }

    public void clickPickedPartyMember(Adventure.PartyMembers partyMember) {
        combatEventController.clickPickedPartyMember(partyMember);
    }

    public boolean clickContinueToCombat() {
        return combatEventController.clickContinueToCombat();
    }
    /****************/
    // endregion

}
