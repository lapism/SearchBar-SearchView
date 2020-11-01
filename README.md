[ ![Download](https://api.bintray.com/packages/lapism/maven/search/images/download.svg) ](https://bintray.com/lapism/maven/search/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)


# Search
 - Material Design Search component for Android
 - Last Google Material Design
 - Styling
 - Kotlin

Material Design pattern:  
https://material.io/design/navigation/search.html  

Versions history:  
https://bintray.com/lapism/maven/search

![Search](https://github.com/lapism/Search/blob/master/images/search.png)

## Apps with this library

* [LapIcons](https://play.google.com/store/apps/details?id=com.lapism.lapicons)

## Api

 - minSdkVersion = 21
 - targetSdkVersion = 30
 - Java = 1.8
 - Kotlin = 1.8
 - Gradle = 4.1.0


Add the dependency to your gradle file:
```groovy
dependencies {
    implementation 'com.lapism:search:2.5.0@aar'
}
```

## Usage

### Code example
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

### Layout example
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
**2.4.0**
- updated dependencies
- improved transition animation

**2.3.0**
- new added XML attributes
- updated dependencies
- OnNavigationClickListener.onNavigationClick has ,,hasFocus" parameter

**2.2.0**
- API 30
- fixed bottom margin bug with elevation more than 0dp
- dependencies update
- fixed transition animation on API 30

**2.1.3**
- margin null type

**2.1.2**
- fixed transition animation

**2.1.1**
- fixed elevation bug in CoordinatorLayout
- SearchBehavior Z now depends on LinearLayout and BottomNavigationView :-)

**2.1.0**
- fixed focus animation
- small improvements
- fixed bugs
- SearchBehavior Z now depends on AppBarLayout, LinearLayout and BottomNavigationView
- added public method MaterialSearchView.setTransitionDuration
- added public method MaterialSearchView.setClearFocusOnBackPressed
- added public.xml file

**2.0.0**
- first release
- SearchMenu item removed
- SearchView renamed to MaterialSearchView
- changed NavigationIconSupport properties
- NavigationIconSupport properties moved to SearchLayout
- fixed bugs
- improved open and hide animation
- new public methods

## Author

* **Martin Lapiš** - [GitHub](https://github.com/lapism)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](https://github.com/lapism/Search/blob/searchview/LICENSE) file for details.
