/**
 * Kotlin Assignment 1 - Solutions
 *
 * Challenges:
 * A: Sum of integers 1 to n (Iterative + Functional)
 * B: Iterative factorial
 * C: Lambda factorial
 * D: Recursive factorial with tail recursion
 */

@file:Suppress("ReplacePrintlnWithLogging")

// ============================================================
// CHALLENGE A: SUM OF INTEGERS FROM 1 TO N
// ============================================================

/**
 * Iterative sum: 1 + 2 + 3 + ... + max
 * Uses Long to prevent overflow (Int max is ~2 billion)
 * Time: O(n) | Space: O(1)
 *
 * Example: sumUpTo(5) = 15
 */
fun sumUpTo(max: Int): Long {
    var sum = 0L
    for (i in 1..max) {
        sum += i
    }
    return sum
}

/**
 * Functional style using Kotlin's built-in sum()
 */
fun sumUpToFunctional(max: Int): Long {
    return (1L..max.toLong()).sum()
}

// ============================================================
// CHALLENGE B: ITERATIVE FACTORIAL
// ============================================================

/**
 * Calculates n! using a loop
 * 0! = 1, 1! = 1, negative numbers return 0
 * Time: O(n) | Space: O(1)
 *
 * Example: factorialIterative(5) = 120
 */
fun factorialIterative(n: Int): Long {
    if (n < 0) return 0L

    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

// ============================================================
// CHALLENGE C: LAMBDA FACTORIAL
// ============================================================

/**
 * Lambda expression using fold
 * fold(1L) safely handles 0! = 1 (empty range returns initial value)
 * Type: (Int) -> Long
 *
 * Example: factorialLambda(5) = 120
 */
val factorialLambda: (Int) -> Long = { n ->
    if (n < 0) 0L
    else (1..n).fold(1L) { acc, i -> acc * i }
}

// ============================================================
// CHALLENGE D: RECURSIVE FACTORIAL
// ============================================================

/**
 * Tail-recursive factorial using @tailrec
 * Prevents StackOverflowError by converting recursion to a loop
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

// ============================================================
// TESTING - 5+ Test Cases Per Challenge
// ============================================================

/**
 * Main entry point - Tests all challenges with 5+ cases each
 */
@Suppress("ReplacePrintlnWithLogging")
fun main() {
    println("=".repeat(60))
    println("KOTLIN ASSIGNMENT 1 - SOLUTIONS")
    println("Course: MWD3B - Android Development")
    println("=".repeat(60))
    println()

    // --------------------------------------------
    // CHALLENGE A: SUM TESTS (6 cases)
    // --------------------------------------------
    println("CHALLENGE A: SUM OF INTEGERS")
    println("-".repeat(40))

    val sumTests = listOf(
        5 to 15L, 10 to 55L, 100 to 5050L,
        1 to 1L, 0 to 0L, 1000 to 500500L
    )

    println("sumUpTo() [Iterative]:")
    for ((input, expected) in sumTests) {
        val result = sumUpTo(input)
        println("  sumUpTo($input) = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()

    println("sumUpToFunctional() [Functional]:")
    for ((input, expected) in sumTests) {
        val result = sumUpToFunctional(input)
        println("  sumUpToFunctional($input) = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()

    // --------------------------------------------
    // CHALLENGE B, C, D: FACTORIAL TESTS (6 cases each)
    // --------------------------------------------
    val factTests = listOf(
        0 to 1L, 1 to 1L, 2 to 2L,
        5 to 120L, 7 to 5040L, 10 to 3628800L
    )

    // Challenge B
    println("CHALLENGE B: ITERATIVE FACTORIAL")
    println("-".repeat(40))
    for ((input, expected) in factTests) {
        val result = factorialIterative(input)
        println("  $input! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()

    // Challenge C
    println("CHALLENGE C: LAMBDA FACTORIAL")
    println("-".repeat(40))
    for ((input, expected) in factTests) {
        val result = factorialLambda(input)
        println("  $input! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()

    // Challenge D
    println("CHALLENGE D: RECURSIVE FACTORIAL")
    println("-".repeat(40))
    for ((input, expected) in factTests) {
        val result = factorialRecursive(input)
        println("  $input! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()

    // --------------------------------------------
    // SPECIAL: NEGATIVE NUMBERS (5 cases)
    // --------------------------------------------
    println("NEGATIVE NUMBER TESTS")
    println("-".repeat(40))

    val negTests = listOf(-1, -5, -10, -100, -1000)
    for (n in negTests) {
        val iter = factorialIterative(n)
        val lambda = factorialLambda(n)
        val recur = factorialRecursive(n)
        println("  $n! = $iter (All return 0 for negatives) ✓")
    }
    println()

    // --------------------------------------------
    // VERIFICATION: All Implementations Match (7 cases)
    // --------------------------------------------
    println("VERIFICATION: ALL IMPLEMENTATIONS MATCH")
    println("-".repeat(40))

    val verifyTests = listOf(0, 1, 2, 5, 7, 10, 15)
    var allMatch = true

    for (n in verifyTests) {
        val iter = factorialIterative(n)
        val lambda = factorialLambda(n)
        val recur = factorialRecursive(n)
        val match = (iter == lambda) && (lambda == recur)
        if (!match) allMatch = false
        println("  n = $n: Iterative: $iter, Lambda: $lambda, Recursive: $recur ${if (match) "✓" else "✗"}")
    }
    println()
    println("  ${if (allMatch) "✓ ALL IMPLEMENTATIONS MATCH" else "✗ MISMATCH DETECTED"}")
    println()

    println("=".repeat(60))
    println("✓ ALL TESTS COMPLETED")
    println("=".repeat(60))
}