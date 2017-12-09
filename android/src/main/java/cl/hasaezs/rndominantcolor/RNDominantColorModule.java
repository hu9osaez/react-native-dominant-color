
package cl.hasaezs.rndominantcolor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.graphics.Palette;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class RNDominantColorModule extends ReactContextBaseJavaModule {

    public RNDominantColorModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNDominantColor";
    }

    private void loadImage(final String url, final Callback callback) {
        final Activity activity = getCurrentActivity();
        Handler uiHandler = new Handler(Looper.getMainLooper());

        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                int color = Palette.from(bitmap).generate().getDominantColor(Color.LTGRAY);
                String hexColor = String.format("#%06X", (0xFFFFFF & color));

                callback.invoke(false, hexColor);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                callback.invoke(true, null);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };

        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Picasso
                        .with(activity.getApplicationContext())
                        .load(url)
                        .into(target);
            }
        });
    }

    @ReactMethod
    public void getDominantColor(String url, final Callback callback) {
        this.loadImage(url, callback);
    }
}