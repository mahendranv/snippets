package leet

class Solution {

    /** 
     * 
     * SlidingWindow
     * 
     *                                                       i
     * a     b     c     d      a     b    c     d     e     f
     *                          |
     *                         *start----------*j
     * 
     */
    fun lengthOfLongestSubstringSlidingWindow(s: String): Int {
        var start = 0
        var length = 0
        var max = 0
        
        outer@for ((i, c) in s.withIndex()) {
            length = 1
            inner@for (j in start until i) {
                if (s[j] == c) {
                    start = j+1
                    break@inner
                } else {
                    length++
                }
            }
            max = Math.max(length, max)
        }
        return max
    }


    /**
     * 
     * Sliding window with memory.
     * 
     * |  a   |  4  |
     * |  b   |  5  |
     * ...
     * 
     * a     b     c     d      a     b    c     d     e     f
     *                          |
     *                         *start---------length-------------*i
     * 
     */
    fun lengthOfLongestSubstring(s: String): Int {
        var start = 0
        var max = 0
        var occ = java.util.HashMap<Char, Int>()
        
        // println("|#|start|end|char|subsequence|length|")
        // println("|--| --- | --- | --- | --- | --- |")
        for ((i, c) in s.withIndex()) {
            var clash = ""
            if (occ.containsKey(c)) {
                val seekPoint = occ[c]!! + 1
                start = Math.max(seekPoint, start)
                clash = "*"
            }
            val length = i - start + 1
            // println("|$i|$start|$i|$c$clash|${s.subSequence(start, i+1)}|$length|")
            max = Math.max(length, max)

            // Update the map with latest occurance
            // when clash happen next time, use this index
            occ[c] = i
        }
        println("Max($s) = $max")
        return max
    }
}

fun main(args: Array<String>) {

    val solution = Solution()

    val set = listOf(
        "",
        "a",
        "aa",
        "ab",
        "abca",
        "abcdefghijk",
        "abccabcde",
        "abccabdefghabcababcab",
        "abcamnijabxyzzz",
    )

    for (s in set) {
        solution.lengthOfLongestSubstring(s)
    }
}