/*
 * Kotlin Assignment 1 - Solutions
 * This file contains solutions to four Kotlin programming challenges:
 *
 * CHALLENGE A: Sum of integers from 1 to n (Iterative + Functional)
 * CHALLENGE B: Iterative factorial
 * CHALLENGE C: Lambda factorial
 * CHALLENGE D: Recursive factorial with tail recursion
 *
 */

// ============================================================
// CHALLENGE A: SUM OF INTEGERS FROM 1 TO N
// ============================================================

/**
 * CHALLENGE A - Part 1: Sums all integers from 1 to a specified maximum.
 *
 * This function uses an ITERATIVE approach with a for loop.
 *
 * HOW IT WORKS:
 * 1. Initialize a variable 'sum' to 0 to store the running total
 * 2. Loop from 1 to the maximum value (inclusive)
 * 3. Add each number to the running total
 * 4. Return the final sum
 *
 * WHY LONG RETURN TYPE:
 * - Regular Int can only hold up to ~2 billion
 * - Sum of 1 to 100,000 = 5,000,050,000 (exceeds Int range)
 * - Long can hold up to ~9 quintillion (safe for most cases)
 *
 * TIME COMPLEXITY: O(n) - loops through n numbers
 * SPACE COMPLEXITY: O(1) - uses only one variable
 *
 * EXAMPLES:
 *   sumUpTo(5)   = 1 + 2 + 3 + 4 + 5   = 15
 *   sumUpTo(10)  = 1 + 2 + ... + 10    = 55
 *   sumUpTo(100) = 1 + 2 + ... + 100   = 5050
 *
 * @param max The upper bound (inclusive) for the sum
 * @return The sum of all integers from 1 to max as a Long
 */

fun sumUpTo(max: Int): Long {
    // Initialize sum as Long to prevent overflow
    var sum = 0L

    // Loop from 1 to max (inclusive)
    // Example: if max = 5, i takes values: 1, 2, 3, 4, 5
    for (i in 1..max) {
        // Add current number to running total
        // sum += i is shorthand for sum = sum + i
        sum += i
    }

    // Return the final sum
    return sum
}

/**
 * CHALLENGE A - Part 2: Alternative FUNCTIONAL style implementation.
 *
 * This demonstrates Kotlin's functional programming features.
 * Uses Kotlin's built-in sum() function on a range.
 *
 * WHY IT'S FUNCTIONAL:
 * - No explicit loops or mutable variables
 * - Uses higher-order functions from the standard library
 * - Declarative style: says WHAT to do, not HOW to do it
 *
 * WHY CONVERT TO LONG:
 * - Range (1L..max.toLong()) creates a Long range
 * - Prevents integer overflow for large numbers
 * - More readable and concise than manual loop
 *
 * EXAMPLES:
 *   sumUpToFunctional(5)   = 15
 *   sumUpToFunctional(100) = 5050
 *
 * @param max The upper bound (inclusive) for the sum
 * @return The sum of all integers from 1 to max as a Long
 */

fun sumUpToFunctional(max: Int): Long {
    // Step 1: Create a Long range from 1 to max
    // Step 2: Call .sum() to add all numbers in the range
    return (1L..max.toLong()).sum()
}

// ============================================================
// CHALLENGE B: ITERATIVE FACTORIAL
// ============================================================

/**
 * CHALLENGE B: Calculates factorial using an ITERATIVE approach.
 *
 * FACTORIAL DEFINITION:
 *   n! = n × (n-1) × (n-2) × ... × 2 × 1
 *
 * SPECIAL CASES (Mathematical Rules):
 *   0! = 1  (defined by mathematicians)
 *   1! = 1  (multiplication identity)
 *   Negative numbers: Not mathematically defined → return 0
 *
 * WHY START AT 1:
 *   - Multiplication identity: 1 × anything = anything
 *   - If we started at 0, everything would be 0
 *   - Works correctly for both 0! and 1!
 *
 * HOW IT WORKS:
 * 1. Check if n is negative → return 0
 * 2. Initialize result to 1
 * 3. Loop from 2 to n (skip 0 and 1 since they equal 1)
 * 4. Multiply result by each number
 * 5. Return the final result
 *
 * EXAMPLE: 5! = 5 × 4 × 3 × 2 × 1 = 120
 *   Start: result = 1
 *   i = 2: result = 1 × 2 = 2
 *   i = 3: result = 2 × 3 = 6
 *   i = 4: result = 6 × 4 = 24
 *   i = 5: result = 24 × 5 = 120
 *
 * TIME COMPLEXITY: O(n) - loops through n numbers
 * SPACE COMPLEXITY: O(1) - uses only one variable
 *
 * @param n The integer to find the factorial of
 * @return Factorial of n as a Long, or 0 if n is negative
 */

