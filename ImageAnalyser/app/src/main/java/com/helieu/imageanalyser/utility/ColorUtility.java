package com.helieu.imageanalyser.utility;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by Neil on 14/11/25.
 */
public class ColorUtility {
    private static final String [] colorStringName = {"AliceBlue","AntiqueWhite","AntiqueWhite1","AntiqueWhite2","AntiqueWhite3","AntiqueWhite4","aquamarine1","aquamarine2","aquamarine4","azure1","azure2","azure3","azure4","beige","bisque1","bisque2","bisque3","bisque4","black","BlanchedAlmond","blue1","blue2","blue4","BlueViolet","brown","brown1","brown2","brown3","brown4","burlywood","burlywood1","burlywood2","burlywood3","burlywood4","CadetBlue","CadetBlue1","CadetBlue2","CadetBlue3","CadetBlue4","chartreuse1","chartreuse2","chartreuse3","chartreuse4","chocolate","chocolate1","chocolate2","chocolate3","coral","coral1","coral2","coral3","coral4","CornflowerBlue","cornsilk1","cornsilk2","cornsilk3","cornsilk4","cyan1","cyan2","cyan3","cyan4","DarkGoldenrod","DarkGoldenrod1","DarkGoldenrod2","DarkGoldenrod3","DarkGoldenrod4","DarkGreen","DarkKhaki","DarkOliveGreen","DarkOliveGreen1","DarkOliveGreen2","DarkOliveGreen3","DarkOliveGreen4","DarkOrange","DarkOrange1","DarkOrange2","DarkOrange3","DarkOrange4","DarkOrchid","DarkOrchid1","DarkOrchid2","DarkOrchid3","DarkOrchid4","DarkSalmon","DarkSeaGreen","DarkSeaGreen1","DarkSeaGreen2","DarkSeaGreen3","DarkSeaGreen4","DarkSlateBlue","DarkSlateGray","DarkSlateGray1","DarkSlateGray2","DarkSlateGray3","DarkSlateGray4","DarkTurquoise","DarkViolet","DeepPink1","DeepPink2","DeepPink3","DeepPink4","DeepSkyBlue1","DeepSkyBlue2","DeepSkyBlue3","DeepSkyBlue4","DimGray","DodgerBlue1","DodgerBlue2","DodgerBlue3","DodgerBlue4","firebrick","firebrick1","firebrick2","firebrick3","firebrick4","FloralWhite","ForestGreen","gainsboro","GhostWhite","gold1","gold2","gold3","gold4","goldenrod","goldenrod1","goldenrod2","goldenrod3","goldenrod4","gray","gray1","gray10","gray11","gray12","gray13","gray14","gray15","gray16","gray17","gray18","gray19","gray2","gray20","gray21","gray22","gray23","gray24","gray25","gray26","gray27","gray28","gray29","gray3","gray30","gray31","gray32","gray33","gray34","gray35","gray36","gray37","gray38","gray39","gray4","gray40","gray41","gray42","gray43","gray44","gray45","gray46","gray47","gray48","gray49","gray5","gray50","gray51","gray52","gray53","gray54","gray55","gray56","gray57","gray58","gray59","gray6","gray60","gray61","gray62","gray63","gray64","gray65","gray66","gray67","gray68","gray69","gray7","gray70","gray71","gray72","gray73","gray74","gray75","gray76","gray77","gray78","gray79","gray8","gray80","gray81","gray82","gray83","gray84","gray85","gray86","gray87","gray88","gray89","gray9","gray90","gray91","gray92","gray93","gray94","gray95","gray97","gray98","gray99","green1","green2","green3","green4","GreenYellow","honeydew1","honeydew2","honeydew3","honeydew4","HotPink","HotPink1","HotPink2","HotPink3","HotPink4","IndianRed","IndianRed1","IndianRed2","IndianRed3","IndianRed4","ivory1","ivory2","ivory3","ivory4","khaki","khaki1","khaki2","khaki3","khaki4","lavender","LavenderBlush1","LavenderBlush2","LavenderBlush3","LavenderBlush4","LawnGreen","LemonChiffon1","LemonChiffon2","LemonChiffon3","LemonChiffon4","light","LightBlue","LightBlue1","LightBlue2","LightBlue3","LightBlue4","LightCoral","LightCyan1","LightCyan2","LightCyan3","LightCyan4","LightGoldenrod1","LightGoldenrod2","LightGoldenrod3","LightGoldenrod4","LightGoldenrodYellow","LightGray","LightPink","LightPink1","LightPink2","LightPink3","LightPink4","LightSalmon1","LightSalmon2","LightSalmon3","LightSalmon4","LightSeaGreen","LightSkyBlue","LightSkyBlue1","LightSkyBlue2","LightSkyBlue3","LightSkyBlue4","LightSlateBlue","LightSlateGray","LightSteelBlue","LightSteelBlue1","LightSteelBlue2","LightSteelBlue3","LightSteelBlue4","LightYellow1","LightYellow2","LightYellow3","LightYellow4","LimeGreen","linen","magenta","magenta2","magenta3","magenta4","maroon","maroon1","maroon2","maroon3","maroon4","medium","MediumAquamarine","MediumBlue","MediumOrchid","MediumOrchid1","MediumOrchid2","MediumOrchid3","MediumOrchid4","MediumPurple","MediumPurple1","MediumPurple2","MediumPurple3","MediumPurple4","MediumSeaGreen","MediumSlateBlue","MediumSpringGreen","MediumTurquoise","MediumVioletRed","MidnightBlue","MintCream","MistyRose1","MistyRose2","MistyRose3","MistyRose4","moccasin","NavajoWhite1","NavajoWhite2","NavajoWhite3","NavajoWhite4","NavyBlue","OldLace","OliveDrab","OliveDrab1","OliveDrab2","OliveDrab4","orange1","orange2","orange3","orange4","OrangeRed1","OrangeRed2","OrangeRed3","OrangeRed4","orchid","orchid1","orchid2","orchid3","orchid4","pale","PaleGoldenrod","PaleGreen","PaleGreen1","PaleGreen2","PaleGreen3","PaleGreen4","PaleTurquoise","PaleTurquoise1","PaleTurquoise2","PaleTurquoise3","PaleTurquoise4","PaleVioletRed","PaleVioletRed1","PaleVioletRed2","PaleVioletRed3","PaleVioletRed4","PapayaWhip","PeachPuff1","PeachPuff2","PeachPuff3","PeachPuff4","pink","pink1","pink2","pink3","pink4","plum","plum1","plum2","plum3","plum4","PowderBlue","purple","rebeccapurple","purple1","purple2","purple3","purple4","red1","red2","red3","red4","RosyBrown","RosyBrown1","RosyBrown2","RosyBrown3","RosyBrown4","RoyalBlue","RoyalBlue1","RoyalBlue2","RoyalBlue3","RoyalBlue4","SaddleBrown","salmon","salmon1","salmon2","salmon3","salmon4","SandyBrown","SeaGreen1","SeaGreen2","SeaGreen3","SeaGreen4","seashell1","seashell2","seashell3","seashell4","sienna","sienna1","sienna2","sienna3","sienna4","SkyBlue","SkyBlue1","SkyBlue2","SkyBlue3","SkyBlue4","SlateBlue","SlateBlue1","SlateBlue2","SlateBlue3","SlateBlue4","SlateGray","SlateGray1","SlateGray2","SlateGray3","SlateGray4","snow1","snow2","snow3","snow4","SpringGreen1","SpringGreen2","SpringGreen3","SpringGreen4","SteelBlue","SteelBlue1","SteelBlue2","SteelBlue3","SteelBlue4","tan","tan1","tan2","tan3","tan4","thistle","thistle1","thistle2","thistle3","thistle4","tomato1","tomato2","tomato3","tomato4","turquoise","turquoise1","turquoise2","turquoise3","turquoise4","violet","VioletRed","VioletRed1","VioletRed2","VioletRed3","VioletRed4","wheat","wheat1","wheat2","wheat3","wheat4","white","WhiteSmoke","yellow1","yellow2","yellow3","yellow4","YellowGreen"};
    private static final String [] colorStringHex = {"#f0f8ff","#faebd7","#ffefdb","#eedfcc","#cdc0b0","#8b8378","#7fffd4","#76eec6","#458b74","#f0ffff","#e0eeee","#c1cdcd","#838b8b","#f5f5dc","#ffe4c4","#eed5b7","#cdb79e","#8b7d6b","#000000","#ffebcd","#0000ff","#0000ee","#00008b","#8a2be2","#a52a2a","#ff4040","#ee3b3b","#cd3333","#8b2323","#deb887","#ffd39b","#eec591","#cdaa7d","#8b7355","#5f9ea0","#98f5ff","#8ee5ee","#7ac5cd","#53868b","#7fff00","#76ee00","#66cd00","#458b00","#d2691e","#ff7f24","#ee7621","#cd661d","#ff7f50","#ff7256","#ee6a50","#cd5b45","#8b3e2f","#6495ed","#fff8dc","#eee8cd","#cdc8b1","#8b8878","#00ffff","#00eeee","#00cdcd","#008b8b","#b8860b","#ffb90f","#eead0e","#cd950c","#8b6508","#006400","#bdb76b","#556b2f","#caff70","#bcee68","#a2cd5a","#6e8b3d","#ff8c00","#ff7f00","#ee7600","#cd6600","#8b4500","#9932cc","#bf3eff","#b23aee","#9a32cd","#68228b","#e9967a","#8fbc8f","#c1ffc1","#b4eeb4","#9bcd9b","#698b69","#483d8b","#2f4f4f","#97ffff","#8deeee","#79cdcd","#528b8b","#00ced1","#9400d3","#ff1493","#ee1289","#cd1076","#8b0a50","#00bfff","#00b2ee","#009acd","#00688b","#696969","#1e90ff","#1c86ee","#1874cd","#104e8b","#b22222","#ff3030","#ee2c2c","#cd2626","#8b1a1a","#fffaf0","#228b22","#dcdcdc","#f8f8ff","#ffd700","#eec900","#cdad00","#8b7500","#daa520","#ffc125","#eeb422","#cd9b1d","#8b6914","#bebebe","#030303","#1a1a1a","#1c1c1c","#1f1f1f","#212121","#242424","#262626","#292929","#2b2b2b","#2e2e2e","#303030","#050505","#333333","#363636","#383838","#3b3b3b","#3d3d3d","#404040","#424242","#454545","#474747","#4a4a4a","#080808","#4d4d4d","#4f4f4f","#525252","#545454","#575757","#595959","#5c5c5c","#5e5e5e","#616161","#636363","#0a0a0a","#666666","#696969","#6b6b6b","#6e6e6e","#707070","#737373","#757575","#787878","#7a7a7a","#7d7d7d","#0d0d0d","#7f7f7f","#828282","#858585","#878787","#8a8a8a","#8c8c8c","#8f8f8f","#919191","#949494","#969696","#0f0f0f","#999999","#9c9c9c","#9e9e9e","#a1a1a1","#a3a3a3","#a6a6a6","#a8a8a8","#ababab","#adadad","#b0b0b0","#121212","#b3b3b3","#b5b5b5","#b8b8b8","#bababa","#bdbdbd","#bfbfbf","#c2c2c2","#c4c4c4","#c7c7c7","#c9c9c9","#141414","#cccccc","#cfcfcf","#d1d1d1","#d4d4d4","#d6d6d6","#d9d9d9","#dbdbdb","#dedede","#e0e0e0","#e3e3e3","#171717","#e5e5e5","#e8e8e8","#ebebeb","#ededed","#f0f0f0","#f2f2f2","#f7f7f7","#fafafa","#fcfcfc","#00ff00","#00ee00","#00cd00","#008b00","#adff2f","#f0fff0","#e0eee0","#c1cdc1","#838b83","#ff69b4","#ff6eb4","#ee6aa7","#cd6090","#8b3a62","#cd5c5c","#ff6a6a","#ee6363","#cd5555","#8b3a3a","#fffff0","#eeeee0","#cdcdc1","#8b8b83","#f0e68c","#fff68f","#eee685","#cdc673","#8b864e","#e6e6fa","#fff0f5","#eee0e5","#cdc1c5","#8b8386","#7cfc00","#fffacd","#eee9bf","#cdc9a5","#8b8970","#eedd82","#add8e6","#bfefff","#b2dfee","#9ac0cd","#68838b","#f08080","#e0ffff","#d1eeee","#b4cdcd","#7a8b8b","#ffec8b","#eedc82","#cdbe70","#8b814c","#fafad2","#d3d3d3","#ffb6c1","#ffaeb9","#eea2ad","#cd8c95","#8b5f65","#ffa07a","#ee9572","#cd8162","#8b5742","#20b2aa","#87cefa","#b0e2ff","#a4d3ee","#8db6cd","#607b8b","#8470ff","#778899","#b0c4de","#cae1ff","#bcd2ee","#a2b5cd","#6e7b8b","#ffffe0","#eeeed1","#cdcdb4","#8b8b7a","#32cd32","#faf0e6","#ff00ff","#ee00ee","#cd00cd","#8b008b","#b03060","#ff34b3","#ee30a7","#cd2990","#8b1c62","#66cdaa","#66cdaa","#0000cd","#ba55d3","#e066ff","#d15fee","#b452cd","#7a378b","#9370db","#ab82ff","#9f79ee","#8968cd","#5d478b","#3cb371","#7b68ee","#00fa9a","#48d1cc","#c71585","#191970","#f5fffa","#ffe4e1","#eed5d2","#cdb7b5","#8b7d7b","#ffe4b5","#ffdead","#eecfa1","#cdb38b","#8b795e","#000080","#fdf5e6","#6b8e23","#c0ff3e","#b3ee3a","#698b22","#ffa500","#ee9a00","#cd8500","#8b5a00","#ff4500","#ee4000","#cd3700","#8b2500","#da70d6","#ff83fa","#ee7ae9","#cd69c9","#8b4789","#db7093","#eee8aa","#98fb98","#9aff9a","#90ee90","#7ccd7c","#548b54","#afeeee","#bbffff","#aeeeee","#96cdcd","#668b8b","#db7093","#ff82ab","#ee799f","#cd6889","#8b475d","#ffefd5","#ffdab9","#eecbad","#cdaf95","#8b7765","#ffc0cb","#ffb5c5","#eea9b8","#cd919e","#8b636c","#dda0dd","#ffbbff","#eeaeee","#cd96cd","#8b668b","#b0e0e6","#a020f0","#663399","#9b30ff","#912cee","#7d26cd","#551a8b","#ff0000","#ee0000","#cd0000","#8b0000","#bc8f8f","#ffc1c1","#eeb4b4","#cd9b9b","#8b6969","#4169e1","#4876ff","#436eee","#3a5fcd","#27408b","#8b4513","#fa8072","#ff8c69","#ee8262","#cd7054","#8b4c39","#f4a460","#54ff9f","#4eee94","#43cd80","#2e8b57","#fff5ee","#eee5de","#cdc5bf","#8b8682","#a0522d","#ff8247","#ee7942","#cd6839","#8b4726","#87ceeb","#87ceff","#7ec0ee","#6ca6cd","#4a708b","#6a5acd","#836fff","#7a67ee","#6959cd","#473c8b","#708090","#c6e2ff","#b9d3ee","#9fb6cd","#6c7b8b","#fffafa","#eee9e9","#cdc9c9","#8b8989","#00ff7f","#00ee76","#00cd66","#008b45","#4682b4","#63b8ff","#5cacee","#4f94cd","#36648b","#d2b48c","#ffa54f","#ee9a49","#cd853f","#8b5a2b","#d8bfd8","#ffe1ff","#eed2ee","#cdb5cd","#8b7b8b","#ff6347","#ee5c42","#cd4f39","#8b3626","#40e0d0","#00f5ff","#00e5ee","#00c5cd","#00868b","#ee82ee","#d02090","#ff3e96","#ee3a8c","#cd3278","#8b2252","#f5deb3","#ffe7ba","#eed8ae","#cdba96","#8b7e66","#ffffff","#f5f5f5","#ffff00","#eeee00","#cdcd00","#8b8b00","#9acd32"};
    private static final String TAG = ColorUtility.class.getSimpleName();

