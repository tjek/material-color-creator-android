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

import android.os.Parcelable;
import androidx.annotation.ColorInt;

public interface MaterialColor extends Parcelable {

    /**
     * Get a modified {@link MaterialColor} from a given {@link Shade}.
     * <p>The returned {@link MaterialColor} should reflect the material design guidelines defined by Google</p>
     * @param s A {@link Shade}
     * @return A {@link MaterialColor}
     */
    MaterialColor getColor(Shade s);

    /**
     * Get the color value of this {@link MaterialColor}
     * @return A color
     */
    @ColorInt
    int getValue();

    /**
     * Get a nice primary-text-color for  text on top of this {@link MaterialColor}
     * @return A color
     */
    @ColorInt
    int getPrimaryText();

    /**
     * Get a nice secondary-text-color for  text on top of this {@link MaterialColor}
     * @return A color
     */
    @ColorInt
    int getSecondaryText();

    /**
     * Get a nice disabled-text-color for text on top of this {@link MaterialColor}
     * @return A color
     */
    @ColorInt
    int getDisabledText();

    /**
     * Returns the luminance of the color.\
     * <p>Formula defined here: http://www.w3.org/TR/2008/REC-WCAG20-20081211/#relativeluminancedef</p>
     * @return The luminance value of this {@link MaterialColor}
     */
    double getLuminance();

    /**
     * Determine if this {@link MaterialColor} is very bright.
     * <p>Implementations are suggested to use a luminance value above 0.95</p>
     * @return {@code true} if this is very bright}, else {@code false}
     */
    boolean isVeryBright();

    /**
     * Determine if this {@link MaterialColor} is bright.
     * <p>Implementations are suggested to use a luminance value above 0.87</p>
     * @return {@code true} if this is bright}, else {@code false}
     */
    boolean isBright();

    /**
     * Determine if this {@link MaterialColor} is light.
     * <p>Implementations are suggested to use a luminance value above 0.64</p>
     * @return {@code true} if this is light}, else {@code false}
     */
    boolean isLight();

    /**
     * Determine if this {@link MaterialColor} is dark.
     * <p>Implementations are suggested to use a luminance value below 0.13</p>
     * @return {@code true} if this is dark}, else {@code false}
     */
    boolean isDark();

    /**
     * Determine if this {@link MaterialColor} is very dark.
     * <p>Implementations are suggested to use a luminance value below 0.025</p>
     * @return {@code true} if this is very dark}, else {@code false}
     */
    boolean isVeryDark();

}
