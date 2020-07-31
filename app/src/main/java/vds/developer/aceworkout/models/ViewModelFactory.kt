package vds.developer.aceworkout.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(val application: Application, val viewModelEnum: ViewModelsEnum) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (viewModelEnum) {
            ViewModelsEnum.TrainingFragment -> TrainingFragmentViewModel(application) as T
            ViewModelsEnum.SelectWorkout -> SelectWorkoutViewModel(application) as T
        }
    }
}