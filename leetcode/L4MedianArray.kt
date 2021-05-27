package leet.code

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    
    // Find median positions, max 2 elements
    val totalElements = nums1.size + nums2.size
    var m1Index = 0
    var m2Index = 0
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
        val n1 = nums1.getOrNull(i) ?: Int.MAX_VALUE
        val n2 = nums2.getOrNull(j) ?: Int.MAX_VALUE
        if (k == m1Index) {
            m1 = Math.min(n1, n2)
        }
        if (k == m2Index) {
            m2 = Math.min(n1, n2)
        }

        // Move one of the pointers
        if (n1 < n2) {
            i++
        } else {
            j++
        }

        // println("$n1 <> $n2 : ${Math.min(n1, n2)} /// [i: $i, j: $j]")
    }
    
    val result = (m1 + m2) / 2.0
    println("\n\nMedian\nn1: ${nums1.joinToString(",")} \nn2: ${nums2.joinToString(",")} \n:: $result")
    return result
}

fun main(args: Array<String>) {

    val testSet = listOf(
        Pair(
            intArrayOf(1,2), 
            intArrayOf(3,4)),

            Pair(
            intArrayOf(1,3), 
            intArrayOf(2)),

            Pair(
            intArrayOf(0,0), 
            intArrayOf(0,0)),

            Pair(
                intArrayOf(), 
                intArrayOf(1)),

                Pair(
                    intArrayOf(2), 
                    intArrayOf()),
    )

    for (pair in testSet) {
        findMedianSortedArrays(pair.first, pair.second)
    }
}