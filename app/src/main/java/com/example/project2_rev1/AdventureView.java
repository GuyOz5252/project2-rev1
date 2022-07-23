package com.example.project2_rev1;

import static com.example.project2_rev1.utils.HelperMethods.toCamelCase;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project2_rev1.eventTypes.ChoiceEvent;
import com.example.project2_rev1.eventTypes.CombatEvent;
import com.example.project2_rev1.eventTypes.ConversationEvent;
import com.example.project2_rev1.eventTypes.CutsceneEvent;
import com.example.project2_rev1.eventTypes.Event;
import com.example.project2_rev1.eventTypes.StoryEvent;
import com.example.project2_rev1.eventTypes.TransitionEvent;
import com.example.project2_rev1.eventTypes.controllers.ConversationEventController;
import com.example.project2_rev1.menus.MainMenu;
import com.example.project2_rev1.utils.TypeWriter;

import java.util.ArrayList;

/**
 * @author Guy Oz
 *
 * tdhndyhndnhdj
 */

@SuppressWarnings("all")
public class AdventureView extends AppCompatActivity implements View.OnTouchListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private AdventureController adventureController;

    private TextView btnPause, animRect;

    // choice
    private ImageView[] imvVisuals;
    private TypeWriter txtStoryText;
    private Button btnChoiceOne, btnChoiceTwo;

    // transition
    private TypeWriter txtTransitionText;

    // conversation
    private LinearLayout[] conversationNodes;
    private ImageView[] imvFaces;
    private TextView[] txtNames;
    private TypeWriter[] txtConversation;
    private TextView btnSkipConversation;
    private TextView conversationTouchListener;

    // cutscene
    private ImageView imvCutsceneVisual;
    private TextView btnSkipCutscene;
    private TypeWriter txtCutsceneText;
    private TextView cutsceneTouchListener;

    // story
    private ImageView[] imvStoryEventVisuals;
    private TypeWriter txtStoryEventText;
    private TextView storyTouchListener;

    // pause
    private Dialog pauseDialog;
    private Button btnResume, btnSettings, btnQuit;

    // combat
    private TextView temp; // TODO TEMP

    // party select
    private Dialog partySelectDialog;
    private LinearLayout lnParty, lnPartySelectDeck1, lnPartySelectDeck2;
    private Button btnContinueToCombat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = getSharedPreferences("sp", 0);
        editor = sharedPreferences.edit();

        adventureController = new AdventureController(this, sharedPreferences.getInt("user_currentAdvIdx", 0));

        loadEvent();
    }

    public void loadView(Event event) {
        if (event instanceof ChoiceEvent) {
            setContentView(R.layout.activity_choice_event_view);
        } else if (event instanceof TransitionEvent) {
            setContentView(R.layout.activity_transition_event_view);
        } else if (event instanceof ConversationEvent) {
            setContentView(R.layout.activity_conversation_event_view);
        } else if (event instanceof CutsceneEvent) {
            setContentView(R.layout.activity_cutscene_event_view);
        } else if (event instanceof StoryEvent) {
            setContentView(R.layout.activity_story_event_view);
        } else if (event instanceof CombatEvent) {
            setContentView(R.layout.activity_combat_event_view);
        }
    }

    public void bindView(Event event) {
        if (event instanceof ChoiceEvent) {
            imvVisuals = new ImageView[] {
                    findViewById(R.id.imvVisualBackground_choiceEvent),
                    findViewById(R.id.imvVisualLayer1_choiceEvent),
                    findViewById(R.id.imvVisualLayer2_choiceEvent),
                    findViewById(R.id.imvVisualLayer3_choiceEvent)
            };
            txtStoryText = findViewById(R.id.txtStoryText_choiceEvent);
            btnChoiceOne = findViewById(R.id.btnChoiceOne_choiceEvent);
            btnChoiceTwo = findViewById(R.id.btnChoiceTwo_choiceEvent);
            btnPause = findViewById(R.id.btnPause_choiceEvent);
            animRect = findViewById(R.id.animRect_choiceEvent);
            btnChoiceOne.setOnTouchListener(this);
            btnChoiceTwo.setOnTouchListener(this);
            btnPause.setOnTouchListener(this);

        } else if (event instanceof TransitionEvent) {
            txtTransitionText = findViewById(R.id.txtTransition_transitionEvent);
            txtTransitionText.setOnTouchListener(this);
            animRect = findViewById(R.id.animRect_TransitionEvent);

        } else if (event instanceof ConversationEvent) {
            conversationNodes = new LinearLayout[] {
                    findViewById(R.id.conversationNode1),
                    findViewById(R.id.conversationNode2),
                    findViewById(R.id.conversationNode3),
                    findViewById(R.id.conversationNode4),
                    findViewById(R.id.conversationNode5)
            };
            txtNames = new TextView[] {
                    findViewById(R.id.txtName1),
                    findViewById(R.id.txtName2),
                    findViewById(R.id.txtName3),
                    findViewById(R.id.txtName4),
                    findViewById(R.id.txtName5)
            };
            imvFaces = new ImageView[] {
                    findViewById(R.id.imvFace1),
                    findViewById(R.id.imvFace2),
                    findViewById(R.id.imvFace3),
                    findViewById(R.id.imvFace4),
                    findViewById(R.id.imvFace5),
            };
            txtConversation = new TypeWriter[] {
                    findViewById(R.id.txtTalk1),
                    findViewById(R.id.txtTalk2),
                    findViewById(R.id.txtTalk3),
                    findViewById(R.id.txtTalk4),
                    findViewById(R.id.txtTalk5)
            };
            btnSkipConversation = findViewById(R.id.btnSkip_conversationEvent);
            conversationTouchListener = findViewById(R.id.touchListener_conversationEvent);
            btnPause = findViewById(R.id.btnPause_conversationEvent);
            animRect = findViewById(R.id.animRect_conversationEvent);
            btnSkipConversation.setOnTouchListener(this);
            conversationTouchListener.setOnTouchListener(this);
            btnPause.setOnTouchListener(this);

        } else if (event instanceof CutsceneEvent) {
            imvCutsceneVisual = findViewById(R.id.imvVisual_cutsceneEvent);
            btnSkipCutscene = findViewById(R.id.btnSkip_cutsceneEvent);
            txtCutsceneText = findViewById(R.id.txtCutsceneText_cutsceneEvent);
            cutsceneTouchListener = findViewById(R.id.touchListener_cutsceneEvent);
            btnPause = findViewById(R.id.btnPause_cutsceneEvent);
            animRect = findViewById(R.id.animRect_cutsceneEvent);
            btnSkipCutscene.setOnTouchListener(this);
            cutsceneTouchListener.setOnTouchListener(this);
            btnPause.setOnTouchListener(this);

        } else if (event instanceof StoryEvent) {
            imvStoryEventVisuals = new ImageView[] {
                    findViewById(R.id.imvVisualBackground_storyEvent),
                    findViewById(R.id.imvVisualLayer1_storyEvent),
                    findViewById(R.id.imvVisualLayer2_storyEvent),
                    findViewById(R.id.imvVisualLayer3_storyEvent)
            };
            txtStoryEventText = findViewById(R.id.txtStoryText_storyEvent);
            storyTouchListener = findViewById(R.id.touchListener_storyEvent);
            btnPause = findViewById(R.id.btnPause_storyEvent);
            animRect = findViewById(R.id.animRect_storyEvent);
            storyTouchListener.setOnTouchListener(this);
            btnPause.setOnTouchListener(this);

        } else if (event instanceof CombatEvent) {
            btnPause = findViewById(R.id.btnPause_combatEvent);
            animRect = findViewById(R.id.animRect_combatEvent);
            btnPause.setOnTouchListener(this);

            temp = findViewById(R.id.temp); // TODO TEMP
        }
    }

    public void loadEvent() {
        Event event = adventureController.getCurrentEvent();
        loadView(event);
        bindView(event);
        startFadeAnimation(animRect);
        if (event instanceof ChoiceEvent) {
            ChoiceEvent choiceEvent = (ChoiceEvent)event;
            for (int i = 0; i < imvVisuals.length; i++) {
                if (i < choiceEvent.getVisuals().length) {
                    imvVisuals[i].setVisibility(View.VISIBLE);
                    imvVisuals[i].setImageResource(choiceEvent.getVisuals()[i]);
                } else {
                    imvVisuals[i].setVisibility(View.INVISIBLE);
                }
            }
            txtStoryText.animate(choiceEvent.getStoryText());
            txtStoryText.setOnTouchListener(this::skipTypeAnimationChoiceEvent);
            btnChoiceOne.setText(choiceEvent.getChoiceOne().getChoiceText());
            btnChoiceTwo.setText(choiceEvent.getChoiceTwo().getChoiceText());

        } else if (event instanceof TransitionEvent) {
            TransitionEvent transitionEvent = (TransitionEvent)event;
            txtTransitionText.animate(transitionEvent.getTransitionText());

        } else if (event instanceof ConversationEvent) {
            ConversationEvent conversationEvent = (ConversationEvent)event;
            txtNames[0].setText(conversationEvent.getConversationArrayList()[0].getName());
            imvFaces[0].setImageResource(conversationEvent.getConversationArrayList()[0].getFace());
            txtConversation[0].animate(conversationEvent.getConversationArrayList()[0].getText(), 70);

        } else if (event instanceof CutsceneEvent) {
            CutsceneEvent cutsceneEvent = (CutsceneEvent)event;
            imvCutsceneVisual.setImageResource(cutsceneEvent.getVisuals()[0]);
            txtCutsceneText.animate(cutsceneEvent.getStoryText()[0]);

        } else if (event instanceof StoryEvent) {
            StoryEvent storyEvent = (StoryEvent)event;
            for (int i = 0; i < imvStoryEventVisuals.length; i++) {
                if (i < storyEvent.getVisuals()[0].length) {
                    imvStoryEventVisuals[i].setVisibility(View.VISIBLE);
                    imvStoryEventVisuals[i].setImageResource(storyEvent.getVisuals()[0][i]);
                } else {
                    imvStoryEventVisuals[i].setVisibility(View.INVISIBLE);
                }
            }
            txtStoryEventText.animate(storyEvent.getStoryText()[0]);

        } else if (event instanceof CombatEvent) {
            //
        }
    }

    public void startFadeAnimation(TextView animRect) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
        animRect.setVisibility(View.VISIBLE);
        animRect.startAnimation(animation);
        animRect.setOnTouchListener(this::skipFadeAnimation);
        animRect.setVisibility(View.INVISIBLE);
    }

    public boolean skipFadeAnimation(View view, MotionEvent motionEvent) {
        animRect.clearAnimation();
        return true;
    }

    // region CHOICE EVENT
    /**CHOICE EVENT**/
    public void clickChoiceOne() {
        if (txtStoryText.getIsRunning()) {
            txtStoryText.stopAnimation();
        } else {
            btnChoiceTwo.setBackgroundColor(ContextCompat.getColor(this, R.color.darkGrey));
            btnChoiceTwo.setTextColor(ContextCompat.getColor(this, R.color.black));
            adventureController.clickChoiceOne();
        }
    }

    public void clickChoiceOne(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown2));
            clickChoiceOne();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown));
        }
    }

    public void clickChoiceTwo() {
        if (txtStoryText.getIsRunning()) {
            txtStoryText.stopAnimation();
        } else {
            btnChoiceOne.setBackgroundColor(ContextCompat.getColor(this, R.color.darkGrey));
            btnChoiceOne.setTextColor(ContextCompat.getColor(this, R.color.black));
            adventureController.clickChoiceTwo();
        }
    }

    public void clickChoiceTwo(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown2));
            clickChoiceTwo();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown));
        }
    }

    public boolean skipTypeAnimationChoiceEvent(View view, MotionEvent motionEvent) {
        txtStoryText.stopAnimation();
        return true;
    }
    // endregion

    // region TRANSITION EVENT
    /**TRANSITION EVENT**/
    public void clickContinue() {
        if (txtTransitionText.getIsRunning()) {
            txtTransitionText.stopAnimation();
        } else {
            adventureController.clickContinue();
        }
    }

    public void clickContinue(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickContinue();
        }
    }
    // endregion

    // region CONVERSATION EVENT
    /**CONVERSATION EVENT**/
    public void hideDialog() {
        for (int i = 1; i < conversationNodes.length; i++) {
            conversationNodes[i].setVisibility(View.INVISIBLE);
        }
    }

    public void advanceConversation(ConversationEvent.ConversationNode conversationNode, boolean animate) {
        conversationNodes[ConversationEventController.getCurrentConvIndex()].setVisibility(View.VISIBLE);
        txtNames[ConversationEventController.getCurrentConvIndex()].setText(conversationNode.getName());
        imvFaces[ConversationEventController.getCurrentConvIndex()].setImageResource(conversationNode.getFace());
        if (animate) {
            txtConversation[ConversationEventController.getCurrentConvIndex()].animate(conversationNode.getText(), 70);
        } else {
            txtConversation[ConversationEventController.getCurrentConvIndex()].setText(conversationNode.getText());
        }
    }

    public void clickAdvanceConversation() {
        adventureController.clickAdvanceConversation();
    }

    public void clickAdvanceConversation(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (txtConversation[ConversationEventController.getCurrentConvIndex()-1].getIsRunning()) {
                txtConversation[ConversationEventController.getCurrentConvIndex()-1].stopAnimation();
            } else {
                clickAdvanceConversation();
            }
        }
    }

    public void clickSkipConversation() {
        adventureController.clickSkipConversation();
        txtConversation[0].stopAnimation();
    }

    public void clickSkipConversation(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.grey)); // light brown??
            clickSkipConversation();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }
    /**********************/
    // endregion

    // region CUTSCENE EVENT
    /**CUTSCENE EVENT**/
    public void advanceCutscene(int visual, String text) {
        imvCutsceneVisual.setImageResource(visual);
        txtCutsceneText.animate(text);
    }

    public void clickAdvanceCutscene() {
        if (txtCutsceneText.getIsRunning()) {
            txtCutsceneText.stopAnimation();
        } else {
            adventureController.clickAdvanceCutscene();
        }
    }

    public void clickAdvanceCutscene(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickAdvanceCutscene();
        }
    }

    public void clickSkipCutscene() {
        adventureController.clickSkipCutscene();
    }

    public void clickSkipCutscene(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
            clickSkipCutscene();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }
    /******************/
    // endregion

    // region STORY EVENT
    /**STORY EVENT**/
    public void advanceStoryEvent(int[] visuals, String text) {
        for (int i = 0; i < imvStoryEventVisuals.length; i++) {
            if (i < visuals.length) {
                imvStoryEventVisuals[i].setVisibility(View.VISIBLE);
                imvStoryEventVisuals[i].setImageResource(visuals[i]);
            } else {
                imvStoryEventVisuals[i].setVisibility(View.INVISIBLE);
            }
        }
        txtStoryEventText.animate(text);
    }

    public void clickAdvanceStoryEvent() {
        if (txtStoryEventText.getIsRunning()) {
            txtStoryEventText.stopAnimation();
        } else {
            adventureController.clickAdvanceStoryEvent();
        }
    }

    public void clickAdvanceStoryEvent(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickAdvanceStoryEvent();
        }
    }
    /***************/
    // endregion

    // region COMBAT
    /**COMBAT EVENT**/
    public void temp(String str) {
        temp.setText(str);
    } // TODO TEMP

    // party select dialog
    public void startPartySelectDialog() {
        partySelectDialog = new Dialog(this, R.style.dialogFullscreen);
        partySelectDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        partySelectDialog.setContentView(R.layout.dialog_party_select);
        partySelectDialog.setTitle("party select");
        partySelectDialog.setOnKeyListener((dialogInterface, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.KEYCODE_BACK) {
                return true;
            }
            return true;
        });

        lnParty = partySelectDialog.findViewById(R.id.lnParty);
        lnPartySelectDeck1 = partySelectDialog.findViewById(R.id.lnPartySelectDeck1);
        lnPartySelectDeck2 = partySelectDialog.findViewById(R.id.lnPartySelectDeck2);
        btnContinueToCombat = partySelectDialog.findViewById(R.id.btnContinue_combatEventPartySelectDialog);

        enableContinueToCombat(false);

        LinearLayout emptyLayout = getPartyMember("name", R.drawable.placeholder_head);
        emptyLayout.setVisibility(View.INVISIBLE);
        lnParty.addView(emptyLayout);

        Adventure.PartyMembers[] partyMembersArr = Adventure.PartyMembers.values();
        for (int i = 0; i < partyMembersArr.length; i++) {
            if (partyMembersArr[i].unlock) {
                LinearLayout linearLayout = getPartyMember(
                        toCamelCase(partyMembersArr[i].name()),
                        partyMembersArr[i].face
                );
                linearLayout.setOnTouchListener(this::clickPartyMember);
                if (i < 3) {
                    lnPartySelectDeck1.addView(linearLayout);
                } else {
                    lnPartySelectDeck2.addView(linearLayout);
                }
            } else {
                LinearLayout emptyLayout1 = getPartyMember("name", R.drawable.placeholder_head);
                emptyLayout1.setVisibility(View.INVISIBLE);
                if (i < 3) {
                    lnPartySelectDeck1.addView(emptyLayout1);
                } else {
                    lnPartySelectDeck2.addView(emptyLayout1);
                }
            }
        }

        partySelectDialog.show();
    }

    public void enableContinueToCombat(boolean flag) {
        if (flag)  {
            btnContinueToCombat.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown2));
            btnContinueToCombat.setTextColor(ContextCompat.getColor(this, R.color.light_brown));
            btnContinueToCombat.setOnTouchListener(this);
        } else {
            btnContinueToCombat.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));
            btnContinueToCombat.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnContinueToCombat.setOnTouchListener((view, motionEvent) -> true);
        }
    }

    public void updateParty(ArrayList<Adventure.PartyMembers> partyMembersArrayList) {
        lnParty.removeAllViews();
        if (partyMembersArrayList.isEmpty()) {
            LinearLayout emptyLayout = getPartyMember("name", R.drawable.placeholder_head);
            emptyLayout.setVisibility(View.INVISIBLE);
            lnParty.addView(emptyLayout);
            enableContinueToCombat(false);
        } else {
            enableContinueToCombat(true);
        }
        for (Adventure.PartyMembers partyMember : partyMembersArrayList) {
            LinearLayout linearLayout = getPartyMember(toCamelCase(partyMember.name()), partyMember.face);
            lnParty.addView(linearLayout);
            linearLayout.setOnTouchListener(this::clickPickedPartyMember);
        }
        for (int i = 0; i < Adventure.PartyMembers.values().length; i++) {
            for (Adventure.PartyMembers partyMember : partyMembersArrayList) {
                if (i < 3) {
                    if (((TextView)((LinearLayout)lnPartySelectDeck1.getChildAt(i)).getChildAt(1)).getText().toString().toUpperCase().equals(partyMember.name())) {
                        ((LinearLayout)lnPartySelectDeck1.getChildAt(i)).setAlpha(0.5f);
                    }
                } else {
                    if (((TextView)((LinearLayout)lnPartySelectDeck2.getChildAt(i-3)).getChildAt(1)).getText().toString().toUpperCase().equals(partyMember.name())) {
                        ((LinearLayout)lnPartySelectDeck2.getChildAt(i-3)).setAlpha(0.5f);
                    }
                }
            }
        }
    }

    public boolean clickPartyMember(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Adventure.PartyMembers partyMember = Adventure.PartyMembers.valueOf(
                    ((TextView)((LinearLayout)view).getChildAt(1)).getText().toString().toUpperCase()
            );
            adventureController.clickPartyMember(partyMember);
        }
        return true;
    }

    public boolean clickPickedPartyMember(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Adventure.PartyMembers partyMember = Adventure.PartyMembers.valueOf(
                    ((TextView)((LinearLayout)view).getChildAt(1)).getText().toString().toUpperCase()
            );
            adventureController.clickPickedPartyMember(partyMember);
            for (int i = 0; i < Adventure.PartyMembers.values().length; i++) {
                if (i < 3) {
                    if (((TextView)((LinearLayout)lnPartySelectDeck1.getChildAt(i)).getChildAt(1)).getText().toString().toUpperCase().equals(partyMember.name())) {
                        ((LinearLayout)lnPartySelectDeck1.getChildAt(i)).setAlpha(1f);
                    }
                } else {
                    if (((TextView)((LinearLayout)lnPartySelectDeck2.getChildAt(i-3)).getChildAt(1)).getText().toString().toUpperCase().equals(partyMember.name())) {
                        ((LinearLayout)lnPartySelectDeck2.getChildAt(i-3)).setAlpha(1f);
                    }
                }
            }
        }
        return true;
    }

    public LinearLayout getPartyMember(String name,@DrawableRes int face) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 10, 10, 10);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setPadding(15, 15, 15, 15);
        linearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.background_custom));

        ImageView imageView = new ImageView(this);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxWidth(175);
        imageView.setMaxHeight(175);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(face);

        TextView textView = new TextView(this);
        textView.setTextAppearance(R.style.fontFamily);
        textView.setTextColor(ContextCompat.getColor(this, R.color.light_brown));
        textView.setText(name);
        textView.setTextSize(20);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        return linearLayout;
    }

    public void clickContinueToCombat() {
        if (adventureController.clickContinueToCombat()) {
            partySelectDialog.dismiss();
        }
    }

    public void clickContinueToCombat(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickContinueToCombat();
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }
    /****************/
    // endregion

    // region PAUSE DIALOG
    /**PAUSE DIALOG**/
    public void clickPause() {
        createPauseDialog();
    }

    public void clickPause(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.light_brown));
            clickPause();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.getBackground().setTint(ContextCompat.getColor(this, R.color.dark_brown));
        }
    }

    public void createPauseDialog() {
        pauseDialog = new Dialog(this);
        pauseDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pauseDialog.setContentView(R.layout.dialog_pause_menu);
        pauseDialog.getWindow().setBackgroundDrawableResource(R.drawable.background_custom);
        pauseDialog.getWindow().setWindowAnimations(R.style.fadeAnim);
        pauseDialog.setTitle("pause menu");

        btnResume = pauseDialog.findViewById(R.id.btnResume_pauseDialog);
        btnSettings = pauseDialog.findViewById(R.id.btnSettings_pauseDialog);
        btnQuit = pauseDialog.findViewById(R.id.btnQuit_pauseDialog);

        btnResume.setOnTouchListener(this);
        btnSettings.setOnTouchListener(this);
        btnQuit.setOnTouchListener(this);

        pauseDialog.show();
    }

    public void clickResume() {
        pauseDialog.dismiss();
    }

    public void clickResume(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickResume();
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown));
        }
    }

    public void clickSettings() {
        // TODO start settings
    }

    public void clickSettings(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickSettings();
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown));
        }
    }

    public void clickQuit() {
        // TODO save game
        pauseDialog.dismiss();
        startActivity(new Intent(this, MainMenu.class));
        this.finish();
    }

    public void clickQuit(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            clickQuit();
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown2));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.dark_brown));
        }
    }
    /****************/
    // endregion

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.btnPause_choiceEvent:
            case R.id.btnPause_conversationEvent:
            case R.id.btnPause_cutsceneEvent:
            case R.id.btnPause_storyEvent:
            case R.id.btnPause_combatEvent:
                clickPause(view, motionEvent);
                break;
            // pause menu dialog
            case R.id.btnResume_pauseDialog:
                clickResume(view, motionEvent);
                break;
            case R.id.btnSettings_pauseDialog:
                clickSettings(view, motionEvent);
                break;
            case R.id.btnQuit_pauseDialog:
                clickQuit(view, motionEvent);
                break;
            // choice event
            case R.id.btnChoiceOne_choiceEvent:
                clickChoiceOne(view, motionEvent);
                break;
            case R.id.btnChoiceTwo_choiceEvent:
                clickChoiceTwo(view, motionEvent);
                break;
            // transition event
            case R.id.txtTransition_transitionEvent:
                clickContinue(view, motionEvent);
                break;
            // conversation event
            case R.id.btnSkip_conversationEvent:
                clickSkipConversation(view, motionEvent);
                break;
            case R.id.touchListener_conversationEvent:
                clickAdvanceConversation(view, motionEvent);
                break;
            // cutscene event
            case R.id.touchListener_cutsceneEvent:
                clickAdvanceCutscene(view, motionEvent);
                break;
            case R.id.btnSkip_cutsceneEvent:
                clickSkipCutscene(view, motionEvent);
                break;
            // story event
            case R.id.touchListener_storyEvent:
                clickAdvanceStoryEvent(view, motionEvent);
                break;
            // combat event

            // party select dialog
            case R.id.btnContinue_combatEventPartySelectDialog:
                clickContinueToCombat(view, motionEvent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
