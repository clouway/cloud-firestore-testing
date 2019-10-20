package com.clouway.testing.firestore

import com.google.cloud.firestore.Firestore
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class FirestoreCleaner(val firestore: Firestore, val collectionNames: List<String> = emptyList()) : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                val collections = if (collectionNames.isEmpty())
                    firestore.listCollections()
                else collectionNames.map {
                    firestore.collection(it)
                }

                collections.forEach {
                    val future = it.get()
                    val documents = future.get().documents
                    documents.forEach { it.reference.delete() }
                }
                base.evaluate()
            }
        }
    }

}