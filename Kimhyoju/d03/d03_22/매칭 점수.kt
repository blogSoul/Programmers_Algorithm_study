// 웹 HTML 문서를 파싱해서 정보를 얻는 문제.
// 정규식을 사용하는 건가 했는데 정규식이라고 또 깔끔한 풀이가 생각나지 않고..
// 막막해서 압도당함.
// 그래서 그냥 해답 영상을 봤다. 문자열 처리 실력이 많이 부족하다는 것을 깨닫게 됐다.

// indexOf와 lastIndexOf 함수의 쓰임.
// 또 문자인지를 구별할때 in 'a'..'z' 뿐만아니라 Character.isLetter, isDigit 등등..
// Character 라이브러리를 한번 살펴봐야겠다.
fun main() {
    class Solution {
        fun solution(wrd: String, pages: Array<String>): Int {
            data class Webpage(
                var basicScore: Int,
                var outLink: Int,
                var linkScore: Double,
                var matchingScore: Int
            )

            val pageMap = mutableMapOf<String, Int>()
            val webpages = Array(pages.size) { Webpage(0, 0, 0.0, 0) }
            val links = Array(pages.size) { mutableListOf<String>() }
            var answer = 0
            var word = wrd.toLowerCase()

            for ((pageIdx, rawPage) in pages.withIndex()) {
                val page = rawPage.toLowerCase()

                var posl = 0
                var mid = 0
                var posr = 0
                while (mid <= posl) {
                    posl = page.indexOf("<meta", posl + 1)
                    posr = page.indexOf(">", posl)
                    mid = page.lastIndexOf("https://", posr) //만약 mid <= posl 이면 https:// 가 meta 태그 밖에.
                    // 하지만 mid > posl, 즉 whild 조건문이 false가 되면 meta 태그 안에 있는 것이다.
                    println("posl: $posl")
                    println("mid: $mid")
                    println("posr: $posr")
                }
                // mid 위치 구했으면 이제 url 마지막 위치를 구한다.
                posr = page.indexOf("\"", mid) // mid = url 시작, url이 끝나는 따옴표가 posr
                var url = page.substring(mid, posr)
                pageMap[url] = pageIdx
                println("url: $url")

                // 이제 body를 찾을 것
                posl = page.indexOf("<body>", posr)
                var start = posl
                while (true) {
                    println("word start: $start")
                    start = page.indexOf(word, start + 1)
                    if (start == -1) { // 더이상 검색어를 찾을 수 없음.
                        break
                    }
                    // 근데, 단어를 찾아도 앞뒤로 알파벳이 붙어있으면 안됨. 정확히 단어가 맞는지 확인.
                    // 단어를 찾은 그 인덱스의 바로 앞문자가 알파벳이면 안됨.
//                    if (page[start-1] !in 'a'..'z' && page[start+word.length] !in 'a'..'z') {
                    if (!Character.isLetter(page[start - 1]) && !Character.isLetter(page[start + word.length])) {
                        webpages[pageIdx].basicScore++
                        start += word.length
                    }
                }

                // 이제 외부 링크
                start = posl // 여전히 body 부터 시작
                while (true) {
                    println("<a href start: $start")
                    start = page.indexOf("<a href", start + 1)
                    if (start == -1) break
                    webpages[pageIdx].outLink++
                    var linkStart = page.indexOf("\"", start)
                    var linkEnd = page.indexOf("\"", linkStart + 1)
                    links[pageIdx].add(page.substring(linkStart + 1, linkEnd))
                }
            }

            for ((idx, webpage) in webpages.withIndex()) {
                for (link in links[idx]) {
                    println("idx: $idx, link: $link")
                    val linkPageIdx = pageMap.getOrDefault(link,-1)
                    if (linkPageIdx != -1) {
                        webpages[linkPageIdx].linkScore += (webpage.basicScore.toDouble() / webpage.outLink.toDouble())
                        println(webpages[linkPageIdx].linkScore)
                    }
                }
            }


            webpages.forEach { println(it.basicScore) }
            webpages.forEach { println(it.outLink) }
            webpages.forEach { println(it.linkScore) }
            var max = 0.0
            answer = 0
            for ((idx, webpage) in webpages.withIndex()) {
                if (webpage.basicScore + webpage.linkScore > max) {
                    max = webpage.basicScore + webpage.linkScore
                    answer = idx
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
    word = "Muzi"
    pages = arrayOf(
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
    )

    println(sol.solution(word, pages))
}

