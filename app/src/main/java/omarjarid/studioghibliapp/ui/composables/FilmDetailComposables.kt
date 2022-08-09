package omarjarid.studioghibliapp.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import omarjarid.example.domain.model.Film
import omarjarid.studioghibliapp.navigateTo
import omarjarid.studioghibliapp.ui.theme.TextWhite

@Composable
fun BoldText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, fontWeight = FontWeight.Bold, modifier = modifier)
}

@Composable
fun RTText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 22.sp, modifier = modifier)
}

@Composable
fun FilmTitle(text: String, modifier: Modifier = Modifier) {
    val offset = Offset(x = 5.0f, y = 10.0f)
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = TextWhite,
        modifier = modifier,
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(color = Color.Black, offset = offset, blurRadius = 3f)
        )
    )
}

@Composable
fun Overview(text: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(8.dp))
        BoldText(text = "Overview ")
        Text(text = text)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun FilmRow(field: String, value: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = modifier.height(8.dp))
        Row(modifier = modifier.padding(start = 8.dp)) {
            BoldText(text = field)
            Text(text = value)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun RTRow(film: Film, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = modifier.height(8.dp))
        Row(modifier = modifier.fillMaxWidth().padding(start = 8.dp)) {
            RTText(text = "Rotten Tomatoes score: ")
            Text(
                text = film.rtScore,
                color = when {
                    film.rtScore.toInt() >= 80 -> Color.Green
                    film.rtScore.toInt() in 60..79 -> Color.Yellow
                    else -> Color.Red
                },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun FilmColumn(field: String, value: String, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = modifier.height(8.dp))
        Column(modifier = modifier.padding(start = 8.dp)) {
            BoldText(text = field)
            Text(text = value)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun FilmDetail(film: Film, navController: NavHostController) {
    // Altezze indicative
    val maxHeight = 300f
    val arrowHeight = 50f
    val d = LocalDensity.current.density
    val imageHeightPx = with(LocalDensity.current) { maxHeight.dp.roundToPx().toFloat() }
    val arrowHeightPx = with(LocalDensity.current) { arrowHeight.dp.roundToPx().toFloat() }
    val horizontalPaddingModifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)

    LazyColumn {
        item {
            ConstraintLayout {
                val (ivBack, ivBanner, tvTitleYear) = createRefs()
                GlideImage(
                    imageModel = film.movieBanner,
                    modifier = Modifier.height((imageHeightPx / d).dp).constrainAs(ivBanner) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                )

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(width = 24.dp, height = 24.dp).constrainAs(ivBack) {
                        start.linkTo(parent.start, margin = 16.dp)
                        top.linkTo(parent.top, margin = (arrowHeightPx / d).dp)
                    }.clickable { navigateTo("films", navController = navController) }
                )

                FilmTitle(
                    text = "${film.title} (${film.releaseDate})",
                    modifier = Modifier.constrainAs(tvTitleYear) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(ivBanner.bottom, margin = 8.dp)
                    }
                )
            }

            RTRow(film = film, modifier = horizontalPaddingModifier)
            Overview(text = film.description)
            FilmRow(
                field = "Original title: ",
                value = "${film.originalTitle} [${film.originalTitleRomanised}]",
                modifier = horizontalPaddingModifier
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = horizontalPaddingModifier
            ) {
                FilmColumn(field = "Director", value = film.director)
                FilmColumn(field = "Producer", value = film.producer)
                FilmColumn(
                    field = "Duration",
                    value = "${film.runningTime} minutes",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}
