package com.learnandroidapplicationdevelopment.magicidapplication

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputID:EditText = findViewById(R.id.inputID)
        val btnSubmit:Button = findViewById(R.id.btnSubmit)
        val tvResults:TextView = findViewById(R.id.tvResults)
        val tvMoreInfo:TextView = findViewById(R.id.tvMoreInfo)

        btnSubmit.setOnClickListener(View.OnClickListener {

            tvResults.setBackgroundColor(Color.parseColor("#FFFFFF"))
            tvResults.text = ""
            tvMoreInfo.text = ""
            tvMoreInfo.setBackgroundColor(Color.parseColor("#FFFFFF"))

            var id = inputID.text.trim()

            var nums = id.map { it.toString().toInt() }

            val controlNum = nums.drop(nums.size - 1)[0]

            nums = nums.dropLast(1)

            val genderNum = nums.drop(nums.size - 1)[0]
            val gender = if (genderNum % 2 == 0) "Female" else "Male"
            val genderColor = if (gender == "Female") "#FF4081" else "#03A9F4"

            var multipliedNums = IntArray(nums.size)

            for ((i, num) in nums.withIndex()) {
                var res: Int
                if (i % 2 == 0) {
                    res = multiplyByOneOrTwo(2, num)
                } else {
                    res = multiplyByOneOrTwo(1, num)
                }

                if (res.toString().length == 2) {
                    var resSplit = res.toString().map { it.toString().toInt() }
                    res = resSplit.reduce {sum, el -> sum + el}.toInt()
                }
                multipliedNums[i] = res
            }

            val numsAddedUp = multipliedNums.reduce { sum, el -> sum + el }

            val result = numsAddedUp + controlNum

            if (result % 10 == 0) {
                tvResults.setBackgroundColor(Color.parseColor("#4CAF50"))
                tvResults.text = "Valid"
                tvMoreInfo.text = gender
                tvMoreInfo.setBackgroundColor(Color.parseColor(genderColor))

            } else {
                tvResults.setBackgroundColor(Color.parseColor("#F44336"))
                tvResults.text ="Invalid"
            }
        })
    }
    val multiplyByOneOrTwo = { n1:Int, n2:Int -> n1 * n2 }


}
