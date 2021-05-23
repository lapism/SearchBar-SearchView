[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)

NEXT VERSION WILL BE AS MAVENCENTRAL DEPENDENCY.

# Search
 - Material Design Search component for Android
 - Last Google Material Design
 - Styling
 - Kotlin

Material Design pattern:  
https://material.io/design/navigation/search.html

![Search](https://github.com/lapism/Search/blob/master_v1/images/search.png)

## Apps with this library

* [LapIcons](https://play.google.com/store/apps/details?id=com.lapism.lapicons)

## Api

 - minSdkVersion = 21
 - targetSdkVersion = 30
 - Java = 1.8
 - Kotlin = 1.8

Add the dependency to your gradle file:
```groovy
removed from jCenter
```

## Usage

### Code
```java
        val materialSearchView = findViewById<MaterialSearchView>(R.id.materialSearchView)
        materialSearchView.apply {
            setAdapterLayoutManager(layoutManager)
            setAdapter(adapter)

            navigationIconSupport = SearchLayout.NavigationIconSupport.SEARCH
            setOnNavigationClickListener(object : SearchLayout.OnNavigationClickListener {
                override fun onNavigationClick(hasFocus: Boolean) {
                    if (hasFocus()) {
                        finishAfterTransition()
                    } else {
                        materialSearchView.requestFocus()
                    }
                }
            })

            setTextHint(getString(R.string.search))
            setOnQueryTextListener(object : SearchLayout.OnQueryTextListener {
                override fun onQueryTextChange(newText: CharSequence): Boolean {
                    adapter.filter(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: CharSequence): Boolean {
                    return true
                }
            })

            setOnMicClickListener(object : SearchLayout.OnMicClickListener {
                override fun onMicClick() {
                    if (SearchUtils.isVoiceSearchAvailable(this@SearchActivity)) {
                        SearchUtils.setVoiceSearch(
                            this@SearchActivity,
                            getString(R.string.speak)
                        )
                    }
                }
            })

            elevation = 0f
            setBackgroundStrokeWidth(resources.getDimensionPixelSize(R.dimen.search_stroke_width))
            setBackgroundStrokeColor(
                ContextCompat.getColor(
                    this@SearchActivity,
                    R.color.divider
                )
            )
            setOnFocusChangeListener(object : SearchLayout.OnFocusChangeListener {
                override fun onFocusChange(hasFocus: Boolean) {
                    navigationIconSupport = if (hasFocus) {
                        SearchLayout.NavigationIconSupport.ARROW
                    } else {
                        SearchLayout.NavigationIconSupport.SEARCH
                    }
                }
            })
        }
```

Also you can use classes SearchBehavior and SearchArrowDrawable.

### Layout
It must be in the CoordinatorLayout.
Also add android:stateListAnimator="@null" to the AppBarLayout.

```xml
        <?xml version="1.0" encoding="utf-8"?>
        <androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <com.google.android.material.appbar.AppBarLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"

                android:stateListAnimator="@null">

                <include layout="@layout/include_material_toolbar" />

            </com.google.android.material.appbar.AppBarLayout>

            <androidx.viewpager2.widget.ViewPager2
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:overScrollMode="never"
                app:layout_behavior="@string/appbar_scrolling_view_behavior" />

            <com.lapism.search.widget.MaterialSearchView
                android:id="@+id/material_search_view"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />

            <include layout="@layout/include_bottom_nav" />

        </androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### XML attributes
```xml
        <attr name="search_navigationIconSupport" format="enum">
            <enum name="none" value="0" />
            <enum name="menu" value="1" />
            <enum name="arrow" value="2" />
            <enum name="search" value="3" />
        </attr>
        <attr name="search_navigationIcon" format="reference" />
        <attr name="search_clearIcon" format="reference" />
        <attr name="search_micIcon" format="reference" />
        <attr name="search_textHint" format="string" />
        <attr name="search_strokeColor" format="reference" />
        <attr name="search_strokeWidth" format="reference" />
        <attr name="search_dividerColor" format="reference" />
        <attr name="search_shadowColor" format="reference" />
        <attr name="search_transitionDuration" format="integer" />
        <attr name="search_radius" format="integer" />
        <attr name="android:elevation" />
        <attr name="android:imeOptions" />
        <attr name="android:inputType" />
```

## Changelog
**2.4.1**
- changed STATE_HAMBURGER to MENU in SearchArrowDrawable
- changed STATE_ARROW to ARROW in SearchArrowDrawable

## Author

* **Martin Lapiš** - [GitHub](https://github.com/lapism)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](https://github.com/lapism/Search/blob/searchview/LICENSE) file for details.
