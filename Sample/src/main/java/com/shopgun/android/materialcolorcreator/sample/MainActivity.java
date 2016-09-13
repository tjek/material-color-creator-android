package com.shopgun.android.materialcolorcreator.sample;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.shopgun.android.materialcolorcreator.MaterialColor;
import com.shopgun.android.materialcolorcreator.MaterialColorImpl;
import com.shopgun.android.materialcolorcreator.Shade;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the first MaterialColor, shade 500
        MaterialColor baseColor = new MaterialColorImpl(Color.parseColor("#ae68ca"));

        Resources r = getResources();
        String resNameFormat = "text_%s_%s";

        for (int i = 0; i < 3; i++) {

            for (Shade s : Shade.values()) {
                // identify the TextView resource
                String resName = String.format(resNameFormat, i, s.ordinal());
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
