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

    private String TAG = this.getClass().getSimpleName();
    private ArrayList<ListViewItem> mDataset;
    private LayoutInflater mInflator;
    private DeleteListener mDelete;

    public UIAdapter(Context context,ArrayList<ListViewItem> data,DeleteListener listener){
        mDataset = data;
//        Log.d(TAG," "+mDataset[0]+" "+mDataset[1]+" "+mDataset[2]);
        mInflator = LayoutInflater.from(context);
        mDelete=listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTitle,txtDistance;
        private ImageButton mImageButton;
        public MyViewHolder(View v) {
            super(v);
            txtTitle = (TextView)v.findViewById(R.id.txtTitle);
            txtDistance =(TextView)v.findViewById(R.id.txtDistance);
            mImageButton = (ImageButton)v.findViewById(R.id.imgBtnOn);
            mImageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            delete (getPosition());
        }
    }

    public void delete(int position){
        mDelete.onDeleted(position, mDataset.get(position).alarmTitle);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public UIAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG,"createViewHolderCalled");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UIAdapter.MyViewHolder holder, int position) {
        Log.d(TAG,"bindViewHolderCalled");
        holder.txtTitle.setText(mDataset.get(position).alarmTitle);
        holder.txtDistance.setText(mDataset.get(position).distance);
        holder.mImageButton.setImageResource(mDataset.get(position).iconId);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
