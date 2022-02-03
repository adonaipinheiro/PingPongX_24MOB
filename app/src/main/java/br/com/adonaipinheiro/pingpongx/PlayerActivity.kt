package br.com.adonaipinheiro.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import br.com.adonaipinheiro.pingpongx.constants.ExtrasContants
import br.com.adonaipinheiro.pingpongx.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        listeners()
    }

    private fun setupView() {
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun listeners() {
        binding.btStartGame.setOnClickListener {
            val nextScreen = Intent(this, MainActivity::class.java)
            nextScreen.putExtra(ExtrasContants.KEY_PLAYER_1, binding.etPlayer1.text.toString())
            nextScreen.putExtra(ExtrasContants.KEY_PLAYER_2, binding.etPlayer2.text.toString())
            previewRequest.launch(nextScreen)
        }
    }

    private val previewRequest =
        registerForActivityResult((ActivityResultContracts.StartActivityForResult())) {
            if (it.resultCode == RESULT_OK) {
                Toast.makeText(this, it.data?.getStringExtra("WINNER"), Toast.LENGTH_LONG).show()
            }
        }
}