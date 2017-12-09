package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static ingenuity.com.beloteeasyscore.EventsHelper.*;
import static ingenuity.com.beloteeasyscore.EventsHelper.team.*;

public class ScoreHelper {

    private static final String LogTag = "BeloteEasyScore: 4";
    private Context mContext;
    private Activity mActivity;

    ScoreHelper (Context _Context) {
        Log.d(LogTag, "ScoreHelper called");
        mContext = _Context;
        mActivity = (Activity) mContext;
    }

    void addNumericInputScore() {
        Log.d(LogTag, "addNumericInputScore called" );
        TextView inputNumberText =(TextView) mActivity.findViewById(R.id.numericInputText);
        String inputNumberString = (String) inputNumberText.getText();
        if (inputNumberString.length() < 1) {
            Log.d(LogTag, "Input number is empty" );
            clearLastInput(inputNumberText);
            return;
        }
        calculateAndUpdateScore(inputNumberString);
        clearLastInput(inputNumberText);
    }

    private void calculateAndUpdateScore(String _score) {
        String rightTeamScore = null;
        String leftTeamScore = null;
        if (getCurrentSelectedTeam() == RIGHT_TEAM ) {
            rightTeamScore = _score;
            leftTeamScore  = calculateLooserScore(_score);
        }
        if (getCurrentSelectedTeam() == LEFT_TEAM ) {
            rightTeamScore = calculateLooserScore(_score);
            leftTeamScore  = _score;
        }
        addRawScoreTable(leftTeamScore,rightTeamScore);
        updateGlobalScore(RIGHT_TEAM,Integer.valueOf(rightTeamScore));
        updateGlobalScore(LEFT_TEAM,Integer.valueOf(leftTeamScore));
        updateGlobalScoreText();
    }

    private String calculateLooserScore(String _score) {
        int looserScore = 0;
        int winnerScore = Integer.valueOf(_score);
        looserScore = 180 - winnerScore; //TODO Find Algorithm to calculate correct score
        return String.valueOf(looserScore);
    }

    private void updateGlobalScoreText(){
        TextView scoreLeftTeamText =(TextView) mActivity.findViewById(R.id.scoreTeam1);
        TextView scoreRightTeamText =(TextView) mActivity.findViewById(R.id.scoreTeam2);
        scoreLeftTeamText.setText(getGlobalScoreString(LEFT_TEAM));
        scoreRightTeamText.setText(getGlobalScoreString(RIGHT_TEAM));
    }

    private void clearLastInput(TextView _TextView) {
        Log.d(LogTag, "clearLastInput called" );
        _TextView.setVisibility(View.INVISIBLE);
        _TextView.setText("");
    }

    private void addRawScoreTable(String _leftTeamScore, String _rightTeamScore) {
        Log.d(LogTag, "addNewResult called" );
        TableLayout table = (TableLayout) mActivity.findViewById(R.id.scoreTable);
        ScrollView scrollView = (ScrollView) mActivity.findViewById(R.id.scrollView);
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.weight = 1;
        TableRow row = new TableRow(mContext);
        addColumnScoreTable("",row, tableRowParams);
        addColumnScoreTable(_leftTeamScore,row, tableRowParams);
        addColumnScoreTable("             ",row, tableRowParams);
        addColumnScoreTable(_rightTeamScore,row, tableRowParams);
        table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void addColumnScoreTable(String _Text, TableRow _row, TableRow.LayoutParams _tableRowParams) {
        TextView _textview = new TextView(mContext);
        _textview.setTextAppearance(mContext, R.style.littleScoreTextLeft);
        _textview.setText(String.valueOf(_Text));
        _row.addView(_textview, _tableRowParams);
    }

    public void initializeDummyScoreTable() {
        Log.d(LogTag, "initializeScoreTable called");
        TableLayout table = (TableLayout) mActivity.findViewById(R.id.scoreTable);
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.weight = 1;
        int counter = 0;
        for(counter = 1 ; counter < 50; counter ++) {
            TableRow row = new TableRow(mContext);
            TextView textEmptyLeft = new TextView(mContext);
            textEmptyLeft.setTextAppearance(mContext, R.style.littleScoreTextLeft);
            textEmptyLeft.setText("");
            row.addView(textEmptyLeft, tableRowParams);

            TextView textLeft = new TextView(mContext);
            textLeft.setTextAppearance(mContext, R.style.littleScoreTextLeft);
            textLeft.setText(String.valueOf(counter));
            row.addView(textLeft, tableRowParams);

            TextView textEmptyRight = new TextView(mContext);
            textEmptyRight.setTextAppearance(mContext, R.style.littleScoreTextLeft);
            textEmptyRight.setText("             ");
            row.addView(textEmptyRight, tableRowParams);

            TextView textRight = new TextView(mContext);
            textRight.setTextAppearance(mContext, R.style.littleScoreTextRight);
            textRight.setText(String.valueOf(counter));
            row.addView(textRight, tableRowParams);

            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
