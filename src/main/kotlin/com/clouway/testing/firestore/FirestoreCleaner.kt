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
                    firestore.listCollections().map { it.id }
                else collectionNames

                collections.forEach { collection ->
                    firestore.collectionGroup(collection).get().get().forEach {
                        it.reference.delete().get()
                    }
                }

                base.evaluate()
            }
        }
    }

}