package eu.kartoffelquadrat.bookstoreinternals;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Collections;

/**
 * @author Maximilian Schiedermeier
 */
public class LocalStockImpl implements LocalStock {

    private Map<Long, Integer> stockByBook;

    public LocalStockImpl() {
        stockByBook = new LinkedHashMap<>();
    }

    public int getAmount(long isbn) {

        // Reject queries for non-indexed books
        if (!AssortmentImpl.getInstance().getEntireAssortment().contains(isbn))
            throw new RuntimeException("Stock queries are only allowed for indexed books.");

        if (!stockByBook.containsKey(isbn))
            return 0;

        return stockByBook.get(isbn);
    }

    public void setAmount(long isbn, int amount) {

        // Reject queries for non-indexed books
        if (!AssortmentImpl.getInstance().getEntireAssortment().contains(isbn))
            throw new RuntimeException("Stock updates are only allowed for indexed books.");

        stockByBook.put(isbn, amount);
    }

    @Override
    public Map<Long, Integer> getEntireStock() {
        return Collections.unmodifiableMap(stockByBook);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("");
        for(long isbn : stockByBook.keySet())
        {
            sb.append(" > " + isbn +": "+stockByBook.get(isbn)+"\n");
        }
        return sb.toString();
    }
}
