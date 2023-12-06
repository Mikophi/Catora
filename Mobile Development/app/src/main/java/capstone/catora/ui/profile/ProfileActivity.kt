package capstone.catora.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import capstone.catora.R
import capstone.catora.data.ArtWorkProfile
import capstone.catora.databinding.ActivityProfileBinding
import capstone.catora.ui.home.HomeActivity
import capstone.catora.ui.upload.UploadActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val list = ArrayList<ArtWorkProfile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() //this line for remove action bar

        binding.rvArtworkProfile.setHasFixedSize(true)
        binding.rvArtworkProfile.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

//        list.add(ArtWorkProfile("https://img.freepik.com/free-vector/flat-world-graphics-day-illustration_23-2148880103.jpg?w=740&t=st=1701679343~exp=1701679943~hmac=87ef872a0160c8eb255dfc97fb2ca8dbfe1963b24da8becccc9a3ebdc85e6622"),)
//        list.add(ArtWorkProfile("https://plus.unsplash.com/premium_photo-1661326416666-f8dff6c5bbe4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyfHx8ZW58MHx8fHx8"),)
//        list.add(ArtWorkProfile("https://plus.unsplash.com/premium_photo-1700782893131-1f17b56098d0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxN3x8fGVufDB8fHx8fA%3D%3D"),)
//        list.add(ArtWorkProfile("https://plus.unsplash.com/premium_photo-1661326416666-f8dff6c5bbe4?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyfHx8ZW58MHx8fHx8"),)
//        list.add(ArtWorkProfile("https://images.unsplash.com/photo-1682686581498-5e85c7228119?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxfHx8ZW58MHx8fHx8"),)

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

        binding.rvArtworkProfile.adapter = ListArtWorkProfileAdapter(list)

//
    }
}