package com.example.catdeployer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.catdeployer.model.EmployeeRole
import com.example.catdeployer.model.EmployeeUiModel
import com.example.catdeployer.model.Gender
import com.example.catdeployer.ui.theme.CatDeployerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatDeployerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Employees(
                        employees = listOf(
                            EmployeeUiModel(
                                name = "Robert",
                                role = EmployeeRole.HUMAN_RESOURCES,
                                gender = Gender.MALE,
                                imageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Wilma",
                                role = EmployeeRole.INFORMATION_TECHNOLOGY,
                                gender = Gender.FEMALE,
                                imageUrl = "https://images.pexels.com/photos/3189024/pexels-photo-3189024.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Curious George",
                                role = EmployeeRole.FINANCE,
                                gender = Gender.OTHER,
                                imageUrl = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?h=750&w=1260"
                            ),
                            EmployeeUiModel(
                                name = "Robert",
                                role = EmployeeRole.HUMAN_RESOURCES,
                                gender = Gender.MALE,
                                imageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Wilma",
                                role = EmployeeRole.INFORMATION_TECHNOLOGY,
                                gender = Gender.FEMALE,
                                imageUrl = "https://images.pexels.com/photos/3189024/pexels-photo-3189024.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Curious George",
                                role = EmployeeRole.FINANCE,
                                gender = Gender.OTHER,
                                imageUrl = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?h=750&w=1260"
                            ),
                            EmployeeUiModel(
                                name = "Robert",
                                role = EmployeeRole.HUMAN_RESOURCES,
                                gender = Gender.MALE,
                                imageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Wilma",
                                role = EmployeeRole.INFORMATION_TECHNOLOGY,
                                gender = Gender.FEMALE,
                                imageUrl = "https://images.pexels.com/photos/3189024/pexels-photo-3189024.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Curious George",
                                role = EmployeeRole.FINANCE,
                                gender = Gender.OTHER,
                                imageUrl = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?h=750&w=1260"
                            ),
                            EmployeeUiModel(
                                name = "Robert",
                                role = EmployeeRole.HUMAN_RESOURCES,
                                gender = Gender.MALE,
                                imageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Wilma",
                                role = EmployeeRole.INFORMATION_TECHNOLOGY,
                                gender = Gender.FEMALE,
                                imageUrl = "https://images.pexels.com/photos/3189024/pexels-photo-3189024.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Curious George",
                                role = EmployeeRole.FINANCE,
                                gender = Gender.OTHER,
                                imageUrl = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?h=750&w=1260"
                            ),
                            EmployeeUiModel(
                                name = "Robert",
                                role = EmployeeRole.HUMAN_RESOURCES,
                                gender = Gender.MALE,
                                imageUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Wilma",
                                role = EmployeeRole.INFORMATION_TECHNOLOGY,
                                gender = Gender.FEMALE,
                                imageUrl = "https://images.pexels.com/photos/3189024/pexels-photo-3189024.jpeg?h=650&w=940"
                            ),
                            EmployeeUiModel(
                                name = "Curious George",
                                role = EmployeeRole.FINANCE,
                                gender = Gender.OTHER,
                                imageUrl = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?h=750&w=1260"
                            )
                        ),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Employees(
    employees: List<EmployeeUiModel>,
    modifier: Modifier = Modifier
) {
    val columnState = rememberLazyListState()

    LazyColumn(
        state = columnState,
        modifier = modifier
    ) {
        items(employees.size) { index ->
            Employee(employee = employees[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeesPreview() {
    CatDeployerTheme {
        Employees(
            employees = listOf(
                EmployeeUiModel(
                    name = "Oliver",
                    role = EmployeeRole.HUMAN_RESOURCES,
                    gender = Gender.MALE,
                    imageUrl = ""
                )
            )
        )
    }
}