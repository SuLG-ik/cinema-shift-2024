package ru.sulgik.tickets.schedule.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitTab
import ru.sulgik.uikit.UIKitTabRow
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens
import ru.sulgik.uikit.tokens.UIKitShapeTokens
import java.time.LocalDate
import java.time.LocalTime

data class Seance(
    val date: LocalDate,
    val time: List<ZonedSeanceTime>,
) {
    data class ZonedSeanceTime(
        val hallType: HallType,
        val time: SeanceTime,
    )

    enum class HallType {
        RED, GREEN, BLUE, SIMPLE
    }

    data class SeanceTime(
        val time: LocalTime,
        val isSelected: Boolean,
    )
}

@Composable
fun ScheduleScreen(
    seances: List<Seance>?,
    isContinueAvailable: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = "Расписание")
                },
                onBack = onBack,
            )
        },
        bottomBar = {
            UIKitContainedButton(
                onClick = { /*TODO*/ },
                largeCorners = false,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Продолжить")
            }
        },
        modifier = modifier,
    ) {
        if (seances != null) {
            ScheduleInfo(
                seances = seances,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    }
}

@Composable
fun ScheduleInfo(
    seances: List<Seance>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding)
            .verticalScroll(rememberScrollState())
    ) {
        val selectedItem = remember { mutableIntStateOf(0) }
        ScheduleDates(
            seances = seances,
            selectedItem = selectedItem.intValue,
            onSelect = { selectedItem.intValue = it },
            modifier = Modifier.fillMaxWidth()
        )
        ScheduleSeances(seances = seances, selectedItem = selectedItem.intValue)
    }
}

@Composable
fun ScheduleDates(
    seances: List<Seance>,
    selectedItem: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    UIKitTabRow(
        modifier = modifier,
        selectedTabIndex = selectedItem,
    ) {
        seances.mapIndexed { index, it ->
            UIKitTab(
                selected = selectedItem == index,
                onClick = { onSelect(index) },
            ) {
                Text(
                    "${it.date.dayOfMonth}.${it.date.monthValue}",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleSeances(
    seances: List<Seance>,
    selectedItem: Int,
    modifier: Modifier = Modifier,
) {
    val pageState = rememberPagerState { seances.size }
    LaunchedEffect(key1 = selectedItem) {
        if (selectedItem != pageState.currentPage)
            pageState.animateScrollToPage(selectedItem)
    }
    HorizontalPager(
        state = pageState,
        key = { it },
    ) {
        SeancePage(
            seance = seances[it],
            modifier = Modifier.fillMaxSize()
                .padding(vertical = UIKitPaddingDefaultTokens.DefaultContentPadding)
        )
    }
}

@Composable
fun SeancePage(
    seance: Seance,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace * 2)
    ) {
        NamedSeanceList(
            title = "Красный зал",
            time = seance.time.filter { it.hallType == Seance.HallType.RED },
            modifier = Modifier.fillMaxWidth(),
        )
        NamedSeanceList(
            title = "Синий зал",
            time = seance.time.filter { it.hallType == Seance.HallType.BLUE },
            modifier = Modifier.fillMaxWidth(),
        )
        NamedSeanceList(
            title = "Зелёный зал",
            time = seance.time.filter { it.hallType == Seance.HallType.GREEN },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun SeanceTime(
    time: Seance.SeanceTime,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.border(
            1.dp,
            MaterialTheme.colorScheme.outline,
            UIKitShapeTokens.CornerMedium,
        ),
    ) {
        Text(
            "${time.time.hour}:${time.time.minute}",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(UIKitPaddingDefaultTokens.DefaultContentPadding),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SeanceList(
    time: List<Seance.ZonedSeanceTime>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        horizontalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
    ) {
        time.forEach {
            SeanceTime(time = it.time)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NamedSeanceList(
    title: String,
    time: List<Seance.ZonedSeanceTime>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        modifier = modifier
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        SeanceList(
            time = time,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}