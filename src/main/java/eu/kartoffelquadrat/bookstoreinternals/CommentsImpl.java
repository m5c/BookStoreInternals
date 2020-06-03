package eu.kartoffelquadrat.bookstoreinternals;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DAO for comments on books. Singleton.
 *
 * @author Maximilian Schiedermeier
 */
public class CommentsImpl implements Comments {

    private static Comments singletonReference = null;
    Map<Long, Map<Long, String>> commentsByIsbn;

    /**
     * Public default constructor for frameworks that automatically initialize beans as singletons.
     */
    public CommentsImpl() {
        this(true);
    }

    /**
     * Private constructor for singleton pattern.
     *
     * @param populate flag causes the comments to initialized with dummy data, if set to true
     */
    private CommentsImpl(boolean populate) {

        commentsByIsbn = new LinkedHashMap<>();
        if (populate)
            populateWithDummyData();
    }

    public static Comments getInstance() {
        if (singletonReference == null)
            singletonReference = new CommentsImpl(true);

        return singletonReference;
    }

    public Map<Long, String> getAllCommentsForBook(long isbn) {

        // If there are no comments yet, return new empty map.
        if (!commentsByIsbn.containsKey(isbn))
            return new LinkedHashMap<Long, String>();

        return Collections.unmodifiableMap(
                commentsByIsbn.get(isbn));
    }

    /*
     * Although comments are indexed by IDs, the creation of a new comment does not require an ID, for the ID is a
     * dynamic / non-global / internal ID.
     */
    public void addComment(long isbn, String comment) {

        // Verify the comment is not empty
        if (comment.trim().isEmpty())
            throw new RuntimeException("Empty comments can not be added.");

        // Verify that the isbn is valid (indexed by assortment)
        if (!AssortmentImpl.getInstance().getEntireAssortment().contains(isbn))
            throw new RuntimeException("Comment can not be added. No such isbn in assortment: " + isbn);

        // Create new map for isbn, if this is the first comment
        if (!commentsByIsbn.containsKey(isbn))
            commentsByIsbn.put(isbn, new LinkedHashMap<Long, String>());

        // Actually add the comment
        commentsByIsbn.get(isbn).put(generateCommentId(isbn), comment);
    }

    public void deleteComment(long isbn, long commentId) {

        if (!commentsByIsbn.containsKey(isbn))
            throw new RuntimeException("Comment can not be removed. No such isbn in assortment: " + isbn);

        if (!commentsByIsbn.get(isbn).containsKey(commentId))
            throw new RuntimeException("Comment can not be removed. For this book there is no comment with ID: " + commentId);

        commentsByIsbn.get(isbn).remove(commentId);
    }

    public void removeAllCommentsForBook(long isbn) {

        if (!commentsByIsbn.containsKey(isbn))
            throw new RuntimeException("Comments can not be removed. No such isbn in assortment: " + isbn);

        commentsByIsbn.remove(isbn);
    }

    public void editComment(long isbn, long commentId, String updatedComment) {

        // Verify the comment is not empty
        if (updatedComment.trim().isEmpty())
            throw new RuntimeException("Empty comments are not allowed.");

        if (!commentsByIsbn.containsKey(isbn))
            throw new RuntimeException("Comment can not be altered. No such isbn in assortment: " + isbn);

        if (!commentsByIsbn.get(isbn).containsKey(commentId))
            throw new RuntimeException("Comment can not be altered. For this book there is no comment with ID: " + commentId);

        commentsByIsbn.get(isbn).put(commentId, updatedComment);
    }

    /**
     * Adds fake comments to some indexed books.
     */
    private void populateWithDummyData() {

        addComment(Long.valueOf("9780739360385"), "Amazing book!");
        addComment(Long.valueOf("9780739360385"), "A must read!");
        addComment(Long.valueOf("9780553382563"), "A classic!");
        addComment(Long.valueOf("9780553382563"), "Would read it again.");
        addComment(Long.valueOf("9781977791122"), "Love it!");
        addComment(Long.valueOf("9780262538473"), "Fantastic!");
    }

    /**
     * Creates a unique but random comment id, for a given book.
     *
     * @param isbn as the id of the book to receive the comment.
     * @return a random long that is not yet used as a comment id for that book.
     */
    private long generateCommentId(long isbn) {
        long randomLong = (long) (Math.random() * 1000000);

        if (commentsByIsbn.get(isbn).containsKey(randomLong))
            return generateCommentId(isbn);
        else
            return randomLong;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("\n **************\n * Comments * \n **************\n");
        for(long isbn: commentsByIsbn.keySet())
        {
            sb.append(isbn+":\n");
            for(long commentId : commentsByIsbn.get(isbn).keySet())
            {
                sb.append(" > "+commentId + ": "+commentsByIsbn.get(isbn).get(commentId)+"\n");
            }
        }
        return sb.toString();
    }
}
