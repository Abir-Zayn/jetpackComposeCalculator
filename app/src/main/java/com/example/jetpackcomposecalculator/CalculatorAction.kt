package com.example.jetpackcomposecalculator

//Sealed Classs the word sealed suggests, sealed classes conform to restricted or bounded class hierarchies.
// A sealed class defines a set of subclasses within it.
// It is used when it is known in advance that a type will conform to one of the subclass types

sealed class CalculatorAction {
    data class  Number(val number:Int): CalculatorAction()
    data object Clear: CalculatorAction()
    data object  Delete: CalculatorAction()
    data object  Decimal: CalculatorAction()
    data object  Calculate: CalculatorAction()
    data class Operations(val operations: CalculatorOperations): CalculatorAction()
}