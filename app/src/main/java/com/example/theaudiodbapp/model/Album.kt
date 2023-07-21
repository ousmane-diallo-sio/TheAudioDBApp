package com.example.theaudiodbapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
class Album(
    val idAlbum: String,
    val idArtist: String,
    val idLabel: String,
    val intLoved: String,
    val intSales: String,
    val intScore: String?,
    val intScoreVotes: String?,
    val intYearReleased: String,
    val strAlbum: String,
    val strAlbum3DCase: String,
    val strAlbum3DFace: String,
    val strAlbum3DFlat: String,
    val strAlbum3DThumb: String,
    val strAlbumCDart: String,
    val strAlbumSpine: String,
    val strAlbumStripped: String,
    val strAlbumThumb: String,
    val strAlbumThumbBack: String,
    val strAlbumThumbHQ: String,
    val strAllMusicID: String,
    val strAmazonID: String,
    val strArtist: String,
    val strArtistStripped: String,
    val strBBCReviewID: String,
    private val strDescription: String?,
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
    val strDiscogsID: String,
    val strGeniusID: String,
    val strGenre: String,
    val strItunesID: String,
    val strLabel: String,
    val strLocation: String,
    val strLocked: String,
    val strLyricWikiID: String,
    val strMood: String,
    val strMusicBrainzArtistID: String,
    val strMusicBrainzID: String,
    val strMusicMozID: String,
    val strRateYourMusicID: String,
    val strReleaseFormat: String,
    val strReview: String,
    val strSpeed: String,
    val strStyle: String,
    val strTheme: String,
    val strWikidataID: String,
    val strWikipediaID: String
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
        } ?: strDescriptionEN ?: strDescription

        return translation
    }
}