package com.example.theaudiodbapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
    val strDescriptionCN: String,
    val strDescriptionDE: String,
    val strDescriptionEN: String,
    val strDescriptionES: String,
    val strDescriptionFR: String,
    val strDescriptionHU: String,
    val strDescriptionIL: String,
    val strDescriptionIT: String,
    val strDescriptionJP: String,
    val strDescriptionNL: String,
    val strDescriptionNO: String,
    val strDescriptionPL: String,
    val strDescriptionPT: String,
    val strDescriptionRU: String,
    val strDescriptionSE: String,
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
) : Parcelable