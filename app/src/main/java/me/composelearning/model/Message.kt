package me.composelearning.model
import com.google.gson.Gson

data class Message(
    var author: String ,
    var body: String ,
) {

    override fun toString(): String {
        return Gson().toJson(this)
    }

}
