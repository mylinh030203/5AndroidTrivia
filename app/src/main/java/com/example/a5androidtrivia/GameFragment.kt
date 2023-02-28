package com.example.a5androidtrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
//import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import com.example.a5androidtrivia.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    data class Question(val text: String, val answers:List<String>)

    private val question: MutableList<Question> = mutableListOf(
        Question(text = "What is Android Jetpack?",
            answers = listOf("All of these", "Tools", "Documentation", "Libraries")),
        Question(text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
        Question(text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
        Question(text = "What do you use to push structured data into a layout?",
            answers = listOf("Data binding", "Data pushing", "Set text", "An OnClick method")),
        Question(text = "What method do you use to inflate layouts in fragments?",
            answers = listOf("onCreateView()", "onActivityCreated()", "onCreateLayout()", "onInflateLayout()")),
        Question(text = "What's the build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
        Question(text = "Which class do you use to create a vector drawable?",
            answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
        Question(text = "Which one of these is an Android navigation component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
        Question(text = "Which XML element lets you register an activity with the launcher activity?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
        Question(text = "What do you use to mark a layout for data binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((question.size + 1) / 2, 3)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        randomizeQuestions()
         val view: View = inflater.inflate( R.layout.fragment_game, container,false)
        view.findViewById<TextView>(R.id.tvQuestion).apply {
            text = currentQuestion.text
        }
        view.findViewById<RadioButton>(R.id.rbAnswer1).apply {
            text = currentQuestion.answers[0]
        }
        view.findViewById<RadioButton>(R.id.rbAnswer2).apply {
            text = currentQuestion.answers[1]
        }
        view.findViewById<RadioButton>(R.id.rbAnswer3).apply {
            text = currentQuestion.answers[2]
        }
        view.findViewById<RadioButton>(R.id.rbAnswer4).apply {
            text = currentQuestion.answers[3]
        }
        view.findViewById<Button>(R.id.btnResult).setOnClickListener(object : View.OnClickListener{


            override fun onClick(v: View) {

                var checked : Int = view.findViewById<RadioGroup>(R.id.rgChoice).checkedRadioButtonId
                if(checked != -1){
                    var answersIndex =0
                    when(checked){
                        R.id.rbAnswer1 -> answersIndex=0
                        R.id.rbAnswer2 -> answersIndex=1
                        R.id.rbAnswer3 -> answersIndex=2
                        R.id.rbAnswer4 -> answersIndex=3
                    }
                    if(currentQuestion.answers[answersIndex] == currentQuestion.answers[0]){
                        questionIndex++
                        if(questionIndex<numQuestions){
                            currentQuestion = question[questionIndex]
                            setQuestion()
                            v.invalidate()

                        }else{
                            v.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                        }
                    }else{
                        v.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                    }
                }
            }
        })



            return view
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        question.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = question[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }




}