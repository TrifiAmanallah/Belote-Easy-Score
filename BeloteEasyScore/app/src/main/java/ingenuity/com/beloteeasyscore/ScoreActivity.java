package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        int counter = 0;
        // get a reference for the TableLayout
        TableLayout table = (TableLayout) findViewById(R.id.scoreTable);

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.weight = 1;

        for(counter = 150 ; counter < 250; counter ++) {
            // create a new TableRow
            TableRow row = new TableRow(this);

            TextView textEmptyLeft = new TextView(this);
            textEmptyLeft.setTextAppearance(this, R.style.littleScoreTextLeft);
            textEmptyLeft.setText( "" );
            row.addView(textEmptyLeft, tableRowParams);

            // create a new TextView
            TextView textLeft = new TextView(this);
            textLeft.setTextAppearance(this, R.style.littleScoreTextLeft);
            textLeft.setText(  String.valueOf(counter) );
            row.addView(textLeft, tableRowParams);

            TextView textEmptyRight = new TextView(this);
            textEmptyRight.setTextAppearance(this, R.style.littleScoreTextLeft);
            textEmptyRight.setText( "             " );
            row.addView(textEmptyRight, tableRowParams);




            TextView textRight = new TextView(this);
            textRight.setTextAppearance(this, R.style.littleScoreTextRight);
            textRight.setText( String.valueOf(counter) );
            row.addView(textRight, tableRowParams);

            // add the TableRow to the TableLayout
            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        }
    }
}
