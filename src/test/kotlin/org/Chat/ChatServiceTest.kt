package org.Chat

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

//import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun add() {
        val c = ChatService()
        val result = c.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "List"))
        assertEquals(1, result)
    }
}

class ChatServiceTest1 {
    @Test
    fun deleteChat() {
        val c = ChatService()
        c.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "List"))
        c.add(Chat(userName = "Sergey"), Message(idUser = 2, messageUser = "List"))
        val result = c.deleteChat(1)
        assertEquals("Chat with this ID deleted", result)
    }

}

class ChatServiceTest2 {

    @Test
    fun listUnreadChat() {
        val c = ChatService()
        c.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "List"))
        val result = c.listUnreadChat()
        assertEquals(1,result)
    }
}

class ChatServiceTest4 {
    @Test
    fun getChat() {
        val c = ChatService()
        c.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "List"))
       val result = c.getChat()
        assertEquals("[Chat(idUser=1, userName=Pavel, messages=[Message(idMessage=1, idUser=1, messageUser=List, readMessage=false, idOwner=0)])]",result)
    }
}