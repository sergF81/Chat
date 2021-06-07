
package org.Chat

data class Chat(
    var idUser: Int = 0,
    val userName: String  = "",
    var messages: MutableList<Message> = mutableListOf()
)