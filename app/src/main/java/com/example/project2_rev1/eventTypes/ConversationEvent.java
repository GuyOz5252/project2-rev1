package com.example.project2_rev1.eventTypes;

public class ConversationEvent extends Event implements Node {

    private ConversationNode[] conversationNodes;
    private Event next;

    public ConversationEvent(ConversationNode[] conversationNodes, Event next) {
        this.conversationNodes = conversationNodes;
        this.next = next;
    }

    public ConversationNode[] getConversationArrayList() {
        return conversationNodes;
    }

    public Event getNextEvent() {
        return next;
    }

    public static class ConversationNode {

        private String name;
        private int face;
        private String text;

        public ConversationNode(String name, int face, String text) {
            this.name = name;
            this.face = face;
            this.text = text;
        }

        public String getName() {
            return name;
        }

        public int getFace() {
            return face;
        }

        public String getText() {
            return text;
        }
    }
}
