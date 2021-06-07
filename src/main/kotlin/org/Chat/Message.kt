package org.Chat

data class Message(
    val idMessage: Int = 0,
    val idUser: Int = 0,
    var messageUser: String = "",
    val readMessage: Boolean = false,
    val idOwner: Int = 0
)


