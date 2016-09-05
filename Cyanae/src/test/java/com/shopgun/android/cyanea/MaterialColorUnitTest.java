package com.shopgun.android.cyanea;

import android.graphics.Color;

import com.shopgun.android.utils.ColorUtils;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Map;

public class MaterialColorUnitTest extends TestCase {

    public static final String TAG = MaterialColorUnitTest.class.getSimpleName();

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Map<MaterialColor, MaterialColorTestCreator.MaterialTestColor> map = MaterialColorTestCreator.getTestMap();

        for (Map.Entry<MaterialColor, MaterialColorTestCreator.MaterialTestColor> e : map.entrySet()) {
            MaterialColor c = e.getKey();
            MaterialColorTestCreator.MaterialTestColor v = e.getValue();
            materialColorUnitTest(c, v);
        }

    }

    @Test
    public static void materialColorUnitTest(MaterialColor c, MaterialColorTestCreator.MaterialTestColor v) {

        assertEquals(c.getValue(), v.value);
        assertEquals(c.getLuminance(), v.luminance, 0.00001);
        assertEquals(c.getPrimaryText(), v.primaryText);
        assertEquals(c.getSecondaryText(), v.secondaryText);
        assertEquals(c.getDisabledText(), v.disabledText);
        for (Shade s : Shade.values()) {
            shadeUnitTest(s, c, v.shades.get(s));
        }

    }

    @Test
    public static void shadeUnitTest(Shade s, MaterialColor c, Float[] expectedHSV) {

        int color = c.getColor(s).getValue();
        float[] hsv = ColorUtils.toHSV(color);

        float delta = 0.01f;
        for (int i = 0; i < 2; i++) {
            assertEquals(expectedHSV[i], hsv[i], delta);
        }
    }

    @Test
    public static void utilsUnitTest() {

        assertNull(MaterialColorUtils.toARGBString(null));
        assertEquals("#FF000000", MaterialColorUtils.toARGBString(new MaterialColorImpl(Color.BLACK)));
        assertEquals("#FF0000FF", MaterialColorUtils.toARGBString(new MaterialColorImpl(Color.BLUE)));
        assertEquals("#FFFF0000", MaterialColorUtils.toARGBString(new MaterialColorImpl(Color.RED)));
        assertEquals("#FF00FF00", MaterialColorUtils.toARGBString(new MaterialColorImpl(Color.GREEN)));

    }

}
