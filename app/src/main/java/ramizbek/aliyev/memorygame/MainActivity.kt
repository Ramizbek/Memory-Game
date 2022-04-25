package ramizbek.aliyev.memorygame


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var timer: CountDownTimer? = null
    var text1 = "FINISH"
    val listImageOchiqYopiq =
        arrayOf(false, false, false, false, false, false, false, false, false, false, false, false)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    var animationDoing = false
    var stop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        image_1.setOnClickListener {
            imageClick(image_1, R.drawable.moto, 0)
        }
        image_2.setOnClickListener {
            imageClick(image_2, R.drawable.tabiat2, 1)
        }
        image_3.setOnClickListener {
            imageClick(image_3, R.drawable.moto, 2)
        }
        image_4.setOnClickListener {
            imageClick(image_4, R.drawable.flag_of_denmark, 3)
        }
        image_5.setOnClickListener {
            imageClick(image_5, R.drawable.tabiat2, 4)
        }
        image_6.setOnClickListener {
            imageClick(image_6, R.drawable.flag_of_denmark, 5)
        }
        image_7.setOnClickListener {
            imageClick(image_7, R.drawable.sticker, 6)
        }
        image_8.setOnClickListener {
            imageClick(image_8, R.drawable.sticker1, 7)
        }
        image_9.setOnClickListener {
            imageClick(image_9, R.drawable.sticker2, 8)
        }
        image_10.setOnClickListener {
            imageClick(image_10, R.drawable.sticker1, 9)
        }
        image_11.setOnClickListener {
            imageClick(image_11, R.drawable.sticker, 10)
        }
        image_12.setOnClickListener {
            imageClick(image_12, R.drawable.sticker2, 11)
        }
//        startCountDown(20000)
    }


    private fun imageClick(imageView: ImageView, rasm: Int, index: Int) {
        if (!stop) {
            if (!animationDoing) {
                if (!listImageOchiqYopiq[index]) {
                    animationOchilishi(imageView, rasm, index)
                } else {
                    animationYopilishi(imageView, rasm, index)
                }
            }
        }
    }

//    private fun startCountDown(timeMills: Long) {
//        stop = false
//        timer?.cancel()
//        timer = object : CountDownTimer(timeMills, 1000) {
//            override fun onTick(timeM: Long) {
//                txt_time.text = (timeM / 1000).toString()
//            }
//
//            @SuppressLint("SetTextI18n")
//            override fun onFinish() {
//                stop = true
//                txt_time.text = text1
//            }
//        }.start()
//    }

    private fun animationOchilishi(imageView: ImageView, rasm: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(rasm)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listImageOchiqYopiq[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++

                        if (ochiqRasm == 2) {
                            if (rasmId[0] == rasmId[1]) {
                                imageViewAniqla(imageIndex[0]).visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1]).visibility = View.INVISIBLE
                                ochiqRasm--
                            } else {
                                animationYopilishi(
                                    imageViewAniqla(imageIndex[0]),
                                    -1,
                                    imageIndex[0]
                                )
                                animationYopilishi(
                                    imageViewAniqla(imageIndex[1]),
                                    -1,
                                    imageIndex[1]
                                )
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

    }


    fun animationYopilishi(imageView: ImageView, rasm: Int, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.yulduzcha)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        listImageOchiqYopiq[index!!] = false
        ochiqRasm--
    }

    fun imageViewAniqla(index: Int?): ImageView {
        var imageView: ImageView? = null
        when (index) {
            0 -> imageView = image_1
            1 -> imageView = image_2
            2 -> imageView = image_3
            3 -> imageView = image_4
            4 -> imageView = image_5
            5 -> imageView = image_6
            6 -> imageView = image_7
            7 -> imageView = image_8
            8 -> imageView = image_9
            9 -> imageView = image_10
            10 -> imageView = image_11
            11 -> imageView = image_12
        }
        return imageView!!
    }

}