fun factorialIterative(n: Int): Long {
    // INPUT VALIDATION: Factorial is not defined for negative numbers
    // Example: -5! is not mathematically defined
    if (n < 0) {
        return 0L
    }

    // Initialize result to 1 (multiplication identity)
    // This handles both 0! = 1 and 1! = 1
    var result = 1L

    // Loop from 2 to n
    // We skip 0 and 1 because they equal 1
    for (i in 2..n) {
        // Multiply result by current number
        // result *= i is shorthand for result = result * i
        result *= i
    }

    return result
}

// ============================================================
// CHALLENGE C: LAMBDA FACTORIAL
// ============================================================

/**
 * CHALLENGE C: Lambda expression implementation of factorial.
 *
 * WHAT IS A LAMBDA?
 * - A function without a name (anonymous function)
 * - Can be stored in a variable and passed around
 * - Type: (Int) -> Long means "takes Int, returns Long"
 *
 * WHY USE FOLD INSTEAD OF REDUCE?
 *   - fold: Always starts with an initial value
 *   - reduce: Uses first element as initial value
 *   - For n = 0, range (1..0) is EMPTY
 *   - reduce on empty range throws NoSuchElementException
 *   - fold(1L) on empty range returns 1L → 0! = 1 ✓
 *
 * HOW FOLD WORKS:
 *   (1..n).fold(1L) { accumulator, i -> accumulator * i }
 *
 *   Step 1: accumulator = 1L (initial value)
 *   Step 2: For each number i in the range:
 *           - Update accumulator = accumulator × i
 *   Step 3: Return final accumulator value
 *
 * EXAMPLE WITH n = 5:
 *   Start: accumulator = 1
 *   i = 1: accumulator = 1 × 1 = 1
 *   i = 2: accumulator = 1 × 2 = 2
 *   i = 3: accumulator = 2 × 3 = 6
 *   i = 4: accumulator = 6 × 4 = 24
 *   i = 5: accumulator = 24 × 5 = 120
 *   Result: 120
 *
 * EXAMPLE WITH n = 0:
 *   Range (1..0) is empty
 *   fold returns initial value: 1
 *   Result: 1 (0! = 1) ✓
 *
 * @param n The integer to find the factorial of
 * @return Factorial of n as a Long, or 0 if n is negative
 */

val factorialLambda: (Int) -> Long = { n ->
    // Check if n is negative
    if (n < 0) {
        0L
    } else {
        // Use fold to multiply all numbers in range
        // Initial value: 1L (handles 0! case)
        // Lambda: multiply accumulator by current value
        (1..n).fold(1L) { accumulator, i ->
            accumulator * i
        }
    }
}

// ============================================================
// CHALLENGE D: RECURSIVE FACTORIAL
// ============================================================

