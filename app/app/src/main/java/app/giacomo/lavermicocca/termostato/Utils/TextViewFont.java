package app.giacomo.lavermicocca.termostato.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import app.giacomo.lavermicocca.termostato.R;

/**
 * Created by Giacomo on 06/01/2015.
 */
public class TextViewFont extends TextView {

    public TextViewFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont(context, attrs);
    }

    public TextViewFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context, attrs);

    }

    public TextViewFont(Context context) {
        super(context);
    }

    private void setFont(Context context, AttributeSet attrs)
    {
        if(context != null && attrs != null) {
            //Typeface.createFromAsset doesn't work in the layout editor. Skipping...
            if (this.isInEditMode()) {
                return;
            }

            TypedArray attribute = getContext().obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
            String fontName = attribute.getString(R.styleable.TypefacedTextView_typeface);

            if (fontName!=null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
                this.setTypeface(myTypeface);
            }
            attribute.recycle();
        }
    }//setFont
}
