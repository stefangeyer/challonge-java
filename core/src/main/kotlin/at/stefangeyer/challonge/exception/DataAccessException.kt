package at.stefangeyer.challonge.exception

class DataAccessException : Exception {
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String) : super(message)
}