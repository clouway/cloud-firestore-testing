package com.clouway.testing.firestore

import com.google.cloud.firestore.Firestore

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
data class FirestorePort(val firestore: Firestore, val host: String, val port: Int)