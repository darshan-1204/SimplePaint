package com.example.simplepaint

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplepaint.PaintView.Companion.colorList
import com.example.simplepaint.PaintView.Companion.currentBrush
import com.example.simplepaint.PaintView.Companion.pathList
import com.example.simplepaint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRed.setOnClickListener {
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }
        binding.btnBlue.setOnClickListener {
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }
        binding.btnBlack.setOnClickListener {
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
        }
        binding.btnErase.setOnClickListener {
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    private fun currentColor(color:Int){
        currentBrush = color
        path = Path()
    }
}