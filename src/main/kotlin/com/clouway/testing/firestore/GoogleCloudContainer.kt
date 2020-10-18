package com.clouway.testing.firestore

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

/**
 * A testing container that starts firestore emulator.
 *
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class GoogleCloudContainer : GenericContainer<GoogleCloudContainer>(DockerImageName.parse("clouway/firestore-emulator:alpine"))