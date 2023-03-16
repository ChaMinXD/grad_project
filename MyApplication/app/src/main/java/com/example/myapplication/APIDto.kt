package com.example.myapplication

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

data class AnalData (
    val data: A_Data
)

data class A_Data (
    val attributes: Attributes,
    val type: String,
    val id: String,
    val links: Links
)

data class Attributes (
    val lastModificationDate: Long,
    val timesSubmitted: Long,
    val totalVotes: TotalVotes,
    val threatNames: List<Any?>,
    val redirectionChain: List<String>,
    val lastSubmissionDate: Long,
    val lastHTTPResponseContentLength: Long,
    val lastHTTPResponseHeaders: LastHTTPResponseHeaders,
    val reputation: Long,
    val tags: List<String>,
    val lastAnalysisDate: Long,
    val firstSubmissionDate: Long,
    val categories: Categories,
    val lastHTTPResponseContentSha256: String,
    val lastHTTPResponseCode: Long,
    val lastFinalURL: String,
    val url: String,
    val title: String,
    val lastAnalysisStats: LastAnalysisStats,
    val lastAnalysisResults: Map<String, LastAnalysisResult>,
    val tld: String,
    val htmlMeta: Map<String, List<String>>,
    val outgoingLinks: List<String>
)

data class Categories (
    val forcepointThreatSeeker: String,
    val sophos: String,
    val xcitiumVerdictCloud: String,
    val bitDefender: String
)

data class LastAnalysisResult (
    val category: Category,
    val result: Result,
    val method: Method,
    val engineName: String
)

enum class Category {
    Harmless,
    Undetected
}

enum class Method {
    Blacklist
}

enum class Result {
    Clean,
    Unrated
}

data class LastAnalysisStats (
    val harmless: Long,
    val malicious: Long,
    val suspicious: Long,
    val undetected: Long,
    val timeout: Long
)

data class LastHTTPResponseHeaders (
    val contentLength: String,
    val xXSSProtection: String,
    val contentEncoding: String,
    val strictTransportSecurity: String,
    val vary: String,
    val server: String,
    val connection: String,
    val pragma: String,
    val cacheControl: String,
    val date: String,
    val p3P: String,
    val referrerPolicy: String,
    val contentType: String,
    val xFrameOptions: String
)

data class TotalVotes (
    val harmless: Long,
    val malicious: Long
)

