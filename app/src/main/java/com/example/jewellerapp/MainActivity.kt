package com.example.jewellerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jewellerapp.ui.theme.JewellerAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.*

//import androidx.compose.ui.Alignment

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.filled.Timeline


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.border

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource


val GoldPrimary = Color(0xFFFFD36B)
val GoldSecondary = Color(0xFFFFB347)
val BackgroundDark = Color(0xFF050608)
val CardDark = Color(0xFF111217)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JewellerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JewellerHomeScreen()
                }
            }
        }
    }
}


@Composable
fun JewellerHomeScreen() {
    var selectedMetal by remember { mutableStateOf(MetalType.GOLD) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E0E0E))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        TopHeader()
        Spacer(modifier = Modifier.height(16.dp))
        LiveRatesAndDateTimeCard()
        MetalSelector(
            selected = selectedMetal,
            onSelect = { selectedMetal = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (selectedMetal) {
            MetalType.GOLD -> GoldLiveRateUI()
            MetalType.SILVER -> SilverLiveRateUI()
            MetalType.PLATINUM -> PlatinumLiveRateUI()
            MetalType.OTHER -> OtherMetalsUI()
        }
    }
}

@Composable
private fun TopHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo at center
            Image(
                painter = painterResource(id = R.drawable.logo), // replace with your logo file
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp) // adjust as needed
            )

            val GoldGradient = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFF9D976),
                    Color(0xFFF7C14D),
                    Color(0xFFE7A53A)
                )
            )

            Text(
                text = "LIVE RATES. TRUSTED EVERYTIME.",
                style = TextStyle(
                    brush = GoldGradient,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            )

        }
    }
}

@Composable
fun LiveRatesAndDateTimeCard() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // LEFT ITEM
        Column(
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "LIVE RATES",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(6.dp))

                Icon(
                    imageVector = Icons.Default.Notifications,   // Replace with your icon
                    contentDescription = null,
                    tint = Color(0xFFFFD36B),
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Prices updated in real-time",
                color = Color(0xFF9A9A9A),
                fontSize = 12.sp
            )
        }

        // RIGHT ITEM
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(
                    color = Color(0xFF191816),
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFF2F2B23),
                    shape = RoundedCornerShape(5.dp)   // optional
                ),
                verticalAlignment = Alignment.CenterVertically,
        ) {

            // LIVE BADGE
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF4CAF50))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "LIVE",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Today, 09:41 AM",
                color = Color(0xFF9A9A9A),
                fontSize = 12.sp
            )
        }
    }
}

enum class MetalType {
    GOLD, SILVER, PLATINUM, OTHER
}



@Composable
fun MetalSelector(
    selected: MetalType,
    onSelect: (MetalType) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        MetalButton("GOLD", Icons.Filled.Star, MetalType.GOLD, selected, onSelect)
        MetalButton("SILVER", Icons.Filled.Star, MetalType.SILVER, selected, onSelect)
        MetalButton("PLATINUM", Icons.Filled.Star, MetalType.PLATINUM, selected, onSelect)
    }
}

@Composable
fun MetalButton(
    label: String,
    icon: ImageVector,
    type: MetalType,
    selected: MetalType,
    onSelect: (MetalType) -> Unit
) {
    val isSelected = type == selected

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (isSelected)
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFFFFD36B),
                            Color(0xFFFFB347)
                        )
                    )
                else
                    Brush.linearGradient(listOf(Color(0xFF1A1A18), Color(0xFF1A1A18)))
            )
            .border(1.dp, Color(0xFF2F2B23), RoundedCornerShape(12.dp))
            .clickable { onSelect(type) }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color(0xFF111217) else Color(0xFF9A9A9A),
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = label,
            color = if (isSelected) Color(0xFF111217) else Color(0xFF9A9A9A),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun GoldLiveRateUI() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.card_bg))
            .border(
                width = 1.dp,
                color = colorResource(R.color.card_border),
                shape = RoundedCornerShape(12.dp)   // optional
            )
            .padding(16.dp)
    ) {

        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Purity",
                color = Color(0xFF969CA4),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Rate (₹/gram)",
                color = Color(0xFF969CA4),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Change\n(vs Yesterday)",
                color = Color(0xFF969CA4),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }

        Divider(color = Color(0xFF2F2B23), thickness = 1.dp)

//        Spacer(modifier = Modifier.height(12.dp))

        // Table Rows
        GoldRateRow("24K", "999", "₹7,245.00","/ grams", "+45.00", "(+5.2%)")
        GoldRateRow("22K", "995","₹6,640.00", "/ grams","+40.00", "(+5.2%)")
        GoldRateRow("18K", "992","₹5,460.00","/ grams", "+32.00", "(+5.2%)")
        GoldRateRow("14K", "990","₹4,240.00", "/ grams","+25.00", "(+5.2%)")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Rates are exclusive of GST and making charges.",
            color = Color(0xFF8C8C8C),
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun GoldRateRow(purity: String, fineness: String, rate: String, unit: String, change: String, percentChange: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Purity inside box
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xFFB68830), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = purity,   // Example: "24K"
                    color = colorResource(R.color.golden_text),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Fineness outside box
            Text(
                text = fineness,   // Example: "999.9"
                color = Color(0xFFB68830),
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = rate,          // Example: "₹ 7,245"
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = unit,          // Example: "per gram" or "₹/gm"
                color = Color(0xFFB0B0B0),
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "▲",
                    color = Color(0xFF4CAF50),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = change,   // Example: "12.45"
                    color = Color(0xFF4CAF50),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = percentChange,   // Example: "+0.18%"
                color = Color(0xFF4CAF50),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }

    Divider(color = Color(0xFF2F2B23), thickness = 1.dp)
}


@Composable
fun SilverLiveRateUI() {
    Text("Silver Live Rate")
}

@Composable
fun PlatinumLiveRateUI() {
    Text("Platinum Live Rate")
}

@Composable
fun OtherMetalsUI() {
    Text("Other Metals Live Rate")
}
