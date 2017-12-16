package ingenuity.com.beloteeasyscore.FacebookTools;

import java.util.ArrayList;

import ingenuity.com.beloteeasyscore.FacebookTools.model.FriendItemData;

public interface FriendsListView {
    void initializeView();

    void loadFriendsList(ArrayList<FriendItemData> friendsList);

    void showError();
}
