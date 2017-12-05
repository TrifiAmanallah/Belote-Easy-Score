package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class ScoreActivity extends Activity {

    private BoomMenuButton rightScoreAdder;
    private BoomMenuButton leftScoreAdder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        IntentFilter onBoomClickedFilter = new IntentFilter("com.nightonke.boommenu.onBoomClicked");
        IntentFilter onBoomBackgroundClickFilter = new IntentFilter("com.nightonke.boommenu.onBoomBackgroundClick");
        IntentFilter onBoomWillHideFilter = new IntentFilter("com.nightonke.boommenu.onBoomWillHide");
        IntentFilter onBoomDidHideFilter = new IntentFilter("com.nightonke.boommenu.onBoomDidHide");
        IntentFilter onBoomWillShowFilter = new IntentFilter("com.nightonke.boommenu.onBoomWillShow");
        IntentFilter onBoomDidShowFilter = new IntentFilter("com.nightonke.boommenu.onBoomDidShow");
        registerReceiver(onBoomClickedReceiver, onBoomClickedFilter);
        registerReceiver(onBoomBackgroundClickReceiver, onBoomBackgroundClickFilter);
        registerReceiver(onBoomWillHideReceiver, onBoomWillHideFilter);
        registerReceiver(onBoomDidHideReceiver, onBoomDidHideFilter);
        registerReceiver(onBoomWillShowReceiver, onBoomWillShowFilter);
        registerReceiver(onBoomDidShowReceiver, onBoomDidShowFilter);
        initializeAddScoreButtons();
        initializeScoreTable();
    }

    private void initializeAddScoreButtons() {
        rightScoreAdder = (BoomMenuButton) findViewById(R.id.rightScoreAdder);
        rightScoreAdder.setButtonEnum(ButtonEnum.Ham);
        rightScoreAdder.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        rightScoreAdder.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        rightScoreAdder.addBuilder(BuilderManager.getHamButtonBuilder());
        initializeInputMenu(rightScoreAdder);

        leftScoreAdder = (BoomMenuButton) findViewById(R.id.leftScoreAdder);
        leftScoreAdder.setButtonEnum(ButtonEnum.Ham);
        leftScoreAdder.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        leftScoreAdder.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        leftScoreAdder.addBuilder(BuilderManager.getHamButtonBuilder());
        initializeInputMenu(leftScoreAdder);
    }

    private void setInputMethodeNumeric() {
        rightScoreAdder = (BoomMenuButton) findViewById(R.id.rightScoreAdder);
        rightScoreAdder.setButtonEnum(ButtonEnum.SimpleCircle);
        rightScoreAdder.setPiecePlaceEnum(PiecePlaceEnum.DOT_10_1);
        rightScoreAdder.setButtonPlaceEnum(ButtonPlaceEnum.SC_10_1);
        rightScoreAdder.setAutoHide(false);
        initializeNumericInput(rightScoreAdder);

        leftScoreAdder = (BoomMenuButton) findViewById(R.id.leftScoreAdder);
        leftScoreAdder.setButtonEnum(ButtonEnum.SimpleCircle);
        leftScoreAdder.setPiecePlaceEnum(PiecePlaceEnum.DOT_10_1);
        leftScoreAdder.setButtonPlaceEnum(ButtonPlaceEnum.SC_10_1);
        leftScoreAdder.setAutoHide(false);
        initializeNumericInput(leftScoreAdder);
    }

    private void initializeInputMenu(BoomMenuButton localScoreAdder) {
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
                        Toast.makeText(ScoreActivity.this, "Score Gird Input selected", Toast.LENGTH_SHORT).show();
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
                        setInputMethodeNumeric();
                        Toast.makeText(ScoreActivity.this, "Numeric Input selected", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ScoreActivity.this, "Voice Feature is Not Free", Toast.LENGTH_SHORT).show();
                    }
                });
        localScoreAdder.addBuilder(builder);

        //Input Methode camer
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
                        Toast.makeText(ScoreActivity.this, "Camera Feature is not Free", Toast.LENGTH_SHORT).show();
                    }
                });
        localScoreAdder.addBuilder(builder);
    }

    private void initializeNumericInput(BoomMenuButton localNumericInput) {
        localNumericInput.clearBuilders();

        for (int i = 0; i < localNumericInput.getPiecePlaceEnum().pieceNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                    .normalImageRes(BuilderManager.getNumericImageResource(i))
                    .normalColor(Color.WHITE)
                    .highlightedColor(Color.GRAY)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Toast.makeText(ScoreActivity.this, "Pressed = " + (index + 1), Toast.LENGTH_SHORT).show();
                            setNumericInputText(index);
                        }
                    });
            localNumericInput.addBuilder(builder);
        }
    }

    @Override
    public void onBackPressed(){
        TextView inputNumberText =(TextView)findViewById(R.id.numericInputText);
        String inputNumberString = (String) inputNumberText.getText();
        if(inputNumberString.length()>0) {
            inputNumberString = inputNumberString.substring(0, inputNumberString.length() - 1);
            inputNumberText.setVisibility(View.VISIBLE);
            inputNumberText.setText(inputNumberString);
        }
    }

    private void setNumericInputText(int index) {
        int inputNumber = index + 1;
        if (inputNumber > 9) inputNumber = 0;
        if (inputNumber < 0) inputNumber = 0;
        TextView inputNumberText =(TextView)findViewById(R.id.numericInputText);
        inputNumberText.setVisibility(View.VISIBLE);
        String inputNumberString = (String) inputNumberText.getText();
        if(inputNumberString.length()<3) {
            inputNumberString = inputNumberString + String.valueOf(inputNumber);
            inputNumberText.setText(inputNumberString);
        }
    }

    private void initializeScoreTable() {
        TableLayout table = (TableLayout) findViewById(R.id.scoreTable);
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.weight = 1;
        int counter = 0;
        for(counter = 150 ; counter < 250; counter ++) {
            // create a new TableRow
            TableRow row = new TableRow(this);

            TextView textEmptyLeft = new TextView(this);
            textEmptyLeft.setTextAppearance(this, R.style.littleScoreTextLeft);
            textEmptyLeft.setText("");

            row.addView(textEmptyLeft, tableRowParams);

            // create a new TextView
            TextView textLeft = new TextView(this);
            textLeft.setTextAppearance(this, R.style.littleScoreTextLeft);
            textLeft.setText(String.valueOf(counter));
            row.addView(textLeft, tableRowParams);

            TextView textEmptyRight = new TextView(this);
            textEmptyRight.setTextAppearance(this, R.style.littleScoreTextLeft);
            textEmptyRight.setText("             ");
            row.addView(textEmptyRight, tableRowParams);


            TextView textRight = new TextView(this);
            textRight.setTextAppearance(this, R.style.littleScoreTextRight);
            textRight.setText(String.valueOf(counter));
            row.addView(textRight, tableRowParams);

            // add the TableRow to the TableLayout
            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    BroadcastReceiver onBoomClickedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        }
    };

    BroadcastReceiver onBoomBackgroundClickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        }
    };

    BroadcastReceiver onBoomWillHideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
    BroadcastReceiver onBoomDidHideReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(ScoreActivity.this, "received onBoomDidHide", Toast.LENGTH_SHORT).show();
            TextView inputNumberText =(TextView)findViewById(R.id.numericInputText);
            inputNumberText.setVisibility(View.INVISIBLE);
        }
    };

    BroadcastReceiver onBoomWillShowReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    BroadcastReceiver onBoomDidShowReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
}
