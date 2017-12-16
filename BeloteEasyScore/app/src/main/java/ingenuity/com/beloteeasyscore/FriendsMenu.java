package ingenuity.com.beloteeasyscore;

import android.app.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendsMenu extends Activity {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "FriendsMenu: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LogTag, SubLogTag +"onCreate Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_menu);
        getFBFriendsList();
    }

    private void getFBFriendsList() {
        //fbToken return from login with facebook
        Log.d(LogTag, SubLogTag +"getFBFriendsList Called");
        AccessToken fbToken = AccessToken.getCurrentAccessToken();
        GraphRequestAsyncTask r = GraphRequest.newGraphPathRequest(fbToken,
                "/me/taggable_friends", new GraphRequest.Callback() {

                    @Override
                    public void onCompleted(GraphResponse response) {
                        parseResponse(response.getJSONObject());
                    }
                }

        ).executeAsync();

    }

    private void parseResponse(JSONObject friends ) {

        try {
            JSONArray friendsArray = (JSONArray) friends.get("data");
            if (friendsArray != null) {
                for (int i = 0; i < friendsArray.length(); i++) {
                    FriendItem item = new FriendItem();
                    try {
                        item.setUserId(friendsArray.getJSONObject(i).get(
                                "id")
                                + "");

                        item.setUserName(friendsArray.getJSONObject(i).get(
                                "name")
                                + "");
                        JSONObject picObject = new JSONObject(friendsArray
                                .getJSONObject(i).get("picture") + "");
                        String picURL = (String) (new JSONObject(picObject
                                .get("data").toString())).get("url");
                        item.setPictureURL(picURL);
                        friendsList.add(item);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // facebook use paging if have "next" this mean you still have friends if not start load fbFriends list
                String next = friends.getJSONObject("paging")
                        .getString("next");
                if (next != null) {
                    getFBFriendsList(next);
                } else {
                    loadFriendsList();
                }
            }
        } catch (JSONException e1) {
            loadFriendsList();
            e1.printStackTrace();
        }
    }

    private void getFBFriendsList(String next) {
        //here i used volley to get next page
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.GET, next,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject friends = null;
                        try {
                            friends = new JSONObject(response);
                            parseResponse(friends);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        private void loadFriendsList() {
            swipeLayout.setRefreshing(false);
            if ((friendsList != null) && (friendsList.size() > 0)) {
                lvFriendsList.setVisibility(View.VISIBLE);
                friendsAdapter.notifyDataSetChanged();
            } else {
                lvFriendsList.setVisibility(View.GONE);
            }
        }
    /*private void getFriendsList(){
        Log.d(LogTag, SubLogTag +"getFriendsList Called");
        AccessToken token = AccessToken.getCurrentAccessToken();
        GraphRequest graphRequest = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                try {
                    Log.d(LogTag, SubLogTag +"GraphRequest onComplete Called");
                    JSONArray jsonArrayFriends = jsonObject.getJSONObject("friendlist").getJSONArray("data");
                    JSONObject friendlistObject = jsonArrayFriends.getJSONObject(0);
                    String friendListID = friendlistObject.getString("id");
                    Log.d(LogTag, SubLogTag +"friendListID = " + friendListID);
                    myNewGraphReq(friendListID);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle param = new Bundle();
        param.putString("fields", "friendlist", "members");
        graphRequest.setParameters(param);
        graphRequest.executeAsync();
    }

    private void myNewGraphReq(String friendlistId) {
        Log.d(LogTag, SubLogTag +"myNewGraphReq Called ");
        final String graphPath = "/"+friendlistId+"/members/";
        AccessToken token = AccessToken.getCurrentAccessToken();
        GraphRequest request = new GraphRequest(token, graphPath, null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                Log.d(LogTag, SubLogTag +"GraphResponse onCompleted Called ");
                JSONObject object = graphResponse.getJSONObject();
                try {
                    JSONArray arrayOfUsersInFriendList= object.getJSONArray("data");
                *//* Do something with the user list *//*
                *//* ex: get first user in list, "name" *//*
                    JSONObject user = arrayOfUsersInFriendList.getJSONObject(0);
                    String usersName = user.getString("name");
                    Log.d(LogTag, SubLogTag +"First Friend Name: " + usersName );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle param = new Bundle();
        param.putString("fields", "name");
        request.setParameters(param);
        request.executeAsync();
    }*/

}
