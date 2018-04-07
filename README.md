[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Dynamico

Android library for inflating dynamic layouts in runtime based on JSON configuration fetched from server.

## Usage

```java
	new Dynamico("activity_main", (ViewGroup) findViewById(R.id.mainLayout))
    	.setLayoutStateListener(new LayoutStateListener() {
        	@Override
            public void onSuccess(String message) {
            	// everything is okay
            }

            @Override
            public void onError(String message) {
            	// notify user and load layout from storage if it exists
            }
		})
        .initialize();
```

### Work in progress

Still pretty fresh. Stay tuned!
