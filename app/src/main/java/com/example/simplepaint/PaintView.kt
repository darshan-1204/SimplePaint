package com.example.simplepaint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.simplepaint.MainActivity.Companion.paintBrush
import com.example.simplepaint.MainActivity.Companion.path

class PaintView : View {

    //    This is used to define the height and the width of canvas with respect of parent layout
    var params: ViewGroup.LayoutParams? = null

    companion object {
        //        this store all the path that we draw
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()

        //        To hold the current value(brush no color) of the brush
        var currentBrush = Color.BLACK
    }

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0) {
        init()
    }

    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
//        This is used to making the structure smooth
        paintBrush.isAntiAlias = true
        paintBrush.color = currentBrush

//        To set the style pf the brush
        paintBrush.style = Paint.Style.STROKE

//        This is the end of the path that you can edit
        paintBrush.strokeJoin = Paint.Join.ROUND

//        To define the width of the brush
        paintBrush.strokeWidth = 8f //(f is the float value)

        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    //    To register the movement of the finger on the screen
    override fun onTouchEvent(event: MotionEvent): Boolean {

        var x = event.x
        var y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                path.moveTo(x,y)
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                path.lineTo(x,y)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
//        This is used to inform the non validate ui changes,some changes has been there in ui
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {

        for(i in pathList.indices){
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
//            Similar to path invalidate
            invalidate()
        }

    }
}