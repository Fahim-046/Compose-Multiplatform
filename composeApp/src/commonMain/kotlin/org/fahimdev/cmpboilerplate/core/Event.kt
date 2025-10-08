package org.fahimdev.cmpboilerplate.core

data class Event<out T>(
    val value: T,
    private val id: Int = if(lastId == Int.MAX_VALUE){
        lastId = Int.MIN_VALUE
        Int.MAX_VALUE
    } else{
        lastId++
    }
){
    companion object{
        private var lastId = Int.MAX_VALUE
    }

    private var valueSent = false

    /**
     * * Get the value only once
     */
    fun getValueOnce(): T? = if(!valueSent){
        valueSent = true
        value
    } else{
        null
    }
}