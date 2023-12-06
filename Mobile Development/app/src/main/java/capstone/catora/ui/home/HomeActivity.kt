package capstone.catora.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import capstone.catora.R
import capstone.catora.data.ArtWorkProfile
import capstone.catora.databinding.ActivityHomeBinding
import capstone.catora.ui.detailartwork.DetailArtworkActivity
import capstone.catora.ui.profile.ListArtWorkProfileAdapter
import capstone.catora.ui.profile.ProfileActivity
import capstone.catora.ui.upload.UploadActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val list = ArrayList<ArtWorkProfile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() //this line for remove action bar


        binding.rvArtwork.setHasFixedSize(true)
        binding.rvArtwork.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        list.add(ArtWorkProfile(R.drawable.dummy_artwork_1))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_9))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_1))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_1))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_1))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_11))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_1))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_1))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_2))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_10))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_3))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_9))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_9))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_10))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_11))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_4))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_11))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_4))
        list.add(ArtWorkProfile(R.drawable.dummy_artwork_10))

        binding.rvArtwork.adapter = ListArtWorkProfileAdapter(list)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    true
                }
                R.id.upload -> {
                    startActivity(Intent(this, UploadActivity::class.java))
                    true
                }

                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}