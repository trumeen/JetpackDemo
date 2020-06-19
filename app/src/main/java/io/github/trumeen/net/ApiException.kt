package io.github.trumeen.net

class ApiException(var code: Int, override var message: String) : RuntimeException()