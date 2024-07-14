package com.example.data.repository

import android.util.Log
import androidx.core.os.trace
import com.example.data.di.IoDispatcher
import com.example.domain.model.User
import com.example.domain.repository.AccountServiceRepository
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth, private val firestore: FirebaseFirestore
) : AccountServiceRepository {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(email: String, password: String, isSignInSuccess: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            Log.d("authenticate", "signInWithEmailAndPassword isSuccess: ${task.isSuccessful}")
            isSignInSuccess.invoke(task.isSuccessful)
        }.await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    override suspend fun linkAccount(email: String, password: String): Unit =
        trace(LINK_ACCOUNT_TRACE) {
            val credential = EmailAuthProvider.getCredential(email, password)
            auth.currentUser!!.linkWithCredential(credential).await()
        }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        if (auth.currentUser!!.isAnonymous) {
            auth.currentUser!!.delete()
        }
        auth.signOut()

//        Sign the user back in anonymously.
//        createAnonymousAccount()
    }

    override suspend fun addItemToFireStore(
        timeStamp: String,
        hashMap: HashMap<String, String>,
        isSuccess: (Boolean) -> Unit
    ) {
        firestore.collection(FIRE_STORE_COLLECTION).document(timeStamp).set(hashMap)
            .addOnCompleteListener {
                isSuccess(true)
            }.addOnFailureListener {
                isSuccess(false)
            }
    }

    override suspend fun deleteItemToFireStore(timeStamp: String, isSuccess: (Boolean) -> Unit) {
        firestore.collection(FIRE_STORE_COLLECTION).document(timeStamp).delete()
            .addOnCompleteListener {
                isSuccess(true)
            }.addOnFailureListener {
                isSuccess(false)
            }
    }

    companion object {
        private const val FIRE_STORE_COLLECTION = "note"
        private const val LINK_ACCOUNT_TRACE = "linkAccount"
    }
}