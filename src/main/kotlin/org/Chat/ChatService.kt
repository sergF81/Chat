package org.Chat

import java.util.*

class ChatService {
    var chats: MutableList<Chat> = mutableListOf()

    fun add(chat: Chat, message1: Message): Int {
        if (chats.isEmpty()) {
            chats
                .also { chats.plusAssign(chat.copy(idUser = 1)) }
                .also { chat.messages.plusAssign(message1.copy(idMessage = 1)) }
        } else if (chats.find { it.idUser == message1.idUser } == null)
            chats.find { it.idUser == message1.idUser }
                .also { chat.messages.plusAssign(message1.copy(idMessage = 1)) }
                .also { chats.plusAssign(chat.copy(idUser = chats.size + 1)) }
        else
            chats.find { it.idUser == message1.idUser }
                .also {
                    it?.messages?.plusAssign(message1.copy(idMessage = message1.idMessage + it.messages.size + 1))
                }
        return chats.last().idUser
    }

    fun deleteChat(idU: Int): String {
        return if (chats.removeIf { it.idUser == idU }) {
            "Chat with this ID deleted"
        } else {
            "Chat with the given ID was not found"
        }
    }

    fun deleteMessage(chatId: Int, messageId: Int) {
        (chats.find { chat -> chat.idUser == chatId } ?: throw ChatNotFoundException())
            .also { chat ->
                chat.messages
                    .find { message -> message.idMessage == messageId } ?: throw MessageNotFoundException()
            }
            .also { chat ->
                chat.messages
                    .removeIf { message -> message.idMessage == messageId }
            }
    }

    fun edit(messageId: Int, userId: Int, messageEdit: String): String {
        chats.asSequence()
            .filter { chat -> chat.idUser == userId }
            .take(1)
            .map { chat ->
                chat.messages.filter { message -> message.idMessage == messageId }
                    .toMutableList()
                    .also { chat.messages.replaceAll { message -> message.copy(messageUser = messageEdit) } }
            }
            .toList()
        return "Message changed!"
    }

    fun listUnreadChat(): String {
        var unreadMessage: Int = 0
        for ((chatIndex, chat) in chats.withIndex()) {
            if (chat.messages.none { it.readMessage }) unreadMessage++
        }
        return "You have $unreadMessage chats with Unread messages!"
    }

    fun getChat(): MutableList<Chat> {
        val listChat: MutableList<Chat> = arrayListOf()
        if (chats.isNotEmpty()) {
            listChat.addAll(chats)
        } else println("You do not have a messages!")
        return listChat
    }

    fun getListMessage(userId: Int, messageId: Int, count: Int) {
        chats.asSequence()
            .filter { chat -> chat.idUser == userId }
            .take(1)
            .map { chat ->
                chat.messages.filter { message -> message.idMessage in messageId until messageId + count }
                    .toMutableList()
                    .toMutableList()
                    .also { chat.messages.replaceAll { message -> message.copy(readMessage = true) } }
            }
            .toList()
            .let { println(it) }
    }
}