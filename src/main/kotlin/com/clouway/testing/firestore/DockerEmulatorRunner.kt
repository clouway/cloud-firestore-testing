package com.clouway.testing.firestore

import com.google.api.gax.core.FixedCredentialsProvider
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.FirestoreOptions
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy


/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class DockerEmulatorRunner : EmulatorRunner {
    private val projectName = "testing"
    private val emulatorPort = 8080

    private lateinit var firestoreContainer: GoogleCloudContainer

    override fun start(): FirestorePort {
        firestoreContainer =
            GoogleCloudContainer()
                .withExposedPorts(emulatorPort)
                .withEnv("FIRESTORE_PROJECT_ID", projectName)
                .waitingFor(LogMessageWaitStrategy().withRegEx("(?s).*running.*$"))

        firestoreContainer.start()

        val firestoreService: Firestore by lazy {
            val containerHost =
                "${firestoreContainer.containerIpAddress}:${firestoreContainer.getMappedPort(emulatorPort)}"

            FirestoreOptions.newBuilder()
                .setProjectId(projectName)
                .setChannelProvider(
                    InstantiatingGrpcChannelProvider.newBuilder()
                        .setEndpoint(containerHost)
                        .setChannelConfigurator { input ->
                            input.usePlaintext()
                            input
                        }
                        .build()
                )
                .setCredentialsProvider(FixedCredentialsProvider.create(FakeCreds))
                .build()
                .service
        }

        return FirestorePort(
            firestoreService,
            firestoreContainer.containerIpAddress,
            firestoreContainer.getMappedPort(emulatorPort)
        )
    }


    override fun stop() {
        firestoreContainer.stop()
    }


}