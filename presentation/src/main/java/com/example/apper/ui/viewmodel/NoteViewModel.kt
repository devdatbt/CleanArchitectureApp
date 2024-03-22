package com.example.apper.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apper.usecase.AppUseCase
import com.example.apper.utils.Resource
import com.example.domain.model.Currency
import com.example.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
open class NoteViewModel @Inject constructor(private val appUseCase: AppUseCase) : ViewModel() {

    private val _statusGetNote: MutableLiveData<Resource<List<Note>>> = MutableLiveData()
    val statusGetNote: LiveData<Resource<List<Note>>> = _statusGetNote

    private val _statusNote: MutableLiveData<Resource<Any>> = MutableLiveData()
    val statusNote: LiveData<Resource<Any>> = _statusNote

    private val _statusGetCurrency: MutableLiveData<Resource<Currency>> = MutableLiveData()
    val statusGetCurrency: LiveData<Resource<Currency>> = _statusGetCurrency

    fun getSearchNoteLists(keyWord: String? = null) {
        _statusGetNote.value = Resource.loading(null)
        val disposable = appUseCase.getNoteListsUseCase
            .invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!keyWord.isNullOrEmpty()) {
                    val listFilter =
                        it.filter { note -> note.getTitleContainsWord(keyWord) }
                    _statusGetNote.value = Resource.success(listFilter)
                } else {
                    _statusGetNote.value = Resource.success(it)
                }
            }, {
                _statusGetNote.value = Resource.error(null, it.message ?: "Error")
            })
        compositeDisposable.add(disposable)
    }

    fun insertNote(note: Note) {
        _statusNote.value = Resource.loading(null)
        val disposable = appUseCase.addNoteUseCase
            .invoke(note)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _statusNote.value = Resource.success("Insert successfully!")
            }, {
                _statusNote.value = Resource.error(null, it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun deleteNote(note: Note) {
        _statusNote.value = Resource.loading(null)
        val disposable = appUseCase.deleteNoteUseCase
            .invoke(note)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _statusNote.value = Resource.loading("Delete successfully!")
            }, {
                _statusNote.value = Resource.error(null, it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun updateNote(title: String, content: String, time: Long) {
        val disposable = appUseCase.updateNoteUseCase
            .invoke(title = title, content = content, time = time)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _statusNote.value = Resource.loading("Update successfully!")
            }, {
                _statusNote.value = Resource.error(null, it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun getCurrencyFromServer() {
        _statusGetCurrency.value = Resource.loading(null)
        val disposable = appUseCase.getCurrencyUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.success) {
                    _statusGetCurrency.value = Resource.success(it)
                } else {
                    _statusGetCurrency.value =
                        Resource.error(null, "Error success: ${it.success}")
                }
            }, {
                _statusGetCurrency.value = Resource.error(null, it.message ?: "Error")
            })
        compositeDisposable.add(disposable)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}