package android.com.jumpco.io.myapplication.pojo

import com.google.gson.annotations.SerializedName

class ClearScoreModel {
    @SerializedName("accountIDVStatus")
    var accountIDVStatus:String? = null
    @SerializedName("creditReportInfo")
    var scoreModel: Score? = null

    class Score {
        @SerializedName("score")
        var score: Int = 0
        @SerializedName("maxScoreValue")
        var maxScoreValue: Int = 0
    }
}