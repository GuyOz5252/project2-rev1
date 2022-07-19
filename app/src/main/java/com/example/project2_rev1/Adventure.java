package com.example.project2_rev1;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;

import androidx.annotation.DrawableRes;

import com.example.project2_rev1.eventTypes.ChoiceEvent;
import com.example.project2_rev1.eventTypes.Event;
import com.example.project2_rev1.eventTypes.Node;
import com.example.project2_rev1.eventTypes.TransitionEvent;
import com.example.project2_rev1.menus.EndGame;

import java.util.HashMap;

public class Adventure {

    private final BinaryTree<Node> adventureBinaryTree;
    private BinaryTree<Node> adventureBinaryTreePointer;
    private int binaryTreeDirection;

    private HashMap<PartyMembers, Integer> socialLinkMap;

    private Event currentEvent;

    public Adventure() {
        adventureBinaryTree = new BinaryTree<>();
        adventureBinaryTreePointer = adventureBinaryTree;
        binaryTreeDirection = 1;

        socialLinkMap = new HashMap<>();
        socialLinkMap.put(PartyMembers.CHAR1, 0);
        socialLinkMap.put(PartyMembers.CHAR2, 0);
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }

    public void insertToBinaryTree(Node node) {
        if (node instanceof ChoiceEvent) {
            adventureBinaryTreePointer.setValue(node);
            adventureBinaryTreePointer.setLeft(new BinaryTree<>(((ChoiceEvent)node).getChoiceOne()));
            adventureBinaryTreePointer.setRight(new BinaryTree<>(((ChoiceEvent)node).getChoiceTwo()));
            if (((ChoiceEvent)node).getChoiceMade() == 0) {
                adventureBinaryTreePointer.getLeft().setLeft(new BinaryTree<>());
                adventureBinaryTreePointer = adventureBinaryTreePointer.getLeft().getLeft();
                binaryTreeDirection = 0;
            } else {
                adventureBinaryTreePointer.getRight().setRight(new BinaryTree<>());
                adventureBinaryTreePointer = adventureBinaryTreePointer.getRight().getRight();
                binaryTreeDirection = 1;
            }

        } else if (node instanceof TransitionEvent) {
            adventureBinaryTreePointer.setValue(node);
            if (binaryTreeDirection == 0) {
                adventureBinaryTreePointer.setLeft(new BinaryTree<>());
                adventureBinaryTreePointer = adventureBinaryTreePointer.getLeft();
            } else {
                adventureBinaryTreePointer.setRight(new BinaryTree<>());
                adventureBinaryTreePointer = adventureBinaryTreePointer.getRight();
            }
        }
    }

    public void unlockCharacter(PartyMembers name) {
        System.out.println(name + ": " + name.unlock);
        name.unlock = true;
        System.out.println(name + ": " + name.unlock);
    }

    public void increaseSocialLink(PartyMembers name) {
        System.out.println(name + ": " + socialLinkMap.get(name));
        socialLinkMap.replace(name, socialLinkMap.get(name), socialLinkMap.get(name)+1);
        System.out.println(name + ": " + socialLinkMap.get(name));
    }

    public void endAdventure(AdventureView adventureView) {
        System.out.println("END");
        printBinaryTree(adventureBinaryTree);
        Intent intent = new Intent(adventureView.getBaseContext(), EndGame.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK); // from exception don't know what is this
        // TODO pass binary tree
        adventureView.getBaseContext().startActivity(intent);
    }

    public void printBinaryTree(BinaryTree<Node> bt) { // test
        if (bt != null) {
            printBinaryTree(bt.getLeft());
            if (bt.getValue() instanceof ChoiceEvent) {
                System.out.println(((ChoiceEvent) bt.getValue()).getStoryText() + " [choice event]");
            } else if (bt.getValue() instanceof ChoiceEvent.Choice) {
                System.out.println(((ChoiceEvent.Choice) bt.getValue()).getChoiceText() + " [choice]");
            } else if (bt.getValue() instanceof TransitionEvent) {
                System.out.println(((TransitionEvent) bt.getValue()).getTransitionText() + " [transition event]");
            }
            printBinaryTree(bt.getRight());
        }
    }

    public enum PartyMembers {
        CHAR1(false, R.drawable.placeholder_head),
        CHAR2(false, R.drawable.placeholder_head),
        CHAR3(false, R.drawable.placeholder_head),
        CHAR4(false, R.drawable.placeholder_head),
        CHAR5(false, R.drawable.placeholder_head);

        public boolean unlock;
        public int face;

        PartyMembers(boolean unlock, @DrawableRes int face) {
            this.unlock = unlock;
            this.face = face;
        }
    }
}
