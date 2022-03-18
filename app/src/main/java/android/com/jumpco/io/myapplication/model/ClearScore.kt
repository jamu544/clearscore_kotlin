package android.com.jumpco.io.myapplication.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName



data class ClearScore constructor(
    var accountIDVStatus:String?,
    @SerialName("creditReportInfo")var scoreModel:Score)

data class Score (
    @SerializedName("score")var score: Int,
    @SerializedName("maxScoreValue")var maxScoreValue: Int)

