package capstone.catora.ui.detailartwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import capstone.catora.R
import capstone.catora.data.ArtWorkProfile
import capstone.catora.databinding.ActivityDetailArtworkBinding
import capstone.catora.ui.profile.ListArtWorkProfileAdapter

class DetailArtworkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArtworkBinding
    private val list = ArrayList<ArtWorkProfile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtworkBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}