package org.Chat

import java.lang.Exception

class MessageNotFoundException : Exception("Message with the given ID was not found") {

}

class CountMessageToMatchException : Exception("You entered incorrect parameters: message id or number of messages") {

}

