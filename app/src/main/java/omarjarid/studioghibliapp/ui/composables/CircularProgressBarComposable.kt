package omarjarid.studioghibliapp.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CircularProgressBar(isDisplayed: Boolean) {
    if(isDisplayed) {
        ConstraintLayout {
            val (progressBar, tvLoading) = createRefs()
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp)
                    .constrainAs(progressBar) {
                        top.linkTo(parent.top, margin = 40.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(text = "Loading...", modifier = Modifier.constrainAs(tvLoading) {
                top.linkTo(progressBar.bottom, margin = 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        }
    }
}