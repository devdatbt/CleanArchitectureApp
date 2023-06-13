package com.example.data

import com.example.data.datasource.local.NoteDao
import com.example.data.datasource.local.NoteEntity
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.model.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.TestObserver
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class NoteRepositoryImplTest {

    private lateinit var noteRepositoryImpl: NoteRepositoryImpl

    @Mock
    lateinit var dao: NoteDao

    @Before
    fun setUp() {
        noteRepositoryImpl = NoteRepositoryImpl(dao)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testGetNoteListsSuccess() {
        val expectedResult = createNoteLists()
        `when`(dao.getNoteLists()).thenReturn(Flowable.just(expectedResult))

        noteRepositoryImpl.getNoteLists().subscribe {
            assertEquals(expectedResult, it)
        }
    }

    @Test
    fun testGetNoteListsIsEmpty() {
        val expectedResult: List<NoteEntity> = emptyList()
        `when`(dao.getNoteLists()).thenReturn(Flowable.just(expectedResult))
        noteRepositoryImpl.getNoteLists().subscribe {
            assertEquals(expectedResult, it)
        }
    }

    private fun createNoteLists(): List<NoteEntity> {
        val noteLists = mutableListOf<NoteEntity>()
        for (i in 1..5) {
            noteLists.add(
                NoteEntity(
                    title = "Test$i",
                    timestamp = 176652523,
                    content = "content$i"
                )
            )
        }
        return noteLists
    }

    @Test
    fun testInsertNoteSuccess() {
        val repositoryInsertObserver: TestObserver<*> = TestObserver.create<Any>()
        val insertCompletable = Completable.complete()
            .doOnSubscribe { d: Disposable? ->
                d?.let {
                    repositoryInsertObserver.onSubscribe(
                        it
                    )
                }
            }
        val expectedResult =
            NoteEntity(title = "Test1", content = "content1", timestamp = 176298377)
        given(dao.insertNote(expectedResult)).willReturn(insertCompletable)
        repositoryInsertObserver.hasSubscription()

        val note =
            Note(title = "Test1", content = "content1", timestamp = 176298377)
        noteRepositoryImpl.insertNote(note).subscribe {

        }
    }

    @Test
    fun testDeleteNoteSuccess() {
        val noteE =
            NoteEntity(title = "Test1", content = "content1", timestamp = 176298377)
        val expectedResult = 1
        `when`(dao.deleteNote(noteE)).thenReturn(Single.just(expectedResult))

        val note =
            Note(title = "Test1", content = "content1", timestamp = 176298377)
        noteRepositoryImpl.deleteNote(note).subscribe { result ->
            assertEquals(expectedResult, result)
        }
    }
}