package com.example.emelyanovpmppr27

class Validator {

    companion object{

        private fun String.isLongEnough() = length >= 8
        private fun String.hasEnoughDigits() = count(Char::isDigit) > 0
        private fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)
        private fun String.hasSpecialChar() = any { it in "!,+^" }

        public fun IsEmail(input: String): Boolean{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
        }
        public fun IsPassword(input: String): Boolean{
            return input.isLongEnough() && input.hasEnoughDigits() && input.isMixedCase()
        }
    }
}