package com.iitkgp.gaurav.geoalarm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gaurav on 6/22/2015.
 */
public class UIAdapter extends RecyclerView.Adapter<UIAdapter.MyViewHolder> {

    private ArrayList<ListViewItem> mDataset;
    private LayoutInflater mInflator;

    public UIAdapter(Context context,ArrayList<ListViewItem> data){
        mDataset = data;
//        Log.d("data::"," "+mDataset[0]+" "+mDataset[1]+" "+mDataset[2]);
        mInflator = LayoutInflater.from(context);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView txtTitle,txtDistance;
        private ImageButton mImageButton;
        public MyViewHolder(View v) {
            super(v);
            txtTitle = (TextView)v.findViewById(R.id.txtTitle);
            txtDistance =(TextView)v.findViewById(R.id.txtDistance);
            mImageButton = (ImageButton)v.findViewById(R.id.imgBtnOn);
        }
    }

    @Override
    public UIAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        Log.e("UIAdapter","createViewHolderCalled");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UIAdapter.MyViewHolder holder, int position) {
        Log.e("UIAdapter","bindViewHolderCalled");
     holder.txtTitle.setText(mDataset.get(position).alarmTitle);
     holder.txtDistance.setText(mDataset.get(position).distance);
     holder.mImageButton.setImageResource(mDataset.get(position).iconId);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
