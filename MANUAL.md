## Content

* [Single view](#single-view)
* [Single layout](#single-layout)
* [Multiple views](#multiple-views)
* [Recursive views](#recursive-views)
* [Device targeting](#device-targeting)
* [Configuration fields](#configuration-fields)
* [Event listeners](#event-listeners)
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
        "src": "http://ecloga.org/dynamico/logo.png"
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
    "src":"http://ecloga.org/dynamico/logo.png",
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
      "clickable":"true | false", 
      "background":"resource_url",
      "layout_weight":"float",
      "layout_gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL",
      "center_horizontal":"true | false",
      "center_vertical":"true | false",
      "center_in_parent":"true | false",
      "align_parent_start":"true | false",
      "align_parent_top":"true | false",
      "align_parent_end":"true | false",
      "align_parent_bottom":"true | false",
      "layout_above":"view_id",
      "layout_below":"view_id",
      "layout_alignStart":"view_id",
      "layout_alignTop":"view_id",
      "layout_alignEnd":"view_id",
      "layout_alignBottom":"view_id",
      "layout_alignBaseline":"view_id",
      "layout_toStartOf":"view_id",
      "layout_toEndOf":"view_id"
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
    "showDividers":"SHOW_DIVIDER_BEGINNING | SHOW_DIVIDER_MIDDLE | SHOW_DIVIDER_END | SHOW_DIVIDER_NONE",
    "dividerDrawable":"resource_url",
    "dividerPadding":"units",
    "baselineAligned":"true | false",
    "measureWithLargestChildEnabled":"true | false",
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
    "measureAllChildren":"true | false",
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
    "columnOrderPreserved":"true | false",
    "rowOrderPreserved":"true | false",
    "useDefaultMargins":"true | false"
  }
}
``` 

#### ScrollView

Inherits everything from FrameLayout.

```json
{  
  "class":"android.widget.ScrollView",
  "attributes":{
    "fillViewport":"true | false",
    "smoothScrollingEnabled":"true | false",
    "overScrollMode":"OVER_SCROLL_ALWAYS | OVER_SCROLL_NEVER | OVER_SCROLL_IF_CONTENT_SCROLLS"
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
    "textSize":"units",
    "textColor":"hex_value | color_name",
    "textStyle":"NORMAL | BOLD | ITALIC | BOLD_ITALIC",    
    "gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL"
  }
}
```

#### EditText

Inherits everything from TextView.

```json
{  
  "class":"android.widget.EditText",
  "attributes":{  
    "ellipsize":"START | MIDDLE | END | MARQUEE | END_SMALL",
    "hint":"string"
  }
}
```

#### ImageView

Inherits everything from View.

```json
{  
  "class":"android.widget.ImageView",
  "attributes":{
    "src":"resource_url",
    "cache":"true | false",
    "scaleType":"MATRIX | FIT_XY | FIT_START | FIT_CENTER | FIT_END | CENTER | CENTER_CROP | CENTER_INSIDE",
    "adjustViewBounds":"true | false",
    "maxWidth":"units",
    "maxHeight":"units",
    "baseline":"units",
    "baselineAlignBottom":"true | false",
    "cropToPadding":"true | false",
    "selected":"true | false",
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
    "checked":"true | false ",
    "buttonDrawable":"resource_url",
    "onCheck":"event_listener"
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
    "showText":"true | false",
    "splitTrack":"true | false"
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