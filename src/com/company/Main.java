package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Proposer à l'utilisateur de choisir une rangée entre x et et un nombre de places
        // Vérifier s'il y a assez de places
        // Afficher la salle (si oui)
        // Proposer de sélectionner les places voulues
        // Sinon message pas assez de place et revenir au début

        // Créer une salle
        String[][] room = new String[7][11];
        System.out.println();
        System.out.println("--Bienvenue à la projection de Retour vers le futur 4--");
        System.out.printf("%38s", "Salle NOM DE ZEUS !!! \n");
        System.out.println();
        printCinema(room);
        System.out.println();
        Scanner sc = new Scanner(System.in);

        begin(room, sc);
        again(sc, room);
    }

    private static void again(Scanner sc, String[][]room) {

        boolean answer = false;
        while (!answer) {
            System.out.println("Voulez-vous réserver d'autres places (Taper '1' pour oui ou taper '2' pour non)");
            int res = sc.nextInt();
            sc.nextLine();
            if (res == 1) {
                answer = true;
            }else{
                begin(room,sc);
            }
        }
    }

    private static void begin(String[][] room, Scanner sc) {

        // Choisir une rangée
        System.out.println("Dans quelle rangée souhaitez vous réserver vos places (entre 1 et 7) : ");

        // Vérifier que celle-ci soit bien existante
        int selectedRow = validRow(sc);

        // Compter le nombre de sièges deja réservés
        int count = countSeats(room, selectedRow);

        // Vérifier s'il y a assez de place
        System.out.println("Combien de places souhaitez-vous réserver dans la rangée ? (10 places maxi) : ");
        int nSeats = enoughSeats(sc, count);

        // Etape de vérification
        System.out.println("Nombre de place(s) souhaité(es) : " + nSeats);

        // Réservation
        booking(room, sc, selectedRow, nSeats);
    }


    private static void booking(String[][] room, Scanner sc, int selectedRow, int nSeats) {

        for (int i = 0; i < nSeats; i++) {

            // Afficher la salle
            printCinema(room);

            // Demander quelle place l'utilisateur souhaite réserver
            System.out.println("Quelle place souhaitez-vous réserver ? : ");
            int selectedSeat = sc.nextInt();
            sc.nextLine();

            // Système de réservation de place
            bookingSeat(room, selectedRow, selectedSeat);
        }
        printCinema(room);
    }

    private static int enoughSeats(Scanner sc, int count) {

        // Vérifier s'il y a de la place de dispo sur la rangée

        int res = -1;
        boolean isValid = false;

        while (!isValid) {
            res = sc.nextInt();
            sc.nextLine();
            if ((10 - count) >= res) {
                isValid = true;
            } else {
                System.out.println("Il ne reste que " + (10 - count) + " place(s) de disponible(s)");
            }
        }
        return res;
    }

    private static int countSeats(String[][] room, int row) {

        // Compter les sièges déjà réservés

        int count = 0;

        for (int i = row; i < row + 1; i++) {
            for (int j = 0; j < room[0].length; j++) {
                if (room[row][j].equals("[ X ]")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void bookingSeat(String[][] room, int selectedRow, int selectedSeat) {

        // Réservation d'une place
        room[selectedRow - 1][selectedSeat] = "[ X ]";
    }

    private static void printCinema(String[][] room) {

        // Afficher la salle de cinéma
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                room[i][0] = " " + (i + 1) + " | ";
                if (room[i][j] == null) {
                    room[i][j] = "[ _ ]";
                }
                System.out.print(room[i][j]);
            }
            System.out.println();
        }
    }

    private static int validRow(Scanner sc) {

        // Vérifier si la rangée est un entier compris entre 1 et 7, sinon demander à nouveau
        int res = -1;
        boolean isValid = false;

        while (!isValid) {
            res = sc.nextInt();
            sc.nextLine();
            if ((res > 0) && (res < 8)) {
                isValid = true;
            } else {
                System.out.println("Merci de choisir une rangée entre 1 et 7 : ");
            }
        }
        return res;
    }
}

