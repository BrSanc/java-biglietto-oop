package org.trains;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Biglietto {


        private int km;
        private int etaPasseggero;

        private final BigDecimal priceOfKm = BigDecimal.valueOf(0.21);

        private final BigDecimal over65 = BigDecimal.valueOf(0.40);

        private final BigDecimal minorenni = BigDecimal.valueOf(0.20);

        private LocalDate data;
        private boolean flessibile;

        private final int durataNormale = 30;
        private final int durataflessibile = 90;



        //---------Costruttore-------------


        public Biglietto(int km, int etaPasseggero, boolean flessibile) {
            isValidKm(km);
            this.km = km;
            isValidEta(etaPasseggero);
            this.etaPasseggero = etaPasseggero;
            this.data = LocalDate.now();
            this.flessibile = flessibile;
        }

        //----------------Getter--------------



        //----------------Setter------------------



        //----------------Metodi----------------------

        private void isValidKm(int km){
            if(km <= 0 ){
                throw new RuntimeException();
            }
        }

        private void isValidEta(int etaPasseggero){
            if (etaPasseggero <= 0){
                throw new RuntimeException();
            }
        }



        public BigDecimal calcolaPrezzo(){

            BigDecimal price;
            BigDecimal kmBigDecimal = BigDecimal.valueOf(km);
            price = priceOfKm.multiply(kmBigDecimal);
            if (flessibile){
                BigDecimal flessibilePrice = price.multiply(BigDecimal.valueOf(0.10));
                price = price.add(flessibilePrice);
            }

            if (etaPasseggero > 65 || etaPasseggero < 18){
                price = calcolaSconto(price);
                return price;
            } else {
                return price;
            }
        }

        private BigDecimal calcolaSconto(BigDecimal price){
            BigDecimal prezzoScontato = null;
            if (etaPasseggero > 65){
                BigDecimal scontoOver65 = price.multiply(over65);
                prezzoScontato = price.subtract(scontoOver65);
            } else if (etaPasseggero < 18) {
                BigDecimal scontoMinorenni = price.multiply(minorenni);
                prezzoScontato = price.subtract(scontoMinorenni);
            }
            return prezzoScontato;
        }

        public LocalDate calcolaDataScadenza (){
            LocalDate dataScadenza = null;
            if (flessibile){
                dataScadenza = data.plusDays(durataflessibile);
            }else {
                dataScadenza = data.plusDays(durataNormale);
            }
            return dataScadenza;
        }



}
