package com.leet.code;

// Runtime: 248 ms, faster than 53.46% of Kotlin online submissions for ZigZag Conversion.
// Memory Usage: 36.4 MB, less than 75.47% of Kotlin online submissions for ZigZag Conversion.
fun convert(s: String, numRows: Int): String {
    val L = numRows-1
    if (L == 0) 
        return s
    val sb = StringBuffer(s.length)
    for (r in 0 until numRows) {
        var c = 0
        while (true) {
            val i = 2*c*L - r
            val j = 2*c*L + r

            // Corners
            if (i >= 0 && i < s.length) {
                sb.append(s[i])
            }
            if (j >= 0 && j != i &&j < s.length && j %L != 0) {
                sb.append(s[j])
            }
            if (i >= s.length || j >= s.length) {
                break
            }
            c++
        }
    }
    return sb.toString()
}


fun main() {

    val s = "PAYPALISHIRING"
    val rows = 3

    // val result = convert(s, rows)
    // println(result)


    val result2 = convert("A", 1)
    println(result2)
}