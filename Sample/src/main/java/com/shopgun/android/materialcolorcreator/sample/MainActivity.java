package com.shopgun.android.materialcolorcreator.sample;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shopgun.android.materialcolorcreator.MaterialColor;
import com.shopgun.android.materialcolorcreator.MaterialColorImpl;
import com.shopgun.android.materialcolorcreator.Shade;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String RES_NAME_FORMAT = "text_%s_%s";

    SeekBar mHue;
    SeekBar mSaturation;
    SeekBar mValue;
    float[] mHsv = new float[]{ 240f, 1.0f, 1.0f};

    SeekBar.OnSeekBarChangeListener mListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mHsv[0] = mHue.getProgress();
            mHsv[1] = (float)mSaturation.getProgress()/100f;
            mHsv[2] = (float)mValue.getProgress()/100f;
            setColor(Color.HSVToColor(mHsv));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHue = (SeekBar) findViewById(R.id.hue);
        mSaturation = (SeekBar) findViewById(R.id.saturation);
        mValue = (SeekBar) findViewById(R.id.value);

        mHue.setOnSeekBarChangeListener(mListener);
        mSaturation.setOnSeekBarChangeListener(mListener);
        mValue.setOnSeekBarChangeListener(mListener);

        mHue.setProgress(240);
    }

    private void setColor(int color) {

        // Create the first MaterialColor, shade 500
        MaterialColor baseColor = new MaterialColorImpl(color);

        Resources r = getResources();

        for (int i = 0; i < 3; i++) {
            for (Shade s : Shade.values()) {
                // identify the TextView resource
                String resName = String.format(RES_NAME_FORMAT, i, s.ordinal());
                int resId = r.getIdentifier(resName, "id", getPackageName());
                // make material color magic
                TextView textView = (TextView) findViewById(resId);
                MaterialColor mc = baseColor.getColor(s);
                textView.setBackgroundColor(mc.getValue());
                textView.setTextColor(mc.getPrimaryText());
            }
            baseColor = baseColor.getColor(Shade.Shade300);
        }

    }

}
