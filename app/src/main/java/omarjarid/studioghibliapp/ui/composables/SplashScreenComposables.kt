package omarjarid.studioghibliapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import omarjarid.studioghibliapp.R
import omarjarid.studioghibliapp.Routes
import omarjarid.studioghibliapp.navigateTo

@Composable
fun SplashScreen(navController: NavHostController) {
    ConstraintLayout(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .fillMaxHeight()) {
        val ivGhibli = createRef()
        Image(
            painter = painterResource(id = R.drawable.studio_ghibli_logo),
            contentDescription = null,
            modifier = Modifier.constrainAs(ivGhibli) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        navigateTo(Routes.FILMS, navController = navController)
    }
}