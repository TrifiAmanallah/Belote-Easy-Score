package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import static ingenuity.com.beloteeasyscore.EventsHelper.*;
import static ingenuity.com.beloteeasyscore.EventsHelper.events.*;
import static ingenuity.com.beloteeasyscore.EventsHelper.team.*;

class NumericInput {

    private static final String LogTag = "BeloteEasyScore: 2";
    private Context mContext = null;
    private Activity mActivity = null;
    private ScoreHelper mScoreHelper = null;

    NumericInput(Context _Context) {
        Log.d(LogTag, "NumericInput called");
        mContext = _Context;
        mActivity = (Activity) mContext;
        mScoreHelper = new ScoreHelper(_Context);
        registerReceivers();
        setInputMethodeNumeric ();
    }

    private void setInputMethodeNumeric () {
        Log.d(LogTag, "setInputMethodeNumeric called");
        BoomMenuButton rightScoreAdder = (BoomMenuButton) mActivity.findViewById(R.id.rightScoreAdder);
        BoomMenuButton leftScoreAdder  = (BoomMenuButton) mActivity.findViewById(R.id.leftScoreAdder);
        initializeNumericInput(rightScoreAdder,RIGHT_TEAM);
        initializeNumericInput(leftScoreAdder,LEFT_TEAM);
    }

    private void initializeNumericInput( BoomMenuButton mBoomMenuButton,team _team) {
        Log.d(LogTag, "initializeNumericInput called");
        mBoomMenuButton.setButtonEnum(ButtonEnum.SimpleCircle);
        mBoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_12_1);
        mBoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_12_1);
        mBoomMenuButton.setAutoHide(false);
        initializeNumericBoomMenuButton(mBoomMenuButton,_team);
    }

    private void initializeNumericBoomMenuButton(final BoomMenuButton localNumericInput,final team _team) {
        Log.d(LogTag, "initializeNumericBoomMenuButton called");
        localNumericInput.clearBuilders();

        for (int i = 0; i < localNumericInput.getPiecePlaceEnum().pieceNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                    .normalImageRes(BuilderManager.getNumericImageResource(i))
                    .normalColor(Color.WHITE)
                    .highlightedColor(Color.GRAY)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Log.d(LogTag,"onBoomButtonClick Pressed = " + (index + 1));
                            OnNumberClicked(_team, index);
                        }
                    });
            localNumericInput.addBuilder(builder);
        }
    }

    private void OnNumberClicked(team _team, int _index) {
        Log.d(LogTag, "OnNumberClicked called");
        setCurrentSelectedTeam(_team);
        setNumericInputText(_index);
    }

    private void setNumericInputText(int index) {
        Log.d(LogTag, "setNumericInputText called");
        int inputNumber = index + 1;
        if (inputNumber == 10) {
            deleteAllInput();
            return;
        }
        if (inputNumber == 12) {
            deleteLastInput();
            return;
        }
        if (inputNumber > 9) inputNumber = 0;
        if (inputNumber < 0) inputNumber = 0;

        TextView inputNumberText =(TextView) mActivity.findViewById(R.id.numericInputText);
        inputNumberText.setVisibility(View.VISIBLE);
        String inputNumberString = (String) inputNumberText.getText();
        if(inputNumberString.length()<3 && !(inputNumberString.length()<1 && inputNumber == 0)) {
            inputNumberString = inputNumberString + String.valueOf(inputNumber);
            inputNumberText.setText(inputNumberString);
        }
    }

    private void deleteLastInput() {
        TextView inputNumberText =(TextView) mActivity.findViewById(R.id.numericInputText);
        String inputNumberString = (String) inputNumberText.getText();
        if(inputNumberString.length()>0) {
            inputNumberString = inputNumberString.substring(0, inputNumberString.length() - 1);
            inputNumberText.setVisibility(View.VISIBLE);
            inputNumberText.setText(inputNumberString);
        }
    }

    private void deleteAllInput() {
        TextView inputNumberText =(TextView) mActivity.findViewById(R.id.numericInputText);
        inputNumberText.setText("");
    }

    private void registerReceivers() {
        IntentFilter onBoomClickedFilter = new IntentFilter("com.nightonke.boommenu.onBoomClicked");
        IntentFilter onBoomBackgroundClickFilter = new IntentFilter("com.nightonke.boommenu.onBoomBackgroundClick");
        IntentFilter onBoomWillHideFilter = new IntentFilter("com.nightonke.boommenu.onBoomWillHide");
        IntentFilter onBoomDidHideFilter = new IntentFilter("com.nightonke.boommenu.onBoomDidHide");
        IntentFilter onBoomWillShowFilter = new IntentFilter("com.nightonke.boommenu.onBoomWillShow");
        IntentFilter onBoomDidShowFilter = new IntentFilter("com.nightonke.boommenu.onBoomDidShow");
        mContext.registerReceiver(onBoomClickedReceiver, onBoomClickedFilter);
        mContext.registerReceiver(onBoomBackgroundClickReceiver, onBoomBackgroundClickFilter);
        mContext.registerReceiver(onBoomWillHideReceiver, onBoomWillHideFilter);
        mContext.registerReceiver(onBoomDidHideReceiver, onBoomDidHideFilter);
        mContext.registerReceiver(onBoomWillShowReceiver, onBoomWillShowFilter);
        mContext.registerReceiver(onBoomDidShowReceiver, onBoomDidShowFilter);
    }

    private BroadcastReceiver onBoomClickedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, "onBoomClicked Received");
        }
    };

    private BroadcastReceiver onBoomBackgroundClickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, "onBoomBackgroundClick Received");
        }
    };

    private BroadcastReceiver onBoomWillHideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, "onBoomWillHide Received");
        }
    };

    private BroadcastReceiver onBoomDidHideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, "onBoomDidHide Received" );
            if (getcurrentEvent() == NUMERIC_INPUTS_SELECTED_DISPLAYED) {
                setcurrentEvent(NUMERIC_INPUTS_SELECTED_NOT_DISPLAYED);
                mScoreHelper.addNumericInputScore();
            }
        }
    };

    private BroadcastReceiver onBoomWillShowReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, "onBoomWillShow Received");
            Log.d(LogTag, "Adding Score to: " + getTeamName(getCurrentSelectedTeam()));
        }
    };

    private BroadcastReceiver onBoomDidShowReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LogTag, "onBoomDidShow Received");
            if (getcurrentEvent() == NUMERIC_INPUTS_SELECTED_NOT_DISPLAYED) setcurrentEvent(NUMERIC_INPUTS_SELECTED_DISPLAYED);
        }
    };
}
