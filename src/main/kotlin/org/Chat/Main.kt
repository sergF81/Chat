package org.Chat

//Programm by Fortis Sergey

fun main() {

    val test = ChatService()
    test.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "List"))
    test.add(Chat(userName = "Serg"), Message(idUser = 2, messageUser = "Hello", readMessage = true))
    test.add(Chat(userName = "Oleg"), Message(idUser = 3, messageUser = "World", readMessage = true))
    //   println(test.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "List")))
    println(test.chats.toList())
//    test.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "Проверка 222"))
//    test.add(Chat(userName = "Сергей"), Message(idUser = 2, messageUser = "Проверка 2221111"))
//    test.add(Chat(userName = "Yura"), Message(idUser = 3, messageUser = "Проверка у Юры", readMessage = true))
//    test.add(Chat(userName = "11"), Message(idUser = 2, messageUser = "Проверка Прошла"))
//    test.add(Chat(userName = "11"), Message(idUser = 1, messageUser = "Проверка Прошла1111"))
    //test.add(Chat(userName = "Pavel"), Message(idUser = 1, messageUser = "List"))
    println(test.chats.toList())

    println("________Delete Chat_______________")
    //  println(test.deleteChat(1))
    println(test.chats.toList())
    //  println(test.chats.toList())

    //   println(test.deleteMessage(2, 2))
    println("________edit message_______________")
    println(test.edit(2, 3, "Юрка прошел проверку"))
    println(test.chats.toList())
    println("________ListUnread message_______________")
    println(test.listUnreadChat())
    println("________getChat message_______________")
    println(test.getChat())
    println("________Delete message_______________")
    println(test.chats.toList())
    test.deleteMessage(1, 2)
    println(test.chats.toList())
    println("________getListMessage message_______________")
    print(test.getListMessage(3, 1, 1))
    //println(test.chats.toList())

}
