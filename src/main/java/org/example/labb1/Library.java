package org.example.labb1;

public class Library {
    private Book[] books;
    private Member[] members;
    private int bookCount;
    private int memberCount;
    private String[] borrowedIsbns = new String[100];
    private int borrowedCount = 0;

    public Library(int maxBooks, int maxMembers) {
        this.books = new Book[maxBooks];
        this.members = new Member[maxMembers];
        this.bookCount = 0;
        this.memberCount = 0;
    }

    public boolean addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
            return true;
        }
        return false;
    }

    public boolean addMember(Member member) {
        if (memberCount < members.length) {
            members[memberCount] = member;
            memberCount++;
            return true;
        }
        return false;
    }

    public Book findBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isbn().equalsIgnoreCase(isbn)) {
                return books[i];
            }
        }
        return null;
    }

    public Member findMember(String id) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId().equalsIgnoreCase(id)) {
                return members[i];
            }
        }
        return null;
    }

    public boolean borrowBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            return false;
        }

        if (isBookBorrowed(isbn)) {
            return false;
        }

        if (!member.canBorrow()) {
            return false;
        }

        // Genomför lånet
        markAsBorrowed(isbn);
        member.borrowBook();
        return true;
    }


    public boolean returnBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book != null && member != null && isBookBorrowed(isbn)) {
            markAsReturned(isbn);
            member.returnBook();
            return true;
        }

        return false;
    }

    public String searchBookByTitleOrAuthor(String search) {
        StringBuilder result = new StringBuilder();
        String searchTerm = search.toLowerCase();

        for (int i = 0; i < bookCount; i++) {
            String title = books[i].title().toLowerCase();
            String author = books[i].author().toLowerCase();

            if (title.contains(searchTerm) || author.contains(searchTerm)) {
                result.append("Titel: ").append(books[i].title())
                        .append(" | Författare: ").append(books[i].author())
                        .append(" | ISBN: ").append(books[i].isbn())
                        .append("\n");
            }
        }

        if (result.length() == 0) {
            return "Inga böcker hittades för sökningen: " + search;
        }

        return result.toString();
    }

    public void displayAllBooks() {
        if (bookCount == 0) {
            IO.println("Inga böcker finns registrerade i biblioteket.");
            return;
        }
        IO.println("Alla böcker i biblioteket:");
        for (int i = 0; i < bookCount; i++) {
            Book b = books[i];
            String status = isBookBorrowed(b.isbn()) ? "Utlånad" : "Tillgänglig";
            IO.println("Titel: " + b.title() + " | Författare: " + b.author() + " | ISBN: " + b.isbn() + " | Status: " + status);
        }
    }

    public boolean isBookBorrowed(String isbn) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedIsbns[i].equalsIgnoreCase(isbn)) {
                return true;
            }
        }
        return false;
    }

    private void markAsBorrowed(String isbn) {
        borrowedIsbns[borrowedCount++] = isbn;
    }

    private void markAsReturned(String isbn) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedIsbns[i].equalsIgnoreCase(isbn)) {
                borrowedIsbns[i] = borrowedIsbns[borrowedCount - 1];
                borrowedIsbns[borrowedCount - 1] = null;
                borrowedCount--;
                break;


            }
        }
    }
}

