package com.example.jetpackcomposecalculator


//A sealed class restricts the creation of instances to the explicitly defined companion objects within the class itself.
// In this case, you can only create instances of CalculatorOperations using the provided companion objects like Add, Subtract, etc.
sealed class CalculatorOperations(val symbol: String) {

    data object Add: CalculatorOperations("+")
    data object Subtract: CalculatorOperations("-")
    data object Multiply: CalculatorOperations("x")
    data object Divide: CalculatorOperations("/")
}