package omarjarid.studioghibliapp.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import omarjarid.example.domain.model.Film
import omarjarid.studioghibliapp.R
import omarjarid.studioghibliapp.navigateTo
import omarjarid.studioghibliapp.presentation.viewmodels.FilmViewModel

@Composable
fun FilmCard(film: Film, navController: NavHostController) {
    Card(
        modifier = Modifier.padding(8.dp).clickable {
            navigateTo(route = "films/${film.id}", navController = navController)
        },
        elevation = 2.dp
    ) {
        GlideImage(
            imageModel = film.image,
            placeHolder = painterResource(id = R.drawable.studio_ghibli_logo),
            error = painterResource(id = R.drawable.studio_ghibli_logo),
            contentScale = ContentScale.Fit,
            contentDescription = film.title
        )
    }
}

@Composable
fun SearchBar(state: MutableState<String>, isDisplayed: Boolean, onSearch: (String) -> Unit) {
    if (isDisplayed) {
        val focusManager = LocalFocusManager.current
        TextField(
            value = state.value,
            onValueChange = { state.value = it },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            singleLine = true,
            maxLines = 1,
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions {
                onSearch(state.value)
                focusManager.clearFocus()
            },
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text("Search") }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilmBodyContent(
    lista: List<Film>,
    textState: MutableState<String>,
    viewModel: FilmViewModel,
    navController: NavHostController
) {
    Column {
        Spacer(modifier = Modifier.height(40.dp))
        SearchBar(state = textState, isDisplayed = lista.isNotEmpty()) { viewModel.search(it) }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(lista.size) { i -> FilmCard(film = lista[i], navController = navController) }
        }
    }
}