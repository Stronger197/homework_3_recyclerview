package com.tinkoff.androidcourse;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class WorkersRecyclerViewAdapter extends RecyclerView.Adapter<WorkersRecyclerViewAdapter.WorkerViewHolder>
        implements ItemTouchHelperAdapter {


    private ArrayList<Worker> workers;

    WorkersRecyclerViewAdapter(@NonNull ArrayList<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(workers, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        workers.remove(position);
        notifyItemRemoved(position);
    }

    void addData(ArrayList<Worker> data) {
        WorkersDiffCallback workersDiffCallback = new WorkersDiffCallback(workers, data);
        DiffUtil.DiffResult workersDiffResult = DiffUtil.calculateDiff(workersDiffCallback);

        workers.addAll(data);
        workersDiffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item, parent, false);
        return new WorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        Worker worker = workers.get(position);

        holder.image.setImageResource(worker.getPhoto());
        holder.name.setText(worker.getName());
        holder.age.setText(worker.getAge());
        holder.position.setText(worker.getPosition());
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    class WorkerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView age;
        TextView position;

        WorkerViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.worker_item_image);
            name = itemView.findViewById(R.id.worker_item_name);
            age = itemView.findViewById(R.id.worker_item_age);
            position = itemView.findViewById(R.id.worker_item_position);
        }
    }
}