    /**
     * Gets the matching colors for the color provided
     * @param color
     * @return
     */
    public static int[] getTetradColorPallet(int color) {
        int [] shiftedColors = new int[3];

        shiftedColors[0] = ColorUtility.shiftColorHue(color, 45f);
        shiftedColors[1] = ColorUtility.shiftColorHue(color, 180f);
        shiftedColors[2] = ColorUtility.shiftColorHue(color, 225f);

        return shiftedColors;
    }

    /**
     * Gets the matching colors for the color provided
     * @param color
     * @return
     */
    public static int[] getAdjacentColorPallet(int color) {
        int [] shiftedColors = new int[2];

        shiftedColors[0] = ColorUtility.shiftColorHue(color, -25f);
        shiftedColors[1] = ColorUtility.shiftColorHue(color, 25f);

        return shiftedColors;
    }

    /**
     * Shifts the hue by the offset specified
     * @param color
     * @param offset
     * @return shifted color
     */
    public static int shiftColorHue(int color, float offset) {
        float [] colorShiftHSV = new float[3];
        Color.colorToHSV(color, colorShiftHSV);
        colorShiftHSV[0] = (colorShiftHSV[0] + offset) % 360;

        return Color.HSVToColor(colorShiftHSV);
    }

    /**
     * Shifts the hsv of the color
     * @param color
     * @param offsetHue
     * @param offsetSaturation
     * @param offsetValue
     * @return shifted color
     */
    public static int shiftColorHSV(int color, float offsetHue, float offsetSaturation, float offsetValue) {
        float [] colorShiftHSV = new float[3];
        Color.colorToHSV(color, colorShiftHSV);

        colorShiftHSV[0] = (colorShiftHSV[0] + offsetHue) % 360;
        colorShiftHSV[1] = colorShiftHSV[1] + offsetSaturation;
        colorShiftHSV[2] = colorShiftHSV[2] + offsetValue;

        return Color.HSVToColor(colorShiftHSV);
    }

