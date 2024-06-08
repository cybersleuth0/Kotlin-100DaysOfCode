fun main(){
    var ayushshende = BankOfSpain("ayush", 100.0)
    ayushshende.deposit(50.0)
    ayushshende.withdraw(20.0)
    ayushshende.transactionchistory()

    var srk = BankOfSpain("srk", 1000.0)
    srk.deposit(50.0)
    srk.withdraw(2000.0)
    srk.transactionchistory()
}
