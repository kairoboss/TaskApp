package com.example.taskapp.ui.home;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.interfaces.OnItemClickListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<String> list = new ArrayList<>();
    TextView textTitle;
    TextView textDate;
    private OnItemClickListener onItemClickListener;

    public void addItem(String text) {
        list.add(0,text);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;

    }

    public String getItem(int pos) {
        return list.get(pos);
    }
    public int getItemPosition(int pos){
        return pos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.list_text);
            textDate = itemView.findViewById(R.id.list_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(String s){
            textTitle.setText(s);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formattingTime = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:ss");
            String formattedTime = currentTime.format(formattingTime);
            textDate.setText(formattedTime);
        }
    }
}
