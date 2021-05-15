package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener {
            if (binding.editService.text.isEmpty())
                Toast.makeText(this, "Enter price...", Toast.LENGTH_SHORT).show()
            else
                calculateTip()
        }
    }

    private fun calculateTip() {
        val price = binding.editService.text.toString().toDouble()
        val tipPercentage = when(binding.radioGroup.checkedRadioButtonId) {
            R.id.radio_amazing -> 0.20
            R.id.radio_good -> 0.15
            else -> 0.1
        }
        var tip = tipPercentage * price
        if (binding.switchRound.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val tipStr = NumberFormat.getCurrencyInstance().format(tip)
        binding.textResult.text = tipStr
    }
}

