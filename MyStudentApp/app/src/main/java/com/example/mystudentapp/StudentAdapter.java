package com.example.mystudentapp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.items, parent, false)
        ) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        holder.itemView.setOnClickListener(
                view -> {
                    Intent intent = new Intent(context, StudentDetailsActivity.class);
                    intent.putExtra("index", index);
                    context.startActivity(intent);
                }
        );

        TextView name = holder.itemView.findViewById(R.id.name);
        TextView desc = holder.itemView.findViewById(R.id.desc);

        Student item = StudentRepository.getStudent().get(index);
        name.setText(String.format("%s. %s", index + 1, item.getName()));
        desc.setText(String.format("%s", item.getDesc()));
    }

    @Override
    public int getItemCount() {
        return StudentRepository.getStudent().size();
    }
}