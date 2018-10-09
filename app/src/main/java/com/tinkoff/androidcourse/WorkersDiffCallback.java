package com.tinkoff.androidcourse;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class WorkersDiffCallback extends DiffUtil.Callback {

    private final List<Worker> oldList;
    private final List<Worker> newList;

    WorkersDiffCallback(List<Worker> oldList, List<Worker> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Worker oldWorker = oldList.get(oldItemPosition);
        Worker newWorker = newList.get(newItemPosition);
        return oldWorker.getId() == newWorker.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Worker oldWorker = oldList.get(oldItemPosition);
        Worker newWorker = newList.get(newItemPosition);

        return oldWorker.getName().equals(newWorker.getName())
                && oldWorker.getAge().equals(newWorker.getAge())
                && oldWorker.getPosition().equals(newWorker.getPosition())
                && oldWorker.getPhoto().equals(newWorker.getPhoto());
    }
}
