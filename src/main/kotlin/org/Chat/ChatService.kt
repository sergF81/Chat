package org.Chat


class ChatService {

    var chats: MutableList<Chat> = mutableListOf()
    var messages: MutableList<Message> = mutableListOf()

    fun add(chat: Chat, message: Message): Int {
        if (chats.isEmpty()) {
            val chatNew = chat.copy(idUser = 1)
            val messageNew = message.copy(idMessage = 1)
            messages.plusAssign(messageNew)
            chat.messages.plusAssign(messages.last())
            chats.plusAssign(chatNew)
        } else {
            var id: Int = 0
            for ((index, chat) in chats.withIndex()) {
                if (chats[index].idUser == message.idUser) {
                    id = message.idUser
                    break
                }
            }
            when {
                id == message.idUser -> {
                    println(message)
                    val messageNew = message.copy(idMessage = messages.last().idMessage + 1)
                    messages.plusAssign(messageNew)
                    chats[id - 1].messages.plusAssign(messages.last())
                }
                id != message.idUser -> {
                    val chatNew = chat.copy(idUser = chats.last().idUser + 1)
                    val messageNew = message.copy(idMessage = 1)
                    messages.plusAssign(messageNew)
                    chat.messages.plusAssign(messageNew)
                    chats.plusAssign(chatNew)
                }
            }
        }
        return chats.last().idUser
    }

    fun deleteChat(idU: Int): String {
        for ((index, chat) in chats.withIndex()) {
            if (chats[index].idUser == idU) {
                chats.removeAt(index)
                return "Chat with this ID deleted"
            }
        }
        return "Chat with the given ID was not found"
    }

    fun deleteMessage(id: Int, idU: Int): String {
        for ((index, message) in messages.withIndex()) {
            if (messages[index].idUser == idU && messages[index].idMessage == id) {
                messages.removeAt(index)
                for ((chatIndex, chat) in chats.withIndex()) {
                    for ((chatMessageIndex, chatMessage) in chat.messages.withIndex()) {
                        if (chat.messages[chatMessageIndex].idUser == idU && chat.messages[chatMessageIndex].idMessage == id) {
                            if (chat.messages.size == 1) {
                                deleteChat(idU)
                                return "Chat and Message with this ID deleted because Chat is empty!"
                                break
                            } else chat.messages.removeAt(chatMessageIndex)
                            break
                        }
                    }
                }
                return ("Message with this ID deleted")
            }
        }
        return ("Message with the given ID was not found")
    }

    fun edit(id: Int, idU: Int, messageEdit: String): String {
        for ((chatIndex, chat) in chats.withIndex()) {
            for ((chatMessageIndex, chatMessage) in chat.messages.withIndex()) {
                if (chat.messages[chatMessageIndex].idUser == idU && chat.messages[chatMessageIndex].idMessage == id) {
                    val chatNew = chat.copy()
                    chat.messages[chatMessageIndex].messageUser = messageEdit
                    return ("Message with this ID edited")
                    break
                }
            }
        }
        return ("Message with the given ID was not found")
    }

    fun listUnreadChat(): Int {
        var unreadMessage: Int = 0
        for ((chatIndex, chat) in chats.withIndex()) {
            for ((chatMessageIndex, chatMessage) in chat.messages.withIndex()) {
                if (chat.messages[chatMessageIndex].readMessage == false) {
                    unreadMessage += 1
                    break
                }
            }
        }
        return unreadMessage
    }

    fun getChat(): String {
        val listChat: MutableList<Chat> = arrayListOf()
        for ((index, chat) in chats.withIndex()) {
            if (!chats[index].messages.isEmpty()) listChat.add(chat)
        }
        if (listChat.isEmpty()) println("You do not have a messages!")
        else return listChat.toString()
        return listChat.toString()
    }

    fun getListMessage(idU: Int, id: Int, count: Int): MutableList<Message> {
        val listChat: MutableList<Message> = arrayListOf()
        var idIndex = id
        for ((indexChat, chat) in chats.withIndex()){
            for ((indexMessage, message) in chat.messages.withIndex()) {
                    if (idIndex < id + count && (count + id - 1) <= chat.messages.size) {
                        if (chats[indexChat].messages[indexMessage].idUser == idU && chats[indexChat].messages[indexMessage].idMessage == idIndex) {
                            listChat.add(chats[indexChat].messages[indexMessage])
                            idIndex++
                        }
                    }
                }
            }

        return listChat
    }
}
