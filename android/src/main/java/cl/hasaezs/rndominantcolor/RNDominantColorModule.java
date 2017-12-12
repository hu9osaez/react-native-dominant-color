
package cl.hasaezs.rndominantcolor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.graphics.Palette;
import com.facebook.react.bridge.*;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class RNDominantColorModule extends ReactContextBaseJavaModule {

    private final int defaultColor;

    public RNDominantColorModule(ReactApplicationContext reactContext) {
        super(reactContext);

        this.defaultColor = Color.LTGRAY;
    }

    @Override
    public String getName() {
        return "RNDominantColor";
    }

    private String intColorToHex(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    private WritableMap mapColors(Palette palette) {
        if (palette == null) {
            return null;
        }

        WritableMap map = Arguments.createMap();

        String dominantColor = intColorToHex(palette.getDominantColor(defaultColor));
        String vibrantColor = intColorToHex(palette.getVibrantColor(defaultColor));
        String darkVibrantColor = intColorToHex(palette.getDarkVibrantColor(defaultColor));
        String lightVibrantColor = intColorToHex(palette.getLightVibrantColor(defaultColor));

        map.putString("dominantColor", dominantColor);
        map.putString("vibrantColor", vibrantColor);
        map.putString("darkVibrantColor", darkVibrantColor);
        map.putString("lightVibrantColor", lightVibrantColor);

        return map;
    }

    private void loadImage(final String url, final Callback callback) {
        final Activity activity = getCurrentActivity();
        Handler uiHandler = new Handler(Looper.getMainLooper());

        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                WritableMap colorMap = mapColors(Palette.from(bitmap).generate());

                callback.invoke(false, colorMap);
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