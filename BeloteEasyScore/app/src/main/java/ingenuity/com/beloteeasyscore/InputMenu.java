package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import static ingenuity.com.beloteeasyscore.EventsHelper.events.*;
import static ingenuity.com.beloteeasyscore.EventsHelper.*;
import static ingenuity.com.beloteeasyscore.EventsHelper.team.*;

class InputMenu {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "InputMenu: ";
    private Context mContext = null;
    Activity mActivity = null;

    InputMenu(Context _Context) {
        Log.d(LogTag, SubLogTag + "NumericInput called");
        mContext = _Context;
        mActivity = (Activity) mContext;
        registerReceivers();
        BoomMenuButton rightScoreAdder = (BoomMenuButton) mActivity.findViewById(R.id.rightScoreAdder);
        BoomMenuButton leftScoreAdder = (BoomMenuButton) mActivity.findViewById(R.id.leftScoreAdder);
        createAddScoreButtons(rightScoreAdder);
        createAddScoreButtons(leftScoreAdder);
    }

    private void createAddScoreButtons(BoomMenuButton _BoomMenuButton) {
        Log.d(LogTag, SubLogTag + "createAddScoreButtons called");
        _BoomMenuButton.setButtonEnum(ButtonEnum.Ham);
        _BoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        _BoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        _BoomMenuButton.setAutoHide(true);
        _BoomMenuButton.addBuilder(BuilderManager.getHamButtonBuilder());
        createInputsList(_BoomMenuButton);
    }

