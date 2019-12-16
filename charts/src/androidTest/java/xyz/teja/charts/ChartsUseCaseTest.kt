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
import xyz.teja.charts.data.remote.chartRemoteDateNow
import xyz.teja.charts.domain.model.MarketPrice
import xyz.teja.charts.domain.usecases.ChartsUseCase
import java.time.Period

@RunWith(AndroidJUnit4ClassRunner::class)
class ChartsUseCaseTest : KoinComponent {
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
    fun chartsUseCaseTest() {
        val testObserver = TestObserver<List<MarketPrice>>()

        get<ChartsUseCase>()
            .getPrices(Period.ofDays(1))
            .subscribe(testObserver)

        testObserver.awaitCount(2)

        testObserver.assertValueCount(2)
        testObserver.assertValueAt(1) { it[0].date == chartRemoteDateNow }

        testObserver.dispose()
    }
}
