package org.example.labb1;

public class LibraryApp {
    static void main() {
        Library library = new Library(100, 100);

        library.seedData();

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
            IO.println("7. Visa alla medlemmar");

            IO.println("e. Avsluta");

            String choice = IO.readln("Välj ett alternativ: ");

            switch (choice.toLowerCase()) {
                case "1":
                    IO.println("--- Lägg till ny bok --- ");
                    String isbn = IO.readln("Ange ISBN: ");
                    String title = IO.readln("Ange titel: ");
                    String author = IO.readln("Ange författare: ");

                    Book book = new Book(isbn, title, author);

                    boolean added = library.addBook(book);
                    if (added) {
                        IO.println("Boken har lagts till i biblioteket.");
                    } else {
                        IO.println("Boken kunde inte läggas till i biblioteket.");
                    }
                    break;

                case "2":
                    IO.println("--- Registrera ny medlem --- ");
                    String id = IO.readln("Ange ID: ");
                    String name = IO.readln("Ange namn: ");

                    Member member = new Member(id, name);

                    boolean registered = library.addMember(member);
                    if (registered) {
                        IO.println("Medlemmen har registrerats.");
                    } else {
                        IO.println("Medlemmen kunde inte registreras.");
                    }
                    break;

                case "3":
                    IO.println("--- Låna bok ---");
                    isbn = IO.readln("Ange bokens ISBN: ");
                    String memberId = IO.readln("Ange medlems-ID: ");

                    boolean success = library.borrowBook(isbn, memberId);
                    if (success) {
                        IO.println("Boken har lånats ut!");
                    } else {
                        IO.println("Kunde inte genomföra lånet. Kontrollera att boken och medlemmen finns samt att medlemmen inte har nått maxgränsen för lån (3 st).");
                    }
                    break;

                case "4":
                    IO.println("--- Lämna tillbaka bok ---");
                    String returnIsbn = IO.readln("Ange bokens ISBN: ");
                    String returnMemberId = IO.readln("Ange medlems-ID: ");

                    boolean returned = library.returnBook(returnIsbn, returnMemberId);
                    if (returned) {
                        IO.println("Boken har lämnats tillbaka!");
                    } else {
                        IO.println("Kunde inte lämna tillbaka boken. Kontrollera att ISBN och medlems-ID är korrekta samt att medlemmen faktiskt har aktiva lån.");
                    }
                    break;

                case "5":
                    IO.println("--- Sök bok ---");
                    String searchTerm = IO.readln("Ange titel eller författare att söka efter: ");

                    String result = library. searchBookByTitleOrAuthor(searchTerm);
                    IO.println(result);
                    break;

                case "6":
                    IO.println("--- Alla böcker i biblioteket ---");
                    library.displayAllBooks();
                    break;

                case "7":
                    IO.println("--- Alla registrerade medlemmar ---");
                    library.displayAllMembers();
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
