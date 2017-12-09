package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;

public class ScoreActivity extends Activity {

    private static final String LogTag = "BeloteEasyScore 1:";
    private BoomMenuButton rightScoreAdder;
    private BoomMenuButton leftScoreAdder;
    private TableLayout table = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LogTag, "onCreate called");
        setContentView(R.layout.activity_score);
        InputMenu mInputMenu = new InputMenu(this);

    }

    @Override //TODO Find solution to delete input number
    public void onBackPressed(){
        Log.d(LogTag, "onBackPressed called");
        TextView inputNumberText =(TextView)findViewById(R.id.numericInputText);
        String inputNumberString = (String) inputNumberText.getText();
        if(inputNumberString.length()>0) {
            inputNumberString = inputNumberString.substring(0, inputNumberString.length() - 1);
            inputNumberText.setVisibility(View.VISIBLE);
            inputNumberText.setText(inputNumberString);
        }
    }

}
