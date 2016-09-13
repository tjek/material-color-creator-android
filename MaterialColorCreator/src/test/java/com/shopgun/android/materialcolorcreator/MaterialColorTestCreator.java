package com.shopgun.android.materialcolorcreator;

import android.graphics.Color;

import com.shopgun.android.utils.ColorUtils;

import java.util.HashMap;
import java.util.Map;

public class MaterialColorTestCreator {

    public static final String TAG = MaterialColorTestCreator.class.getSimpleName();

    public static final Map<String, Integer> ALL = new HashMap<String, Integer>();
    public static final Map<String, Integer> CUSTOM = new HashMap<String, Integer>();
    public static final Map<String, Integer> BRANDS = new HashMap<String, Integer>();

    static {

        CUSTOM.put("ShopGun", Color.parseColor("#ee1e79"));
        CUSTOM.put("eTilbudsavis", Color.parseColor("#7b9119"));
        CUSTOM.put("eTilbudsavis-new", Color.parseColor("#01c545"));
        CUSTOM.put("Black", Color.parseColor("#000000"));
        CUSTOM.put("White", Color.parseColor("#FFFFFF"));
        CUSTOM.put("Red", Color.parseColor("#FF0000"));
        CUSTOM.put("Green", Color.parseColor("#00FF00"));
        CUSTOM.put("Blue", Color.parseColor("#0000FF"));

        BRANDS.put("Netto", Color.parseColor("#ffdf00"));
        BRANDS.put("Føtex", Color.parseColor("#20325A"));
        BRANDS.put("Humac", Color.parseColor("#ECECEC"));
        BRANDS.put("Super Brugsen", Color.parseColor("#f0393d"));
        BRANDS.put("Søstrene Grene", Color.parseColor("#FFFFFF"));
        BRANDS.put("Spar", Color.parseColor("#ed1c24"));
        BRANDS.put("Silvan", Color.parseColor("#3350a0"));
        BRANDS.put("Rema", Color.parseColor("#034899"));
        BRANDS.put("Meny", Color.parseColor("#CD0931"));
        BRANDS.put("Flugger", Color.parseColor("#3b3f00"));

        ALL.putAll(CUSTOM);
        ALL.putAll(BRANDS);

    }

    private MaterialColorTestCreator() {
        // empty
    }

    public static Map<MaterialColor, MaterialTestColor> getTestMap() {

        Map<MaterialColor, MaterialTestColor> map = new HashMap<MaterialColor, MaterialTestColor>();

        addTests(map);

        return map;
    }

    public static void generateTests() {

        for (Map.Entry<String, Integer> e : ALL.entrySet()) {
            String name = e.getKey();
            name = name.trim().replace(" ", "").toLowerCase();
            MaterialColor color = new MaterialColorImpl(e.getValue());
            materialColorTestGenerator("map", name, color);
        }

    }

    protected static class MaterialTestColor {

        float threshold = 0.01f;
        int value = Color.BLACK;
        double luminance = 0.0d;
        int primaryText = 0xFFFFFFFF;
        int secondaryText = 0xB2FFFFFF;
        int disabledText = 0x4CFFFFFF;
        Map<Shade, Float[]> shades = new HashMap<Shade, Float[]>();

    }

    public static void materialColorTestGenerator(String mapName, String name, MaterialColor color) {

        StringBuilder sb = new StringBuilder();
        sb.append("// test ").append(name).append("\n");
        sb.append("MaterialTestColor ").append(name).append(" = new MaterialTestColor();").append("\n");
        sb.append(name).append(".value = ").append(toHex(color.getValue())).append("; ").append("\n");
        sb.append(name).append(".luminance = ").append(color.getLuminance()).append("; ").append("\n");
        sb.append(name).append(".primaryText = ").append(toHex(color.getPrimaryText())).append("; ").append("\n");
        sb.append(name).append(".secondaryText = ").append(toHex(color.getSecondaryText())).append("; ").append("\n");
        sb.append(name).append(".disabledText = ").append(toHex(color.getDisabledText())).append("; ").append("\n");

        for (Shade s : Shade.values()) {
            printShadesToMap(sb, (name + ".shades"), s, color);
        }

        String mapFormat = "%s.put(new MaterialColorImpl(%s), %s);";
        sb.append(String.format(mapFormat, mapName, toHex(color.getValue()), name));
        sb.append("\n");
//        SgnLog.d(TAG, sb.toString());

    }

