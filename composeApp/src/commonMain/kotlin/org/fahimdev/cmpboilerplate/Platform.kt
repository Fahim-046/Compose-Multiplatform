package org.fahimdev.cmpboilerplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform