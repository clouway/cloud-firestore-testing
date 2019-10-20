package com.clouway.testing.firestore

import com.google.cloud.firestore.Firestore
import org.junit.rules.ExternalResource

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class FirestoreRule : ExternalResource() {
    private lateinit var firestorePort: FirestorePort
    private lateinit var runner: EmulatorRunner

    override fun before() {
        val ci = System.getenv("CI")
        if (ci == "true") {
            runner = CIEmulatorRunner()
        } else {
            runner = DockerEmulatorRunner()
        }

        firestorePort = runner.start()
    }

    override fun after() {
        runner.stop()
    }

    val firestore: Firestore
        get() = firestorePort.firestore

    val port: FirestorePort
        get() = firestorePort
}