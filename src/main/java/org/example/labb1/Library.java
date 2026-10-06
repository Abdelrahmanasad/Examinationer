package org.example.labb1;

public class Library {
    private Book[] books;
    private Member[] members;
    private int bookCount;
    private int memberCount;
    private String[] borrowedIsbns = new String[100];
    private String[] borrowedByMemberIds = new String[100];
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

        markAsBorrowed(isbn, memberId);
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
            String borrowerId = getBorrowerId(b.isbn());

            String status;
            if (borrowerId != null) {
                Member borrower = findMember(borrowerId);
                String borrowerName = (borrower != null) ? borrower.getName() : borrowerId;
                status = "Utlånad till " + borrowerName + " (ID: " + borrowerId + ")";
            } else {
                status = "Tillgänglig";
            }

            IO.println("Titel: " + b.title() + " | Författare: " + b.author() + " | ISBN: " + b.isbn() + " | Status: " + status);
        }
    }

    public String getBorrowerId(String isbn) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedIsbns[i].equalsIgnoreCase(isbn)) {
                return borrowedByMemberIds[i];
            }
        }
        return null;
    }

    public boolean isBookBorrowed(String isbn) {
        return getBorrowerId(isbn) != null;
    }

    private void markAsBorrowed(String isbn, String memberId) {
        borrowedIsbns[borrowedCount] = isbn;
        borrowedByMemberIds[borrowedCount] = memberId;
        borrowedCount++;
    }

    private void markAsReturned(String isbn) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedIsbns[i].equalsIgnoreCase(isbn)) {
                borrowedIsbns[i] = borrowedIsbns[borrowedCount - 1];
                borrowedByMemberIds[i] = borrowedByMemberIds[borrowedCount - 1];

                borrowedIsbns[borrowedCount - 1] = null;
                borrowedByMemberIds[borrowedCount - 1] = null;
                borrowedCount--;
                break;
            }
        }
    }

    public void displayAllMembers() {
        if (memberCount == 0) {
            IO.println("Inga medlemmar finns registrerade.");
            return;
        }
        IO.println("Alla registrerade medlemmar:");
        for (int i = 0; i < memberCount; i++) {
            Member m = members[i];
            IO.println("ID: " + m.getId() + " | Namn: " + m.getName() + " | Aktiva lån: " + m.getActiveLoans());
        }
    }

    public void seedData() {
        addBook(new Book("111", "Pippi Långstrump", "Astrid Lindgren"));
        addBook(new Book("222", "Hundraåringen som klev ut genom fönstret och försvann", "Jonas Jonasson"));
        addBook(new Book("333", "Män som hatar kvinnor", "Stieg Larsson"));
        addBook(new Book("444", "En man som heter Ove", "Fredrik Backman"));
        addBook(new Book("555", "Snabba cash", "Jens Lapidus"));
        addBook(new Book("666", "Harry Potter and the Philosopher's Stone", "J.K. Rowling"));
        addBook(new Book("777", "The Lord of the Rings", "J.R.R. Tolkien"));
        addBook(new Book("888", "1984", "George Orwell"));
        addBook(new Book("999", "To Kill a Mockingbird", "Harper Lee"));
        addBook(new Book("112", "The Great Gatsby", "F. Scott Fitzgerald"));

        addMember(new Member("M001", "Samuel"));
        addMember(new Member("M002", "Abbe"));
        addMember(new Member("M003", "Amir"));
    }
}
