package com.example.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.ui.theme.DashboardTheme

enum class Metric {
    TOTAL_SALES,
    ACTIVE_USERS,
    CONVERSION_RATE,
    REVENUE_GROWTH
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashBoardScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DashBoardScreen(modifier: Modifier = Modifier) {
    var selectedMetric by remember { mutableStateOf(Metric.TOTAL_SALES) }
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(
                R.string.business_dashboard
            ),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(150.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MetricCard(
                text = stringResource(R.string.total_sales),
                selected = selectedMetric == Metric.TOTAL_SALES,
                onClick = { selectedMetric = Metric.TOTAL_SALES },
                modifier = Modifier.weight(1f),
                color = Color(0xFF4CAF50)
            )
            MetricCard(
                text = stringResource(R.string.active_users),
                selected = selectedMetric == Metric.ACTIVE_USERS,
                onClick = { selectedMetric = Metric.ACTIVE_USERS },
                modifier = Modifier.weight(1f),
                color = Color(0xFF2196F3)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(150.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MetricCard(
                text = stringResource(R.string.conversion_rate),
                selected = selectedMetric == Metric.CONVERSION_RATE,
                onClick = { selectedMetric = Metric.CONVERSION_RATE },
                modifier = Modifier.weight(1f),
                color = Color(0xFFFF9800)
            )
            MetricCard(
                text = stringResource(R.string.revenue_growth),
                selected = selectedMetric == Metric.REVENUE_GROWTH,
                onClick = { selectedMetric = Metric.REVENUE_GROWTH },
                modifier = Modifier.weight(1f),
                color = Color(0xFFE91E63)
            )
        }

        MetricScreen(metric = selectedMetric)
    }
}

@Composable
fun MetricCard(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxHeight(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MetricScreen(
    metric: Metric,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = when (metric) {
                    Metric.TOTAL_SALES -> stringResource(R.string.total_sales)
                    Metric.ACTIVE_USERS -> stringResource(R.string.active_users)
                    Metric.CONVERSION_RATE -> stringResource(R.string.conversion_rate)
                    Metric.REVENUE_GROWTH -> stringResource(R.string.revenue_growth)
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Detailed information about ${
                    metric.name.replace("_", " ").lowercase()
                } goes here.",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun DashBoardScreenPreview() {
    DashboardTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            DashBoardScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}
