package vn.edu.greenwich.cw_1_001244924.CustomFonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class Medium extends AppCompatTextView {
    public Medium(@NonNull Context context) {
        super(context);
        SetFonts();
    }

    public Medium(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        SetFonts();
    }

    public Medium(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SetFonts();
    }
    private  void SetFonts()
    {
        Typeface typeface = Utils.getOswaldMedium(getContext());
        setTypeface(typeface);
    }
}
