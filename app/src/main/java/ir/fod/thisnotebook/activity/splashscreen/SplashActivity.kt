package ir.fod.thisnotebook.activity.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import ir.fod.thisnotebook.R
import ir.fod.thisnotebook.activity.MainActivity
import ir.fod.thisnotebook.data.databaseroom.Entity.SplashEntity
import ir.fod.thisnotebook.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var imgView : ImageView

    private  val splashViewModel :SplashViewModel by  viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        imgView = findViewById(R.id.imageView)

        overridePendingTransition(R.anim.fadein , R.anim.fadeout)



        val imagePath = "android.resource://${packageName}/${R.drawable.icon}"
        splashViewModel.insertImage(SplashEntity(0, imagePath))

            splashViewModel.dataLocal.observe(this) { value ->

                if (value.isNotEmpty()){

                    Picasso.get()
                        .load(value[0].image)
                        .error(R.drawable.warning)
                        .into(imgView)

                    val  fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein)
                    imgView.startAnimation(fadeInAnimation)

                }


            }



        lifecycleScope.launch {

            delay(3000)

            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()

        }



    }



}