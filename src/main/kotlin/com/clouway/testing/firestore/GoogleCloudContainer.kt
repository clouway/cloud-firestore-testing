package com.clouway.testing.firestore

import org.testcontainers.containers.GenericContainer

/**
 * A testing container that starts firestore emulator.
 *
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class GoogleCloudContainer : GenericContainer<GoogleCloudContainer>("clouway/firestore-emulator:alpine")