package com.example.testingappdemo.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.testingappdemo.rules.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import kotlin.time.Duration

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {
    //
//    @get:Rule
//    val rule = MainDispatcherRule()
    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.shoppingDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertShoppingItem() = runTest {
        val shoppingItem = ShoppingItem(
            name = "name",
            amount = 5,
            price = 2F,
            imageUrl = "image",
            id = 1
        )

        dao.insertShoppingItem(shoppingItem)

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            dao.observeAllShoppingItems().collect {
                assertThat(it).contains(shoppingItem)
            }
        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteShoppingItem() = runTest {
        val shoppingItem = ShoppingItem(
            name = "name",
            amount = 5,
            price = 2F,
            imageUrl = "image",
            id = 1
        )

        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            dao.observeAllShoppingItems().collect {
                assertThat(it).doesNotContain(shoppingItem)
            }
        }


    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun observeTotalPriceSum() = runTest {
        val shoppingItem1 = ShoppingItem(
            name = "name",
            amount = 5,
            price = 2F,
            imageUrl = "image",
            id = 1
        )
        val shoppingItem2 = ShoppingItem(
            name = "name",
            amount = 3,
            price = 4.5F,
            imageUrl = "image",
            id = 2
        )
        val shoppingItem3 = ShoppingItem(
            name = "name",
            amount = 6,
            price = 1F,
            imageUrl = "image",
            id = 3
        )
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            dao.observeTotalPrice().collect {
                assertThat(it).isEqualTo(5 * 2F + 3 * 4.5F + 6 * 1F)
            }

        }


    }
}