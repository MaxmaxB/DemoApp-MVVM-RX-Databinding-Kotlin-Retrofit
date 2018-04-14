package com.factory.mobile.architecturedemoapp.manager

import android.content.Context
import java.lang.ref.WeakReference

/**
 * Created by maxence bourdin on 14/04/2018.
 */
object ContextManager {
    private var mContextWeakReference: WeakReference<Context>? = null

    private var mApplicationContextWeakReference: WeakReference<Context>? = null

    fun updateContext(context: Context?) {
        context?.let {
            mContextWeakReference = WeakReference(context)
            mApplicationContextWeakReference = WeakReference(context.applicationContext)
        }
    }

    fun getContext(): Context? {
        return when {
            (mContextWeakReference != null && mContextWeakReference?.get() != null) -> mContextWeakReference?.get()
            (mApplicationContextWeakReference != null && mApplicationContextWeakReference?.get() != null) -> mApplicationContextWeakReference?.get()
            else -> null
        }
    }
}