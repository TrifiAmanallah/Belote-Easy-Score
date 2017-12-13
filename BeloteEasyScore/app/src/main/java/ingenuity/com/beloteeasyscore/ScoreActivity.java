package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import ingenuity.com.beloteeasyscore.tools.DownloadImage;

public class ScoreActivity extends Activity {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "ScoreActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LogTag, SubLogTag + "onCreate called");
        setContentView(R.layout.activity_score);
        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();
        InputMenu mInputMenu = new InputMenu(this);

        TextView player1Team1 = (TextView)findViewById(R.id.player1Team1);
        player1Team1.setText(""+name+" "+surname);
        new DownloadImage((ImageView) findViewById(R.id.player1Team1Picture)).execute(imageUrl);

        TextView player2Team1 = (TextView)findViewById(R.id.player2Team1);
        player2Team1.setText(""+name+" "+surname);
        new DownloadImage((ImageView) findViewById(R.id.player2Team1Picture)).execute(imageUrl);

        TextView player1Team2 = (TextView)findViewById(R.id.player1Team2);
        player1Team2.setText(""+name+" "+surname);
        new DownloadImage((ImageView) findViewById(R.id.player1Team2Picture)).execute(imageUrl);

        TextView player2Team2 = (TextView)findViewById(R.id.player2Team2);
        player2Team2.setText(""+name+" "+surname);
        new DownloadImage((ImageView) findViewById(R.id.player2Team2Picture)).execute(imageUrl);

        //Intent intent = new Intent(this, OpeningMenu.class);
        //Intent intent = new Intent(this, ConnectionMenu.class);
        //startActivity(intent);
    }

}