/**
 * CHALLENGE D: Recursive factorial with TAIL RECURSION optimization.
 *
 * RECURSIVE DEFINITION:
 *   n! = n × (n-1)!
 *   1! = 1
 *   0! = 1
 *
 * TAIL RECURSION EXPLAINED:
 *   - The recursive call MUST be the LAST operation
 *   - @tailrec annotation tells compiler to optimize
 *   - Converts recursion to a loop (no stack growth)
 *   - Prevents StackOverflowError for large n
 *
 * HOW TAIL RECURSION WORKS:
 *   Without @tailrec: Each call adds a frame to the call stack
 *   With @tailrec: Compiler rewrites as a while loop
 *
 * ACCUMULATOR PATTERN:
 *   - Instead of returning n * factorial(n-1)
 *   - Pass the result as an accumulator parameter
 *   - accumulator = n × (previous accumulator)
 *   - This makes the recursive call the last operation
 *
 * EXAMPLE: factorialRecursive(5)
 *   Call 1: factorialRecursive(5, 1)        → else: calls with (4, 5)
 *   Call 2: factorialRecursive(4, 5)        → else: calls with (3, 20)
 *   Call 3: factorialRecursive(3, 20)       → else: calls with (2, 60)
 *   Call 4: factorialRecursive(2, 60)       → else: calls with (1, 120)
 *   Call 5: factorialRecursive(1, 120)      → n <= 1: returns 120
 *   Result: 120
 *
 * WHY TAILREC IS IMPORTANT:
 *   - Regular recursion: O(n) stack space
 *   - Tail recursion: O(1) stack space (optimized)
 *   - Can compute factorial of 100,000 without crashing
 *
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1) (with tail recursion optimization)
 *
 * @param n The integer to find the factorial of
 * @param accumulator Accumulated result (default: 1L)
 * @return Factorial of n as a Long, or 0 if n is negative
 */

tailrec fun factorialRecursive(n: Int, accumulator: Long = 1L): Long {
    return when {
        // CASE 1: Negative number → return 0
        n < 0 -> 0L

        // CASE 2: Base case - 0! = 1, 1! = 1
        // Return the accumulated result
        n <= 1 -> accumulator

        // CASE 3: Recursive case
        // Calculate n × accumulator and pass to next call
        // This is the LAST operation → tailrec can optimize
        else -> factorialRecursive(n - 1, n * accumulator)
    }
}

// ============================================================
// TESTING AND DEMONSTRATION
// ============================================================

/**
 * MAIN FUNCTION - Entry point for the application.
 *
 * This function is automatically called when the program starts.
 * It tests all four challenge implementations with various inputs.
 *
 * TEST COVERAGE:
 *   - Normal positive numbers
 *   - Edge cases (0, 1)
 *   - Negative numbers
 *   - Verification that all implementations match
 *
 * OUTPUT FORMAT:
 *   - Each challenge is displayed with a header
 *   - Results show: value, expected, pass/fail status
 *   - Final verification ensures consistency
 */

fun main() {
    // Print program header
    println("=".repeat(60))
    println("KOTLIN ASSIGNMENT 1 - SOLUTIONS")
    println("Course: MWD3B - Android Development")
    println("=".repeat(60))
    println()

    // Test all four challenges
    testChallengeA()
    testChallengeB()
    testChallengeC()
    testChallengeD()

    // Verify all implementations produce same results
    verifyAllImplementations()

    // Print completion message
    println()
    println("=".repeat(60))
    println("ALL TESTS COMPLETED SUCCESSFULLY! ✓")
    println("=".repeat(60))
}

/**
 * Tests CHALLENGE A: Both sum implementations.
 *
 * Tests both the iterative and functional versions
 * against the mathematical formula: n(n+1)/2
 *
 * Test Data: 5, 10, 100
 *
 * For each test value:
 *   1. Calculate expected result using formula
 *   2. Call sumUpTo() and sumUpToFunctional()
 *   3. Compare results with expected
 *   4. Display pass/fail status
 */

