import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.system.exitProcess

class Person() {
    var name: String? = ""
    var item_price = 0
    var item_name: String? = ""
    var cBudget = Budget()
    var pBudget = Budget()

    class Budget() {

        var totalUsed = 0
        var groceries = 0
        var rent = 0
        var misc = 0
        var savings = 0
        var income = 0
        var travel = 0
        var perT = 0
        var perG = 0
        var perR = 0
        var perM = 0
        var perU = 0
        var perS = 0
        var pGroceries = 0
        var pSavings = 0
        var pMisc = 0
        var pTravel = 0
        var leftOver = 0
        var funMoney = 0


        fun setTotal() {
            totalUsed = rent + groceries + misc + travel
        }


        fun calcPercentage() {
            savings = income - totalUsed

            perG = ((groceries.toDouble()/income) * 100).roundToInt()
            perR = ((rent.toDouble()/income) * 100).roundToInt()
            perM = ((misc.toDouble()/income) * 100).roundToInt()
            perU = ((totalUsed.toDouble()/income) * 100).roundToInt()
            perS = ((savings.toDouble()/income) * 100).roundToInt()
            perT = ((travel.toDouble()/income) * 100).roundToInt()

            print("The breakdown of your budget for the past month is: \n")
            print("Groceries = %${perG}\n")
            print("Rent = %${perR}\n")
            print("Miscellaneous = %${perM}\n")
            print("Travel = %${perT}\n")
            print("Total amount spent = $${totalUsed}\n")
            print("All together you are spending %${perU} of your income.\n")
            print("Which means you are saving $${savings} or %${perS} percent of your budget.\n")
        }

        fun calcDollars() {
            leftOver = income - rent - savings - travel - groceries
            perG.toDouble()
            perM.toDouble()
            perS.toDouble()
            perT.toDouble()

            pGroceries = (leftOver * perG)/100
            pMisc = (leftOver * perM)/100
            pSavings = (leftOver * perS)/100
            pTravel = (leftOver * perT)/100

            print("Your budget for groceries is $${pGroceries}\n")
            print("Your budget for other expenses is $${pMisc}\n")
            print("Your budget for savings is $${pSavings}\n")
            print("Your budget for travel is $${pTravel}\n")

            funMoney = leftOver - pGroceries - pMisc - pSavings - pTravel

            /* use leftOver to plan for potential item*/
        }
    }
}

fun monthsUntil(person1: Person) {
    var numMonths = (person1.item_price.toDouble() / person1.pBudget.funMoney).roundToInt()

    println("You have a total of $${person1.pBudget.funMoney} left after your expenses.")
    println("This means that in ${numMonths} months you will have enough money for your ${person1.item_name}.")
}

fun checkYes(answer: String?) {
    when(answer) {
        "yes" -> println("Sweet! Let's go!")
        "y" -> println("Awesome! Let's go!")
        else -> exitProcess(1)
    }

}

fun getPercentages(person1: Person) {
    do {
        print("What is your income for next month?\n$")
        person1.pBudget.income = Integer.valueOf(readLine())

        print("What is your rent for next month?\n$")
        person1.pBudget.rent = Integer.valueOf(readLine())

        println("Now enter the percentage of your remaining budget you want to spend on groceries, travel, saviings, and other expenses")
        println("WARNING: make sure your percentage total does not surpass %100.")
        println("PS: if you remember entering these numbers before then you surpassed 100 with your percentages.\n")
        print("What percentage of your income do you want to go to groceries?\n%")
        person1.pBudget.perG = Integer.valueOf(readLine())

        print("What percentage of your income do you want to go to other expenses?\n%")
        person1.pBudget.perM = Integer.valueOf(readLine())

        print("What percentage of your income do you want to go to travel expenses?\n%")
        person1.pBudget.perT = Integer.valueOf(readLine())

        print("What percentage of your income do you want to go to savings for emergencies?\n%")
        person1.pBudget.perS = Integer.valueOf(readLine())
    }while ((person1.pBudget.perG + person1.pBudget.perM + person1.pBudget.perT + person1.pBudget.perS) > 100)

    person1.pBudget.calcDollars()
}

fun getDollars(person1: Person) {
        print("What is your income for this month? (in dollars)\n$")
        person1.cBudget.income = Integer.valueOf(readLine())

        print("What item do you want to save up for?\n")
        person1.item_name = readLine()

        print("That is so cool! How much does it cost?\n$")
        person1.item_price = Integer.valueOf(readLine())

        print("What is your budget for groceries? \n$")
        person1.cBudget.groceries = Integer.valueOf(readLine())

        print("What is your rent/living expenses? \n$")
        person1.cBudget.rent = Integer.valueOf(readLine())

        print("What is the total for your travel expenses? \n$")
        person1.cBudget.travel = Integer.valueOf(readLine())

        print("What is the total of your other expenses?\n$")
        person1.cBudget.misc = Integer.valueOf(readLine())

        person1.cBudget.setTotal()
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

    person1.cBudget.calcPercentage()

    print("Do you want to plan for next month?\n")
    var answer2 = readLine()
    checkYes(answer2)

    getPercentages(person1)
    monthsUntil(person1)
}

main()