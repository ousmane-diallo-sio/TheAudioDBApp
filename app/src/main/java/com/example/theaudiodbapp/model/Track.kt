package com.example.theaudiodbapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
class Track(
    val idAlbum: String,
    val idArtist: String,
    val idIMVDB: String,
    val idLyric: String,
    val idTrack: String,
    val intCD: String,
    val intDuration: String,
    val intLoved: String,
    val intMusicVidComments: String,
    val intMusicVidDislikes: String,
    val intMusicVidFavorites: String,
    val intMusicVidLikes: String,
    val intMusicVidViews: String,
    val intScore: String,
    val intScoreVotes: String,
    val intTotalListeners: String,
    val intTotalPlays: String,
    val intTrackNumber: String,
    val strAlbum: String,
    val strArtist: String,
    val strArtistAlternate: String,
    private val strDescriptionCN: String?,
    private val strDescriptionDE: String?,
    private val strDescriptionEN: String?,
    private val strDescriptionES: String?,
    private val strDescriptionFR: String?,
    private val strDescriptionHU: String?,
    private val strDescriptionIL: String?,
    private val strDescriptionIT: String?,
    private val strDescriptionJP: String?,
    private val strDescriptionNL: String?,
    private val strDescriptionNO: String?,
    private val strDescriptionPL: String?,
    private val strDescriptionPT: String?,
    private val strDescriptionRU: String?,
    private val strDescriptionSE: String?,
    val strGenre: String,
    val strLocked: String,
    val strMood: String,
    val strMusicBrainzAlbumID: String,
    val strMusicBrainzArtistID: String,
    val strMusicBrainzID: String,
    val strMusicVid: String,
    val strMusicVidCompString: String,
    val strMusicVidDirector: String,
    val strMusicVidScreen1: String,
    val strMusicVidScreen2: String,
    val strMusicVidScreen3: String,
    val strStyle: String,
    val strTheme: String,
    val strTrack: String,
    val strTrack3DCase: String,
    val strTrackLyrics: String,
    val strTrackThumb: String
) : Parcelable {

    fun getDescriptionByLanguage(): String? {
        val translation = when (Locale.getDefault().language) {
            "cn" -> strDescriptionCN
            "de" -> strDescriptionDE
            "es" -> strDescriptionES
            "hu" -> strDescriptionHU
            "fr" -> strDescriptionFR
            "il" -> strDescriptionIL
            "it" -> strDescriptionIT
            "jp" -> strDescriptionJP
            "nl" -> strDescriptionNL
            "no" -> strDescriptionNO
            "pl" -> strDescriptionPL
            "pt" -> strDescriptionPT
            "ru" -> strDescriptionRU
            "se" -> strDescriptionSE
            else -> strDescriptionEN
        } ?: strDescriptionEN

        return translation
    }
}