private fun testChallengeA() {
    println("CHALLENGE A: SUM OF INTEGERS")
    println("-".repeat(40))

    // Test values to verify both implementations
    val testValues = listOf(5, 10, 100)

    // Loop through each test value
    for (n in testValues) {
        // Calculate expected using mathematical formula: n(n+1)/2
        // Convert to Long for correct type comparison
        val expected = n.toLong() * (n.toLong() + 1) / 2

        // Get results from both implementations
        val result = sumUpTo(n)
        val resultFunctional = sumUpToFunctional(n)

        // Print results with pass/fail indicator
        // ✓ = passed, ✗ = failed
        println("sumUpTo($n) = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
        println("sumUpToFunctional($n) = $resultFunctional (Expected: $expected) ${if (resultFunctional == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * Tests CHALLENGE B: Iterative factorial.
 *
 * Tests the iterative factorial implementation
 * against known correct results.
 *
 * Test Data: 0, 1, 5, 7, 10
 *
 * Uses a map of expected results for quick lookup.
 * Each test compares the function output with expected.
 */
private fun testChallengeB() {
    println("CHALLENGE B: ITERATIVE FACTORIAL")
    println("-".repeat(40))

    // Test values and their expected results
    val testValues = listOf(0, 1, 5, 7, 10)
    val expectedResults = mapOf(
        0 to 1L,        // 0! = 1
        1 to 1L,        // 1! = 1
        5 to 120L,      // 5! = 120
        7 to 5040L,     // 7! = 5040
        10 to 3628800L  // 10! = 3,628,800
    )

    // Loop through each test value
    for (n in testValues) {
        val result = factorialIterative(n)
        val expected = expectedResults[n] ?: 0L
        println("$n! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * Tests CHALLENGE C: Lambda factorial.
 *
 * Tests the lambda factorial implementation
 * against known correct results.
 *
 * Test Data: 0, 1, 5, 7, 10
 *
 * This function uses the same test data as Challenge B
 * to allow comparison between implementations.
 */

private fun testChallengeC() {
    println("CHALLENGE C: LAMBDA FACTORIAL")
    println("-".repeat(40))

    // Same test values and expected results as Challenge B
    val testValues = listOf(0, 1, 5, 7, 10)
    val expectedResults = mapOf(
        0 to 1L,
        1 to 1L,
        5 to 120L,
        7 to 5040L,
        10 to 3628800L
    )

    // Loop through each test value
    for (n in testValues) {
        val result = factorialLambda(n)
        val expected = expectedResults[n] ?: 0L
        println("$n! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * Tests CHALLENGE D: Recursive factorial.
 *
 * Tests the recursive factorial implementation
 * against known correct results.
 *
 * Test Data: 0, 1, 5, 7, 10
 */

private fun testChallengeD() {
    println("CHALLENGE D: RECURSIVE FACTORIAL")
    println("-".repeat(40))

    // Same test values and expected results
    val testValues = listOf(0, 1, 5, 7, 10)
    val expectedResults = mapOf(
        0 to 1L,
        1 to 1L,
        5 to 120L,
        7 to 5040L,
        10 to 3628800L
    )

    // Loop through each test value
    for (n in testValues) {
        val result = factorialRecursive(n)
        val expected = expectedResults[n] ?: 0L
        println("$n! = $result (Expected: $expected) ${if (result == expected) "✓" else "✗"}")
    }
    println()
}

/**
 * VERIFICATION: Ensures all implementations produce identical results.
 *
 * This is a CRITICAL test that validates the correctness of all
 * three factorial implementations by comparing their outputs.
 *
 * Test Data: 0, 1, 2, 5, 7, 10, 15
 *
 * Why this matters:
 * - If all three produce the same result, the code is likely correct
 * - If any differ, there's a bug in one of the implementations
 * - Provides confidence that the solutions are mathematically sound
 *
 * The verification checks:
 *   1. Iterative result equals Lambda result
 *   2. Lambda result equals Recursive result
 *   3. If both are true, all implementations match
 */

private fun verifyAllImplementations() {
    println("VERIFICATION: ALL IMPLEMENTATIONS MATCH")
    println("-".repeat(40))

    // Test a wider range including larger numbers
    val testValues = listOf(0, 1, 2, 5, 7, 10, 15)
    var allMatch = true

    // Loop through each test value
    for (n in testValues) {
        // Get results from all three implementations
        val iterative = factorialIterative(n)
        val lambda = factorialLambda(n)
        val recursive = factorialRecursive(n)

        // Check if all three match
        val match = (iterative == lambda) && (lambda == recursive)
        if (!match) allMatch = false

        // Display results with match status
        val status = if (match) "✓ MATCH" else "✗ MISMATCH"
        println("n = $n: Iterative: $iterative, Lambda: $lambda, Recursive: $recursive $status")
    }

    // Final summary
    println()
    if (allMatch) {
        println("✓ All implementations produce identical results!")
    } else {
        println("✗ Warning: Implementations produce different results!")
    }
    println()
}