package capstone.catora.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtWorkProfile(
//    val image_url:String
    val image_url:Int
) : Parcelable
