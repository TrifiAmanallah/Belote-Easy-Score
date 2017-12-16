package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class OpeningMenu extends Activity {

    private Context mContext = null;
    private Activity mActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = (Activity) mContext;
        setContentView(R.layout.activity_opening_menu);
        initializeButtonsListners();
    }

    private void newTableMenuSelected() {
        Intent _Activity = new Intent(mActivity, ScoreActivity.class);
        startActivity(_Activity);
    }

    private void myFriendsMenuSelected() {
        Intent _Activity = new Intent(mActivity, FriendsMenu.class);
        startActivity(_Activity);
    }

    private void myStatsMenuSelected() {
        Toast.makeText(mContext, "myStatsMenu", Toast.LENGTH_SHORT).show();
    }

    private void mySettingsMenuSelected() {
        Toast.makeText(mContext, "mySettingsMenu", Toast.LENGTH_SHORT).show();
    }

    private void initializeButtonsListners() {
        ImageButton newTableButton = (ImageButton) mActivity.findViewById(R.id.newTable);
        newTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTableMenuSelected();
            }
        });

        ImageButton myFriends = (ImageButton) mActivity.findViewById(R.id.myFriends);
        myFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFriendsMenuSelected();
            }
        });

        ImageButton myStats = (ImageButton) mActivity.findViewById(R.id.myStats);
        myStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStatsMenuSelected();
            }
        });

        ImageButton mySettings = (ImageButton) mActivity.findViewById(R.id.mySettings);
        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySettingsMenuSelected();
            }
        });
    }
}
