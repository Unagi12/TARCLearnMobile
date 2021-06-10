package com.example.tarclearn.viewmodel.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarclearn.model.ChapterDetailDto
import com.example.tarclearn.model.ChapterDto
import com.example.tarclearn.repository.ChapterRepository
import kotlinx.coroutines.launch

class ManageChapterViewModel(
    private val repository: ChapterRepository
) : ViewModel() {
    private val _chapter = MutableLiveData<ChapterDto>()
    val chapter: LiveData<ChapterDto>
        get() = _chapter

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _success = MutableLiveData<Boolean?>()
    val success: LiveData<Boolean?>
        get() = _success

    fun createChapter(courseId: Int, chapterNo: String, title: String) {
        val newChap = ChapterDto(chapterNo,title)
        viewModelScope.launch {
            val response = repository.createChapter(courseId, newChap)
            if (response.code() == 201) {
                _chapter.value = response.body()
                _success.value = true
            }
            if (response.code() == 400) {
                _error.value = "Invalid Chapter Name"
            }
        }
    }

    fun updateChapter(chapterId: Int, chapterNo: String, title:String){
        val updatedChapter = ChapterDto(chapterNo, title)
        viewModelScope.launch {
            val response = repository.updateChapter(chapterId, updatedChapter)
            if (response.code() == 200) {
                _chapter.value = response.body()
                _success.value = true
            }
            if (response.code() == 400) {
                _error.value = "Invalid Chapter Name"
            }
        }

    }
    fun fetchChapter(chapterId: Int){
        viewModelScope.launch {
            val response = repository.getChapter(chapterId)
            if(response.code() == 200){
                _chapter.value = response.body()
            }
        }
    }
    fun resetSuccessFlag() {
        _success.value = null
    }
}