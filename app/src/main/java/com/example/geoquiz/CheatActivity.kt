/**
 * Author: Sijan Shrestha
 * Prepared for University of the Sunshine Coast
 * CRN: 1148556
 * Mobile App Project, 2022
 */

package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.example.quiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false


    private val MainLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        //Handle the result
        if (result.resultCode == Activity.RESULT_OK){

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        //Enabling cheating
        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            //showing answer when pressed the button
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
            val messageResId = R.string.judgement_toast
            Toast.makeText(
                this,
                messageResId,
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.backButton.setOnClickListener {
            val intent = MainActivity.newIntent(this@CheatActivity)
            MainLauncher.launch(intent)

        }
    }

//setting a result when user presses show answer button
    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    //create intent for cheatActivity. Companion object to access functions without having an instance of a class
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }

    }
}