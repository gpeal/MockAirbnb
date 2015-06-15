package com.airbnb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.airbnb.net.AirbnbAdapter;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class MessagesController implements ViewController {
    public static final String TAG = "MessagesController";

    private final Context mContext;

    private MessagesAdapter mAdapter;

    public MessagesController(Context context) {
        mContext = context;
    }

    @Override
    public ViewGroup initialize(ViewGroup container) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        mAdapter = new MessagesAdapter();
        rv.setAdapter(mAdapter);

        loadMessages();

        return rv;
    }

    private void loadMessages() {
        AirbnbAdapter.getInstance().getMessages()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Message>>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error loading messages!", e);
                    }

                    @Override
                    public void onNext(List<Message> messages) {
                        mAdapter.setMessages(messages);
                    }
                });
    }

}
