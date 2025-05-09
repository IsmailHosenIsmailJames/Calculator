package com.ismail.calculator

class ExpressionModifier {
    private  var listOfOperator = listOf<String>("+","-","*","/", "%")
    fun addDigit(expression: String, digit:String):String{
        if(expression=="0") return  digit
        return  expression + digit
    }

    fun addOperator(expression: String, operator:String):String{
        if(expression.last()=='(' && operator!="-") return  expression;
        val isLastCharOperator = listOfOperator.contains(expression.last().toString())
        if(isLastCharOperator) return  expression.dropLast(1) + operator
        return  expression + operator
    }

    fun  addParentheses(expression: String):String{
        val isNeedCloseParentheses = expression.last().isDigit();
        if(isNeedCloseParentheses){
            var openParentheses = 0
            var closeParentheses = 0
            for(char in expression){
                if(char == '(') openParentheses++
                if(char == ')') closeParentheses++
            }
            if(openParentheses>closeParentheses){
                if(expression.last()==')') return "$expression)"
            }
            if(openParentheses == closeParentheses) return "$expression("
        }
        if(isNeedCloseParentheses) return "$expression)"
        return "$expression("

    }
}