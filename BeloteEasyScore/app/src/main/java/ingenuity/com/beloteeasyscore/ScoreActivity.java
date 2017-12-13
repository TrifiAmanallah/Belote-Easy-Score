package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;

public class ScoreActivity extends Activity {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "ScoreActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LogTag, SubLogTag + "onCreate called");
        //setContentView(R.layout.activity_score);
        //InputMenu mInputMenu = new InputMenu(this);
        Intent intent = new Intent(this, OpeningMenu.class);
        startActivity(intent);
    }

}
