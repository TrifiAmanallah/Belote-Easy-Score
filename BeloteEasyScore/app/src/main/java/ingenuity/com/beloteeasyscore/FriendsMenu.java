package ingenuity.com.beloteeasyscore;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import ingenuity.com.beloteeasyscore.FacebookTools.FriendsListPresenter;
import ingenuity.com.beloteeasyscore.FacebookTools.FriendsListView;
import ingenuity.com.beloteeasyscore.FacebookTools.adapter.adapter.FriendsAdapter;
import ingenuity.com.beloteeasyscore.FacebookTools.model.FriendItemData;

public class FriendsMenu extends AppCompatActivity implements FriendsListView,SearchView.OnQueryTextListener {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "FriendsMenu: ";
    private ArrayList<FriendItemData> friendsList = new ArrayList<FriendItemData>();
    private SwipeRefreshLayout swipeLayout;
    private RecyclerView recyclerViewFriendList;
    private FriendsAdapter friendsAdapter;
    private FriendsListPresenter presenter;
    private SearchView searchView;
    private MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LogTag, SubLogTag +"onCreate Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new FriendsListPresenter(this);
        presenter.onGetFBFriendsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchFriend).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        //searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void initializeView() {
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));
        swipeLayout.setRefreshing(true);

        recyclerViewFriendList = (RecyclerView) findViewById(R.id.recycler_view_friends_list);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewFriendList.setLayoutManager(linearLayoutManager);

        friendsAdapter = new FriendsAdapter(friendsList);
        recyclerViewFriendList.setAdapter(friendsAdapter);

        recyclerViewFriendList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemsCount = linearLayoutManager.getItemCount();
                int visibleItemsCount = recyclerViewFriendList.getChildCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                presenter.onLoadMore(totalItemsCount, visibleItemsCount, firstVisibleItemPosition);
            }
        });
    }

    @Override
    public void loadFriendsList(ArrayList<FriendItemData> fLst) {
        friendsList.removeAll(friendsList);
        friendsList.addAll(fLst);
        swipeLayout.setRefreshing(false);

        if ((friendsList != null) && (friendsList.size() > 0)) {
            recyclerViewFriendList.setVisibility(View.VISIBLE);
            friendsAdapter.notifyDataSetChanged();
        } else {
            recyclerViewFriendList.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this, "loginErrorMessage", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(LogTag, SubLogTag +"onQueryTextSubmit Called");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        friendsAdapter.getFilter().filter(newText);
        Log.d(LogTag, SubLogTag +"onQueryTextChange Called");
        return true;
    }

}
