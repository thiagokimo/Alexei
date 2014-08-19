package com.kimo.lib.alexei;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Kimo on 8/14/14.
 */
public class Alexei {

    public static final String TAG = Alexei.class.getSimpleName();

    static Alexei singleton = null;
    public static Utils UTILS = new Utils();

    final Context mContext;

    private Alexei(Context context) {
        mContext = context;
    }

    public static Alexei with(Context context) {
        if(singleton == null)
            synchronized (Alexei.class) {
                if(singleton == null)
                    singleton = new Builder(context).build();
            }
        return singleton;
    }

    public RequestProcess analize(ImageView image) {
        return new RequestProcess(this, image, 0);
    }
    public RequestProcess analize(Bitmap image) {
        return new RequestProcess(this, image, 0);
    }

    public static class Builder {
        private final Context context;

        public Builder(Context context) {

            if(context == null)
                throw new IllegalArgumentException("Context must not be null");

            this.context = context.getApplicationContext();
        }

        public Alexei build() {

            return new Alexei(this.context);
        }
    }
}
