package vn.edu.greenwich.cw_1_001244924.CustomFonts;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    private  static Typeface OswaldMediumItalic;
    private  static Typeface OswaldMedium;
    private  static Typeface OswaldRegular;

    public static Typeface getOswaldMediumItalic(Context context) {
        if (OswaldMediumItalic == null)
        {
            OswaldMediumItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Oswald-MediumItalic.ttf");
        }
        return OswaldMediumItalic;
    }

    public static Typeface getOswaldMedium(Context context) {

        if (OswaldMedium == null)
        {
            OswaldMedium = Typeface.createFromAsset(context.getAssets(), "fonts/Oswald-Medium.ttf");
        }
        return OswaldMedium;

    }

    public static Typeface getOswaldRegular(Context context) {
        if (OswaldRegular == null)
        {
            OswaldRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Oswald-Regular.ttf");
        }
        return OswaldRegular;
    }
}
