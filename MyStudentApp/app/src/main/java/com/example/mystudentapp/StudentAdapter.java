package com.example.mystudentapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    StudentAdapter adapter;
    private Context context;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.items, parent, false)
        ) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int index) {
        holder.itemView.setOnClickListener(
                view -> {
                    Intent intent = new Intent(context, StudentDetailsActivity.class);
                    intent.putExtra("index", index);
                    context.startActivity(intent);
                }
        );

        Student item = StudentRepository.getInstance().get(index);
        holder.name.setText(String.format("%s. %s", index + 1, item.getName()));
        holder.desc.setText(String.format("%s", item.getDesc()));
    }

    public static class StudentHolder extends RecyclerView.ViewHolder {
        TextView desc, name;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
        }
    }

    @Override
    public int getItemCount() {
        return StudentRepository.getInstance().size();
    }
}