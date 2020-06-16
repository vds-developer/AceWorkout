package vds.developer.aceworkout.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class TrainingFragmentViewModelFactory(val application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TrainingFragmentViewModel(application) as T
}