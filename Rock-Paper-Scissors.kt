fun main() {
    var playerChoice: String? = null
    while (playerChoice == null) {
        println("Rock [R] Paper [P] Scissors [S] Enter your choice:")
        val input = readln().uppercase()

        if (input in listOf("R", "P", "S")) {
            playerChoice = input
        } else {
            println("Invalid choice! Please enter R, P, or S.")
        }
    }

    val computerChoice = when ((1..3).random()) {
        1 -> "R"
        2 -> "P"
        else -> "S"
    }

    println("Computer choice: $computerChoice")

    val winner = when {
        playerChoice == computerChoice -> "It's a tie!"
        (playerChoice == "R" && computerChoice == "S") ||
                (playerChoice == "P" && computerChoice == "R") ||
                (playerChoice == "S" && computerChoice == "P") -> "Player wins!"
        else -> "Computer wins!"
    }
    println(winner)
}