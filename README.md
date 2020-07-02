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

## Donations

`Please support me!`

<a href="https://www.paypal.me/lapism">
  <img alt="Paypal"
       src="https://github.com/lapism/search/blob/master/images/paypal.png" />
</a>

## Usage
minSdkVersion 21  
targetSdkVersion 30
Java 1.8
Kotlin 1.8

Add the dependency to your gradle file:
```groovy
dependencies {
    implementation 'com.lapism:search:2.2.0@aar'
}
```

## SearchView

### Code example
```java
        val materialSearchView = findViewById<MaterialSearchView>(R.id.materialSearchView)
        materialSearchView.apply {
            setAdapterLayoutManager(layoutManager)
            setAdapter(adapter)

            navigationIconSupport = SearchLayout.NavigationIconSupport.SEARCH
            setOnNavigationClickListener(object : SearchLayout.OnNavigationClickListener {
                override fun onNavigationClick() {
                    binding.materialSearchView.requestFocus()
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

            setMicIconImageResource(R.drawable.ic_outline_mic_none_24dp)
            setOnMicClickListener(object : SearchLayout.OnMicClickListener {
                override fun onMicClick() {
                    if (SearchUtils.isVoiceSearchAvailable(this@MainActivity)) {
                        SearchUtils.setVoiceSearch(
                            this@MainActivity,
                            getString(R.string.speak)
                        )
                    }
                }
            })

            setMenuIconImageResource(R.drawable.layer_list_settings)
            setOnMenuClickListener(object : SearchLayout.OnMenuClickListener {
                override fun onMenuClick() {

                }
            })

            elevation = 0f
            setBackgroundStrokeWidth(resources.getDimensionPixelSize(R.dimen.search_stroke_width))
            setBackgroundStrokeColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.divider
                )
            )
            setOnFocusChangeListener(object : SearchLayout.OnFocusChangeListener {
                override fun onFocusChange(hasFocus: Boolean) {
                    if (hasFocus) {
                        navigationIconSupport = SearchLayout.NavigationIconSupport.ARROW
                    } else {
                        navigationIconSupport = SearchLayout.NavigationIconSupport.SEARCH
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
        <attr name="search_navigation_icon_support" format="enum">
            <enum name="none" value="1000" />
            <enum name="menu" value="1001" />
            <enum name="arrow" value="1002" />
            <enum name="search" value="1003" />
        </attr>
```

## Changelog
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
- NOT COMPATIBLE WITH 1.0 !!!
- SearchMenu item removed
- SearchView renamed to MaterialSearchView
- changed NavigationIconSupport properties
- NavigationIconSupport properties moved to SearchLayout
- fixed bugs
- improved open and hide animation
- new public methods

**1.0.0**
- first test release

## Apps with this library

* [LapIcons](https://play.google.com/store/apps/details?id=com.lapism.lapicons)

## Author

* **Martin Lapiš** - [GitHub](https://github.com/lapism)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](https://github.com/lapism/Search/blob/searchview/LICENSE) file for details.
