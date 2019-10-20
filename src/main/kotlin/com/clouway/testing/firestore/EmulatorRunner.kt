package com.clouway.testing.firestore

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
internal interface EmulatorRunner {

  fun start(): FirestorePort

  fun stop()

}