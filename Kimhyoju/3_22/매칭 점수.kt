// 정규식을 사용하는 건가 했는데 정규식이라고 또 깔끔한 풀이가 생각나지 않고..
// 막막해서 압도당함.
// 그래서 그냥 해답 영상을 봤다. 문자열 처리 실력이 많이 부족하다는 것을 깨닫게 됐다.
fun main() {
    class Solution {
        fun solution(word: String, pages: Array<String>): Int {
            data class Webpage(
                var basicScore: Int,
                var outLink: Int,
                var linkScore: Int,
                var matchingScore: Int
            )

            val pageMap = mutableMapOf<String, Int>()
            val webpages = Array(pages.size) { Webpage(1, 1, 1, 1) }
            val links = Array(pages.size) { mutableListOf<String>() }
            var answer = 0

            for ((pageIdx, rawPage) in pages.withIndex()) {
                val page = rawPage.toLowerCase()
                var pageName = ""
                var basicScore = 0
                var outLink = 0
                var posl = 0
                var mid = 0
                var posr = 0
                while (mid <= posl) {
                    posl = page.indexOf("<meta", posl + 1)
                    posr = page.indexOf(">", posl)
                    mid = page.lastIndexOf("https://", posr) //만약 mid <= posl 이면 https:// 가 meta 태그 밖에.
                    // 하지만 mid > posl, 즉 whild 조건문이 false가 되면 meta 태그 안에 있는 것이다.
                }
                // mid 위치 구했으면 이제 url 마지막 위치를 구한다.
                posr = page.indexOf("\"", mid) // mid = url 시작, url이 끝나는 따옴표가 posr
                var url = page.substring(mid, posr)



                pageMap[url] = pageIdx





                webpages[pageIdx].basicScore = basicScore
                webpages[pageIdx].outLink = outLink
            }

            for ((pageIdx, webpage) in webpages.withIndex()) {
                for (link in links[pageIdx]) {

                }
            }



            return answer
        }
    }

    val sol = Solution()

    var word: String
    var pages: Array<String>

    word = "blind"
    pages = arrayOf(
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
    )

    println(sol.solution(word, pages))
}

