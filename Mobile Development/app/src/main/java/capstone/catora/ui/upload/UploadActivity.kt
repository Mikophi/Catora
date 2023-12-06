package capstone.catora.ui.upload

import android.app.ActionBar
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import capstone.catora.R
import capstone.catora.databinding.ActivityUploadBinding
import kotlin.random.Random


class UploadActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUploadBinding

    private var currentImageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var actionBar = supportActionBar

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        binding.llChooseImage.setOnClickListener {
            startGalerry()
        }
        binding.tvChooseOtherImage.setOnClickListener {
            startGalerry()
        }
        binding.btnUpload.setOnClickListener {
            //Random.nextBoolean() just for giving dummy boolean, remove this when system has response from server
            uploadAction(Random.nextBoolean())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun uploadAction(isHumanArt: Boolean) {

        if (isHumanArt){
            AlertDialog.Builder(this@UploadActivity).apply {
                setTitle("Success!")
                setMessage("Your art likely human art")
                setPositiveButton("Continue"){_,_ ->
                    finish()
                }
                create()
                show()
            }
        } else{
            AlertDialog.Builder(this@UploadActivity).apply {
                setTitle("Failed")
                setMessage("Your art likely Ai")
                setPositiveButton("Close"){_,_ ->
                    finish()
                }
                create()
                show()
            }

        }
    }

    private fun startGalerry(){
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ){uri: Uri? ->
        if (uri != null){
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image Uri", "showImage: $it")
            binding.ivArtWork.setImageURI(it)
            binding.ivArtWork.visibility = View.VISIBLE
            binding.llChooseImage.visibility = View.GONE
            binding.tvChooseOtherImage.visibility = View.VISIBLE
        }
    }
}