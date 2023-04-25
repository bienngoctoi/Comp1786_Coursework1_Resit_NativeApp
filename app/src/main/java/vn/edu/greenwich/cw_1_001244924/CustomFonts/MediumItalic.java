package vn.edu.greenwich.cw_1_001244924.CustomFonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class MediumItalic  extends AppCompatTextView {
    public MediumItalic(@NonNull Context context) {
        super(context);
        SetFonts();
    }

    public MediumItalic(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        SetFonts();
    }

    public MediumItalic(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SetFonts();
    }
    private  void SetFonts()
    {
        Typeface typeface = Utils.getOswaldMediumItalic(getContext());
        setTypeface(typeface);
    }
}
