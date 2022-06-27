package eu.kartoffelquadrat.bookstoreinternals;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Maximilian Schiedermeier
 */
public class CommentsTest {

    @Test
    public void testSingleton() {
        Comments commentsOne = CommentsImpl.getInstance();
        Comments commentsTwo = CommentsImpl.getInstance();
        assert commentsOne == commentsTwo;
        assert commentsOne != null;
    }


    @Test
    public void testPopulateComments() {

        /*
         * Make sure the book test is indexed to avoid an internal exception. Instantiation of the assortment will
         * automatically populate the assortment and index the book.
         */
        AssortmentImpl.getInstance();
        long harryPotterIsbn = Long.valueOf("9780739360385");

        Comments comments = getNewCommentsInstance();
        Map<Long, String> hpComments = comments.getAllCommentsForBook(harryPotterIsbn);
        assert hpComments.keySet().size() == 2;
        assert hpComments.values().contains("Amazing book!");
    }

    @Test(expected = RuntimeException.class)
    public void testAddComment() {
        AssortmentImpl.getInstance();
        long harryPotterIsbn = Long.valueOf("9780739360385");

        Comments comments = getNewCommentsInstance();
        comments.addComment(harryPotterIsbn, "Brilliant!");
        assert comments.getAllCommentsForBook(harryPotterIsbn).size() == 3;
        comments.addComment(harryPotterIsbn, "");
    }

    @Test(expected = RuntimeException.class)
    public void testAddCommentForNonIndexedBook() {
        AssortmentImpl.getInstance();
        Comments comments = getNewCommentsInstance();
        comments.addComment(Long.valueOf("42"), "42!");
    }

    @Test
    public void testDeleteComment() {
        AssortmentImpl.getInstance();
        Comments comments = getNewCommentsInstance();

        long harryPotterIsbn = Long.valueOf("9780739360385");
        Map<Long, String> hpComments = comments.getAllCommentsForBook(harryPotterIsbn);

        comments.deleteComment(harryPotterIsbn, hpComments.keySet().iterator().next());
        assert comments.getAllCommentsForBook(harryPotterIsbn).size() == 1;

        comments.removeAllCommentsForBook(harryPotterIsbn);
        assert comments.getAllCommentsForBook(harryPotterIsbn).size() == 0;
    }

    @Test
    public void testEditComment() {
        AssortmentImpl.getInstance();
        Comments comments = getNewCommentsInstance();

        long harryPotterIsbn = Long.valueOf("9780739360385");
        comments.addComment(harryPotterIsbn, "A comment that will be replaced.");
        Map<Long, String> hpComments = comments.getAllCommentsForBook(harryPotterIsbn);

        long commentId = hpComments.keySet().iterator().next();
        String nextComment = "Better than the movie.";
        comments.editComment(harryPotterIsbn, commentId, nextComment);

        assert comments.getAllCommentsForBook(harryPotterIsbn).get(commentId).equals(nextComment);
    }

    @Test(expected = RuntimeException.class)
    public void testEditCommentForBlank() {
        AssortmentImpl.getInstance();
        Comments comments = getNewCommentsInstance();

        long harryPotterIsbn = Long.valueOf("9780739360385");
        comments.addComment(harryPotterIsbn, "A comment that will be replaced by blank.");
        Map<Long, String> hpComments = comments.getAllCommentsForBook(harryPotterIsbn);

        long commentId = hpComments.keySet().iterator().next();
        String nextComment = "    "; // something blank that should be rejected
        comments.editComment(harryPotterIsbn, commentId, nextComment);
    }

    /**
     * Singleton constructor test
     */
    @Test
    public void testCommentsSingleton()
    {
        Comments cs = CommentsImpl.getInstance();
    }

    /**
     * Helper method to access the private default constructor. This test should not operate on the singleton's
     * getInstance method, since the tests may alter the bean state and therefore cannot be executed in arbitrary order.
     * To avoid this we use this method instead to ensure individual tests operate on isolated instances.
     *
     * @return a guaranteed new instance of CommentsImpl
     */
    private Comments getNewCommentsInstance() {
        try {
            Constructor<CommentsImpl> constructor = CommentsImpl.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Something went wrong creating a NEW instance of AssortmentImpl via reflection");
        }
    }
}
