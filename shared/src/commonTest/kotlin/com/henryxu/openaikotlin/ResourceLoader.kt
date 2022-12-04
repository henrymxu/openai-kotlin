package com.henryxu.openaikotlin

/** Read the given resource as binary data. */
expect fun readImageResource(
    imageName: String
): ByteArray
