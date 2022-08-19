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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
            navController.navigate(route = "films/${film.id}")
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
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    isDisplayed: Boolean,
    onSearch: (String) -> Unit
) {
    if (isDisplayed) {
        val focusManager = LocalFocusManager.current
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            maxLines = 1,
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions {
                onSearch(value)
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
fun FilmBodyContent(lista: List<Film>, viewModel: FilmViewModel, navController: NavHostController) {
    val textState = remember { viewModel.searchState }

    Column {
        Spacer(modifier = Modifier.height(40.dp))
        SearchBar(
            value = textState.value,
            onValueChange = { textState.value = it } ,
            isDisplayed = lista.isNotEmpty(),
            onSearch = { viewModel.search(it) }
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(lista.size) { i -> FilmCard(film = lista[i], navController = navController) }
        }
    }
}