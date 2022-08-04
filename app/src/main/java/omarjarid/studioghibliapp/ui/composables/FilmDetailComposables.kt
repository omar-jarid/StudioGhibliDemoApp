package omarjarid.studioghibliapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import omarjarid.example.domain.model.Film
import omarjarid.studioghibliapp.R
import omarjarid.studioghibliapp.navigateTo
import omarjarid.studioghibliapp.ui.theme.TextWhite

@Composable
fun BackButton(modifier: Modifier = Modifier, navController: NavHostController) {
    Button(
        onClick = { navigateTo("films", navController = navController) },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.LightGray
        ),
        elevation = ButtonDefaults.elevation(),
        modifier = modifier
            .width(30.dp)
            .height(30.dp)
    ) { Icon(painter = painterResource(id = R.drawable.ic_arrowback), contentDescription = null) }
}

@Composable
fun BoldText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, fontWeight = FontWeight.Bold, modifier = modifier)
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
            shadow = Shadow(
                color = Color.Black,
                offset = offset,
                blurRadius = 3f
            )
        )
    )
}

@Composable
fun Synopsis(text: String) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Spacer(modifier = Modifier.height(8.dp))
        BoldText(text = "Synopsis: ")
        Text(text = text)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun FilmRow(field: String, value: String, modifier: Modifier = Modifier) {
    Column {
        Spacer(modifier = modifier.height(8.dp))
        Row(modifier = modifier.padding(start = 8.dp)) {
            BoldText(text = "$field: ")
            Text(text = value)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.height(2.dp))
    }
}

@Composable
fun ImageDetail(film: Film) {
    ConstraintLayout {
        // Destructuring declaration per i riferimenti.
        val (imgFilm, tvTitle) = createRefs()
        GlideImage(
            imageModel = film.image,
            placeHolder = painterResource(id = R.drawable.studio_ghibli_logo),
            error = painterResource(id = R.drawable.studio_ghibli_logo),
            contentDescription = film.title,
            modifier = Modifier.constrainAs(imgFilm) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        FilmTitle(
            text = film.title,
            modifier = Modifier.constrainAs(tvTitle) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        )
    }
}

@Composable
fun ListDetail(film: Film) {
    Column {
        Synopsis(text = film.description)
        Divider(modifier = Modifier.height(2.dp))
        FilmRow(
            field = "Original title",
            value = "${film.originalTitle} [${film.originalTitleRomanised}]"
        )

        FilmRow(
            field = "Director",
            value = film.director
        )

        FilmRow(
            field = "Producer",
            value = film.producer
        )

        FilmRow(
            field = "Year",
            value = film.releaseDate
        )

        FilmRow(
            field = "Duration",
            value = "${film.runningTime} minutes"
        )

        FilmRow(
            field = "Rotten Tomatoes score",
            value = "${film.rtScore}%"
        )

        // Così non mi taglia l'ultima riga se scrollo.
        Spacer(modifier = Modifier.height(16.dp))
    }
}


// La paginetta va fatta un po' più bellina. Vedi UIzard.
@Composable
fun FilmDetail(film: Film, navController: NavHostController) {
    // Altezze indicative
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

    Box {
        Box(modifier = Modifier
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
        }
    }

    Row {
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            BackButton(navController = navController)
        }
    }
}