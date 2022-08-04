package omarjarid.studioghibliapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import omarjarid.studioghibliapp.R

val DMSans = FontFamily(
    Font(R.font.dmsans_regular),
    Font(R.font.dmsans_bold, FontWeight.Bold),
    Font(R.font.dmsans_medium, FontWeight.Medium),
    Font(R.font.dmsans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.dmsans_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.dmsans_medium_italic, FontWeight.Medium, FontStyle.Italic)
)

// Faccio una roba con un font un po' più bellino.
val GhibliTypography = Typography(
    defaultFontFamily = DMSans,
    body1 = TextStyle(
        fontFamily = DMSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (-1).sp
    )
)