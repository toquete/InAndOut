package com.toquete.inandout

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform