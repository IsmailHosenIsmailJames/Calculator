package com.ismail.calculator

import java.util.Stack

object CalculateInfix {
    fun prec(c: Char): Int {
        if (c == '^') return 3
        else if (c == '/' || c == '*') return 2
        else if (c == '+' || c == '-') return 1
        else return -1
    }

    fun associativity(c: Char): Char {
        if (c == '^') return 'R'
        return 'L'
    }

    fun infixToPostfix(s: String): ArrayList<String> {
        val result = ArrayList<String>()
        val stack = Stack<Char?>()

        var i = 0
        while (i < s.length) {
            var c = s.get(i)

            if ((c >= '0' && c <= '9')) {
                var temString = ""
                for (x in i..<s.length) {
                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) break
                    c = s.get(i)
                    if (c == '+' || c == '-' || c == '/' || c == '*' || c == '(' || c == ')') break
                    temString += c
                    i++
                }
                i--
                result.add(temString)
            } else if (c == '(') {
                stack.push(c)
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.add("" + (stack.pop()))
                }
                stack.pop()
            } else {
                while (!stack.isEmpty() && (prec(s.get(i)) < CalculateInfix.prec(stack.peek()!!) ||
                            prec(s.get(i)) == CalculateInfix.prec(stack.peek()!!) &&
                            associativity(s.get(i)) == 'L')
                ) {
                    result.add("" + stack.pop())
                }
                stack.push(c)
            }
            i++
        }
        while (!stack.isEmpty()) {
            result.add("" + stack.pop())
        }
        return result
    }

    @Throws(Exception::class)
    fun calculateInfix(infixString: String): Double {
        val postfix = infixToPostfix(infixString)
        val calculatePostfix = CalculatePostfix()
        return calculatePostfix.calculate(postfix)
    }
}

internal class CalculatePostfix {
    @Throws(Exception::class)
    fun calculate(postfix: ArrayList<String>): Double {
        val tem = singleCalculation(postfix)
        return tem.get(0).toDouble()
    }

    @Throws(Exception::class)
    private fun singleCalculation(postfix: ArrayList<String>): ArrayList<String> {
        if (postfix.size == 1) return postfix
        var index = -1
        for (i in postfix.indices) {
            val currentString = postfix.get(i)

            if (currentString == "+" || currentString == "-" || currentString == "/"
                || currentString == "*"
            ) {
                index = i
                break
            }
        }
        if (index < 2) throw Exception("Got some error")
        val num1 = postfix.get(index - 1).toDouble()
        val num2 = postfix.get(index - 2).toDouble()
        val operatorString = postfix.get(index)
        val result = operation(num1, num2, operatorString)
        postfix.removeAt(index)
        postfix.add(index, result.toString() + "")
        postfix.removeAt(index - 2)
        postfix.removeAt(index - 2)
        singleCalculation(postfix)
        return postfix
    }

    private fun operation(num2: Double, num1: Double, oprator: String): Double {
        if (oprator == "+") return num1 + num2
        else if (oprator == "-") return num1 - num2
        else if (oprator == "/") return num1 / num2
        else return num1 * num2
    }
}