package com.example.myapplication.Retrofit

import com.google.gson.annotations.SerializedName

data class ScanData (
    @SerializedName("data") val data: Data
)
data class Data (
    @SerializedName("type")val type: String,
    @SerializedName("id")val id: String,
    @SerializedName("links")val links: Links
)

data class Links (
    @SerializedName("self")val self: String
)

data class SummaryData(
    val message : String,
    val content : String,
    val passed : Boolean
)
data class SummaryRequest(
    val url:String,
    val secondFilteringWords:List<String>
)

data class VirustotalUrlDto(
    val data: Data,
    val id: String,
    val links: Links,
    val type: String
) {
    data class Data(
        val attributes: Attributes
    ) {
        data class Attributes(
            val categories: Map<String, String>,
            val favicon: Favicon,
            val first_submission_date: Long,
            val html_meta: Map<String, List<String>>,
            val last_analysis_date: Long,
            val last_analysis_results: Map<String, AnalysisResult>,
            val last_analysis_stats: AnalysisStats,
            val last_final_url: String,
            val last_http_response_code: Int,
            val last_http_response_content_length: Int,
            val last_http_response_content_sha256: String,
            val last_http_response_cookies: Map<String, String>,
            val last_http_response_headers: Map<String, String>,
            val last_modification_date: Long,
            val last_submission_date: Long,
            val outgoing_links: List<String>,
            val redirection_chain: List<String>,
            val reputation: Int,
            val tags: List<String>,
            val targeted_brand: Map<String, String>,
            val times_submitted: Int,
            val title: String,
            val total_votes: TotalVotes,
            val trackers: Map<String, List<Tracker>>,
            val url: String
        ) {
            data class Favicon(
                val dhash: String,
                val raw_md5: String
            )

            data class AnalysisResult(
                val category: String,
                val engine_name: String,
                val method: String,
                val result: String
            )

            data class AnalysisStats(
                val harmless: Int,
                val malicious: Int,
                val suspicious: Int,
                val timeout: Int,
                val undetected: Int
            )

            data class TotalVotes(
                val harmless: Int,
                val malicious: Int
            )

            data class Tracker(
                val id: String,
                val timestamp: Long,
                val url: String
            )
        }
    }

    data class Links(
        val self: String
    )
}


