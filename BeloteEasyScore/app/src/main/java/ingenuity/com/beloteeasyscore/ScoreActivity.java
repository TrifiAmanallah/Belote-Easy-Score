package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import ingenuity.com.beloteeasyscore.ImageTools.DownloadImage;

import static ingenuity.com.beloteeasyscore.EventsHelper.*;
import static ingenuity.com.beloteeasyscore.EventsHelper.player.PLAYER_1_RIGHT_TEAM;
import static ingenuity.com.beloteeasyscore.EventsHelper.player.PLAYER_2_LEFT_TEAM;
import static ingenuity.com.beloteeasyscore.EventsHelper.player.PLAYER_2_RIGHT_TEAM;

public class ScoreActivity extends Activity {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "ScoreActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LogTag, SubLogTag + "onCreate called");
        setContentView(R.layout.activity_score);
        Bundle inBundle = getIntent().getExtras();
        InputMenu mInputMenu = new InputMenu(this);
        String _pictureUrl = null;

        TextView player1Team1 = (TextView)findViewById(R.id.player1Team1);
        player1Team1.setText(""+getUserName()+" "+getUserSurname());
        _pictureUrl = getUserPictureUrl();
        if (_pictureUrl != null)
        new DownloadImage((ImageView) findViewById(R.id.player1Team1Picture)).execute(_pictureUrl);

        TextView player2Team1 = (TextView)findViewById(R.id.player2Team1);
        player2Team1.setText(""+getPlayerName(PLAYER_2_LEFT_TEAM));
        _pictureUrl = getPlayerPictureUrl(PLAYER_2_LEFT_TEAM);
        if (_pictureUrl != null)
        new DownloadImage((ImageView) findViewById(R.id.player2Team1Picture)).execute(_pictureUrl);


        TextView player1Team2 = (TextView)findViewById(R.id.player1Team2);
        player1Team2.setText(""+getPlayerName(PLAYER_1_RIGHT_TEAM));
        _pictureUrl = getPlayerPictureUrl(PLAYER_1_RIGHT_TEAM);
        if (_pictureUrl != null)
        new DownloadImage((ImageView) findViewById(R.id.player1Team2Picture)).execute(_pictureUrl);


        TextView player2Team2 = (TextView)findViewById(R.id.player2Team2);
        player2Team2.setText(""+getPlayerName(PLAYER_2_RIGHT_TEAM));
        _pictureUrl = getPlayerPictureUrl(PLAYER_2_RIGHT_TEAM);
        if (_pictureUrl != null)
        new DownloadImage((ImageView) findViewById(R.id.player2Team2Picture)).execute(_pictureUrl);

        //Intent intent = new Intent(this, OpeningMenu.class);
        //Intent intent = new Intent(this, ConnectionMenu.class);
        //startActivity(intent);
    }

}
