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
        var perG = 0
        var perR = 0
        var perM = 0
        var perT = 0
        var perS = 0
        var pGroceries = 0
        var pSavings = 0
        var pMisc = 0


        fun setTotal() {
            totalUsed = rent + groceries + misc
        }

        fun setSavings() {
            savings = income - totalUsed
        }

        fun calcPercentage() {
            perG = ((groceries.toDouble()/income) * 100).roundToInt()
            perR = ((rent.toDouble()/income) * 100).roundToInt()
            perM = ((misc.toDouble()/income) * 100).roundToInt()
            perT = ((totalUsed.toDouble()/income) * 100).roundToInt()
            perS = ((savings.toDouble()/income) * 100).roundToInt()

            print("The breakdown of your budget for the past month is: \n")
            print("Groceries = %${perG}\n")
            print("Rent = %${perR}\n")
            print("Miscellaneous = %${perM}\n")
            print("Total amount spent = $${totalUsed}\n")
            print("All together you are spending %${perT} of your income.\n")
            print("Which means you are saving $${savings} or %${perS} percent of your budget.\n")
        }

        fun calcDollars() {
            var leftOver = income - rent
            perG.toDouble()
            perM.toDouble()

            pGroceries = (leftOver * perG)/100
            pMisc = (leftOver * perM)/100
            pSavings = leftOver - pGroceries - pMisc

            print("Your budget for groceries is $${pGroceries}\n")
            print("Your budget for other expenses is $${pMisc}\n")
            print("Your budget for savings is $${pSavings}\n")
        }
    }
}

fun checkYes(answer: String?) {
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

fun getPercentages(person1: Person) {
    print("What is your income for next month?\n$")
    person1.pBudget.income = Integer.valueOf(readLine())

    print("What is your rent for next month?\n$")
    person1.pBudget.rent = Integer.valueOf(readLine())

    print("What percentage of your income do you want to go to groceries?\n%")
    person1.pBudget.perG = Integer.valueOf(readLine())

    print("What percentage of your income do you want to go to other expenses?\n%")
    person1.pBudget.perM = Integer.valueOf(readLine())

    person1.pBudget.calcDollars()
}

fun getDollars(person1: Person) {
        print("What is your income for this month? (in dollars)\n$")
        person1.cBudget.income = Integer.valueOf(readLine())

        print("What is your budget for groceries? \n$")
        person1.cBudget.groceries = Integer.valueOf(readLine())

        print("What is your rent/living expenses? \n$")
        person1.cBudget.rent = Integer.valueOf(readLine())

        print("What is the total of your other expenses?\n$")
        person1.cBudget.misc = Integer.valueOf(readLine())

        person1.cBudget.setTotal()
        person1.cBudget.setSavings()
        person1.cBudget.calcPercentage()
}
fun main() {
    var person1 = Person()

    print("Hi! What's your name? \n")
    person1.name = readLine()

    print("Do you need help with your budget, ${person1.name}? \n")
    var answer = readLine()
    checkYes(answer)
    getDollars(person1)

    while (person1.cBudget.totalUsed > person1.cBudget.income) {
        print("Whoops! Looks like your using more than your income! Please enter the values in again.\n")
        getDollars(person1)
    }

    print("Shall we plan for next month?\n")
    var answer2 = readLine()
    checkYes(answer2)

    print("Do you want to plan based on percentages or dollars?\n")
    var response: String? = readLine()

    if (response == "dollars")
        getDollars(person1)

    else if (response == "d")
        getDollars(person1)

    else
        getPercentages(person1)

    /* add when statement for checkAnswer and other responses */
}

main()