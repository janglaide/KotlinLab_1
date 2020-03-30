package com.example.lab1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_chooser.*
import kotlinx.android.synthetic.main.fragment_info.*


class ChooserFragment : Fragment() {

    private lateinit var data: Task

    private lateinit var _resultButton: Button

    private lateinit var _radioDiff: RadioGroup
    private lateinit var _radioType: RadioGroup

    private var _run = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = Task()
        arguments?.let {
            it.getString("difficulty")
            it.getString("type")
            it.getString("description", "")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chooser, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _radioDiff = view.findViewById(R.id.radioDiff)
        _radioType = view.findViewById(R.id.radioType)
         _resultButton = view.findViewById(R.id.resButton)

        resButton.setOnClickListener{
            val flag: Boolean = (_radioDiff.checkedRadioButtonId != -1) and (_radioType.checkedRadioButtonId != -1)
            if(flag){

                val task = Task(view.findViewById<RadioButton>(_radioDiff.checkedRadioButtonId).text as String,
                    view.findViewById<RadioButton>(_radioType.checkedRadioButtonId).text as String, resources)
                data.description = task.description
                data.difficulty = view.findViewById<RadioButton>(_radioDiff.checkedRadioButtonId).text as String
                data.type = view.findViewById<RadioButton>(_radioType.checkedRadioButtonId).text as String
                _run = true

                createResult(view.context as MainActivity)
            }
            else{
                Toast.makeText(
                    view.context, "Not all or none selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun createResult(context: MainActivity){
        context.replaceFragment(InfoFragment(data))
    }
}
