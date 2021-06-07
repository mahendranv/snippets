package com.ex2.leet

// Runtime: 400 ms, faster than 76.69% of Kotlin online submissions for Longest Palindromic Substring.
// Memory Usage: 18.4 MB, less than 68.69% of Kotlin online submissions for Longest Palindromic Substring.

fun longestPalindrome(s: String): String {
    var result = 0..0
    for (i in 0 until s.length) {
      val newResult = getPalindrome(s, i)
      if (newResult.count() > result.count()) {
          result = newResult
      }
    }
    return s.subSequence(result.start, result.endInclusive+1).toString()
}

private fun getPalindrome(s: String, i: Int) : IntRange {
    var p1 = getPalindrome(s, i, i)
    if (i < s.lastIndex && s[i] == s[i+1]) {
        val p2 = getPalindrome(s, i, i+1)
        if (p1.count() < p2.count()) {
            p1 = p2
        }
    }
    return p1
}

private fun getPalindrome(s: String, i: Int, j: Int) : IntRange {
    var start = i
    var end = j
    while(s[start] == s[end]) {
        start--
        end++
        if (start < 0 || end > s.lastIndex) {
            break
        }
    }
    start++
    end--
    return start..end
}


fun main() {

    val input = listOf(
        "babad",
        "cbbd",
        "cbbbd",
        "a",
        "ac",
        "bb",
        "aaaa",
        "aaaaa",
    )

    for( s in input) {
        val res = longestPalindrome(s)
        println("f($s) = $res")
    }

}