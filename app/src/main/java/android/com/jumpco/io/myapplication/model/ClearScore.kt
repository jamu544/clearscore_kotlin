package android.com.jumpco.io.myapplication.model


data class ClearScore (var accountIDVStatus:String?,
                       var creditReportInfo:Score,
                       var dashboardStatus:String,
                       var personaType:String)

data class Score (var score: Int,
                  var maxScoreValue: Int,
                  var clientRef: String,
                  var status: String
                  )

