package capstone.catora.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import capstone.catora.R
import capstone.catora.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() //this line for remove action bar

        binding.tvToLogin.setOnClickListener {
            finish()
            //this will route back to login by destroying current activity
        }

        binding.btnRegister.setOnClickListener {
            //this will handle the registration
            finish()
        }

    }
}