package org.example.labb1;

public class LibraryApp {
    static void main() {
        Library library = new Library(100, 100);
        boolean running = true;

        while (running) {
            IO.println("====================");
            IO.println("Bibliotekshanteraren");
            IO.println("====================");
            IO.println("1. Lägg till en bok");
            IO.println("2. Registrera medlem");
            IO.println("3. Låna bok");
            IO.println("4. Lämna tillbaka bok");
            IO.println("5. Sök bok (titel eller författare)");
            IO.println("6. Visa alla böcker");

            IO.println("e. Avsluta");

            String choice = IO.readln("Välj ett alternativ: ");

            switch (choice.toLowerCase()) {
                case "1":

                    break;
                case "2":
                    // Kod för att registrera medlem
                    break;
                case "3":
                    // Kod för att låna bok
                    break;
                case "4":
                    // Kod för att lämna tillbaka bok
                    break;
                case "5":
                    // Kod för att söka bok
                    break;
                case "6":
                    library.displayAllBooks();
                    break;
                case "e":
                    IO.println("Tack för idag! Programmet avslutas.");
                    running = false;
                    break;
                default:
                    IO.println("Ogiltigt val, försök igen.");
            }
        }
    }

}
