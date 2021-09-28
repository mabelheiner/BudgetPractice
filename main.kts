import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.system.exitProcess

class Person() {
    var name: String? = ""
    var cBudget = Budget()
    var pBudget = Budget()

    class Budget() {

        var totalUsed = 0
        var groceries = 0
        var rent = 0
        var misc = 0
        var savings = 0
        var income = 0

        fun setTotal() {
            totalUsed = rent + groceries + misc
        }

        fun setSavings() {
            savings = income - totalUsed
        }

        fun calcPercentage() {
            var perG = ((groceries.toDouble()/income) * 100).roundToInt()
            var perR = ((rent.toDouble()/income) * 100).roundToInt()
            var perM = ((misc.toDouble()/income) * 100).roundToInt()
            var perT = ((totalUsed.toDouble()/income) * 100).roundToInt()

            print("The breakdown of your budget for the past month is \n")
            print("Groceries = %${perG}\n")
            print("Rent = %${perR}\n")
            print("Miscellaneous = %${perM}\n")
            print("Total amount spent = $${totalUsed}\n")
            print("All together you are spending %${perT} of your income")
        }
    }
}

fun checkAnswer(answer: String?) {
    var check1: String = "yes"
    var check2: String = "y"
    if (answer == check1) {
        print("Sweet! Let's go!\n")
    }

    else if (answer == check2) {
        print("Sweet! Let's go!\n")
    }

    else {
        print("Looks like your done here then")
        exitProcess(1)
    }
}

fun getValues(person1: Person) {
        print("What is your income for this month?")
        person1.cBudget.income = Integer.valueOf(readLine())

        print("What is your budget for groceries? \n")
        person1.cBudget.groceries = Integer.valueOf(readLine())

        print("What is your rent/living expenses? \n")
        person1.cBudget.rent = Integer.valueOf(readLine())

        print("What is the total of your other expenses?\n")
        person1.cBudget.misc = Integer.valueOf(readLine())

        person1.cBudget.setTotal()
}
fun main() {
    var person1 = Person()

    print("Hi! What's your name? \n")
    person1.name = readLine()
    print("Nice to meet you ${person1.name} \n")

    print("Do you need help with your budget, ${person1.name}? \n")
    var answer = readLine()
    checkAnswer(answer)
    getValues(person1)
    while (person1.cBudget.totalUsed > person1.cBudget.income) {
        print("Whoops! Looks like your using more than your income! Please enter the values in again.\n")
        getValues(person1)
    }

    print("Shall we plan for next month?\n")
    var answer2 = readLine()
    checkAnswer(answer2)

    print("Awesome!\n")
    person1.cBudget.calcPercentage()



}

main()