package com.example.student_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_app.R;
import com.example.student_app.model.UserData;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerviewHolder> {

    Context context;
    List<UserData> userDataList;
    List<UserData> filteredUserDataList;

    public RecyclerViewAdapter(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
        this.filteredUserDataList = userDataList;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item, parent, false);

        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, int position) {
        holder.userName.setText(userDataList.get(position).getUsername());
        holder.userDescr.setText(userDataList.get(position).getAge());
        holder.userImage.setImageResource(userDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return filteredUserDataList.size();
    }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        TextView userName, userDescr;

        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            userDescr = itemView.findViewById(R.id.userDescr);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    filteredUserDataList = userDataList;
                } else {
                    List<UserData> listFiltered = new ArrayList<>();
                    for (UserData row : userDataList) {
                        if (row.getUsername().toLowerCase().contains(key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    filteredUserDataList = listFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUserDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                filteredUserDataList = (List<UserData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
