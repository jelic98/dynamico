package org.ecloga.dynamico.style;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;

final class EditTextStyler extends TextViewStyler {

    EditTextStyler(ViewFactory factory, Context context) {
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
            try {
                editText.setEllipsize(TextUtils.TruncateAt.valueOf(attributes.getString("ellipsize")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        return editText;
    }
}