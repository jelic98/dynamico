package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.ecloga.dynamico.Display;
import org.ecloga.dynamico.Util;
import org.ecloga.dynamico.ViewFactory;
import org.json.JSONObject;

public class EditTextStyler extends TextViewStyler {

    public EditTextStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        EditText editText = (EditText) view;

        if(attributes.has("hint")) {
            editText.setHint(attributes.getString("hint"));
        }

        if(attributes.has("ellipsize")) {
            editText.setEllipsize(TextUtils.TruncateAt.valueOf(attributes.getString("ellipsize")));
        }

        return editText;
    }
}