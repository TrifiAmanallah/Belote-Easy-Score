package ingenuity.com.beloteeasyscore.FacebookTools.adapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;

import ingenuity.com.beloteeasyscore.R;
import ingenuity.com.beloteeasyscore.FacebookTools.model.FriendItemData;
import ingenuity.com.beloteeasyscore.ImageTools.DownloadImage;

import static ingenuity.com.beloteeasyscore.EventsHelper.*;

public class FriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    public final int ITEM_TYPE_FRIEND = 0;
    public final int ITEM_TYPE_LOAD = 1;
    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "FriendsAdapter: ";
    private ArrayList<FriendItemData> friendsList;
    private ArrayList<FriendItemData> friendsListFiltered;
    private FriendFilter friendFilter;
    private Context context;
    private final OnItemClickListener listener;

    public FriendsAdapter(ArrayList<FriendItemData> friendsList, OnItemClickListener _listener) {
        this.friendsList = friendsList;
        this.friendsListFiltered = friendsList;
        this.listener = _listener;
        getFilter();
    }

    public interface OnItemClickListener {
        void onItemClick(FriendItemData _FriendItemData);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == ITEM_TYPE_FRIEND) {
            return new FriendViewHolder(inflater.inflate(R.layout.item_friend, parent, false));
        } else {
            return new LoadingViewHolder(inflater.inflate(R.layout.item_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == ITEM_TYPE_FRIEND) {
            FriendViewHolder friendViewHolder = ((FriendViewHolder)viewHolder);
            String friendName = friendsListFiltered.get(position).getName();
            friendViewHolder.tvUserName.setText(position + "  " + friendName);
            String imageUrl = friendsListFiltered.get(position).getPicture();
            if (imageUrl != null) {
                if (imageUrl.trim().length() > 0) {
                    //Picasso.with(context).load(imageUrl).into(friendViewHolder.ivUser);
                    new DownloadImage(friendViewHolder.ivUser).execute(imageUrl);
                }
            }

            Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            friendViewHolder.ivUser.setAnimation(anim);
            friendViewHolder.bind(friendsListFiltered.get(position), listener);
        }
    }

    @Override
    public int getItemCount() {
        return friendsListFiltered.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (friendsListFiltered.get(position) != null) {
            return ITEM_TYPE_FRIEND;
        } else {
            return ITEM_TYPE_LOAD;
        }
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivUser;
        public TextView tvUserName;

        public FriendViewHolder(View v) {
            super(v);
            ivUser = (ImageView) v.findViewById(R.id.iv_User);
            tvUserName = (TextView) v.findViewById(R.id.tv_UserName);
        }

        public void bind(final FriendItemData _item, final OnItemClickListener _listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    _listener.onItemClick(_item);
                }
            });
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        public ProgressBar pbLoading;

        public LoadingViewHolder(View v) {
            super(v);
            pbLoading = (ProgressBar) v.findViewById(R.id.pb_loading);
        }
    }

    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new FriendFilter();
        }
        return friendFilter;
    }

    private class FriendFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<FriendItemData> tempList = new ArrayList<FriendItemData>();
                for (FriendItemData _FriendItemData : friendsList) {

                    if (_FriendItemData != null) {
                        if (_FriendItemData.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(_FriendItemData);
                        }
                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
                friendsListFiltered = tempList;

            } else {
                filterResults.count = friendsList.size();
                filterResults.values = friendsList;
                friendsListFiltered = friendsList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    }
}
