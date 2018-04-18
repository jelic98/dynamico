[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/jelic98/dynamico.svg)](https://jitpack.io/#jelic98/dynamico)

# Dynamico

Android library for inflating dynamic layouts in runtime based on JSON configuration fetched from server. Useful in situations when layouts need to change without updating the app.

## Advanced features
* Targeting specific devices by brand, model and Android API version
* Layout changes based on configuration fields

## Installing

1. Add repository in root ```build.gradle```

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```gradle
dependencies {
    compile 'com.github.jelic98:dynamico:1.1.0'
}
```

## Usage

1. Create JSON layout and upload it somewhere

```json
{
  "views":[  
    {  
      "class":"android.widget.ImageView",
      "attributes":{  
        "layout_width":"wrap_content",
        "layout_height":"wrap_content",
        "src":"http://ecloga.org/dynamico/logo.png",
        "cache":true
      }
    },
    {  
      "class":"android.widget.TextView",
      "attributes":{  
        "text":"Yo!",
        "textColor":"#FF69B4"
      }
    }
  ]
}
```

2. Create XML wrapper layout that will contain loaded views

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical"
	android:id="@+id/mainLayout">
		
	<!-- Dynamic content will be added here -->
</LinearLayout>
```

3. Initialize Dynamico by passing it:
* URL = Link to JSON layout **directory**
* name = Name of JSON layout **file**
* layout = Wrapper layout that will contain loaded views

```java
new Dynamico("http://ecloga.org/dynamico",
    "activity_main",
    findViewById(R.id.mainLayout))
    .initialize();
```

Done. For advanced usage, take a look at this awesome [manual](https://github.com/jelic98/dynamico/blob/master/MANUAL.md).

## Additional features

* Event listener

```java
setListener(new LayoutStateListener() {
	@Override
	public void onSuccess(String message) {
		// everything is okay
	}
	
	@Override
	public void onError(String message) {
		// notify user
	}
})
```

* Loading from cache (skip layout fetching from server)

```java
setOptions(CACHE_ONLY)
```

## Supported views in 'android.widget'
* EditText
* TextView
* ImageView
* Button
* CompoundButton (CheckBox, RadioButton, Switch, ToggleButton)
* LinearLayout

## Documentation

###### `public Dynamico(String url, String name, ViewGroup layout) throws DynamicoException`

One and only constructor

 * **Parameters:**
   * `url` — URL of directory where JSON layout file is located (for example, "http://ecloga.org/dynamico")
   * `name` — JSON layout file name with or without extension (for example, "activity_main")
   * `layout` — wrapper layout that will contain inflated layout from JSON file (for example, findViewById(R.id.mainLayout))
 * **Exceptions:** `DynamicoException` — if any of passed parameters is null

###### `public Dynamico setListener(DynamicoListener listener)`

Attaches event listener to Dynamico object

 * **Parameters:** `listener` — listener for success and error events caused by network, storage, etc.
 * **Returns:** Dynamico object ready for initialization

###### `public Dynamico setOptions(DynamicoOptions.Option ... options)`

Attaches options to Dynamico object

 * **Parameters:** `options` — options for Dynamico (for example, ONLY_CACHE)
 * **Returns:** Dynamico object ready for initialization

###### `public void initialize()`

Starts layout fetching from cache/server depending on provided options

## TODO
* Support RelativeLayout, ConstraintLayout, Gridlayout, FrameLayout, etc.
* Custom layouts
* Support vector drawables
