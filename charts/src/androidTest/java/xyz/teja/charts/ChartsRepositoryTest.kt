package xyz.teja.charts

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.get
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.repository.ChartsRepository
import java.time.Period

@RunWith(AndroidJUnit4ClassRunner::class)
class ChartsRepositoryTest : KoinComponent {
    @Before
    fun beforeTest() {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().context)
            modules(koinTestModule)
        }
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun chartsRepositoryTest() {
        val testObserver = TestObserver<List<MarketPrice>>()

        get<ChartsRepository>()
            .getPrices(Period.ofDays(1))
            .subscribe(testObserver)

        testObserver.awaitCount(1)

        testObserver.assertValue { it.count() == 1 }

        testObserver.dispose()
    }
}
