package com.example.calculatorrefactoring.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorrefactoring.R
import com.example.calculatorrefactoring.data.SymbolEnum
import com.example.calculatorrefactoring.data.utils.Constants
import com.example.calculatorrefactoring.presentation.CalculatorViewModel
import com.example.calculatorrefactoring.ui.theme.GoogleSansBold
import com.example.calculatorrefactoring.ui.theme.LargeFontSize
import com.example.calculatorrefactoring.ui.theme.MediumFontSize

@Preview(
    showBackground = true, device = "spec:width=1080px,height=1920px,dpi=440",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ExpressionSection() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val expressionSectionPadding =
        if (screenHeight >= 780.dp && screenWidth >= 360.dp) 48.dp else 16.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Calculator",
            color = Color.White,
            fontSize = MediumFontSize,
            fontFamily = GoogleSansBold
        )
        Spacer(modifier = Modifier.height(expressionSectionPadding))
        Text(
            text = "123",
            maxLines = Constants.MAX_LINE,
            color = Color.White,
            fontSize = LargeFontSize,
            fontFamily = GoogleSansBold
        )
        Spacer(modifier = Modifier.height(expressionSectionPadding))
        IconButton(
            onClick = { /*TODO*/ },

            modifier = Modifier
                .align(alignment = Alignment.End)
                .size(48.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.delete_text_button),
                contentDescription = "Clear button",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.padding(4.dp))


    }
}

