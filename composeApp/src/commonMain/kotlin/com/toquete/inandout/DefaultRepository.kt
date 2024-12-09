package com.toquete.inandout

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultRepository(
    private val firestore: FirebaseFirestore = Firebase.firestore
) {

    fun getItems(): Flow<List<Item>> = flow {
        firestore.collection("items")
            .snapshots
            .collect { querySnapshot ->
                val items = querySnapshot.documents.map {
                    it.data<Item>()
                }
                emit(items)
            }
    }
}