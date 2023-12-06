package capstone.catora.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import capstone.catora.R
import capstone.catora.data.ArtWorkProfile
import capstone.catora.databinding.ItemArtworkProfileBinding
import com.bumptech.glide.Glide

class ListArtWorkProfileAdapter(private val listArtWorkProfile: ArrayList<ArtWorkProfile>) : RecyclerView.Adapter<ListArtWorkProfileAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemArtworkProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artwork: ArtWorkProfile){
//            val image : ImageView = itemView.findViewById(R.id.iv_item_artwork_profile)
//            Glide.with(itemView)
//                .load(artwork.image_url)
//                .into(binding.ivItemArtworkProfile)
            binding.itemArtwork.setImageResource(artwork.image_url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_artwork_profile, parent, false)
        val binding = ItemArtworkProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listArtWorkProfile.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val artworkProfile = listArtWorkProfile[position]
        if (artworkProfile != null){
            holder.bind(artworkProfile)
        }
    }

}