    /**
     * Convert the color value to hex
     * @param color
     * @return hex string
     */
    public static String colorToHex(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }



    /**
     * ((r2 - r1)2 + (g2 - g1)2 + (b2 - b1)2)1/2
     */
    public static double getColorShadeComparison(int inputColor, int comparisonColor) {
        int inputColorRed = ColorUtility.getRedFromColor(inputColor);
        int inputColorGreen = ColorUtility.getGreenFromColor(inputColor);
        int inputColorBlue = ColorUtility.getBlueFromColor(inputColor);

        int comparisonColorRed = ColorUtility.getRedFromColor(comparisonColor);
        int comparisonColorGreen = ColorUtility.getGreenFromColor(comparisonColor);
        int comparisonColorBlue = ColorUtility.getBlueFromColor(comparisonColor);

        return Math.pow((Math.pow((comparisonColorRed - inputColorRed), 2f) +
                Math.pow((comparisonColorGreen - inputColorGreen), 2f) +
                Math.pow(comparisonColorBlue - inputColorBlue, 2f)), 0.5f);
    }

    /**
     * Generates a monochromatic color pallet of the passed color
     * @param color
     * @return color pallet
     */
    public static Integer[] getMonochromaticColorPallet(int color) {
        Integer [] colorPallet = new Integer[20];


        colorPallet[0] = ColorUtility.shiftColorHSV(color, 0f, -0.5f, 0.5f);
        colorPallet[1] = ColorUtility.shiftColorHSV(color, 0f, -0.4f, 0.4f);
        colorPallet[2] = ColorUtility.shiftColorHSV(color, 0f, -0.3f, 0.3f);
        colorPallet[3] = ColorUtility.shiftColorHSV(color, 0f, -0.2f, 0.2f);
        colorPallet[4] = ColorUtility.shiftColorHSV(color, 0f, -0.1f, 0.1f);
        colorPallet[5] = ColorUtility.shiftColorHSV(color, 0f, 0f, 0.1f);
        colorPallet[6] = ColorUtility.shiftColorHSV(color, 0f, 0.1f, 0.2f);
        colorPallet[7] = ColorUtility.shiftColorHSV(color, 0f, 0.2f, 0.3f);
        colorPallet[8] = ColorUtility.shiftColorHSV(color, 0f, 0.3f, 0.4f);
        colorPallet[9] = ColorUtility.shiftColorHSV(color, 0f, 0.4f, 0.5f);

        colorPallet[10] = ColorUtility.shiftColorHSV(color, 0f, 0.5f, -0.5f);
        colorPallet[11] = ColorUtility.shiftColorHSV(color, 0f, 0.4f, -0.4f);
        colorPallet[12] = ColorUtility.shiftColorHSV(color, 0f, 0.3f, -0.3f);
        colorPallet[13] = ColorUtility.shiftColorHSV(color, 0f, 0.2f, -0.2f);
        colorPallet[14] = ColorUtility.shiftColorHSV(color, 0f, 0.1f, -0.1f);
        colorPallet[15] = ColorUtility.shiftColorHSV(color, 0f, 0f, -0.1f);
        colorPallet[16] = ColorUtility.shiftColorHSV(color, 0f, -0.1f, -0.2f);
        colorPallet[17] = ColorUtility.shiftColorHSV(color, 0f, -0.2f, -0.3f);
        colorPallet[18] = ColorUtility.shiftColorHSV(color, 0f, -0.3f, -0.4f);
        colorPallet[19] = ColorUtility.shiftColorHSV(color, 0f, -0.4f, -0.5f);

        return colorPallet;
    }

