package com.lapism.search.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation


object SearchUtils {

    const val SPEECH_REQUEST_CODE = 300

    @JvmStatic
    fun setVoiceSearch(activity: Activity, text: String) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, text)
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)

        activity.startActivityForResult(
            intent,
            SPEECH_REQUEST_CODE
        )
    }

    @JvmStatic
    fun isVoiceSearchAvailable(context: Context): Boolean {
        val pm = context.packageManager
        val activities =
            pm.queryIntentActivities(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0)
        return activities.size != 0
    }

    @JvmStatic
    fun addFocusAlphaAnimation(view: View?, duration: Long) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.duration = duration
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                view?.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view?.startAnimation(anim)
    }

    @JvmStatic
    fun removeFocusAlphaAnimation(view: View?, duration: Long) {
        val anim = AlphaAnimation(1.0f, 0.0f)
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.duration = duration
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view?.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view?.startAnimation(anim)
    }

}
