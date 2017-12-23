package ingenuity.com.beloteeasyscore.FacebookTools;



import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ingenuity.com.beloteeasyscore.FacebookTools.model.FriendItemData;
import ingenuity.com.beloteeasyscore.FacebookTools.model.FriendsListResponse;
import ingenuity.com.beloteeasyscore.FacebookTools.service.FacebookFriendList;
import retrofit2.Call;
import retrofit2.Callback;

public class FriendsListPresenter {
    private final static int PAGE_SIZE = 25;
    private String userId;
    private AccessToken fbToken;
    private FriendsListView view;
    private ArrayList<FriendItemData> friendsList = new ArrayList<FriendItemData>();
    private String nextPageId;
    private String previousPageId;
    private boolean isLoadingMore = false;
    private boolean isFullLoading = true;


    public FriendsListPresenter(FriendsListView view) {
        this.view = view;
        view.initializeView();
    }

    public void onGetFBFriendsList() {
        fbToken = AccessToken.getCurrentAccessToken();
        if(fbToken != null) {
            userId = fbToken.getUserId();
            getFBFriendsList(userId, fbToken.getToken(), PAGE_SIZE, nextPageId, friendsListCallback);
        }else {
            view.showError();
        }
    }

    public void onLoadMore(int totalItemsCount, int visibleItemsCount, int firstVisibleItemPosition) {
        if ((nextPageId != null) && !isLoadingMore && (visibleItemsCount + firstVisibleItemPosition >= totalItemsCount)) {
            //loadMore
            isLoadingMore = true;
            getFBFriendsList(userId, fbToken.getToken(), PAGE_SIZE, nextPageId, friendsListCallback);
        }
    }

    private void loadAll() {
        if (nextPageId != null){
            isFullLoading = true;
            final Timer timerAsync = new Timer();
            TimerTask timerTaskAsync = new TimerTask() {
                @Override
                public void run() {
                    if ((nextPageId != null) && !isLoadingMore) {
                        isLoadingMore = true;
                        getFBFriendsList(userId, fbToken.getToken(), PAGE_SIZE, nextPageId, friendsListCallback);
                    }
                    if (nextPageId == null) {
                        timerAsync.cancel();
                    }
                }
            };
            timerAsync.schedule(timerTaskAsync, 0, 500);
        }
    }

    private final Callback<FriendsListResponse> friendsListCallback = new Callback<FriendsListResponse>() {
        @Override
        public void onResponse(Call<FriendsListResponse> call, retrofit2.Response<FriendsListResponse> response) {
            isLoadingMore = false;
            if (response.isSuccessful()) {
                FriendsListResponse responseResult = response.body();
                nextPageId = responseResult.getNextPageId();
                previousPageId = responseResult.getPreviousPageId();
                ArrayList<FriendItemData> newFriendsList = responseResult.getFriendsDataList();

                //Get Correct insert Index
                int index = 0;
                if (friendsList.size() > 0) {
                    index = friendsList.size() - 1;
                }
                friendsList.addAll(index, newFriendsList);

                if ((nextPageId != null)) {
                    //Add Null object for loading more item
                    if (index == 0) {
                        friendsList.add(null);
                    }
                } else {
                    //Remove loading more item
                    index = friendsList.size() - 1;
                    if (friendsList.get(index) == null) {
                        friendsList.remove(index);
                    }
                }
                if(nextPageId == null) isFullLoading = false;
                if(!isFullLoading) view.loadFriendsList(friendsList);
            } else {
                //TODO show error message
            }
            loadAll();
        }

        @Override
        public void onFailure(Call<FriendsListResponse> call, Throwable t) {
            isLoadingMore = false;
            //TODO Show error message
        }
    };

    private void getFBFriendsList(String userId, String accessToken, int limit, String afterPage, Callback<FriendsListResponse> friendsListCallback) {
        FacebookFriendList facebookListService = ApiService.getService().create(FacebookFriendList.class);
        Call<FriendsListResponse> call = facebookListService.getFriendsList(userId, accessToken, limit, afterPage);
        call.enqueue(friendsListCallback);
    }
}
