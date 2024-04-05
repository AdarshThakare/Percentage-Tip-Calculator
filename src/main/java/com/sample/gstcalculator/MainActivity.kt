package com.sample.gstcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.gstcalculator.ui.theme.GSTCalculatorTheme import java.text.NumberFormat
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GSTCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout(){
    var amountInput by remember {mutableStateOf("")}
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipInput by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    var roundUp by remember { mutableStateOf(false) }
    val tip = calculateTip(amount, tipPercent, roundUp)
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(Alignment.Start)
        )
        EditNumberField(

            label = R.string.bill_amount,
            value = amountInput,
            onValueChange ={amountInput = it},
            leadingIcon = R.drawable.money,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            imeAction = ImeAction.Next,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth())
        EditNumberField(
            label = R.string.how_was_the_service,
            value = tipInput,
            onValueChange = { tipInput = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            imeAction = ImeAction.Done,
            leadingIcon = R.drawable.percentage,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth())
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChenged = { roundUp = it },
            modifier = Modifier.padding(bottom =32.dp)
        )
        Text(text = stringResource(id = R.string.tip_amount, "Rs" ,tip),
            style = MaterialTheme.typography.displaySmall,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top= 64.dp)
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): String {
    var tip = (tipPercent / 100) * amount
    if (roundUp) {
        tip = kotlin.math.round(tip)
    }
    return tip.toString()
}

@Composable
fun EditNumberField(
    keyboardOptions: KeyboardOptions,
    imeAction: ImeAction,
    @StringRes label : Int,
    @DrawableRes leadingIcon : Int,
    value : String,
    onValueChange : (String) -> Unit,
    modifier: Modifier = Modifier){
    TextField(
        value = value,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(stringResource(label))},
        modifier = modifier)
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChenged : (Boolean) -> Unit,
    modifier: Modifier = Modifier){
    Row (modifier = Modifier
        .fillMaxWidth()
        .size(48.dp),
        verticalAlignment = Alignment.CenterVertically){
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            checked = roundUp ,
            onCheckedChange = onRoundUpChenged,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GSTCalculatorTheme {
        TipTimeLayout()
    }
}