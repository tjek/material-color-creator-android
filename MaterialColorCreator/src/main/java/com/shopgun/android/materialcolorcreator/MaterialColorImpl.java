/*******************************************************************************
 * Copyright 2015 ShopGun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.shopgun.android.materialcolorcreator;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.shopgun.android.utils.ColorUtils;

public class MaterialColorImpl implements MaterialColor {

    public static final String TAG = MaterialColorImpl.class.getSimpleName();

    /** Google definition: alpha = 0.30f */
    public static final int TEXT_ALPHA_DISABLED_LIGHT = (int)(255*0.3f);
    /** Google definition: alpha = 0.38f */
    public static final int TEXT_ALPHA_DISABLED_DARK = (int)(255*0.38f);
    /** Google definition: alpha = 0.70f */
    public static final int TEXT_ALPHA_SECONDARY_LIGHT = (int)(255*0.70f);
    /** Google definition: alpha = 0.54f */
    public static final int TEXT_ALPHA_SECONDARY_DARK = (int)(255*0.54f);
    /** Google definition: alpha = 1.0f */
    public static final int TEXT_ALPHA_PRIMARY_LIGHT = (int)(255*1.0f);
    /** Google definition: alpha = 0.87f */
    public static final int TEXT_ALPHA_PRIMARY_DARK = (int)(255*0.87f);

    public static final double THRESHOLD_VERY_BRIGHT = 0.95d;
    public static final double THRESHOLD_BRIGHT = 0.87d;
    public static final double THRESHOLD_LIGHT = 0.54d;
    public static final double THRESHOLD_DARK = 0.13d;
    public static final double THRESHOLD_VERY_DARK = 0.025d;

    // We have modifier-percentages that map to shades.
    // Use this list to find the matching percentage, or lerp if the shade is not on a x100 bounds
    private static float[] mValuePercentConversion = new float[]{
            1.06f,//50
            0.70f,//100
            0.50f,//200
            0.30f,//300
            0.15f,//400
            0.00f,//500
            -0.10f,//600
            -0.25f,//700
            -0.42f,//800
            -0.59f,//900
    };

    private final int mColor;

    public MaterialColorImpl() {
        this(Color.BLACK);
    }

    public MaterialColorImpl(int color) {
        this.mColor = color;
    }

    public MaterialColorImpl(MaterialColor color) {
        this.mColor = color.getValue();
    }

    public static float getModifiedHue(float hue, Shade shade) {
        float s = (float)shade.getValue();
        if (s > 500) {
            // Initial calculations are based on hue being in the range [0.0-1.0]
            // java has the range [0.0-360.0] so we'll do a little conversion
            hue = hue/360.0f;
            float hueAt900 = (1.003f*hue) - 0.016f;
            hue = ((hueAt900 - hue)/((float)900-(float)500))*(s-(float)500) + hue;
            return hue*360.0f;
        } else {
            return hue;
        }
    }

    public static float getModifiedSaturation(float saturation, Shade shade) {
        if (shade.getValue() == 500) {
            return saturation;
        }

        if (shade.getValue() < 500) {
            // get the saturation target @ 50:
            // clamp to 0.0
            float f = (0.136f * saturation) - 0.025f;
            float satAt50 = Math.max(f, 0.0f);
            // lerp shade 500->900
            return ((saturation - satAt50)/(500-50))*(shade.getValue()-50) + satAt50;
        } else {
            // get the saturation target @ 900:
            // quick inaccurate version:
            // 110% of the base saturation (clamped to 1.0)
            //            CGFloat satAt900 = MIN(baseSaturation * 1.10, 1.0);
            // expensive(?) accurate version
            float satAt900 = Math.min((-1.019f * saturation * saturation) + (2.283f * saturation) - 0.281f, 1.0f);
            // lerp shade 500->900
            return ((satAt900 - saturation)/(900-500))*(shade.getValue()-500) + saturation;
        }
    }


    public static float getModifiedValue(float value, Shade shade) {

        if (shade.getValue() == 500) {
            return value;
        }

        float indexFloat = ((float)shade.getValue())/100.0f;

        int indexFloor = (int)Math.floor(indexFloat);
        int indexCeil = (int)Math.ceil(indexFloat);

        int max = mValuePercentConversion.length-1;
        int lowerIndex = Math.min( Math.max(indexFloor , 0 ), max );
        int upperIndex = Math.min( Math.max(indexCeil, 0), max );

        float lowerPercent = mValuePercentConversion[lowerIndex];
        float valuePercent;
        if (lowerIndex != upperIndex) {
            float upperPercent = mValuePercentConversion[upperIndex];
            float deltaPercent = upperPercent-lowerPercent;
            float deltaIndex = upperIndex-lowerIndex;
            valuePercent = lowerPercent + (deltaPercent / deltaIndex) * (indexFloat-(float)lowerIndex);
        } else {
            valuePercent = lowerPercent;
        }

        if (shade.getValue() < 500) {
            return value + ((1.0f-value)*valuePercent);
        } else {
            return value + (value*valuePercent);
        }
    }

    public static MaterialColor getModifiedColor(int color, Shade shade) {
        int modified = color;
        if (shade != Shade.Shade500) {
            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[0] = getModifiedHue(hsv[0], shade);
            hsv[1] = getModifiedSaturation(hsv[1], shade);
            hsv[2] = getModifiedValue(hsv[2], shade);
            modified = Color.HSVToColor(Color.alpha(color), hsv);
        }
        return new MaterialColorImpl(modified);
    }

    @Override
    public MaterialColor getColor(Shade s) {
        return MaterialColorImpl.getModifiedColor(mColor, s);
    }

    @Override
    public int getValue() {
        return mColor;
    }

    @Override
    public int getPrimaryText() {
        return getPrimaryTextColor(isLight());
    }

    /**
     * @param brightBackground {@code true} if the background is bright, else {@code false}
     * @return A foreground text color to fit the background
     */
    public static int getPrimaryTextColor(boolean brightBackground) {
        int color = brightBackground ? Color.BLACK : Color.WHITE;
        int alpha = brightBackground ? TEXT_ALPHA_PRIMARY_DARK : TEXT_ALPHA_PRIMARY_LIGHT;
        return ColorUtils.setAlphaComponent(color, alpha);
    }

    @Override
    public int getSecondaryText() {
        return getSecondaryTextColor(isLight());
    }

    /**
     * @param brightBackground {@code true} if the background is bright, else {@code false}
     * @return A foreground text color to fit the background
     */
    public static int getSecondaryTextColor(boolean brightBackground) {
        int color = brightBackground ? Color.BLACK : Color.WHITE;
        int alpha = brightBackground ? TEXT_ALPHA_SECONDARY_DARK : TEXT_ALPHA_SECONDARY_LIGHT;
        return ColorUtils.setAlphaComponent(color, alpha);
    }

    @Override
    public int getDisabledText() {
        return getDisabledTextColor(isLight());
    }

    /**
     * @param brightBackground {@code true} if the background is bright, else {@code false}
     * @return A foreground text color to fit the background
     */
    public static int getDisabledTextColor(boolean brightBackground) {
        int color = brightBackground ? Color.BLACK : Color.WHITE;
        int alpha = brightBackground ? TEXT_ALPHA_DISABLED_DARK : TEXT_ALPHA_DISABLED_LIGHT;
        return ColorUtils.setAlphaComponent(color, alpha);
    }

    @Override
    public double getLuminance() {
        return ColorUtils.calculateLuminance(mColor);
    }

    @Override
    public boolean isVeryBright() {
        return getLuminance() > THRESHOLD_VERY_BRIGHT;
    }

    @Override
    public boolean isBright() {
        return getLuminance() > THRESHOLD_BRIGHT;
    }

    @Override
    public boolean isLight() {
        return getLuminance() > THRESHOLD_LIGHT;
    }

    @Override
    public boolean isDark() {
        return getLuminance() < THRESHOLD_DARK;
    }

    @Override
    public boolean isVeryDark() {
        return getLuminance() < THRESHOLD_VERY_DARK;
    }

    @Override
    public String toString() {
        return ColorUtils.toARGBString(mColor);
    }

    /**
     * Test is a color is darker than {@code this}
     * @param other A color
     * @return {@code true} if {@code other} is darker than {@code this}, else {@code false}
     */
    public boolean isDarker(int other) {
        return getLuminance() < ColorUtils.calculateLuminance(other);
    }

    /**
     * Test is a color is lighter than {@code this}
     * @param other A color
     * @return {@code true} if {@code other} is lighter than {@code this}, else {@code false}
     */
    public boolean isLighter(int other) {
        return getLuminance() > ColorUtils.calculateLuminance(other);
    }

    /**
     * Convert this color into a Hue, Saturation, Value human readable string
     * @return A string
     */
    public String toHSVString() {
        return ColorUtils.toHsvString(mColor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialColorImpl materialColorImpl = (MaterialColorImpl) o;

        return mColor == materialColorImpl.mColor;

    }

    @Override
    public int hashCode() {
        return mColor;
    }

    public boolean equals(MaterialColorImpl o, int tolerance) {

        if (this == o) return true;

        if (o == null) return false;

        if (mColor == o.mColor) {
            return true;
        }

        if (tolerance == 0) {
            return false;
        }

        int r1 = Color.red(mColor);
        int g1 = Color.green(mColor);
        int b1 = Color.blue(mColor);
        int a1 = Color.alpha(mColor);

        int r2 = Color.red(o.mColor);
        int g2 = Color.green(o.mColor);
        int b2 = Color.blue(o.mColor);
        int a2 = Color.alpha(o.mColor);

        return Math.abs(r1-r2) <= tolerance &&
                Math.abs(g1-g2) <= tolerance &&
                Math.abs(b1-b2) <= tolerance &&
                Math.abs(a1-a2) <= tolerance;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mColor);
    }

    protected MaterialColorImpl(Parcel in) {
        this.mColor = in.readInt();
    }

    public static final Parcelable.Creator<MaterialColorImpl> CREATOR = new Parcelable.Creator<MaterialColorImpl>() {
        public MaterialColorImpl createFromParcel(Parcel source) {
            return new MaterialColorImpl(source);
        }

        public MaterialColorImpl[] newArray(int size) {
            return new MaterialColorImpl[size];
        }
    };

}
