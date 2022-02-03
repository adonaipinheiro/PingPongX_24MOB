package br.com.adonaipinheiro.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import br.com.adonaipinheiro.pingpongx.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initAnimation()
        nextScreen()
    }

    private fun nextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val nextScreen = Intent(this, PlayerActivity::class.java)
            startActivity(nextScreen)
            finish()
        }, 2000)
    }

    private fun initView() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash)
        binding.ivLogo.startAnimation(anim)
        binding.ivName.startAnimation(anim)
    }

}