package com.clouway.testing.firestore

import com.google.auth.Credentials
import java.net.URI

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
internal object FakeCreds : Credentials() {
    private val HEADERS = mutableMapOf(
        "Authorization" to mutableListOf("Bearer owner")
    )

    override fun hasRequestMetadata(): Boolean {
        return true
    }

    override fun hasRequestMetadataOnly(): Boolean {
        return true
    }

    override fun refresh() {

    }

    override fun getAuthenticationType(): String {
        return "OAuth2"
    }

    override fun getRequestMetadata(uri: URI?): MutableMap<String, MutableList<String>> {
        return HEADERS
    }
}