    private static String toHex(int color) {
        return ColorUtils.toARGBString(color).replace("#", "0x");
    }

    private static void printShadesToMap(StringBuilder sb, String mapName, Shade s, MaterialColor color) {
        MaterialColor shadeColor = color.getColor(s);
        float[] hsv = ColorUtils.toHSV(shadeColor.getValue());
        String format = "%s.put(%s.%s, new Float[]{ %.2ff, %.2ff, %.2ff});";
        sb.append(String.format(format, mapName, Shade.class.getSimpleName(), s, hsv[0], hsv[1], hsv[2])).append("\n");
    }

    public static void addTests(Map<MaterialColor, MaterialTestColor> map) {

        MaterialTestColor Føtex = new MaterialTestColor();
        Føtex.value = 0xFF20325A;
        Føtex.luminance = 0.03326465713130804;
        Føtex.primaryText = 0xFFFFFFFF;
        Føtex.secondaryText = 0xB2FFFFFF;
        Føtex.disabledText = 0x4CFFFFFF;
        Føtex.shades.put(Shade.Shade50, new Float[]{ 224.00f, 0.06f, 0.93f});
        Føtex.shades.put(Shade.Shade100, new Float[]{ 221.54f, 0.13f, 0.81f});
        Føtex.shades.put(Shade.Shade200, new Float[]{ 220.91f, 0.25f, 0.68f});
        Føtex.shades.put(Shade.Shade300, new Float[]{ 221.11f, 0.39f, 0.55f});
        Føtex.shades.put(Shade.Shade400, new Float[]{ 221.69f, 0.51f, 0.45f});
        Føtex.shades.put(Shade.Shade500, new Float[]{ 221.38f, 0.64f, 0.35f});
        Føtex.shades.put(Shade.Shade600, new Float[]{ 219.27f, 0.68f, 0.32f});
        Føtex.shades.put(Shade.Shade700, new Float[]{ 218.75f, 0.72f, 0.26f});
        Føtex.shades.put(Shade.Shade800, new Float[]{ 216.92f, 0.75f, 0.20f});
        Føtex.shades.put(Shade.Shade900, new Float[]{ 215.17f, 0.78f, 0.15f});
        map.put(new MaterialColorImpl(0xFF20325A), Føtex);

        MaterialTestColor Humac = new MaterialTestColor();
        Humac.value = 0xFFECECEC;
        Humac.luminance = 0.8387990117407399;
        Humac.primaryText = 0xDD000000;
        Humac.secondaryText = 0x89000000;
        Humac.disabledText = 0x60000000;
        Humac.shades.put(Shade.Shade50, new Float[]{ 0.00f, 0.00f, 0.99f});
        Humac.shades.put(Shade.Shade100, new Float[]{ 0.00f, 0.00f, 0.98f});
        Humac.shades.put(Shade.Shade200, new Float[]{ 0.00f, 0.00f, 0.96f});
        Humac.shades.put(Shade.Shade300, new Float[]{ 0.00f, 0.00f, 0.95f});
        Humac.shades.put(Shade.Shade400, new Float[]{ 0.00f, 0.00f, 0.94f});
        Humac.shades.put(Shade.Shade500, new Float[]{ 0.00f, 0.00f, 0.93f});
        Humac.shades.put(Shade.Shade600, new Float[]{ 0.00f, 0.00f, 0.84f});
        Humac.shades.put(Shade.Shade700, new Float[]{ 0.00f, 0.00f, 0.69f});
        Humac.shades.put(Shade.Shade800, new Float[]{ 0.00f, 0.00f, 0.54f});
        Humac.shades.put(Shade.Shade900, new Float[]{ 0.00f, 0.00f, 0.38f});
        map.put(new MaterialColorImpl(0xFFECECEC), Humac);

        MaterialTestColor SøstreneGrene = new MaterialTestColor();
        SøstreneGrene.value = 0xFFFFFFFF;
        SøstreneGrene.luminance = 1.0;
        SøstreneGrene.primaryText = 0xDD000000;
        SøstreneGrene.secondaryText = 0x89000000;
        SøstreneGrene.disabledText = 0x60000000;
        SøstreneGrene.shades.put(Shade.Shade50, new Float[]{ 0.00f, 0.00f, 1.00f});
        SøstreneGrene.shades.put(Shade.Shade100, new Float[]{ 0.00f, 0.00f, 1.00f});
        SøstreneGrene.shades.put(Shade.Shade200, new Float[]{ 0.00f, 0.00f, 1.00f});
        SøstreneGrene.shades.put(Shade.Shade300, new Float[]{ 0.00f, 0.00f, 1.00f});
        SøstreneGrene.shades.put(Shade.Shade400, new Float[]{ 0.00f, 0.00f, 1.00f});
        SøstreneGrene.shades.put(Shade.Shade500, new Float[]{ 0.00f, 0.00f, 1.00f});
        SøstreneGrene.shades.put(Shade.Shade600, new Float[]{ 0.00f, 0.00f, 0.90f});
        SøstreneGrene.shades.put(Shade.Shade700, new Float[]{ 0.00f, 0.00f, 0.75f});
        SøstreneGrene.shades.put(Shade.Shade800, new Float[]{ 0.00f, 0.00f, 0.58f});
        SøstreneGrene.shades.put(Shade.Shade900, new Float[]{ 0.00f, 0.00f, 0.41f});
        map.put(new MaterialColorImpl(0xFFFFFFFF), SøstreneGrene);

        MaterialTestColor Rema = new MaterialTestColor();
        Rema.value = 0xFF034899;
        Rema.luminance = 0.06953996322950529;
        Rema.primaryText = 0xFFFFFFFF;
        Rema.secondaryText = 0xB2FFFFFF;
        Rema.disabledText = 0x4CFFFFFF;
        Rema.shades.put(Shade.Shade50, new Float[]{ 212.31f, 0.11f, 0.95f});
        Rema.shades.put(Shade.Shade100, new Float[]{ 212.61f, 0.20f, 0.88f});
        Rema.shades.put(Shade.Shade200, new Float[]{ 212.20f, 0.40f, 0.80f});
        Rema.shades.put(Shade.Shade300, new Float[]{ 212.48f, 0.59f, 0.72f});
        Rema.shades.put(Shade.Shade400, new Float[]{ 212.03f, 0.79f, 0.66f});
        Rema.shades.put(Shade.Shade500, new Float[]{ 212.40f, 0.98f, 0.60f});
        Rema.shades.put(Shade.Shade600, new Float[]{ 210.88f, 0.99f, 0.54f});
        Rema.shades.put(Shade.Shade700, new Float[]{ 209.73f, 0.98f, 0.45f});
        Rema.shades.put(Shade.Shade800, new Float[]{ 207.95f, 0.99f, 0.35f});
        Rema.shades.put(Shade.Shade900, new Float[]{ 207.54f, 0.98f, 0.24f});
        map.put(new MaterialColorImpl(0xFF034899), Rema);

        MaterialTestColor Spar = new MaterialTestColor();
        Spar.value = 0xFFED1C24;
        Spar.luminance = 0.18962407588560998;
        Spar.primaryText = 0xFFFFFFFF;
        Spar.secondaryText = 0xB2FFFFFF;
        Spar.disabledText = 0x4CFFFFFF;
        Spar.shades.put(Shade.Shade50, new Float[]{ 357.50f, 0.09f, 0.99f});
        Spar.shades.put(Shade.Shade100, new Float[]{ 357.39f, 0.18f, 0.98f});
        Spar.shades.put(Shade.Shade200, new Float[]{ 357.27f, 0.36f, 0.96f});
        Spar.shades.put(Shade.Shade300, new Float[]{ 357.23f, 0.53f, 0.95f});
        Spar.shades.put(Shade.Shade400, new Float[]{ 357.53f, 0.71f, 0.94f});
        Spar.shades.put(Shade.Shade500, new Float[]{ 357.70f, 0.88f, 0.93f});
        Spar.shades.put(Shade.Shade600, new Float[]{ 356.27f, 0.90f, 0.84f});
        Spar.shades.put(Shade.Shade700, new Float[]{ 355.21f, 0.92f, 0.70f});
        Spar.shades.put(Shade.Shade800, new Float[]{ 354.33f, 0.93f, 0.54f});
        Spar.shades.put(Shade.Shade900, new Float[]{ 352.83f, 0.95f, 0.38f});
        map.put(new MaterialColorImpl(0xFFED1C24), Spar);

        MaterialTestColor Green = new MaterialTestColor();
        Green.value = 0xFF00FF00;
        Green.luminance = 0.7152;
        Green.primaryText = 0xDD000000;
        Green.secondaryText = 0x89000000;
        Green.disabledText = 0x60000000;
        Green.shades.put(Shade.Shade50, new Float[]{ 120.00f, 0.11f, 1.00f});
        Green.shades.put(Shade.Shade100, new Float[]{ 120.00f, 0.21f, 1.00f});
        Green.shades.put(Shade.Shade200, new Float[]{ 120.00f, 0.41f, 1.00f});
        Green.shades.put(Shade.Shade300, new Float[]{ 120.00f, 0.60f, 1.00f});
        Green.shades.put(Shade.Shade400, new Float[]{ 120.00f, 0.80f, 1.00f});
        Green.shades.put(Shade.Shade500, new Float[]{ 120.00f, 1.00f, 1.00f});
        Green.shades.put(Shade.Shade600, new Float[]{ 118.43f, 1.00f, 0.90f});
        Green.shades.put(Shade.Shade700, new Float[]{ 117.17f, 0.99f, 0.75f});
        Green.shades.put(Shade.Shade800, new Float[]{ 115.51f, 0.99f, 0.58f});
        Green.shades.put(Shade.Shade900, new Float[]{ 114.17f, 0.99f, 0.41f});
        map.put(new MaterialColorImpl(0xFF00FF00), Green);

        MaterialTestColor Flugger = new MaterialTestColor();
        Flugger.value = 0xFF3B3F00;
        Flugger.luminance = 0.04484820321188035;
        Flugger.primaryText = 0xFFFFFFFF;
        Flugger.secondaryText = 0xB2FFFFFF;
        Flugger.disabledText = 0x4CFFFFFF;
        Flugger.shades.put(Shade.Shade50, new Float[]{ 62.31f, 0.11f, 0.91f});
        Flugger.shades.put(Shade.Shade100, new Float[]{ 64.39f, 0.21f, 0.78f});
        Flugger.shades.put(Shade.Shade200, new Float[]{ 63.69f, 0.41f, 0.62f});
        Flugger.shades.put(Shade.Shade300, new Float[]{ 63.29f, 0.60f, 0.47f});
        Flugger.shades.put(Shade.Shade400, new Float[]{ 64.05f, 0.80f, 0.36f});
        Flugger.shades.put(Shade.Shade500, new Float[]{ 63.81f, 1.00f, 0.25f});
        Flugger.shades.put(Shade.Shade600, new Float[]{ 62.14f, 1.00f, 0.22f});
        Flugger.shades.put(Shade.Shade700, new Float[]{ 60.00f, 1.00f, 0.18f});
        Flugger.shades.put(Shade.Shade800, new Float[]{ 60.00f, 1.00f, 0.14f});
        Flugger.shades.put(Shade.Shade900, new Float[]{ 60.00f, 1.00f, 0.10f});
        map.put(new MaterialColorImpl(0xFF3B3F00), Flugger);

        MaterialTestColor Silvan = new MaterialTestColor();
        Silvan.value = 0xFF3350A0;
        Silvan.luminance = 0.08979194254580962;
        Silvan.primaryText = 0xFFFFFFFF;
        Silvan.secondaryText = 0xB2FFFFFF;
        Silvan.disabledText = 0x4CFFFFFF;
        Silvan.shades.put(Shade.Shade50, new Float[]{ 222.35f, 0.07f, 0.96f});
        Silvan.shades.put(Shade.Shade100, new Float[]{ 222.58f, 0.14f, 0.89f});
        Silvan.shades.put(Shade.Shade200, new Float[]{ 223.16f, 0.27f, 0.82f});
        Silvan.shades.put(Shade.Shade300, new Float[]{ 224.42f, 0.41f, 0.74f});
        Silvan.shades.put(Shade.Shade400, new Float[]{ 224.21f, 0.55f, 0.68f});
        Silvan.shades.put(Shade.Shade500, new Float[]{ 224.04f, 0.68f, 0.63f});
        Silvan.shades.put(Shade.Shade600, new Float[]{ 222.52f, 0.72f, 0.56f});
        Silvan.shades.put(Shade.Shade700, new Float[]{ 221.12f, 0.74f, 0.47f});
        Silvan.shades.put(Shade.Shade800, new Float[]{ 220.00f, 0.77f, 0.36f});
        Silvan.shades.put(Shade.Shade900, new Float[]{ 218.49f, 0.82f, 0.25f});
        map.put(new MaterialColorImpl(0xFF3350A0), Silvan);

        MaterialTestColor Netto = new MaterialTestColor();
        Netto.value = 0xFFFFDF00;
        Netto.luminance = 0.740353524354257;
        Netto.primaryText = 0xDD000000;
        Netto.secondaryText = 0x89000000;
        Netto.disabledText = 0x60000000;
        Netto.shades.put(Shade.Shade50, new Float[]{ 53.57f, 0.11f, 1.00f});
        Netto.shades.put(Shade.Shade100, new Float[]{ 53.21f, 0.21f, 1.00f});
        Netto.shades.put(Shade.Shade200, new Float[]{ 52.50f, 0.41f, 1.00f});
        Netto.shades.put(Shade.Shade300, new Float[]{ 52.60f, 0.60f, 1.00f});
        Netto.shades.put(Shade.Shade400, new Float[]{ 52.68f, 0.80f, 1.00f});
        Netto.shades.put(Shade.Shade500, new Float[]{ 52.47f, 1.00f, 1.00f});
        Netto.shades.put(Shade.Shade600, new Float[]{ 51.13f, 1.00f, 0.90f});
        Netto.shades.put(Shade.Shade700, new Float[]{ 49.63f, 0.99f, 0.75f});
        Netto.shades.put(Shade.Shade800, new Float[]{ 48.16f, 0.99f, 0.58f});
        Netto.shades.put(Shade.Shade900, new Float[]{ 47.18f, 0.99f, 0.41f});
        map.put(new MaterialColorImpl(0xFFFFDF00), Netto);

        MaterialTestColor Blue = new MaterialTestColor();
        Blue.value = 0xFF0000FF;
        Blue.luminance = 0.0722;
        Blue.primaryText = 0xFFFFFFFF;
        Blue.secondaryText = 0xB2FFFFFF;
        Blue.disabledText = 0x4CFFFFFF;
        Blue.shades.put(Shade.Shade50, new Float[]{ 240.00f, 0.11f, 1.00f});
        Blue.shades.put(Shade.Shade100, new Float[]{ 240.00f, 0.21f, 1.00f});
        Blue.shades.put(Shade.Shade200, new Float[]{ 240.00f, 0.41f, 1.00f});
        Blue.shades.put(Shade.Shade300, new Float[]{ 240.00f, 0.60f, 1.00f});
        Blue.shades.put(Shade.Shade400, new Float[]{ 240.00f, 0.80f, 1.00f});
        Blue.shades.put(Shade.Shade500, new Float[]{ 240.00f, 1.00f, 1.00f});
        Blue.shades.put(Shade.Shade600, new Float[]{ 238.43f, 1.00f, 0.90f});
        Blue.shades.put(Shade.Shade700, new Float[]{ 237.49f, 0.99f, 0.75f});
        Blue.shades.put(Shade.Shade800, new Float[]{ 235.92f, 0.99f, 0.58f});
        Blue.shades.put(Shade.Shade900, new Float[]{ 234.76f, 0.99f, 0.41f});
        map.put(new MaterialColorImpl(0xFF0000FF), Blue);

        MaterialTestColor Red = new MaterialTestColor();
        Red.value = 0xFFFF0000;
        Red.luminance = 0.2126;
        Red.primaryText = 0xFFFFFFFF;
        Red.secondaryText = 0xB2FFFFFF;
        Red.disabledText = 0x4CFFFFFF;
        Red.shades.put(Shade.Shade50, new Float[]{ 0.00f, 0.11f, 1.00f});
        Red.shades.put(Shade.Shade100, new Float[]{ 0.00f, 0.21f, 1.00f});
        Red.shades.put(Shade.Shade200, new Float[]{ 0.00f, 0.41f, 1.00f});
        Red.shades.put(Shade.Shade300, new Float[]{ 0.00f, 0.60f, 1.00f});
        Red.shades.put(Shade.Shade400, new Float[]{ 0.00f, 0.80f, 1.00f});
        Red.shades.put(Shade.Shade500, new Float[]{ 0.00f, 1.00f, 1.00f});
        Red.shades.put(Shade.Shade600, new Float[]{ 0.00f, 1.00f, 0.90f});
        Red.shades.put(Shade.Shade700, new Float[]{ 0.00f, 0.99f, 0.75f});
        Red.shades.put(Shade.Shade800, new Float[]{ 0.00f, 0.99f, 0.58f});
        Red.shades.put(Shade.Shade900, new Float[]{ 0.00f, 0.99f, 0.41f});
        map.put(new MaterialColorImpl(0xFFFF0000), Red);

        MaterialTestColor SuperBrugsen = new MaterialTestColor();
        SuperBrugsen.value = 0xFFF0393D;
        SuperBrugsen.luminance = 0.21788441760296845;
        SuperBrugsen.primaryText = 0xFFFFFFFF;
        SuperBrugsen.secondaryText = 0xB2FFFFFF;
        SuperBrugsen.disabledText = 0x4CFFFFFF;
        SuperBrugsen.shades.put(Shade.Shade50, new Float[]{ 357.00f, 0.08f, 1.00f});
        SuperBrugsen.shades.put(Shade.Shade100, new Float[]{ 358.46f, 0.16f, 0.98f});
        SuperBrugsen.shades.put(Shade.Shade200, new Float[]{ 358.42f, 0.31f, 0.97f});
        SuperBrugsen.shades.put(Shade.Shade300, new Float[]{ 358.41f, 0.46f, 0.96f});
        SuperBrugsen.shades.put(Shade.Shade400, new Float[]{ 358.39f, 0.61f, 0.95f});
        SuperBrugsen.shades.put(Shade.Shade500, new Float[]{ 358.69f, 0.76f, 0.94f});
        SuperBrugsen.shades.put(Shade.Shade600, new Float[]{ 357.19f, 0.79f, 0.85f});
        SuperBrugsen.shades.put(Shade.Shade700, new Float[]{ 356.33f, 0.82f, 0.71f});
        SuperBrugsen.shades.put(Shade.Shade800, new Float[]{ 354.92f, 0.85f, 0.55f});
        SuperBrugsen.shades.put(Shade.Shade900, new Float[]{ 353.72f, 0.88f, 0.38f});
        map.put(new MaterialColorImpl(0xFFF0393D), SuperBrugsen);

        MaterialTestColor Meny = new MaterialTestColor();
        Meny.value = 0xFFCD0931;
        Meny.luminance = 0.13396261147898136;
        Meny.primaryText = 0xFFFFFFFF;
        Meny.secondaryText = 0xB2FFFFFF;
        Meny.disabledText = 0x4CFFFFFF;
        Meny.shades.put(Shade.Shade50, new Float[]{ 346.15f, 0.10f, 0.98f});
        Meny.shades.put(Shade.Shade100, new Float[]{ 347.50f, 0.20f, 0.94f});
        Meny.shades.put(Shade.Shade200, new Float[]{ 347.33f, 0.39f, 0.90f});
        Meny.shades.put(Shade.Shade300, new Float[]{ 347.72f, 0.58f, 0.86f});
        Meny.shades.put(Shade.Shade400, new Float[]{ 347.56f, 0.77f, 0.84f});
        Meny.shades.put(Shade.Shade500, new Float[]{ 347.76f, 0.96f, 0.80f});
        Meny.shades.put(Shade.Shade600, new Float[]{ 346.52f, 0.96f, 0.73f});
        Meny.shades.put(Shade.Shade700, new Float[]{ 345.50f, 0.97f, 0.60f});
        Meny.shades.put(Shade.Shade800, new Float[]{ 343.97f, 0.97f, 0.47f});
        Meny.shades.put(Shade.Shade900, new Float[]{ 343.17f, 0.98f, 0.33f});
        map.put(new MaterialColorImpl(0xFFCD0931), Meny);

        MaterialTestColor Black = new MaterialTestColor();
        Black.value = 0xFF000000;
        Black.luminance = 0.0;
        Black.primaryText = 0xFFFFFFFF;
        Black.secondaryText = 0xB2FFFFFF;
        Black.disabledText = 0x4CFFFFFF;
        Black.shades.put(Shade.Shade50, new Float[]{ 0.00f, 0.00f, 0.88f});
        Black.shades.put(Shade.Shade100, new Float[]{ 0.00f, 0.00f, 0.70f});
        Black.shades.put(Shade.Shade200, new Float[]{ 0.00f, 0.00f, 0.50f});
        Black.shades.put(Shade.Shade300, new Float[]{ 0.00f, 0.00f, 0.30f});
        Black.shades.put(Shade.Shade400, new Float[]{ 0.00f, 0.00f, 0.15f});
        Black.shades.put(Shade.Shade500, new Float[]{ 0.00f, 0.00f, 0.00f});
        Black.shades.put(Shade.Shade600, new Float[]{ 0.00f, 0.00f, 0.00f});
        Black.shades.put(Shade.Shade700, new Float[]{ 0.00f, 0.00f, 0.00f});
        Black.shades.put(Shade.Shade800, new Float[]{ 0.00f, 0.00f, 0.00f});
        Black.shades.put(Shade.Shade900, new Float[]{ 0.00f, 0.00f, 0.00f});
        map.put(new MaterialColorImpl(0xFF000000), Black);

        MaterialTestColor eTilbudsavis = new MaterialTestColor();
        eTilbudsavis.value = 0xFF7B9119;
        eTilbudsavis.luminance = 0.24531938838449666;
        eTilbudsavis.primaryText = 0xFFFFFFFF;
        eTilbudsavis.secondaryText = 0xB2FFFFFF;
        eTilbudsavis.disabledText = 0x4CFFFFFF;
        eTilbudsavis.shades.put(Shade.Shade50, new Float[]{ 71.43f, 0.09f, 0.95f});
        eTilbudsavis.shades.put(Shade.Shade100, new Float[]{ 69.47f, 0.17f, 0.87f});
        eTilbudsavis.shades.put(Shade.Shade200, new Float[]{ 70.75f, 0.34f, 0.78f});
        eTilbudsavis.shades.put(Shade.Shade300, new Float[]{ 70.79f, 0.50f, 0.70f});
        eTilbudsavis.shades.put(Shade.Shade400, new Float[]{ 70.56f, 0.67f, 0.64f});
        eTilbudsavis.shades.put(Shade.Shade500, new Float[]{ 71.00f, 0.83f, 0.57f});
        eTilbudsavis.shades.put(Shade.Shade600, new Float[]{ 69.64f, 0.85f, 0.51f});
        eTilbudsavis.shades.put(Shade.Shade700, new Float[]{ 68.21f, 0.87f, 0.43f});
        eTilbudsavis.shades.put(Shade.Shade800, new Float[]{ 66.40f, 0.89f, 0.33f});
        eTilbudsavis.shades.put(Shade.Shade900, new Float[]{ 65.56f, 0.92f, 0.23f});
        map.put(new MaterialColorImpl(0xFF7B9119), eTilbudsavis);

        MaterialTestColor ShopGun = new MaterialTestColor();
        ShopGun.value = 0xFFEE1E79;
        ShopGun.luminance = 0.2048616547122194;
        ShopGun.primaryText = 0xFFFFFFFF;
        ShopGun.secondaryText = 0xB2FFFFFF;
        ShopGun.disabledText = 0x4CFFFFFF;
        ShopGun.shades.put(Shade.Shade50, new Float[]{ 332.50f, 0.09f, 0.99f});
        ShopGun.shades.put(Shade.Shade100, new Float[]{ 332.61f, 0.18f, 0.98f});
        ShopGun.shades.put(Shade.Shade200, new Float[]{ 333.41f, 0.36f, 0.97f});
        ShopGun.shades.put(Shade.Shade300, new Float[]{ 333.49f, 0.53f, 0.96f});
        ShopGun.shades.put(Shade.Shade400, new Float[]{ 333.53f, 0.71f, 0.95f});
        ShopGun.shades.put(Shade.Shade500, new Float[]{ 333.75f, 0.87f, 0.93f});
        ShopGun.shades.put(Shade.Shade600, new Float[]{ 332.50f, 0.89f, 0.84f});
        ShopGun.shades.put(Shade.Shade700, new Float[]{ 331.29f, 0.91f, 0.70f});
        ShopGun.shades.put(Shade.Shade800, new Float[]{ 330.00f, 0.93f, 0.54f});
        ShopGun.shades.put(Shade.Shade900, new Float[]{ 329.01f, 0.94f, 0.38f});
        map.put(new MaterialColorImpl(0xFFEE1E79), ShopGun);

        MaterialTestColor White = new MaterialTestColor();
        White.value = 0xFFFFFFFF;
        White.luminance = 1.0;
        White.primaryText = 0xDD000000;
        White.secondaryText = 0x89000000;
        White.disabledText = 0x60000000;
        White.shades.put(Shade.Shade50, new Float[]{ 0.00f, 0.00f, 1.00f});
        White.shades.put(Shade.Shade100, new Float[]{ 0.00f, 0.00f, 1.00f});
        White.shades.put(Shade.Shade200, new Float[]{ 0.00f, 0.00f, 1.00f});
        White.shades.put(Shade.Shade300, new Float[]{ 0.00f, 0.00f, 1.00f});
        White.shades.put(Shade.Shade400, new Float[]{ 0.00f, 0.00f, 1.00f});
        White.shades.put(Shade.Shade500, new Float[]{ 0.00f, 0.00f, 1.00f});
        White.shades.put(Shade.Shade600, new Float[]{ 0.00f, 0.00f, 0.90f});
        White.shades.put(Shade.Shade700, new Float[]{ 0.00f, 0.00f, 0.75f});
        White.shades.put(Shade.Shade800, new Float[]{ 0.00f, 0.00f, 0.58f});
        White.shades.put(Shade.Shade900, new Float[]{ 0.00f, 0.00f, 0.41f});
        map.put(new MaterialColorImpl(0xFFFFFFFF), White);

    }

}
