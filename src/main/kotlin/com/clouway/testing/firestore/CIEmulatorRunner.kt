package com.clouway.testing.firestore

import com.google.cloud.NoCredentials
import com.google.cloud.firestore.FirestoreOptions

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class CIEmulatorRunner : EmulatorRunner {
    private val projectName = "testing"

    override fun start(): FirestorePort {
        val host = System.getenv("FIRESTORE_HOST")
        val port = System.getenv("FIRESTORE_PORT")

        val firestore = FirestoreOptions.newBuilder()
            .setProjectId(projectName)
            .setHost("http://$host:$port")
            .setCredentials(NoCredentials.getInstance())
            .build()
            .service

        return FirestorePort(firestore, host, port.toInt())
    }

    override fun stop() {
        // CI will shut it down after all tests are executed
    }

}