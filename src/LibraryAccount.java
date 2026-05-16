@FunctionalInterface
interface MessagePrinter {
    void print(String message);
}

public class LibraryAccount {
    private String readerName;
    private int borrowedBooks;
    private int lateDays;

    public LibraryAccount(String readerName, int borrowedBooks, int lateDays) {
        this.readerName = readerName;
        this.borrowedBooks = borrowedBooks;
        this.lateDays = lateDays;
    }

    public String getReaderName() {
        return readerName;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getLateDays() {
        return lateDays;
    }

    public class FineCalculator {
        public double calculate(){
            return borrowedBooks * lateDays * 1.50;
        }
    }
}
