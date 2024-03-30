package com.example.roomdb.Api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.roomdb.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder> {

    private List<ListModel> listModels = new ArrayList<>();
    private Context context;


    public ListAdapter(Context context,List<ListModel> listModels){
        this.context = context;
        this.listModels = listModels;
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtteamName,txtplayerId1,txtplayerName1,txtplayerPoints1,txtplayerId2,txtplayerName2,txtplayerPoints2;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtteamName = mView.findViewById(R.id.txtteamName);

            txtplayerId1 = mView.findViewById(R.id.txtplayerId1);
            txtplayerName1 = mView.findViewById(R.id.txtplayerName1);
            txtplayerPoints1 = mView.findViewById(R.id.txtplayerPoints1);

            txtplayerId2 = mView.findViewById(R.id.txtplayerId2);
            txtplayerName2 = mView.findViewById(R.id.txtplayerName2);
            txtplayerPoints2 = mView.findViewById(R.id.txtplayerPoints2);


        }


        void bind(final ListModel userModal) {

            txtteamName.setText(userModal.getStrteamName());

            txtplayerId1.setText(userModal.getStrplayerId1());
            txtplayerName1.setText(userModal.getStrplayerName1());
            txtplayerPoints1.setText(userModal.getStrplayerPoints1());

            txtplayerId2.setText(userModal.getStrplayerId2());
            txtplayerName2.setText(userModal.getStrplayerName2());
            txtplayerPoints2.setText(userModal.getStrplayerPoints2());

        }

    }

    @Override
    public ListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ListAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.CustomViewHolder holder, int position) {
        holder.bind(listModels.get(position));


    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }

}