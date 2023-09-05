package org.trains;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Biglietteria {

    private final static String DB_URl ="jdbc:mysql://localhost:3306/db_trains";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "root";

    private final static String SQL_TRAVELS = "select id, departure, arrival  from travels;";
    private final static String SQL_KM = "select km from travels t where id = ?;";



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfKm = 0;

        try(Connection connection = DriverManager.getConnection(DB_URl,DB_USER,DB_PASSWORD)){

            try(PreparedStatement ps = connection.prepareStatement(SQL_TRAVELS)){
                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()){
                        int id = rs.getInt("id");
                        String departure = rs.getString("departure");
                        String arrival = rs.getString("arrival");
                        System.out.print(id + ") ");
                        System.out.print(departure);
                        System.out.print("--->");
                        System.out.println(arrival);
                    }
                }

            }


            System.out.println("Choose a travel: ");
            int choice = Integer.parseInt(scan.nextLine());
            System.out.println("you chouse " + choice);
            try(PreparedStatement ps = connection.prepareStatement(SQL_KM)){
                ps.setInt(1,choice);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        numberOfKm = rs.getInt("km");
                        System.out.println("Number of km: " + rs.getInt("km"));
                    }else {
                        System.out.println("No travels with id " + choice);
                    }
                }
            }

        }catch(SQLException exception){
            System.out.println("An error occurred");
        }

        System.out.println("eta passeggero: ");
        int etaPasseggero = Integer.parseInt(scan.nextLine());

        System.out.println("Flessibile: true/false");
        boolean flessibile = scan.nextBoolean();

        System.out.println("Create ticket with " + numberOfKm + "km");

        Biglietto b1 = new Biglietto(numberOfKm,etaPasseggero,flessibile);
        System.out.println();
        System.out.println("Biglietto valido");
        System.out.println("Prezzo: "+ b1.calcolaPrezzo());
        System.out.println("Scadenza: "+b1.calcolaDataScadenza());



       /*
       Scanner scan = new Scanner(System.in);

        //  input km
        int km = 0;
        try {
            System.out.println("Km");
            km = Integer.parseInt(scan.nextLine());
        } catch (RuntimeException e) {
            System.out.println("Dato non corretto: numero possitivo maggiore di 0");
        }
        // input eta passeggero
        int etaPasseggero = 0;
        try {
            System.out.println("eta passeggero");
            etaPasseggero = Integer.parseInt(scan.nextLine());
        } catch (RuntimeException e) {
            System.out.println("Dato non corretto: numero possitivo maggiore di 0");
        }

        // flessibilita booleano
        boolean flessibile = false;
        try {
            System.out.println("Flessibile: true/false");
            flessibile = scan.nextBoolean();
        } catch (RuntimeException e) {
            System.out.println("Dato non corretto: true/false");
        }


        try {
            Biglietto b1 = new Biglietto(km,etaPasseggero,flessibile);
            System.out.println("Biglietto valido");
            System.out.println("Prezzo: "+ b1.calcolaPrezzo());
            System.out.println("Scadenza: "+b1.calcolaDataScadenza());
        } catch (RuntimeException e) {
            System.out.println("numero km o eta passeggero negativo");
        }*/

    }
}
