package com.airbnb;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Generic interface to bind items in a list. Presenters are separated from adapters in order to
 * enable multiple adapters to bind the same type of view.
 * @param <T> The type of object that will be bound to a view.
 * @param <H> The {@link RecyclerView.ViewHolder} that holds the view to be bound.
 */
public interface ListPresenter<T, H extends RecyclerView.ViewHolder> {

    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);

    /**
     * Bind a {@link RecyclerView.ViewHolder} with the contents from a given object.
     */
    void bindViewHolder(H holder, T object);

    /**
     * Perform any cleanup when a {@link RecyclerView.ViewHolder} is detached.
     */
    void unbindViewHolder(H holder);
}
