package org.ecloga.dynamico.style;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.text.InputType;
import android.text.Layout;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import org.ecloga.dynamico.Util;
import org.json.JSONObject;
import java.util.Locale;

class TextViewStyler extends ViewStyler {

    TextViewStyler(ViewFactory factory, Context context) {
        super(factory, context);
    }

    @Override
    public View style(View view, JSONObject attributes) throws Exception {
        super.style(view, attributes);

        TextView textView = (TextView) view;

        if(attributes.has("text")) {
            textView.setText(attributes.getString("text"));
        }

        if(attributes.has("error")) {
            textView.setError(attributes.getString("error"));
        }

        if(attributes.has("hint")) {
            textView.setHint(attributes.getString("hint"));
        }

        if(attributes.has("textKeepState")) {
            textView.setTextKeepState(attributes.getString("textKeepState"));
        }

        if(attributes.has("textSize")) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Display.unitToPx(attributes.getString("textSize"), context));
        }

        if(attributes.has("textColor")) {
            try {
                textView.setTextColor(Color.parseColor(attributes.getString("textColor")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(attributes.has("hintTextColor")) {
            try {
                textView.setHintTextColor(Color.parseColor(attributes.getString("hintTextColor")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(attributes.has("linkTextColor")) {
            try {
                textView.setLinkTextColor(Color.parseColor(attributes.getString("linkTextColor")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(attributes.has("highlightColor")) {
            try {
                textView.setHighlightColor(Color.parseColor(attributes.getString("highlightColor")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(attributes.has("singleLine") && attributes.getBoolean("singleLine")) {
            textView.setSingleLine();
        }

        if(attributes.has("textStyle")) {
            String style = attributes.getString("textStyle");

            if(style.equalsIgnoreCase("normal")) {
                textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
            }else if(style.equalsIgnoreCase("bold")) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
            }else if(style.equalsIgnoreCase("italic")) {
                textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
            }else if(style.equalsIgnoreCase("bold_italic")) {
                textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
            }
        }

        if(attributes.has("gravity")) {
            String gravity = attributes.getString("gravity");

            if(gravity.equalsIgnoreCase("start")) {
                textView.setGravity(Gravity.START);
            }else if(gravity.equalsIgnoreCase("top")) {
                textView.setGravity(Gravity.TOP);
            }else if(gravity.equalsIgnoreCase("end")) {
                textView.setGravity(Gravity.END);
            }else if(gravity.equalsIgnoreCase("bottom")) {
                textView.setGravity(Gravity.BOTTOM);
            }else if(gravity.equalsIgnoreCase("center")) {
                textView.setGravity(Gravity.CENTER);
            }else if(gravity.equalsIgnoreCase("center_horizontal")) {
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
            }else if(gravity.equalsIgnoreCase("center_vertical")) {
                textView.setGravity(Gravity.CENTER_VERTICAL);
            }
        }

        if(attributes.has("ellipsize")) {
            try {
                textView.setEllipsize(TextUtils.TruncateAt.valueOf(attributes.getString("ellipsize")));
            }catch(IllegalArgumentException e) {
                Util.log("Style error", e.getMessage());
            }
        }

        if(attributes.has("allCaps")) {
            textView.setAllCaps(attributes.getBoolean("allCaps"));
        }

        if(attributes.has("cursorVisible")) {
            textView.setCursorVisible(attributes.getBoolean("cursorVisible"));
        }

        if(attributes.has("enabled")) {
            textView.setEnabled(attributes.getBoolean("enabled"));
        }

        if(attributes.has("freezesText")) {
            textView.setFreezesText(attributes.getBoolean("freezesText"));
        }

        if(attributes.has("horizontallyScrolling")) {
            textView.setHorizontallyScrolling(attributes.getBoolean("horizontallyScrolling"));
        }

        if(attributes.has("includeFontPadding")) {
            textView.setIncludeFontPadding(attributes.getBoolean("includeFontPadding"));
        }

        if(attributes.has("linksClickable")) {
            textView.setLinksClickable(attributes.getBoolean("linksClickable"));
        }

        if(attributes.has("selectAllOnFocus")) {
            textView.setSelectAllOnFocus(attributes.getBoolean("selectAllOnFocus"));
        }

        if(attributes.has("selected")) {
            textView.setSelected(attributes.getBoolean("selected"));
        }

        if(attributes.has("textIsSelectable")) {
            textView.setTextIsSelectable(attributes.getBoolean("textIsSelectable"));
        }

        if(attributes.has("privateImeOptions")) {
            textView.setPrivateImeOptions(attributes.getString("privateImeOptions"));
        }

        if(attributes.has("textScaleX")) {
            textView.setTextScaleX((float) attributes.getDouble("textScaleX"));
        }

        if(attributes.has("width")) {
            textView.setWidth(Display.unitToPx(attributes.getString("width"), context));
        }

        if(attributes.has("maxWidth")) {
            textView.setMaxWidth(Display.unitToPx(attributes.getString("maxWidth"), context));
        }

        if(attributes.has("minWidth")) {
            textView.setMinWidth(Display.unitToPx(attributes.getString("minWidth"), context));
        }

        if(attributes.has("height")) {
            textView.setHeight(Display.unitToPx(attributes.getString("height"), context));
        }

        if(attributes.has("maxHeight")) {
            textView.setMaxHeight(Display.unitToPx(attributes.getString("maxHeight"), context));
        }

        if(attributes.has("minHeight")) {
            textView.setMinHeight(Display.unitToPx(attributes.getString("minHeight"), context));
        }

        if(attributes.has("lines")) {
            textView.setLines(attributes.getInt("lines"));
        }

        if(attributes.has("maxLines")) {
            textView.setMaxLines(attributes.getInt("maxLines"));
        }

        if(attributes.has("minLines")) {
            textView.setMinLines(attributes.getInt("minLines"));
        }

        if(attributes.has("ems")) {
            textView.setEms(attributes.getInt("ems"));
        }

        if(attributes.has("maxEms")) {
            textView.setMaxEms(attributes.getInt("maxEms"));
        }

        if(attributes.has("minEms")) {
            textView.setMinEms(attributes.getInt("minEms"));
        }

        if(attributes.has("compoundDrawablePadding")) {
            textView.setCompoundDrawablePadding(Display.unitToPx(attributes.getString("compoundDrawablePadding"), context));
        }

        if(attributes.has("onKey")) {
            final MethodInvoker invoker = new MethodInvoker
                    .Builder(attributes.getJSONObject("onKey"), context)
                    .build();

            textView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    invoker.invoke();

                    return false;
                }
            });
        }

        if(attributes.has("textLocale")) {
            String locale = attributes.getString("textLocale");

            if(locale.equalsIgnoreCase("english")) {
                textView.setTextLocale(Locale.ENGLISH);
            }else if(locale.equalsIgnoreCase("french")) {
                textView.setTextLocale(Locale.FRENCH);
            }else if(locale.equalsIgnoreCase("german")) {
                textView.setTextLocale(Locale.GERMAN);
            }else if(locale.equalsIgnoreCase("italian")) {
                textView.setTextLocale(Locale.ITALIAN);
            }else if(locale.equalsIgnoreCase("japanese")) {
                textView.setTextLocale(Locale.JAPANESE);
            }else if(locale.equalsIgnoreCase("korean")) {
                textView.setTextLocale(Locale.KOREAN);
            }else if(locale.equalsIgnoreCase("chinese")) {
                textView.setTextLocale(Locale.CHINESE);
            }else if(locale.equalsIgnoreCase("simplified_chinese")) {
                textView.setTextLocale(Locale.SIMPLIFIED_CHINESE);
            }else if(locale.equalsIgnoreCase("traditional_chinese")) {
                textView.setTextLocale(Locale.TRADITIONAL_CHINESE);
            }else if(locale.equalsIgnoreCase("france")) {
                textView.setTextLocale(Locale.FRANCE);
            }else if(locale.equalsIgnoreCase("germany")) {
                textView.setTextLocale(Locale.GERMANY);
            }else if(locale.equalsIgnoreCase("italy")) {
                textView.setTextLocale(Locale.ITALY);
            }else if(locale.equalsIgnoreCase("japan")) {
                textView.setTextLocale(Locale.JAPAN);
            }else if(locale.equalsIgnoreCase("korea")) {
                textView.setTextLocale(Locale.KOREA);
            }else if(locale.equalsIgnoreCase("uk")) {
                textView.setTextLocale(Locale.UK);
            }else if(locale.equalsIgnoreCase("us")) {
                textView.setTextLocale(Locale.US);
            }else if(locale.equalsIgnoreCase("canada")) {
                textView.setTextLocale(Locale.CANADA);
            }else if(locale.equalsIgnoreCase("canada_french")) {
                textView.setTextLocale(Locale.CANADA_FRENCH);
            }
        }

        if(attributes.has("imeOptions")) {
            String options = attributes.getString("imeOptions");

            if(options.equalsIgnoreCase("done")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            }else if(options.equalsIgnoreCase("go")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_GO);
            }else if(options.equalsIgnoreCase("next")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            }else if(options.equalsIgnoreCase("none")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_NONE);
            }else if(options.equalsIgnoreCase("previous")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
            }else if(options.equalsIgnoreCase("search")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            }else if(options.equalsIgnoreCase("send")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_SEND);
            }else if(options.equalsIgnoreCase("unspecified")) {
                textView.setImeOptions(EditorInfo.IME_ACTION_UNSPECIFIED);
            }
        }

        if(attributes.has("inputType")) {
            String type = attributes.getString("inputType");

            if(type.equalsIgnoreCase("class_text")) {
                textView.setInputType(InputType.TYPE_CLASS_TEXT);
            }else if(type.equalsIgnoreCase("class_datetime")) {
                textView.setInputType(InputType.TYPE_CLASS_DATETIME);
            }else if(type.equalsIgnoreCase("class_number")) {
                textView.setInputType(InputType.TYPE_CLASS_NUMBER);
            }else if(type.equalsIgnoreCase("class_phone")) {
                textView.setInputType(InputType.TYPE_CLASS_PHONE);
            }else if(type.equalsIgnoreCase("datetime_variation_date")) {
                textView.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
            }else if(type.equalsIgnoreCase("datetime_variation_normal")) {
                textView.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
            }else if(type.equalsIgnoreCase("datetime_variation_time")) {
                textView.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
            }else if(type.equalsIgnoreCase("mask_class")) {
                textView.setInputType(InputType.TYPE_MASK_CLASS);
            }else if(type.equalsIgnoreCase("mask_flags")) {
                textView.setInputType(InputType.TYPE_MASK_FLAGS);
            }else if(type.equalsIgnoreCase("mask_variation")) {
                textView.setInputType(InputType.TYPE_MASK_VARIATION);
            }else if(type.equalsIgnoreCase("null")) {
                textView.setInputType(InputType.TYPE_NULL);
            }else if(type.equalsIgnoreCase("number_flag_decimal")) {
                textView.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }else if(type.equalsIgnoreCase("number_flag_signed")) {
                textView.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
            }else if(type.equalsIgnoreCase("number_variation_normal")) {
                textView.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
            }else if(type.equalsIgnoreCase("number_variation_password")) {
                textView.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            }else if(type.equalsIgnoreCase("text_flag_auto_complete")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
            }else if(type.equalsIgnoreCase("text_flag_auto_correct")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
            }else if(type.equalsIgnoreCase("text_flag_cap_characters")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            }else if(type.equalsIgnoreCase("text_flag_cap_sentences")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            }else if(type.equalsIgnoreCase("text_flag_cap_words")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            }else if(type.equalsIgnoreCase("text_flag_ime_multi_line")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
            }else if(type.equalsIgnoreCase("text_flag_multi_line")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            }else if(type.equalsIgnoreCase("text_flag_no_suggestions")) {
                textView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            }else if(type.equalsIgnoreCase("text_variation_email_address")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            }else if(type.equalsIgnoreCase("text_variation_email_subject")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
            }else if(type.equalsIgnoreCase("text_variation_filter")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_FILTER);
            }else if(type.equalsIgnoreCase("text_variation_long_message")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
            }else if(type.equalsIgnoreCase("text_variation_normal")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            }else if(type.equalsIgnoreCase("text_variation_password")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else if(type.equalsIgnoreCase("text_variation_person_name")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            }else if(type.equalsIgnoreCase("text_variation_phonetic")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_PHONETIC);
            }else if(type.equalsIgnoreCase("text_variation_postal_address")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
            }else if(type.equalsIgnoreCase("text_variation_short_message")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
            }else if(type.equalsIgnoreCase("text_variation_uri")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
            }else if(type.equalsIgnoreCase("text_variation_visible_password")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }else if(type.equalsIgnoreCase("text_variation_web_edit_text")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
            }else if(type.equalsIgnoreCase("text_variation_web_email_address")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
            }else if(type.equalsIgnoreCase("text_variation_web_password")) {
                textView.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
            }
        }

        if(attributes.has("rawInputType")) {
            String type = attributes.getString("rawInputType");

            if(type.equalsIgnoreCase("class_text")) {
                textView.setRawInputType(InputType.TYPE_CLASS_TEXT);
            }else if(type.equalsIgnoreCase("class_datetime")) {
                textView.setRawInputType(InputType.TYPE_CLASS_DATETIME);
            }else if(type.equalsIgnoreCase("class_number")) {
                textView.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            }else if(type.equalsIgnoreCase("class_phone")) {
                textView.setRawInputType(InputType.TYPE_CLASS_PHONE);
            }else if(type.equalsIgnoreCase("datetime_variation_date")) {
                textView.setRawInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
            }else if(type.equalsIgnoreCase("datetime_variation_normal")) {
                textView.setRawInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
            }else if(type.equalsIgnoreCase("datetime_variation_time")) {
                textView.setRawInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
            }else if(type.equalsIgnoreCase("mask_class")) {
                textView.setRawInputType(InputType.TYPE_MASK_CLASS);
            }else if(type.equalsIgnoreCase("mask_flags")) {
                textView.setRawInputType(InputType.TYPE_MASK_FLAGS);
            }else if(type.equalsIgnoreCase("mask_variation")) {
                textView.setRawInputType(InputType.TYPE_MASK_VARIATION);
            }else if(type.equalsIgnoreCase("null")) {
                textView.setRawInputType(InputType.TYPE_NULL);
            }else if(type.equalsIgnoreCase("number_flag_decimal")) {
                textView.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }else if(type.equalsIgnoreCase("number_flag_signed")) {
                textView.setRawInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
            }else if(type.equalsIgnoreCase("number_variation_normal")) {
                textView.setRawInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
            }else if(type.equalsIgnoreCase("number_variation_password")) {
                textView.setRawInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            }else if(type.equalsIgnoreCase("text_flag_auto_complete")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
            }else if(type.equalsIgnoreCase("text_flag_auto_correct")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
            }else if(type.equalsIgnoreCase("text_flag_cap_characters")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            }else if(type.equalsIgnoreCase("text_flag_cap_sentences")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            }else if(type.equalsIgnoreCase("text_flag_cap_words")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            }else if(type.equalsIgnoreCase("text_flag_ime_multi_line")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
            }else if(type.equalsIgnoreCase("text_flag_multi_line")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            }else if(type.equalsIgnoreCase("text_flag_no_suggestions")) {
                textView.setRawInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            }else if(type.equalsIgnoreCase("text_variation_email_address")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            }else if(type.equalsIgnoreCase("text_variation_email_subject")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
            }else if(type.equalsIgnoreCase("text_variation_filter")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_FILTER);
            }else if(type.equalsIgnoreCase("text_variation_long_message")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
            }else if(type.equalsIgnoreCase("text_variation_normal")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            }else if(type.equalsIgnoreCase("text_variation_password")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else if(type.equalsIgnoreCase("text_variation_person_name")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            }else if(type.equalsIgnoreCase("text_variation_phonetic")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_PHONETIC);
            }else if(type.equalsIgnoreCase("text_variation_postal_address")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
            }else if(type.equalsIgnoreCase("text_variation_short_message")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
            }else if(type.equalsIgnoreCase("text_variation_uri")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_URI);
            }else if(type.equalsIgnoreCase("text_variation_visible_password")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }else if(type.equalsIgnoreCase("text_variation_web_edit_text")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);
            }else if(type.equalsIgnoreCase("text_variation_web_email_address")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
            }else if(type.equalsIgnoreCase("text_variation_web_password")) {
                textView.setRawInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
            }
        }

        if(attributes.has("autoLinkMask")) {
            String mask = attributes.getString("autoLinkMask");

            if(mask.equalsIgnoreCase("all")) {
                textView.setAutoLinkMask(Linkify.ALL);
            }else if(mask.equalsIgnoreCase("email_addresses")) {
                textView.setAutoLinkMask(Linkify.EMAIL_ADDRESSES);
            }else if(mask.equalsIgnoreCase("map_addresses")) {
                textView.setAutoLinkMask(Linkify.MAP_ADDRESSES);
            }else if(mask.equalsIgnoreCase("phone_numbers")) {
                textView.setAutoLinkMask(Linkify.PHONE_NUMBERS);
            }else if(mask.equalsIgnoreCase("web_urls")) {
                textView.setAutoLinkMask(Linkify.WEB_URLS);
            }
        }

        if(attributes.has("marqueeRepeatLimit")) {
            textView.setMarqueeRepeatLimit(attributes.getInt("marqueeRepeatLimit"));
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(attributes.has("letterSpacing")) {
                textView.setLetterSpacing((float) attributes.getDouble("letterSpacing"));
            }

            if(attributes.has("elegantTextHeight")) {
                textView.setElegantTextHeight(attributes.getBoolean("elegantTextHeight"));
            }

            if(attributes.has("showSoftInputOnFocus")) {
                textView.setShowSoftInputOnFocus(attributes.getBoolean("showSoftInputOnFocus"));
            }

            if(attributes.has("fontFeatureSettings")) {
                textView.setFontFeatureSettings(attributes.getString("fontFeatureSettings"));
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(attributes.has("compoundDrawableTintMode")) {
                try {
                    textView.setCompoundDrawableTintMode(PorterDuff.Mode.valueOf(attributes.getString("compoundDrawableTintMode")));
                }catch(IllegalArgumentException e) {
                    Util.log("Style error", e.getMessage());
                }
            }

            if(attributes.has("breakStrategy")) {
                String strategy = attributes.getString("breakStrategy");

                if(strategy.equalsIgnoreCase("balanced")) {
                    textView.setBreakStrategy(Layout.BREAK_STRATEGY_BALANCED);
                }else if(strategy.equalsIgnoreCase("simple")) {
                    textView.setBreakStrategy(Layout.BREAK_STRATEGY_SIMPLE);
                }else if(strategy.equalsIgnoreCase("high_quality")) {
                    textView.setBreakStrategy(Layout.BREAK_STRATEGY_HIGH_QUALITY);
                }
            }

            if(attributes.has("hyphenationFrequency")) {
                String frequency = attributes.getString("hyphenationFrequency");

                if(frequency.equalsIgnoreCase("normal")) {
                    textView.setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_NORMAL);
                }else if(frequency.equalsIgnoreCase("full")) {
                    textView.setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_FULL);
                }else if(frequency.equalsIgnoreCase("none")) {
                    textView.setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_NONE);
                }
            }
        }

        if(Build.VERSION.SDK_INT >= 26) {
            if(attributes.has("fontVariationSettings")) {
                textView.setFontVariationSettings(attributes.getString("fontVariationSettings"));
            }

            if(attributes.has("autoSizeTextTypeWithDefaults")) {
                String auto = attributes.getString("autoSizeTextTypeWithDefaults");

                if(auto.equalsIgnoreCase("uniform")) {
                    textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
                }else if(auto.equalsIgnoreCase("none")) {
                    textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
                }
            }

            if(attributes.has("justificationMode")) {
                String mode = attributes.getString("justificationMode");

                if(mode.equalsIgnoreCase("inter_word")) {
                    textView.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                }else if(mode.equalsIgnoreCase("none")) {
                    textView.setJustificationMode(Layout.JUSTIFICATION_MODE_NONE);
                }
            }
        }

        return textView;
    }
}