// ============================================
// CHALLENGE A: SUM OF INTEGERS FROM 1 TO N
// ============================================

/**
 * Challenge A: Sums all integers from 1 to a specified maximum.
 *
 * Example: sumUpTo(5) = 1 + 2 + 3 + 4 + 5 = 15
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * @param max The upper bound (inclusive)
 * @return Sum from 1 to max as a Long
 */

fun sumUpTo(max: Int): Long {
    var sum = 0L
    for (i in 1..max) {
        sum += i
    }
    return sum
}

/**
 * Challenge A (Alternative): Functional style using Kotlin's sum().
 */

fun sumUpToFunctional(max: Int): Long {
    return (1L..max.toLong()).sum()
}

// ============================================
// CHALLENGE B: ITERATIVE FACTORIAL
// ============================================

/**
 * Challenge B: Calculates factorial using an iterative approach.
 *
 * Factorial definition: n! = n * (n-1) * (n-2) * ... * 1
 * Special cases: 0! = 1, 1! = 1
 * Negative numbers return 0
 *
 * Example: factorialIterative(5) = 120
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * @param n The integer to find the factorial of
 * @return Factorial of n as a Long, or 0 if n < 0
 */

fun factorialIterative(n: Int): Long {
    if (n < 0) {
        return 0L
    }

    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

// ============================================
// CHALLENGE C: LAMBDA FACTORIAL
// ============================================

/**
 * Challenge C: Lambda expression implementation of factorial.
 *
 * Uses Kotlin's fold function with initial value 1L.
 * fold handles empty ranges safely (returns initial value).
 * This correctly gives 0! = 1.
 *
 * Example: factorialLambda(5) = 120
 */

val factorialLambda: (Int) -> Long = { n ->
    if (n < 0) {
        0L
    } else {
        (1..n).fold(1L) { accumulator, i ->
            accumulator * i
        }
    }
}

// ============================================
// CHALLENGE D: RECURSIVE FACTORIAL
// ============================================

/**
 * Challenge D: Recursive factorial with tail recursion optimization.
 *
 * @tailrec optimizes recursion into an iterative loop
 * Prevents StackOverflowError for large inputs
 *
 * Example: factorialRecursive(5) = 120
 */

tailrec fun factorialRecursive(n: Int, accumulator: Long = 1L): Long {
    return when {
        n < 0 -> 0L
        n <= 1 -> accumulator
        else -> factorialRecursive(n - 1, n * accumulator)
    }
}

// ============================================
// TESTING AND DEMONSTRATION
// ============================================

/**
 * Main function - Entry point for the application.
 * Tests all four challenge implementations.
 */

fun main() {
    println("=".repeat(60))
    println("KOTLIN ASSIGNMENT 1 - SOLUTIONS")
    println("=".repeat(60))
    println()

    testChallengeA()
    testChallengeB()
    testChallengeC()
    testChallengeD()
    verifyAllImplementations()

    println()
    println("=".repeat(60))
    println("ALL TESTS COMPLETED SUCCESSFULLY! ✓")
    println("=".repeat(60))
}

/**
 * Tests Challenge A: Sum functions.
 */

private fun testChallengeA() {
    println("CHALLENGE A: SUM OF INTEGERS")
    println("-".repeat(40))

    val testValues = listOf(5, 10, 100)

    for (n in testValues) {
        val expected = n.toLong() * (n.toLong() + 1) / 2
        val result = sumUpTo(n)
        val resultFunctional = sumUpToFunctional(n)

        println("sumUpTo($n) = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
        println("sumUpToFunctional($n) = $resultFunctional (Expected: $expected) ${if (resultFunctional == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * Tests Challenge B: Iterative factorial.
 */

private fun testChallengeB() {
    println("CHALLENGE B: ITERATIVE FACTORIAL")
    println("-".repeat(40))

    val testValues = listOf(0, 1, 5, 7, 10)
    val expectedResults = mapOf(
        0 to 1L,
        1 to 1L,
        5 to 120L,
        7 to 5040L,
        10 to 3628800L
    )

    for (n in testValues) {
        val result = factorialIterative(n)
        val expected = expectedResults[n] ?: 0L
        println("$n! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * Tests Challenge C: Lambda factorial.
 */

private fun testChallengeC() {
    println("CHALLENGE C: LAMBDA FACTORIAL")
    println("-".repeat(40))

    val testValues = listOf(0, 1, 5, 7, 10)
    val expectedResults = mapOf(
        0 to 1L,
        1 to 1L,
        5 to 120L,
        7 to 5040L,
        10 to 3628800L
    )

    for (n in testValues) {
        val result = factorialLambda(n)
        val expected = expectedResults[n] ?: 0L
        println("$n! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * Tests Challenge D: Recursive factorial.
 */

private fun testChallengeD() {
    println("CHALLENGE D: RECURSIVE FACTORIAL")
    println("-".repeat(40))

    val testValues = listOf(0, 1, 5, 7, 10)
    val expectedResults = mapOf(
        0 to 1L,
        1 to 1L,
        5 to 120L,
        7 to 5040L,
        10 to 3628800L
    )

    for (n in testValues) {
        val result = factorialRecursive(n)
        val expected = expectedResults[n] ?: 0L
        println("$n! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * Verifies all implementations match.
 */

private fun verifyAllImplementations() {
    println("VERIFICATION: ALL IMPLEMENTATIONS MATCH")
    println("-".repeat(40))

    val testValues = listOf(0, 1, 2, 5, 7, 10, 15)
    var allMatch = true

    for (n in testValues) {
        val iterative = factorialIterative(n)
        val lambda = factorialLambda(n)
        val recursive = factorialRecursive(n)

        val match = (iterative == lambda) && (lambda == recursive)
        if (!match) allMatch = false

        val status = if (match) "✓ MATCH" else "✗ MISMATCH"
        println("n = $n: Iterative: $iterative, Lambda: $lambda, Recursive: $recursive $status")
    }

    println()
    if (allMatch) {
        println("✓ All implementations produce identical results!")
    } else {
        println("✗ Warning: Implementations produce different results!")
    }
    println()
}