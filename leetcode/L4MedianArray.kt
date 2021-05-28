package leet.code


/**
 * Runtime: 236 ms, faster than 99.74% of Kotlin online submissions for Median of Two Sorted Arrays.
 * Memory Usage: 45.4 MB, less than 67.79% of Kotlin online submissions for Median of Two Sorted Arrays.
 */
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
   
    // Pointers to array
    var i = 0
    var j = 0

    // Identified medians
    var m1 = 0
    var m2 = 0
    
    for (k in 0..(nums1.size+nums2.size)/2) {
        m1 = m2
        if (i == nums1.size) {
            m2 = nums2[j]
            j++
        } else if (j == nums2.size) {
            m2 = nums1[i]
            i++
        } else if (nums1[i] < nums2[j]) {
            m2 = nums1[i]
            i++
        } else {
            m2 = nums2[j]
            j++
        }
    }

    return if ((nums1.size+nums2.size)%2 == 0) {
        (m1 + m2) / 2.0
    } else m2.toDouble()    
}

fun main(args: Array<String>) {

    val testSet = listOf(
        Pair(intArrayOf(1,2), intArrayOf(3,4)),
        Pair(intArrayOf(1,3), intArrayOf(2)),
        Pair(intArrayOf(0,0), intArrayOf(0,0)),
        Pair(intArrayOf(), intArrayOf(1)),
        Pair(intArrayOf(2), intArrayOf()),
    )

    for (pair in testSet) {
        val result = findMedianSortedArrays(pair.first, pair.second)
        println("""
        median:::
        nums1 = ${pair.first.joinToString(",")}
        nums2 = ${pair.second.joinToString(",")}
        === $result """)
    }
}