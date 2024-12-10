package com.toquete.inandout

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultRepository(
    private val firestore: FirebaseFirestore = Firebase.firestore
) {

    fun getTransactions(): Flow<List<Transaction>> = firestore.collection("transactions")
        .snapshots
        .map { querySnapshot ->
            querySnapshot.documents.map {
                it.data<Transaction>()
            }
        }
}