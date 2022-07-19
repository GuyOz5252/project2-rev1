package com.example.project2_rev1.eventTypes;

import com.example.project2_rev1.Adventure;
import com.example.project2_rev1.R;

import java.util.ArrayList;

public abstract class Event {

    public static class EventPool {

        private static ArrayList<Event> eventPoolAdvOne = new ArrayList<>();
        private static ArrayList<Event> eventPoolAdvTwo = new ArrayList<>();
        private static final int FIRST_EVENT_INDEX_ADV1 = 8;

        private static void initEventPoolAdvOne() {
            eventPoolAdvOne.add(new TransitionEvent(
                    "you sacrificed yourself and everyone loves you",
                    null
            ));
            eventPoolAdvOne.add(new TransitionEvent(
                    "you are now wanted all over the world for your crime",
                    null
            ));
            eventPoolAdvOne.add(new ChoiceEvent(
                    new int[] {
                            R.drawable.placeholder_visual,
                            R.drawable.placeholder_visual,
                            R.drawable.placeholder_visual,
                            R.drawable.placeholder_visual,
                    },
                    "a big monster is hungry",
                    new ChoiceEvent.Choice(
                            "kill yourself",
                            eventPoolAdvOne.get(0),
                            new ChoiceEvent.ChoiceAction[] {
                                    (ChoiceEvent.IncreaseSocialLink) () -> Adventure.PartyMembers.CHAR1,
                                    (ChoiceEvent.IncreaseSocialLink) () -> Adventure.PartyMembers.CHAR2 // wont work
                            }
                    ),
                    new ChoiceEvent.Choice(
                            "let everyone die",
                            eventPoolAdvOne.get(1),
                            null
                    )
            ));
            eventPoolAdvOne.add(new TransitionEvent(
                    "too many people are dying you are hated all over the world",
                    eventPoolAdvOne.get(2)
            ));
            eventPoolAdvOne.add(new CombatEvent(null));
            eventPoolAdvOne.add(
                    new ConversationEvent(new ConversationEvent.ConversationNode[] {
                            new ConversationEvent.ConversationNode("blue", R.drawable.placeholder_head, "the monster is coming"),
                            new ConversationEvent.ConversationNode("orange", R.drawable.placeholder_head, "oh no"),
                            new ConversationEvent.ConversationNode("blue", R.drawable.placeholder_head, "we need to fight"),
                            new ConversationEvent.ConversationNode("orange", R.drawable.placeholder_head, "people are dying")
                    },
                            eventPoolAdvOne.get(3)
            ));
            eventPoolAdvOne.add(new ChoiceEvent(
                    new int[] {
                            R.drawable.placeholder_visual,
                            R.drawable.placeholder2,
                    },
                    "a monster has spawned",
                    new ChoiceEvent.Choice(
                            "fight the monster",
                            eventPoolAdvOne.get(4),
                            new ChoiceEvent.ChoiceAction[] {
                                    (ChoiceEvent.UnlockCharacter) () -> Adventure.PartyMembers.CHAR1,
                                    (ChoiceEvent.UnlockCharacter) () -> Adventure.PartyMembers.CHAR2,
                                    (ChoiceEvent.UnlockCharacter) () -> Adventure.PartyMembers.CHAR3,
                                    (ChoiceEvent.UnlockCharacter) () -> Adventure.PartyMembers.CHAR4,
                                    (ChoiceEvent.UnlockCharacter) () -> Adventure.PartyMembers.CHAR5,
                            }
                    ),
                    new ChoiceEvent.Choice(
                            "lead the monster to town",
                            eventPoolAdvOne.get(5),
                            new ChoiceEvent.ChoiceAction[] {
                                    (ChoiceEvent.UnlockCharacter) () -> Adventure.PartyMembers.CHAR1,
                            }
                    )
            ));
            eventPoolAdvOne.add(new StoryEvent(
                    new int[][] {
                            new int[] {
                                    R.drawable.placeholder_visual,
                                    R.drawable.placeholder2,
                                    R.drawable.ic_adventure
                            },
                            new int[] {
                                    R.drawable.placeholder_visual,
                                    R.drawable.placeholder_visual
                            }
                    },
                    new String[] {
                            "did you spawn the monster???",
                            "all of us are gonna be killed because of you!"
                    },
                    eventPoolAdvOne.get(6)
            ));
            eventPoolAdvOne.add(new CutsceneEvent(
                    new int[] {R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene, R.drawable.placeholder_cutscene},
                    new String[] {
                            "Our story begins in a solemn realm led by a grave queen",
                            "The adored queen had a thousand names and ruled for over 300 years",
                            "With the exception of one guy, no one despised the queen",
                            "A powerful user of the dark arts capable of overthrowing the so-called kingdom",
                            "As he had no one at his side, he unleashed \"?\" who terrorized and destroyed everything in their path",
                            "The queen was most afraid of death; thus, she built an island for her most adored royal guards and their families",
                            "The rest of the people were left alone when she vanished and she was never to be seen again",
                            "The new king had taken over the capital, but the \"?\" lingered, he lacked the strength to eliminate them",
                            "So, he had formed the mists, which split the land and oceans and blocked all beings from coming out",
                            "Only the king known how to open the seal, which was forged between all of the remaining islands",
                            "His empire crumbled after a few years, and he sought to modify the magic"
                    },
                    eventPoolAdvOne.get(7)
            ));
        }

        private static void initEventPoolAdvTwo() {
//            eventPoolAdvTwo.add(new ChoiceEvent( // 0
//                    R.drawable.placeholder,
//                    "and...",
//                    new ChoiceEvent.Choice("desert you", null),
//                    new ChoiceEvent.Choice("hurt you", null)
//            ));
//            eventPoolAdvTwo.add(new ChoiceEvent( // 1
//                    R.drawable.placeholder,
//                    "never gonna...",
//                    new ChoiceEvent.Choice("run around", eventPoolAdvTwo.get(0)),
//                    new ChoiceEvent.Choice("hurt you", null)
//            ));
//            eventPoolAdvTwo.add(new ChoiceEvent( // 2
//                    R.drawable.placeholder,
//                    "never gonna...",
//                    new ChoiceEvent.Choice("run around", null),
//                    new ChoiceEvent.Choice("let you down", eventPoolAdvTwo.get(1))
//            ));
//            eventPoolAdvTwo.add(new ChoiceEvent( // 3
//                    R.drawable.placeholder,
//                    "never gonna...",
//                    new ChoiceEvent.Choice("give you up", eventPoolAdvTwo.get(2)),
//                    new ChoiceEvent.Choice("desert you", null)
//            ));
        }

        private static void initEventPoolAdvThree() {

        }

        public static Event getFirstEventInAdventureByIndex(int idx) {
            switch (idx) {
                case 0:
                    initEventPoolAdvOne();
                    return eventPoolAdvOne.get(FIRST_EVENT_INDEX_ADV1);
                case 1:
                    initEventPoolAdvTwo();
                    return eventPoolAdvTwo.get(3);
                case 2:
                    initEventPoolAdvThree();
                    return null;
            }
            return null;
        }
    }
}
