package ru.sulgik.tickets.schedule.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sulgik.core.datetime.LocalDateTimeFormatter
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
        val date: LocalDate,
        val isSelected: Boolean,
    )
}

@Composable
fun ScheduleScreen(
    seances: List<Seance>?,
    isContinueAvailable: Boolean,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onSelect: (Seance.ZonedSeanceTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = stringResource(R.string.schedule_top_bar))
                },
                onBack = onBack,
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = isContinueAvailable,
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut() + slideOutVertically { it / 2 }
            ) {
                UIKitContainedButton(
                    onClick = onContinue,
                    largeCorners = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
                ) {
                    Text(text = "Продолжить")
                }
            }
        },
        modifier = modifier,
    ) {
        if (seances != null) {
            ScheduleInfo(
                seances = seances,
                onSelect = onSelect,
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
    onSelect: (Seance.ZonedSeanceTime) -> Unit,
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
        ScheduleSeances(
            seances = seances,
            onSelect = onSelect,
            selectedItem = selectedItem.intValue
        )
    }
}

@Composable
fun ScheduleDates(
    seances: List<Seance>,
    selectedItem: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val parser = LocalDateTimeFormatter.current
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
                    parser.formatDateWithWeek(it.date),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding + 5.dp,
                            vertical = UIKitPaddingDefaultTokens.DefaultContentPadding - 5.dp,
                        )
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
    onSelect: (Seance.ZonedSeanceTime) -> Unit,
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
        modifier = modifier,
        userScrollEnabled = false
    ) {
        SeancePage(
            seance = seances[it],
            onSelect = onSelect,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = UIKitPaddingDefaultTokens.DefaultContentPadding)
        )
    }
}

@Composable
fun SeancePage(
    seance: Seance,
    onSelect: (Seance.ZonedSeanceTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace * 2)
    ) {
        NamedSeanceList(
            title = stringResource(R.string.schedule_red_hall),
            time = seance.time.filter { it.hallType == Seance.HallType.RED },
            onSelect = onSelect,
            modifier = Modifier.fillMaxWidth(),
        )
        NamedSeanceList(
            title = stringResource(R.string.schedule_blue_hall),
            time = seance.time.filter { it.hallType == Seance.HallType.BLUE },
            onSelect = onSelect,
            modifier = Modifier.fillMaxWidth(),
        )
        NamedSeanceList(
            title = stringResource(R.string.schedule_green_hall),
            time = seance.time.filter { it.hallType == Seance.HallType.GREEN },
            onSelect = onSelect,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun SeanceTime(
    time: Seance.SeanceTime,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val parser = LocalDateTimeFormatter.current
    val color =
        animateColorAsState(targetValue = if (time.isSelected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.background)
    Text(
        parser.formatTime(time.time),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .clip(UIKitShapeTokens.CornerMedium)
            .background(color.value)
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                UIKitShapeTokens.CornerMedium,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onSelect,
            )
            .padding(
                horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding + 5.dp,
                vertical = UIKitPaddingDefaultTokens.DefaultContentPadding - 5.dp,
            ),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SeanceList(
    time: List<Seance.ZonedSeanceTime>,
    onSelect: (Seance.ZonedSeanceTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        horizontalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
    ) {
        time.forEach {
            SeanceTime(time = it.time, onSelect = { onSelect(it) })
        }
    }
}

@Composable
fun NamedSeanceList(
    title: String,
    time: List<Seance.ZonedSeanceTime>,
    onSelect: (Seance.ZonedSeanceTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        modifier = modifier
    ) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        SeanceList(
            time = time,
            onSelect = onSelect,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}