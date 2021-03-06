package vds.developer.aceworkout.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vds.developer.aceworkout.activity.addset.viewmodel.SelectExerciseViewModel
import vds.developer.aceworkout.activity.trainingday.viewmodel.TrainingFragmentViewModel


class ViewModelFactory(val application: Application, val viewModelEnum: ViewModelsEnum) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (viewModelEnum) {
            ViewModelsEnum.TrainingFragment -> TrainingFragmentViewModel(application) as T
            ViewModelsEnum.SelectWorkout -> SelectExerciseViewModel(application) as T
        }
    }
}