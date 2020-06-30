package com.example.watchedmovies.extensions

import android.content.res.Resources
import com.example.watchedmovies.App

// number to color conversion
inline val Int.argb
    get() = this
inline val Int.rgb
    get() = (this + 0xFF000000).int

// number to dp conversion
inline val Int.dp
    get() = try {
        (this * App.instance.resources.displayMetrics.density).int
    } catch (e: UninitializedPropertyAccessException) {
        (this * Resources.getSystem().displayMetrics.density).int
    }
inline val Long.dp
    get() = try {
        (this * App.instance.resources.displayMetrics.density).long
    } catch (e: UninitializedPropertyAccessException) {
        (this * Resources.getSystem().displayMetrics.density).long
    }
inline val Float.dp
    get() = try {
        this * App.instance.resources.displayMetrics.density
    } catch (e: UninitializedPropertyAccessException) {
        this * Resources.getSystem().displayMetrics.density
    }
inline val Double.dp
    get() = try {
        (this * App.instance.resources.displayMetrics.density).double
    } catch (e: UninitializedPropertyAccessException) {
        (this * Resources.getSystem().displayMetrics.density).double
    }

// number to sp conversion
inline val Int.sp
    get() = try {
        (this * App.instance.resources.displayMetrics.scaledDensity).int
    } catch (e: UninitializedPropertyAccessException) {
        (this * Resources.getSystem().displayMetrics.scaledDensity).int
    }
inline val Long.sp
    get() = try {
        (this * App.instance.resources.displayMetrics.scaledDensity).long
    } catch (e: UninitializedPropertyAccessException) {
        (this * Resources.getSystem().displayMetrics.scaledDensity).long
    }
inline val Float.sp
    get() = try {
        this * App.instance.resources.displayMetrics.scaledDensity
    } catch (e: UninitializedPropertyAccessException) {
        this * Resources.getSystem().displayMetrics.scaledDensity
    }
inline val Double.sp
    get() = try {
        (this * App.instance.resources.displayMetrics.scaledDensity).double
    } catch (e: UninitializedPropertyAccessException) {
        (this * Resources.getSystem().displayMetrics.scaledDensity).double
    }

inline val Number.int
    get() = this.toInt()
inline val Number.long
    get() = this.toLong()
inline val Number.float
    get() = this.toFloat()
inline val Number.double
    get() = this.toDouble()