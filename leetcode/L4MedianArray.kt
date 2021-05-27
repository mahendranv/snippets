package leet.code


/**
 * Runtime: 236 ms, faster than 99.74% of Kotlin online submissions for Median of Two Sorted Arrays.
 * Memory Usage: 45.4 MB, less than 67.79% of Kotlin online submissions for Median of Two Sorted Arrays.
 */
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    
    // Find median positions, max 2 elements
    val totalElements = nums1.size + nums2.size
    var m1Index: Int
    var m2Index: Int
    if (totalElements % 2 == 0) {
        m1Index = totalElements/2 - 1
        m2Index = m1Index + 1
    } else {
        m1Index = totalElements/2
        m2Index = m1Index
    }

    // Pointers to array
    var i = 0
    var j = 0

    // Identified medians
    var m1 = 0
    var m2 = 0

    for (k in 0..m2Index) {
        val picked : Int = 
        if (i < nums1.size && j < nums2.size) {
            if (nums1[i] < nums2[j]) {
                i++
                nums1[i-1]
            } else {
                j++
                nums2[j-1]
            }
        } else if (i < nums1.size) {
            i++
            nums1[i-1]
        } else if (j < nums2.size) {
            j++
            nums2[j-1]
        } else {
            0
        }

        if (k == m1Index) {
            m1 = picked
        }
        if (k == m2Index) {
            m2 = picked
        }

        // println("$n1 <> $n2 : ${Math.min(n1, n2)} /// [i: $i, j: $j]")
    }
    
    // println("""
    
    // median:::
    // nums1 = ${nums1.joinToString(",")}
    // nums2 = ${nums2.joinToString(",")}
    // ${if (m1Index == m2Index) m1.toDouble() else (m1+m2)/2.0}""")

    return if (m1Index == m2Index) m1.toDouble() else (m1+m2)/2.0
}

fun main(args: Array<String>) {

    val testSet = listOf(
        Pair(intArrayOf(1,2), intArrayOf(3,4)),
        Pair(intArrayOf(1,3), intArrayOf(2)),
        Pair(intArrayOf(0,0), intArrayOf(0,0)),
        Pair(intArrayOf(), intArrayOf(1)),
        Pair(intArrayOf(2), intArrayOf()),
        Pair(intArrayOf(), intArrayOf()),
    )

    for (pair in testSet) {
        findMedianSortedArrays(pair.first, pair.second)
    }
}