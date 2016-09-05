package com.shopgun.android.cyanea.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.shopgun.android.cyanea.MaterialColor;
import com.shopgun.android.cyanea.MaterialColorImpl;
import com.shopgun.android.cyanea.Shade;

public class MainActivity extends AppCompatActivity {

    private int mInitialColor = Color.parseColor("#ae68ca");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv01 = (TextView) findViewById(R.id.text_01);
        TextView tv02 = (TextView) findViewById(R.id.text_02);
        TextView tv03 = (TextView) findViewById(R.id.text_03);
        TextView tv04 = (TextView) findViewById(R.id.text_04);
        TextView tv05 = (TextView) findViewById(R.id.text_05);
        TextView tv06 = (TextView) findViewById(R.id.text_06);

        // Create the first MaterialColor, shade 500
        MaterialColor materialColor = new MaterialColorImpl(mInitialColor);

        MaterialColor mc01 = materialColor.getColor(Shade.Shade600);
        tv01.setBackgroundColor(mc01.getValue());
        tv01.setTextColor(mc01.getPrimaryText());

        MaterialColor mc02 = materialColor; // default is Shade500
        tv02.setBackgroundColor(mc02.getValue());
        tv02.setTextColor(mc02.getPrimaryText());

        MaterialColor mc03 = materialColor.getColor(Shade.Shade400);
        tv03.setBackgroundColor(mc03.getValue());
        tv03.setTextColor(mc03.getPrimaryText());

        MaterialColor mc04 = materialColor.getColor(Shade.Shade300);
        tv04.setBackgroundColor(mc04.getValue());
        tv04.setTextColor(mc04.getPrimaryText());

        MaterialColor mc05 = materialColor.getColor(Shade.Shade200);
        tv05.setBackgroundColor(mc05.getValue());
        tv05.setTextColor(mc05.getPrimaryText());

        MaterialColor mc06 = materialColor.getColor(Shade.Shade100);
        tv06.setBackgroundColor(mc06.getValue());
        tv06.setTextColor(mc06.getPrimaryText());

    }
}
