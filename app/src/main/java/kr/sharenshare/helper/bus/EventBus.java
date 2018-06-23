package kr.sharenshare.helper.bus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public final class EventBus extends Bus {

    private static EventBus instance;

    public static EventBus getInstance() {
        if (instance == null) instance = new EventBus();
        return instance;
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    EventBus.getInstance().post(event);
                }
            });
        }
    }
}
