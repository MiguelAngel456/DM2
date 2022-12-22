package com.example.unir1

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var drag1ImageView : ImageView = findViewById(R.id.drag1ImageView)
    private var drag2ImageView : ImageView? = null
    private var drag3ImageView : ImageView? = null

    private var target1ImageView : ImageView? = null
    private var target2ImageView : ImageView? = null
    private var target3ImageView : ImageView? = null

    private var statusTextView : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drag1ImageView.setOnLongClickListener(longClickListener)
        drag2ImageView?.setOnLongClickListener(longClickListener)
        drag3ImageView?.setOnLongClickListener(longClickListener)

        target1ImageView?.setOnDragListener(dragListener)
        target2ImageView?.setOnDragListener(dragListener)
        target3ImageView?.setOnDragListener(dragListener)
    }

    private class MyDragShadowBuilder(val v: View) : View.DragShadowBuilder(v) {

        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            size.set(view.width, view.height)
            touch.set(view.width / 2, view.height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            v.draw(canvas)
        }
    }

    private val longClickListener = View.OnLongClickListener { v ->
        val item = ClipData.Item(v.tag as? CharSequence)

        val dragData = ClipData(
            v.tag as CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )

        val myShadow = MyDragShadowBuilder(v)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(dragData, myShadow,null,0)
        } else {
            v.startDrag(dragData, myShadow,null,0)
        }

        true
    }

    private val dragListener = View.OnDragListener { v, event ->
        val receiverView:ImageView = v as ImageView

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                statusTextView?.text = "Estas arrastrando una figura"
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.GREEN)
                    statusTextView?.text = "Imagen Correcta!"
                } else {
                    receiverView.setColorFilter(Color.RED)
                    statusTextView?.text = "No Imagen Inorrecta!"
                }
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                true

            DragEvent.ACTION_DRAG_EXITED -> {
                if(event.clipDescription.label == receiverView.tag as String) {
                    receiverView.setColorFilter(Color.YELLOW)
                    statusTextView?.text = "Casi la tenias!"
                    v.invalidate()
                }
                true
            }

            DragEvent.ACTION_DROP -> {
                statusTextView?.text = "Soltaste la imagen!"
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                true
            }

            else -> {
                false
            }
        }
    }
}