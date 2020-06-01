package com.lapism.search.widget

import android.view.View
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lapism.search.internal.SearchLayout


class SearchBehavior<S : SearchLayout> : CoordinatorLayout.Behavior<S>() {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: S,
        dependency: View
    ): Boolean {
        return if (dependency is AppBarLayout) {
            true
        } else
            if (dependency is LinearLayout || dependency is BottomNavigationView) {
                ViewCompat.setZ(dependency, ViewCompat.getZ(child) + 1)
                true
            } else {
                super.layoutDependsOn(parent, child, dependency)
            }
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: S,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout) {
            child.translationY = dependency.getY()
            return true
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: S,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

}
