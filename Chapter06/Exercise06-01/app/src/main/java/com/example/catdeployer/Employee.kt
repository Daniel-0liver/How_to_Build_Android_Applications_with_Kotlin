package com.example.catdeployer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catdeployer.model.EmployeeUiModel

@Composable
fun Employee(
    employee: EmployeeUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        if (employee.imageUrl.isNotEmpty()) {
            LoadedImage(
                imageUrl = employee.imageUrl,
                modifier = modifier
            )
        } else {
            Spacer(modifier = Modifier.size(64.dp))
        }
        Column {
            Text(text = employee.name)
            Text(text = employee.role.label)
        }
    }
}