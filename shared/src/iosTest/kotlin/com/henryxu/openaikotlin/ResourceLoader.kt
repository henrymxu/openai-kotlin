package com.henryxu.openaikotlin

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import platform.Foundation.NSBundle
import platform.Foundation.NSData
import platform.Foundation.dataWithContentsOfFile
import platform.posix.getenv
import platform.posix.memcpy

/** Read the given resource as binary data. */
actual fun readImageResource(
    imageName: String
): ByteArray {
    val pathParts = imageName.split(".")
    val path = NSBundle.mainBundle
        .pathForResource("resources/${pathParts[0]}", pathParts[1])
    val data = NSData.dataWithContentsOfFile(path!!)
    return data!!.toByteArray()
}

internal fun NSData.toByteArray(): ByteArray {
    return ByteArray(length.toInt()).apply {
        usePinned {
            memcpy(it.addressOf(0), bytes, length)
        }
    }
}

