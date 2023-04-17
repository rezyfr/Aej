package com.rezyfr.aej.utils

class CustomException constructor(var code: Int = 0, override var message: String) : Exception()
