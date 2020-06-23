package eu.kartoffelquadrat.bookstoreinternals;

/**
 * @author Maximilian Schiedermeier
 */
public class BookDetailsImpl {

    private long isbn;
    private String title;
    private String author;
    private int priceInCents;
    private String bookAbstract;

    // Default constructor required to support json deserialization without objenesis
    public BookDetailsImpl(){}

    public BookDetailsImpl(long isbn, String title, String author, int priceInCents, String bookAbstract) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.priceInCents = priceInCents;
        this.bookAbstract = bookAbstract;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public String getBookAbstract() {
        return bookAbstract;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("ISBN: ").append(isbn).append("\n");
        sb.append(" > Title: ").append(title).append("\n");
        sb.append(" > Author: ").append(author).append("\n");
        sb.append(" > Price: ").append(priceInCents/100).append(",").append(priceInCents%100).append(" CAD\n");
        sb.append(" > Abstract: ").append(bookAbstract).append("\n");
        return sb.toString();
    }
}
