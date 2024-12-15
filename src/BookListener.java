import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import io.documentnode.epub4j.domain.Book;
import io.documentnode.epub4j.viewer.Viewer;

public class BookListener implements ActionListener {
    private Book book;

    public BookListener(Book book) {
        this.book = book;
    }

    public void actionPerformed(ActionEvent e) {
        new Viewer(book);
    }
}
