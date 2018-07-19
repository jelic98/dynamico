## Content

* [Single view](#single-view)
* [Single layout](#single-layout)
* [Multiple views](#multiple-views)
* [Recursive views](#recursive-views)
* [Device targeting](#device-targeting)
* [Configuration fields](#configuration-fields)
* [Event listeners](#event-listeners)
* [Resource caching](#resource-caching)
* [Custom views](#custom-views)
* [Supported attributes](#supported-attributes)
    * [View](#view)
    * [LinearLayout](#linearlayout)
    * [RelativeLayout](#relativelayout)
    * [FrameLayout](#framelayout)
    * [GridLayout](#gridlayout)
    * [ScrollView](#scrollview)
    * [TextView](#textview)
    * [EditText](#edittext)
    * [ImageView](#imageview)
    * [Button](#button)
    * [CompoundButton](#compoundbutton)
    * [CheckBox](#checkbox)
    * [RadioButton](#radiobutton)
    * [Switch](#switch)
    * [ToggleButton](#togglebutton)
    * [GridView](#gridview)

### Single view

```json
{
  "views":[
    {  
      "class":"android.widget.TextView",
      "attributes":{  
        "text":"Sample text",
        "textColor":"#FF69B4"
      }
    }
  ]
}
```

### Single layout

```json
{
  "class":"android.widget.LinearLayout",
  "views":[
    ...
  ]
}
```

### Multiple views

```json
{
  "views":[
    {  
      "class":"android.widget.TextView",
      "attributes":{  
        "text":"Sample text",
        "textColor":"#FF69B4"
      }
    },
    {  
      "class":"android.widget.ImageView",
      "attributes":{
        "src": "https://ecloga.org/projects/dynamico/logo.png"
      }
    }
  ]
}
```

### Recursive views

```json
{
  "views":[
    {  
      "class":"android.widget.TextView",
      "attributes":{  
        "text":"Sample text",
        "textColor":"#FF69B4"
      }
    },
    {
      "class": "android.widget.LinearLayout",
      "views":[
        {  
          "class":"android.widget.TextView",
          "attributes":{
            "text":"Recursively added"
          }
        }
      ]
    }
  ]
}
```

### Device targeting

```json
{
  "targets": [
    {
      "key":"BRAND | MODEL | SDK",
      "value":"Samsung | SM-J330F | 27 | ...",
      "matcher":"EQUAL | BELOW | ABOVE",
      "views": [
        ...
      ]
    }
  ],
  "default":{
    "views":[
      ...   
    ]
  }
}
```

### Configuration fields

```json
{
  "views":[
    {  
      "class":"android.widget.TextView",
      "attributes":{  
        "text":"Sample text",
        "conditions":[
          {
            "class": "org.ecloga.demo.AppConfig",
            "field": "THEME",
            "value": "red",
            "attributes": {
              "textColor": "#FF0000"
             }
          },
          {
            "class": "org.ecloga.demo.AppConfig",
            "field": "THEME",
            "value": "blue",
            "attributes": {
              "textColor": "#0000FF"
            }
          }
        ] 
      }
    }
  ]
}
```

### Event listeners

```json
{  
  "class":"android.widget.ImageView",
  "attributes":{  
    "src":"https://ecloga.org/projects/dynamico/logo.png",
    "clickable":"true",
    "onClick":{
      "class":"org.ecloga.demo.ActivityMain",
      "method":"onImageClick",
      "parameters":[
        {
          "type": "java.lang.String",
          "value": "Sample text"
        },
        {
          "type": "java.lang.Integer",
          "value": "123"
        }
      ]
    }
  }
}
```

### Resource caching

```json
{  
  "class":"android.widget.ImageView",
  "attributes":{  
    "cache":"TRUE | FALSE"
   }
}
```

### Custom views

```json
{  
  "class":"android.widget.CustomView",
  "attributes":{
    "custom":[  
      {  
        "method":"setCustomText",
        "parameters":[  
          {  
            "type":"java.lang.String",
            "value":"Sample text"
          }
        ]
      }
    ]
  }
}
```

### Supported attributes

#### View

Parent of all other views. Every view inherits attributes from this.

```json
{  
  "class":"android.widget.View",
  "attributes":{
      "id":"int",
      "tag":"string",
      "layout_width":"units | wrap_content | match_parent",
      "layout_height":"units | wrap_content | match_parent",
      "minWidth":"units",
      "minHeight":"units",
      "layout_margin":"units",
      "layout_marginStart":"units",
      "layout_marginTop":"units",
      "layout_marginEnd":"units",
      "layout_marginBottom":"units", 
      "padding":"units",
      "paddingStart":"units", 
      "paddingTop":"units", 
      "paddingEnd":"units",
      "paddingBottom":"units", 
      "visibility":"VISIBLE | INVISIBLE | GONE", 
      "alpha":"float",
      "rotation":"float", 
      "rotationX":"float", 
      "rotationY":"float", 
      "translationX":"float", 
      "translationY":"float", 
      "scaleX":"float", 
      "scaleY":"float", 
      "pivotX":"float", 
      "pivotY":"float", 
      "x":"float", 
      "y":"float", 
      "clickable":"TRUE | FALSE", 
      "background":"resource_url",
      "onClick":"event_listener",
      "conditions":"device_targets",
      "layout_weight":"float",
      "layout_gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
      "center_horizontal":"TRUE | FALSE",
      "center_vertical":"TRUE | FALSE",
      "center_in_parent":"TRUE | FALSE",
      "align_parent_start":"TRUE | FALSE",
      "align_parent_top":"TRUE | FALSE",
      "align_parent_end":"TRUE | FALSE",
      "align_parent_bottom":"TRUE | FALSE",
      "layout_above":"view_id",
      "layout_below":"view_id",
      "layout_alignStart":"view_id",
      "layout_alignTop":"view_id",
      "layout_alignEnd":"view_id",
      "layout_alignBottom":"view_id",
      "layout_alignBaseline":"view_id",
      "layout_toStartOf":"view_id",
      "layout_toEndOf":"view_id",
    }
}
```

#### LinearLayout

Inherits everything from View.

```json
{  
  "class":"android.widget.LinearLayout",
  "attributes":{
    "orientation":"VERTICAL | HORIZONTAL",
    "weightSum":"float",
    "gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
    "horizontalGravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
    "verticalGravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
    "showDividers":"BEGINNING | MIDDLE | END | NONE",
    "dividerDrawable":"resource_url",
    "dividerPadding":"units",
    "baselineAligned":"TRUE | FALSE",
    "measureWithLargestChildEnabled":"TRUE | FALSE",
    "baselineAlignedChildIndex":"int"
  }
}
``` 

#### RelativeLayout

Inherits everything from View.

```json
{  
  "class":"android.widget.RelativeLayout",
  "attributes":{
    "gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
    "horizontalGravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
    "verticalGravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
    "ignoreGravity":"view_id"
  }
}
``` 

#### FrameLayout

Inherits everything from View.

```json
{  
  "class":"android.widget.FrameLayout",
  "attributes":{
    "measureAllChildren":"TRUE | FALSE",
    "foregroundGravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL"
  }
}
```

#### GridLayout

Inherits everything from View.

```json
{  
  "class":"android.widget.GridLayout",
  "attributes":{
    "alignmentMode":"ALIGN_BOUNDS | ALIGN_MARGINS",
    "orientation":"VERTICAL | HORIZONTAL",
    "columnCount":"int",
    "rowCount":"int", 
    "columnOrderPreserved":"TRUE | FALSE",
    "rowOrderPreserved":"TRUE | FALSE",
    "useDefaultMargins":"TRUE | FALSE"
  }
}
``` 

#### ScrollView

Inherits everything from FrameLayout.

```json
{  
  "class":"android.widget.ScrollView",
  "attributes":{
    "fillViewport":"TRUE | FALSE",
    "smoothScrollingEnabled":"TRUE | FALSE",
    "overScrollMode":"ALWAYS | NEVER | IF_CONTENT_SCROLLS"
  }
}
``` 

#### TextView

Inherits everything from View.

```json
{  
  "class":"android.widget.TextView",
  "attributes":{
    "text":"string",
    "error":"string",
    "hint":"string",
    "textKeepState":"string",
    "textSize":"units",
    "textColor":"hex_value | color_name",
    "hintTextColor":"hex_value | color_name",
    "linkTextColor":"hex_value | color_name",
    "highlightColor":"hex_value | color_name",
    "singleLine":"TRUE | FALSE",
    "textStyle":"NORMAL | BOLD | ITALIC | BOLD_ITALIC",    
    "gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
    "ellipsize":"START | MIDDLE | END | MARQUEE | END_SMALL",
    "allCaps":"TRUE | FALSE",
    "cursorVisible":"TRUE | FALSE",
    "enabled":"TRUE | FALSE",
    "freezesText":"TRUE | FALSE",
    "horizontallyScrolling":"TRUE | FALSE",
    "includeFontPadding":"TRUE | FALSE",
    "linksClickable":"TRUE | FALSE",
    "selectAllOnFocus":"TRUE | FALSE",
    "selected":"TRUE | FALSE",
    "textIsSelectable":"TRUE | FALSE",
    "privateImeOptions":"string",
    "textScaleX":"float",
    "width":"units",
    "maxWidth":"units",
    "minWidth":"units",
    "height":"units",
    "maxHeight":"units",
    "minHeight":"units",
    "lines":"int",
    "maxLines":"int",
    "minLines":"int",
    "ems":"int",
    "maxEms":"int",
    "minEms":"int",
    "compoundDrawablePadding":"units",
    "onKey":"event_listener",
    "textLocale":"ENGLISH | FRENCH | GERMAN | ITALIAN | JAPANESE | KOREAN | CHINESE | SIMPLIFIED_CHINESE | TRADITIONAL_CHINESE | FRANCE | GERMANY | ITALY | JAPAN | KOREA | UK | US | CANADA | CANADA_FRENCH",
    "imeOptions":"DONE | GO | NEXT | NONE | PREVIOUS | SEARCH | SEND | UNSPECIFIED",
    "inputType":"CLASS_TEXT | CLASS_DATETIME | CLASS_NUMBER | CLASS_PHONE | DATETIME_VARIATION_DATE | DATETIME_VARIATION_NORMAL | DATETIME_VARIATION_TIME | MASK_CLASS | MASK_FLAGS | MASK_VARIATION | NULL | NUMBER_FLAG_DECIMAL | NUMBER_FLAG_SIGNED | NUMBER_VARIATION_NORMAL | NUMBER_VARIATION_PASSWORD | TEXT_FLAG_AUTO_COMPLETE | TEXT_FLAG_AUTO_CORRECT | TEXT_FLAG_CAP_CHARACTERS | TEXT_FLAG_CAP_SENTENCES | TEXT_FLAG_CAP_WORDS | TEXT_FLAG_IME_MULTI_LINE | TEXT_FLAG_MULTI_LINE | TEXT_FLAG_NO_SUGGESTIONS | TEXT_VARIATION_EMAIL_ADDRESS | TEXT_VARIATION_EMAIL_SUBJECT | TEXT_VARIATION_FILTER | TEXT_VARIATION_LONG_MESSAGE | TEXT_VARIATION_NORMAL | TEXT_VARIATION_PASSWORD | TEXT_VARIATION_PERSON_NAME | TEXT_VARIATION_PHONETIC | TEXT_VARIATION_POSTAL_ADDRESS | TEXT_VARIATION_SHORT_MESSAGE | TEXT_VARIATION_URI | TEXT_VARIATION_VISIBLE_PASSWORD | TEXT_VARIATION_WEB_EDIT_TEXT | TEXT_VARIATION_WEB_EMAIL_ADDRESS | TEXT_VARIATION_WEB_PASSWORD",
    "rawInputType":"CLASS_TEXT | CLASS_DATETIME | CLASS_NUMBER | CLASS_PHONE | DATETIME_VARIATION_DATE | DATETIME_VARIATION_NORMAL | DATETIME_VARIATION_TIME | MASK_CLASS | MASK_FLAGS | MASK_VARIATION | NULL | NUMBER_FLAG_DECIMAL | NUMBER_FLAG_SIGNED | NUMBER_VARIATION_NORMAL | NUMBER_VARIATION_PASSWORD | TEXT_FLAG_AUTO_COMPLETE | TEXT_FLAG_AUTO_CORRECT | TEXT_FLAG_CAP_CHARACTERS | TEXT_FLAG_CAP_SENTENCES | TEXT_FLAG_CAP_WORDS | TEXT_FLAG_IME_MULTI_LINE | TEXT_FLAG_MULTI_LINE | TEXT_FLAG_NO_SUGGESTIONS | TEXT_VARIATION_EMAIL_ADDRESS | TEXT_VARIATION_EMAIL_SUBJECT | TEXT_VARIATION_FILTER | TEXT_VARIATION_LONG_MESSAGE | TEXT_VARIATION_NORMAL | TEXT_VARIATION_PASSWORD | TEXT_VARIATION_PERSON_NAME | TEXT_VARIATION_PHONETIC | TEXT_VARIATION_POSTAL_ADDRESS | TEXT_VARIATION_SHORT_MESSAGE | TEXT_VARIATION_URI | TEXT_VARIATION_VISIBLE_PASSWORD | TEXT_VARIATION_WEB_EDIT_TEXT | TEXT_VARIATION_WEB_EMAIL_ADDRESS | TEXT_VARIATION_WEB_PASSWORD",
    "autoLinkMask":"ALL | EMAIL_ADDRESSES | MAP_ADDRESSES | PHONE_NUMBERS | WEB_URLS",
    "marqueeRepeatLimit":"int",
    "letterSpacing":"float",
    "elegantTextHeight":"TRUE | FALSE",
    "showSoftInputOnFocus":"TRUE | FALSE", 
    "fontFeatureSettings":"string",
    "compoundDrawableTintMode":"CLEAR | SRC | DST | SRC_OVER | DST_OVER | SRC_IN | DST_IN | SRC_OUT | DST_OUT | SRC_ATOP | DST_ATOP | XOR | DARKEN | LIGHTEN | MULTIPLY | SCREEN | ADD | OVERLAY",
    "hyphenationFrequency":"NORMAL | FULL | NONE",
    "breakStrategy":"BALANCED | SIMPLE | HIGH_QUALITY",
    "fontVariationSettings":"string",
    "autoSizeTextTypeWithDefaults":"UNIFORM | NONE",
    "justificationMode":"INTER_WORD | NONE"
}
```

#### EditText

Inherits everything from TextView.

#### ImageView

Inherits everything from View.

```json
{  
  "class":"android.widget.ImageView",
  "attributes":{
    "src":"resource_url",
    "scaleType":"MATRIX | FIT_XY | FIT_START | FIT_CENTER | FIT_END | CENTER | CENTER_CROP | CENTER_INSIDE",
    "adjustViewBounds":"TRUE | FALSE",
    "maxWidth":"units",
    "maxHeight":"units",
    "baseline":"units",
    "baselineAlignBottom":"TRUE | FALSE",
    "cropToPadding":"TRUE | FALSE",
    "selected":"TRUE | FALSE",
    "imageAlpha":"int",
    "imageLevel":"int",
    "colorFilter":"hex_value | color_name",
    "imageTintMode":"CLEAR | SRC | DST | SRC_OVER | DST_OVER | SRC_IN | DST_IN | SRC_OUT | DST_OUT | SRC_ATOP | DST_ATOP | XOR | DARKEN | LIGHTEN | MULTIPLY | SCREEN | ADD | OVERLAY"
  }
}
```

#### Button

Inherits everything from View.

#### CompoundButton

Inherits everything from Button.

```json
{  
  "class":"android.widget.CompoundButton",
  "attributes":{
    "checked":"TRUE | FALSE ",
    "buttonDrawable":"resource_url",
    "onCheck":"event_listener",
    "buttonTintMode":"CLEAR | SRC | DST | SRC_OVER | DST_OVER | SRC_IN | DST_IN | SRC_OUT | DST_OUT | SRC_ATOP | DST_ATOP | XOR | DARKEN | LIGHTEN | MULTIPLY | SCREEN | ADD | OVERLAY"
  }
}
``` 

#### CheckBox

Inherits everything from CompoundButton.

#### RadioButton

Inherits everything from CompoundButton.

#### Switch

Inherits everything from CompoundButton.

```json
{  
  "class":"android.widget.CompoundButton",
  "attributes":{
    "textOn":"string",
    "textOff":"string",
    "switchMinWidth":"units",
    "switchPadding":"units",
    "thumbTextPadding":"units",
    "thumbDrawable":"resource_url",
    "trackDrawable":"resource_url",
    "showText":"TRUE | FALSE",
    "splitTrack":"TRUE | FALSE",
    "thumbTintMode":"CLEAR | SRC | DST | SRC_OVER | DST_OVER | SRC_IN | DST_IN | SRC_OUT | DST_OUT | SRC_ATOP | DST_ATOP | XOR | DARKEN | LIGHTEN | MULTIPLY | SCREEN | ADD | OVERLAY",
    "trackTintMode":"CLEAR | SRC | DST | SRC_OVER | DST_OVER | SRC_IN | DST_IN | SRC_OUT | DST_OUT | SRC_ATOP | DST_ATOP | XOR | DARKEN | LIGHTEN | MULTIPLY | SCREEN | ADD | OVERLAY"
  }
}
``` 

#### ToggleButton

Inherits everything from CompoundButton.

```json
{  
  "class":"android.widget.CompoundButton",
  "attributes":{
    "textOn":"string",
    "textOff":"string"
  }
}
``` 

#### GridView

Inherits everything from View.

```json
{  
  "class":"android.widget.GridView",
  "attributes":{
    "numColumns":"int",
    "selection":"int",
    "horizontalSpacing":"units",
    "verticalSpacing":"units",
    "columnWidth":"units",
    "stretchMode":"NO_STRETCH | STRETCH_SPACING | STRETCH_COLUMN_WIDTH | STRETCH_SPACING_UNIFORM",
    "gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL"
  }
}
``` 