    private void createInputsList(BoomMenuButton localScoreAdder) {
        Log.d(LogTag, SubLogTag + "createInputsList called");
        localScoreAdder.clearBuilders();
        //Input Methode Gird
        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(R.drawable.input_gird)
                .normalTextRes(R.string.input_methode_gird)
                .subNormalTextRes(R.string.input_methode_gird_sub_text)
                .normalTextColor(Color.BLUE)
                .subNormalTextColor(Color.BLACK)
                .normalColor(Color.WHITE)
                .highlightedColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //if (getcurrentEvent() == SELECT_INPUT_MENU_DISPLAYED) setcurrentEvent(GIRD_INPUTS_SELECTED);
                        Toast.makeText(mContext, "Score Gird Input not yet implemented", Toast.LENGTH_SHORT).show();
                    }
                });
        localScoreAdder.addBuilder(builder);

        //Input Methode Numeric
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.input_calculatrice)
                .normalTextRes(R.string.input_methode_calculator)
                .subNormalTextRes(R.string.input_methode_calculator_sub_text)
                .normalTextColor(Color.BLUE)
                .subNormalTextColor(Color.BLACK)
                .normalColor(Color.WHITE)
                .highlightedColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        if (getcurrentEvent() == SELECT_INPUT_MENU_DISPLAYED) setcurrentEvent(NUMERIC_INPUTS_SELECTED_NOT_DISPLAYED);
                        NumericInput mNumericInput = new NumericInput(mContext);
                        Toast.makeText(mContext, "Numeric Input selected", Toast.LENGTH_SHORT).show();
                    }
                });
        localScoreAdder.addBuilder(builder);

        //Input Methode Voice
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.input_voice)
                .normalTextRes(R.string.input_methode_voice)
                .subNormalTextRes(R.string.input_methode_voice_sub_text)
                .normalTextColor(Color.LTGRAY)
                .subNormalTextColor(Color.BLACK)
                .normalColor(Color.WHITE)
                .highlightedColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //if (getcurrentEvent() == SELECT_INPUT_MENU_DISPLAYED) setcurrentEvent(VOICE_INPUTS_SELECTED);
                        Toast.makeText(mContext, "Voice Feature is Not Free", Toast.LENGTH_SHORT).show();
                    }
                });
        localScoreAdder.addBuilder(builder);

        //Input Methode camera
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.input_camera)
                .normalTextRes(R.string.input_methode_camera)
                .subNormalTextRes(R.string.input_methode_camera_sub_text)
                .normalTextColor(Color.LTGRAY)
                .subNormalTextColor(Color.BLACK)
                .normalColor(Color.WHITE)
                .highlightedColor(Color.BLUE)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        //if (getcurrentEvent() == SELECT_INPUT_MENU_DISPLAYED)setcurrentEvent(CAMERA_INPUTS_SELECTED);
                        Toast.makeText(mContext, "Camera Feature is not Free", Toast.LENGTH_SHORT).show();
                    }
                });
        localScoreAdder.addBuilder(builder);
    }

    private void changeInputMethode() {
        BoomMenuButton rightScoreAdder = (BoomMenuButton) mActivity.findViewById(R.id.rightScoreAdder);
        BoomMenuButton leftScoreAdder = (BoomMenuButton) mActivity.findViewById(R.id.leftScoreAdder);
        createAddScoreButtons(rightScoreAdder);
        createAddScoreButtons(leftScoreAdder);
        /*if(getCurrentSelectedTeam() == LEFT_TEAM) {
            leftScoreAdder.boom();
        }
        if(getCurrentSelectedTeam() == team.RIGHT_TEAM) {
            rightScoreAdder.boomImmediately();
        }*/
    }

    private void registerReceivers() {
        IntentFilter onBoomClickedFilter = new IntentFilter("com.nightonke.boommenu.onBoomClicked");
        IntentFilter onBoomBackgroundClickFilter = new IntentFilter("com.nightonke.boommenu.onBoomBackgroundClick");
        IntentFilter onBoomWillHideFilter = new IntentFilter("com.nightonke.boommenu.onBoomWillHide");
        IntentFilter onBoomDidHideFilter = new IntentFilter("com.nightonke.boommenu.onBoomDidHide");
        IntentFilter onBoomWillShowFilter = new IntentFilter("com.nightonke.boommenu.onBoomWillShow");
        IntentFilter onBoomDidShowFilter = new IntentFilter("com.nightonke.boommenu.onBoomDidShow");
        IntentFilter onBoomLongClickFilter = new IntentFilter("com.nightonke.boommenu.onLongClick");

        mContext.registerReceiver(onBoomClickedReceiver, onBoomClickedFilter);
        mContext.registerReceiver(onBoomBackgroundClickReceiver, onBoomBackgroundClickFilter);
        mContext.registerReceiver(onBoomWillHideReceiver, onBoomWillHideFilter);
        mContext.registerReceiver(onBoomDidHideReceiver, onBoomDidHideFilter);
        mContext.registerReceiver(onBoomWillShowReceiver, onBoomWillShowFilter);
        mContext.registerReceiver(onBoomDidShowReceiver, onBoomDidShowFilter);
        mContext.registerReceiver(onBoomLongClickReceiver, onBoomLongClickFilter);
    }

    private BroadcastReceiver onBoomClickedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, SubLogTag + "onBoomClicked received");
        }
    };

    private BroadcastReceiver onBoomBackgroundClickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, SubLogTag + "onBoomBackgroundClick received");
        }
    };

    private BroadcastReceiver onBoomWillHideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, SubLogTag + "onBoomWillHide received");
        }
    };

    private BroadcastReceiver onBoomDidHideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, SubLogTag + "onBoomDidHide received");
            if (getcurrentEvent() == SELECT_INPUT_MENU_DISPLAYED) setcurrentEvent(FIRST_LAUNCH);
        }
    };

    private BroadcastReceiver onBoomWillShowReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, SubLogTag + "onBoomWillShow received");
        }
    };

    private BroadcastReceiver onBoomDidShowReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, SubLogTag + "onBoomDidShow received");
            if (getcurrentEvent() == FIRST_LAUNCH) setcurrentEvent(SELECT_INPUT_MENU_DISPLAYED);
        }
    };

    private BroadcastReceiver onBoomLongClickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, SubLogTag + "onBoomLongClick Received");
            if (getcurrentEvent() == NUMERIC_INPUTS_SELECTED_NOT_DISPLAYED) setcurrentEvent(SELECT_INPUT_MENU_DISPLAYED);
            changeInputMethode();
        }
    };
}
