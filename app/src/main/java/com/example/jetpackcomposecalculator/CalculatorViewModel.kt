package com.example.jetpackcomposecalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class CalculatorViewModel : ViewModel() {
//defines the logic behind the calculator app's state management and actions

    var state by mutableStateOf(CalculatorState())



    // In the Sealed Class there are data objects are defined.This objects are responsible
    // for taking an action and returning the new state.
    fun onAction(action: CalculatorAction){
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operations -> enterOperation(action.operations)
            is CalculatorAction.Delete ->  performDelete()
            is CalculatorAction.Calculate -> performCalculate()
        }
    }

    //Essentially, this function ensures that an operation
    // can only be selected if the user has already entered a valid first operand (number1).
    private fun enterOperation(operations: CalculatorOperations) {
            if (state.number1.isNotBlank()){
                state = state.copy(operation = operations)
            }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 9
    }


    private fun performDelete() {
       when {
           state.number2.isNotBlank() -> state = state.copy(
               number2 =  state.number2.dropLast(1)
           )
           state.operation !=null -> state = state.copy(
               operation = null
           )
           state.number1.isNotBlank() -> state = state.copy(
               number1 =  state.number1.dropLast(1)
           )
           else ->  state = state
       }
    }

    private fun performCalculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()

        if (number1 == null || number2 == null || state.operation == null) {
            return
        }

        val result = when (state.operation) {
            is CalculatorOperations.Add -> number1 + number2
            is CalculatorOperations.Divide -> number1 / number2
            is CalculatorOperations.Multiply -> number1 * number2
            is CalculatorOperations.Subtract -> number1 - number2
            null -> return
        }

        state = state.copy(
            number1 = result.toString().take(MAX_NUM_LENGTH),
            number2 = "",
            operation = null
        )
    }

    // handles the logic for entering a number into the calculator
    private fun enterNumber(number: Int) {


        if(state.operation == null) {
            if(state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }




    private  fun enterDecimal() {
        if(state.operation ==null && !state.number1.contains(".") && state.number1.isNotBlank()
        ) {
            state = state.copy(
                number1 =  state.number1 + "."
            )
            return
        }
        if(state.operation ==null && !state.number2.contains(".") && state.number2.isNotBlank()
        ) {
            state = state.copy(
                number1 =  state.number2 + "."
            )
        }
    }


}