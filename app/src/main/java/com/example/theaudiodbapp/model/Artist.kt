package com.example.theaudiodbapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
class Artist(
    val idArtist: String,
    val idLabel: String,
    val intBornYear: String,
    val intCharted: String,
    val intDiedYear: String,
    val intFormedYear: String,
    val intMembers: String,
    val strArtist: String,
    val strArtistAlternate: String,
    val strArtistBanner: String,
    val strArtistClearart: String,
    val strArtistCutout: String,
    val strArtistFanart: String?,
    val strArtistFanart2: String?,
    val strArtistFanart3: String,
    val strArtistFanart4: String,
    val strArtistLogo: String,
    val strArtistThumb: String,
    val strArtistWideThumb: String?,
    private val strBiographyCN: String?,
    private val strBiographyDE: String?,
    private val strBiographyEN: String?,
    private val strBiographyES: String?,
    private val strBiographyFR: String?,
    private val strBiographyHU: String?,
    private val strBiographyIL: String?,
    private val strBiographyIT: String?,
    private val strBiographyJP: String?,
    private val strBiographyNL: String?,
    private val strBiographyNO: String?,
    private val strBiographyPL: String?,
    private val strBiographyPT: String?,
    private val strBiographyRU: String?,
    private val strBiographySE: String?,
    val strCountry: String?,
    val strCountryCode: String,
    val strFacebook: String,
    val strGender: String,
    val strGenre: String?,
    val strLabel: String,
    val strLastFMChart: String,
    val strLocked: String,
    val strMood: String,
    val strMusicBrainzID: String,
    val strStyle: String,
    val strTwitter: String,
    val strWebsite: String
) : Parcelable {

    fun getBiographyByLanguage(): String? {
        val translation = when (Locale.getDefault().language) {
            "cn" -> strBiographyCN
            "de" -> strBiographyDE
            "es" -> strBiographyES
            "hu" -> strBiographyHU
            "fr" -> strBiographyFR
            "il" -> strBiographyIL
            "it" -> strBiographyIT
            "jp" -> strBiographyJP
            "nl" -> strBiographyNL
            "no" -> strBiographyNO
            "pl" -> strBiographyPL
            "pt" -> strBiographyPT
            "ru" -> strBiographyRU
            "se" -> strBiographySE
            else -> strBiographyEN
        } ?: strBiographyEN

        return translation
    }
}