import java.util.Scanner;

public class Biglietteria {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Km");
        int km = scan.nextInt();
        System.out.println("eta passeggero");
        int etaPasseggero = scan.nextInt();
        System.out.println("Flessibile: true/false");
        boolean flessibile = scan.nextBoolean();


        try {
            Biglietto b1 = new Biglietto(km,etaPasseggero,flessibile);
            System.out.println("Biglietto valido");
            System.out.println("Prezzo: "+ b1.calcolaPrezzo());
            System.out.println("Scadenza: "+b1.calcolaDataScadenza());
        } catch (RuntimeException e) {
            System.out.println("numero km o eta passeggero negativo");
        }

    }
}
