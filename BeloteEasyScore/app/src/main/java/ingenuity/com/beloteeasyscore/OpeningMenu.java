package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class OpeningMenu extends Activity {

    private Context mContext = null;
    Activity mActivity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = (Activity) mContext;
        setContentView(R.layout.activity_opening_menu);
        initializeButtonsListners();
    }

    private void initializeButtonsListners() {
        ImageButton newTableButton = (ImageButton) mActivity.findViewById(R.id.newTable);
        newTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "newTableButton", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton myFriends = (ImageButton) mActivity.findViewById(R.id.myFriends);
        myFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "myFriends", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton myStats = (ImageButton) mActivity.findViewById(R.id.myStats);
        myStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "myStats", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton mySettings = (ImageButton) mActivity.findViewById(R.id.mySettings);
        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "mySettings", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
