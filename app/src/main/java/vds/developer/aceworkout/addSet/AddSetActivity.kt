package vds.developer.aceworkout.addSet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vds.developer.aceworkout.R

import kotlinx.android.synthetic.main.add_set_body_part_activity.*
import vds.developer.aceworkout.models.*

class AddSetActivity : AppCompatActivity() {
    private lateinit var addSetActivityViewModel : AddSetActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_set_body_part_activity)
//        addSetActivityViewModel = ViewModelProvider(this, ViewModelFactory(application = this.application, viewModelEnum = ViewModelsEnum.AddSetActivity))
//                .get(AddSetActivityViewModel::class.java)
        bodyPartRecyclerView.adapter = BodyPartRecyclerView()

//        addSetActivityViewModel.allExercise.let {
//            it.observe(this, androidx.lifecycle.Observer {
//
//            })
//        }


    }
}