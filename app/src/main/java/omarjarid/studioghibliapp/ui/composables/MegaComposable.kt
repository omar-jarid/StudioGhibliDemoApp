package omarjarid.studioghibliapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skydoves.landscapist.glide.GlideImage
import omarjarid.example.domain.model.Film
import omarjarid.studioghibliapp.R

@Composable
fun MegaRT(film: Film, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BoldText(text = "Rotten Tomatoes score")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = film.rtScore,
            color = Color.Black,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(16.dp)
                .drawBehind {
                    drawCircle(
                        color = when {
                            film.rtScore.toInt() >= 80 -> Color.Green
                            film.rtScore.toInt() in 60..79 -> Color.Yellow
                            else -> Color.Red
                        },

                        radius = this.size.maxDimension
                    )
                }
        )
    }
}

@Composable
fun MegaComposable() {
    val film = Film(
        title = "Il mio film",
        releaseDate = "1993",
        rtScore = "90",
        runningTime = "100",
        description = "Tarapìa tapioco come se fosse antani a posterdati anche per due",
        director = "Pinco panco",
        producer = "Panco pinco",
        movieBanner = "https://image.tmdb.org/t/p/w533_and_h300_bestv2/3cyjYtLWCBE1uvWINHFsFnE8LUK.jpg",
        originalTitle = "NDOCOJOCOJO",
        originalTitleRomanised = "Scrivo cose a caso"
    )

    // Altezze indicative.
    val maxHeight = 300f
    val minHeight = 80f
    val d = LocalDensity.current.density
    val imageHeightPx = with(LocalDensity.current) { maxHeight.dp.roundToPx().toFloat() }
    val imageMinHeightPx = with(LocalDensity.current) { minHeight.dp.roundToPx().toFloat() }
    val imageOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = imageOffsetHeightPx.value + delta
                imageOffsetHeightPx.value = newOffset.coerceIn(imageMinHeightPx - imageHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val a = imageHeightPx + imageOffsetHeightPx.value
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = imageOffsetHeightPx.value) {
        progress = (a / imageHeightPx - minHeight / maxHeight) / (1f - minHeight / maxHeight)
    }

    ConstraintLayout {
        // Image detail.
        val (
            ivBack, ivBanner, tvTitleYear
        ) = createRefs()
        /*Box(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)) {
            LazyColumn {
                item {
                    // E' lui che devo animare!
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height((a / d).dp)) {
                        ImageDetail(film = film)
                    }

                    ListDetail(film = film)

                }
            }
        }*/

//        val leftGuideline = createGuidelineFromStart(0.4f) // Guideline sinistra al 40%.
//        val rightGuideline = createGuidelineFromStart(0.6f) // Guideline destra al 60%.
        Image(
            painter = painterResource(id = R.drawable.ic_arrowback),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
                .clickable {}
                .constrainAs(ivBack) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                }
        )

        GlideImage(
            imageModel = film.movieBanner,
            placeHolder = painterResource(id = R.drawable.studio_ghibli_logo),
            error = painterResource(id = R.drawable.studio_ghibli_logo),
            contentDescription = film.title,
            modifier = Modifier.constrainAs(ivBanner) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Preview
@Composable
fun MegaComposablePreview() { MegaComposable() }