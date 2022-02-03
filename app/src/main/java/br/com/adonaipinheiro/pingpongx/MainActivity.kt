package br.com.adonaipinheiro.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.adonaipinheiro.pingpongx.constants.ExtrasContants
import br.com.adonaipinheiro.pingpongx.databinding.ActivityMainBinding
import br.com.adonaipinheiro.pingpongx.databinding.ActivityPlayerBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var homeScore = 0
    private var awayScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        listeners()
        handleExtras()

        if (savedInstanceState != null) {
            homeScore = savedInstanceState.getInt("PLAYER_ONE_SCORE", 0)
            awayScore = savedInstanceState.getInt("PLAYER_TWO_SCORE", 0)
            binding.tvHomeScore.text = homeScore.toString()
            binding.tvAwayScore.text = awayScore.toString()
        }
    }

    private fun setupView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("PLAYER_ONE_SCORE", homeScore)
        outState.putInt("PLAYER_TWO_SCORE", awayScore)
    }

    private fun listeners() {
        binding.ivBackButton.setOnClickListener() {
            val nextScreen = Intent(this, PlayerActivity::class.java)
            startActivity(nextScreen)
            finish()
        }

        binding.btPointHome.setOnClickListener() {
            homeScore++
            binding.tvHomeScore.text = homeScore.toString()
        }

        binding.btPointAway.setOnClickListener() {
            awayScore++
            binding.tvAwayScore.text = awayScore.toString()
        }

        binding.btFinish.setOnClickListener() {
            val ret = Intent()
            ret.putExtra("WINNER", getWinner())
            setResult(RESULT_OK, ret)
            finish()
        }

        binding.btRevange.setOnClickListener() {
            homeScore = 0
            awayScore = 0
            binding.tvHomeScore.text = "0"
            binding.tvAwayScore.text = "0"
        }
    }

    private fun getWinner(): String? {
        return if (homeScore > awayScore) "Jogador 1 ganhou" else if (homeScore < awayScore) "Jogador 2 ganhou" else "Empate"
    }

    private fun handleExtras() {
        binding.tvHomeName.text = intent?.getStringExtra(ExtrasContants.KEY_PLAYER_1) ?: "Jogador 1"
        binding.tvAwayName.text = intent?.getStringExtra(ExtrasContants.KEY_PLAYER_2) ?: "Jogador 2"
    }
}