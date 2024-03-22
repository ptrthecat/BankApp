//poczatek programu
import java.util.Scanner;

class BankAccount {
    private double balance; //saldo konta

    public BankAccount(double openingBalance)   { //konstruktor
        balance = openingBalance;   //inicjowanie konta
    }

    public void deposit(double amount) {    //dokonuje wplaty
        balance = balance + amount;
    }

    public void withdraw(double amount) {   //dokonuje wyplaty
        balance = balance - amount;
    }

    public void display() { //wypisuje saldo
        System.out.println("saldo=" + balance);
    }

    public double capital12(double x, double p, double t) { // kapitalizacja roczna
        int k = 1;
        return x * Math.pow( (p/(k * 100) + 1) , Math.floor(t/12) * k );
    }

    public double capital6(double x, double p, double t) {  // kapitalizacja polroczna
        int k = 2;
        return x * Math.pow( (p/(k * 100) + 1) , Math.floor(t/12) * k );
    }

    public double capital3(double x, double p, double t) {  // kapitalizacja kwartalna
        int k = 4;
        return x * Math.pow( (p/(k * 100) + 1) , Math.floor(t/12) * k );
    }

    public double lending_rate12(double x, double t, double y) { // kapitalizacja roczna
        int k=1;
        return (Math.pow( (y/x) , 1/( (t/12) * k )) - 1) * 100 * k;
    }

    public double lending_rate6(double x, double t, double y) { // kapitalizacja polroczna
        int k=2;
        return (Math.pow( (y/x) , 1/( (t/12) * k )) - 1) * 100 * k;
    }

    public double lending_rate3(double x, double t, double y) { // kapitalizacja kwartalna
        int k=4;
        return (Math.pow( (y/x) , 1/( (t/12) * k )) - 1) * 100 * k;
    }

    public double interest(double y, double t, double p) {

        return ((y * p * (t * 12 +1) ) / 2400);
    }

    public double equal_installments(double y, double t, double p) {

        return (Math.round((  y  / ( t * 12 ) ) * 100 ) / 100.0 );
    }

}   //koniec klasy BankAccount
//========================================================
class BankApp {
    public static void main(String[] args) {
        BankAccount ba1 = new BankAccount(100.00);  //tworzymy obiekt o nazwie ba1 (konto)

        Scanner scanner = new Scanner(System.in);   //tworzymy obiekt o nazwie scanner (obsluga wejscia)
        char op; double n; float m;

        //System.out.println("Przed transakcjami, ");
        //ba1.display();  //wypisujemy saldo

        do {
            menu();
            System.out.print("Wybor: ");
            op = scanner.next().charAt(0);

            switch (op) {
                case 'a': //kapital koncowy
                {
                    double x = 0;
                    double p = 0;
                    double t = 0;

                    System.out.print("kapital poczatkowy: ");
                    x = scanner.nextDouble();
                    System.out.print("oprocentowanie: ");
                    p = scanner.nextDouble();
                    System.out.print("liczba miesiecy: ");
                    t = scanner.nextDouble();

                    System.out.println();
                    System.out.println("wartosc koncowa przy kapitalizacji rocznej: " + Math.round( ba1.capital12(x,p,t) * 100.0 ) / 100.0 + "zl");
                    System.out.println("wartosc koncowa przy kapitalizacji polrocznej: " + Math.round( ba1.capital6(x,p,t) * 100.0 ) / 100.0 + "zl");
                    System.out.println("wartosc koncowa przy kapitalizacji kwartalnej: " + Math.round( ba1.capital3(x,p,t) * 100.0 ) / 100.0 + "zl");

                    break;
                }
                case 'b': //oprocentowanie w banku
                {
                    double x = 0;
                    double t = 0;
                    double y = 0;

                    System.out.print("zalozona lokata: ");
                    x = scanner.nextDouble();
                    System.out.print("liczba miesiecy: ");
                    t = scanner.nextDouble();
                    System.out.print("kapital koncowy: ");
                    y = scanner.nextDouble();


                    System.out.println();
                    System.out.println("oprocentowanie przy kapitalizacji rocznej: " + Math.round( ba1.lending_rate12(x,t,y) * 100.0 ) / 100.0 + "%");
                    System.out.println("oprocentowanie przy kapitalizacji polrocznej: " + Math.round( ba1.lending_rate6(x,t,y)  * 100.0 ) / 100.0 + "%");
                    System.out.println("oprocentowanie przy kapitalizacji kwartalnej: " + Math.round( ba1.lending_rate3(x,t,y)  * 100.0 ) / 100.0 + "%");

                    break;

                }
                case 'c': //odsetki od kredytu
                {
                    double y = 0;
                    double t = 0;
                    double p = 0;

                    System.out.print("kwota kredytu: ");
                    y = scanner.nextDouble();
                    System.out.print("liczba lat: ");
                    t = scanner.nextDouble();
                    System.out.print("roczna stopa procentowa: ");
                    p = scanner.nextDouble();

                    System.out.println( "odsetki: " + Math.round( ba1.interest(y,t,p) * 100.0 ) / 100.0 );


                    break;
                }
                case 'd': //wysokosc rat kredytu
                {
                    char wybor = 0;

                    System.out.println("Raty sa splacane: ");
                    System.out.println("1. w kwartalnych ratach malejacych ");
                    System.out.println("2. w kwartalnych ratach rownych ");
                    System.out.print("wybor: ");
                    wybor = scanner.next().charAt(0);

                    if(wybor == '1'){

                    }
                    else{

                        double y = 0;
                        double t = 0;
                        double p = 0;

                        System.out.print("kwota kredytu: " );
                        y = scanner.nextDouble();
                        System.out.print("liczba lat: " );
                        t = scanner.nextDouble();
                        System.out.print("roczna stopa procentowa: ");
                        p = scanner.nextDouble();

                        System.out.print("raty rowne: " + ba1.equal_installments(y,t,p));
                    }

                    break;
                }

                case '0': //koniec
                {
                    System.out.println("koniec programu");
                    break;
                }
                default:
                    System.out.println("Zla opcja !!!");
            }   //koniec switch
        }while(op != '0');
    }   //koniec main()

    //========================================================
    public static void menu() {
        System.out.println("\n______________________________");
        System.out.println(" Operacje ");
        System.out.println("_______________________________");
        System.out.println(" a. kapital koncowy");
        System.out.println(" b. oprocentowanie w banku");
        System.out.println(" c. odsetki od kredytu");
        System.out.println(" d. wysokosc rat kredytu");
        System.out.println(" 0. Koniec programu ");
        System.out.println("===============================");
    }
    //========================================================
}   //koniec klasy BankApp
