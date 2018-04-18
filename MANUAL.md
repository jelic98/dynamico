## Content

* Single view
* Single layout
* Multiple views
* Recursive views
* Device targeting
* Configuration fields
* Event listeners
* Supported attributes
    * View
    * LinearLayout
    * TextView
    * EditText
    * ImageView
    * Button
    * CompoundButton
    * CheckBox
    * RadioButton
    * Switch
    * ToggleButton

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

### Supported attributes

##### View

```json
{  
  "class":"android.widget.View",
  "attributes":{
      "layout_width":"units | wrap_content | match_parent",
      "layout_height":"units | wrap_content | match_parent"
    }
}
```

##### LinearLayout

Inherits everything from View.

##### TextView

Inherits everything from View.

```json
{  
  "class":"android.widget.TextView",
  "attributes":{
    "text":"string",
    "textSize":"units",
    "textColor":"hex_value",
    "gravity":"START | END | TOP | BOTTOM | CENTER | CENTER_HORIZONTAL | CENTER_VERTICAL"
  }
}
```

##### EditText

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

##### ImageView

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

##### Button

Inherits everything from View.

##### CompoundButton

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

##### CheckBox

Inherits everything from CompoundButton.

##### RadioButton

Inherits everything from CompoundButton.

##### Switch

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

##### ToggleButton

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