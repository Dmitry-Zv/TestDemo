package com.example.testingappdemo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    private lateinit var resourceCoparer: ResourceComparer


    @Before
    fun setup(){
        resourceCoparer = ResourceComparer()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCoparer.isEqual(
            context = context,
            resId = R.string.app_name,
            string = "TestingAppDemo"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCoparer.isEqual(
            context = context,
            resId = R.string.app_name,
            string = "Bullish"
        )
        assertThat(result).isFalse()
    }

}