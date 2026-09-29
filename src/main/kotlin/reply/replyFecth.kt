package reply

data class ReplyFecth(
    val status: Int,
    val message: String,
    val data: Any? = null
)
