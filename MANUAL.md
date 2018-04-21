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

```json
{  
  "class":"android.widget.View",
  "attributes":{
      "layout_width":"units | wrap_content | match_parent",
      "layout_height":"units | wrap_content | match_parent"
    }
}
```

#### LinearLayout

Inherits everything from View.

```json
{  
  "class":"android.widget.LinearLayout",
  "attributes":{
    "orientation":"vertical | horizontal",
    "weightSum":"float",
    "gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL"
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
    "verticalGravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL"
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
    "orientation":"vertical | horizontal",
    "columnCount":"int",
    "rowCount":"int",
    "columnOrderPreserved":"true | false",
    "rowOrderPreserved":"true | false"
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
    "textColor":"hex_value",
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
    "scaleType":"MATRIX | FIT_XY | FIT_START | FIT_CENTER | FIT_END | CENTER | CENTER_CROP | CENTER_INSIDE"  
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