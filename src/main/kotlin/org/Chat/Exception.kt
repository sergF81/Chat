package org.Chat

import java.lang.Exception

class MessageNotFoundException : Exception("Message with the given ID was not found") {

}

class ChatNotFoundException : Exception("Message with the given ID was not found") {
}
