package capstone.catora.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import capstone.catora.R
import capstone.catora.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling login or to homepage here
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}