    public static int blendColors (int a, int b, float ratio) {
        if (ratio > 1f) {
            ratio = 1f;
        } else if (ratio < 0f) {
            ratio = 0f;
        }
        float iRatio = 1.0f - ratio;

        int aA = (a >> 24 & 0xff);
        int aR = ((a & 0xff0000) >> 16);
        int aG = ((a & 0xff00) >> 8);
        int aB = (a & 0xff);

        int bA = (b >> 24 & 0xff);
        int bR = ((b & 0xff0000) >> 16);
        int bG = ((b & 0xff00) >> 8);
        int bB = (b & 0xff);

        int A = (int) ((aA * iRatio) + (bA * ratio));
        int R = (int) ((aR * iRatio) + (bR * ratio));
        int G = (int) ((aG * iRatio) + (bG * ratio));
        int B = (int) ((aB * iRatio) + (bB * ratio));

        return A << 24 | R << 16 | G << 8 | B;
    }

    /**
     * Gets the view background color
     * @param textView
     * @return
     */
    public static int getBackgroundColor(View textView) {
        ColorDrawable drawable = (ColorDrawable) textView.getBackground();
        if (Build.VERSION.SDK_INT >= 11) {
            return drawable.getColor();
        }
        try {
            Field field = drawable.getClass().getDeclaredField("mState");
            field.setAccessible(true);
            Object object = field.get(drawable);
            field = object.getClass().getDeclaredField("mUseColor");
            field.setAccessible(true);
            return field.getInt(object);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return 0;
    }

    public static String getColorName(String hexColor) {
        return getColorName(Color.parseColor(hexColor));
    }

    public static String getColorName(int color) {
        String name = "";
        int ACCEPT_DIFFERENTIAL_THRESHOLD = 5;

        while (StringUtility.isNull(name)) {
            for (int i = 0; i < colorStringHex.length; i++) {
                String hex = colorStringHex[i];
                if (getColorShadeComparison(color, Color.parseColor(hex)) <= ACCEPT_DIFFERENTIAL_THRESHOLD) {
                    name = colorStringName[i];
                }
            }

            ACCEPT_DIFFERENTIAL_THRESHOLD += 10;
        }

        return name;
    }

    /**
     * Modifies the text color to get a better
     * color for the background the text is displayed on
     * @param color
     * @param offset
     * @return optimized text color
     */
    public static int getOptimizedTextColor(int color, float offset) {
        int textColor = shiftColorHue(color, offset);

        double y = 0.2126 * Color.red(textColor) + 0.7152 * Color.green(textColor) + 0.0722 * Color.blue(textColor);
        if (y < 180)
            textColor = Color.argb(255, 255, 255, 255);
        else
            textColor = Color.argb(255, 0, 0, 0);

        return textColor;
    }

    public static int getRedFromColor(int color) {
        return (color >> 16) & 0xFF;
    }

    public static int getGreenFromColor(int color) {
        return (color >> 8) & 0xFF;
    }

    public static int getBlueFromColor(int color) {
        return color & 0xFF;
    }
}
