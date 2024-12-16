import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import io.documentnode.epub4j.domain.Book;
import io.documentnode.epub4j.epub.EpubReader;

public class HandleBook {
    private ArrayList<Book> bookList;

    public HandleBook() {
        this(new Configuration().getBooksdir());
    }

    public HandleBook(String path) {
        EpubReader reader = new EpubReader();
        this.bookList = new ArrayList<>();
        File booksDir = new File(path);
        File[] books = booksDir.listFiles();

        try {
            for (File book : books) {
                bookList.add(reader.readEpub((InputStream) new FileInputStream(book)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HandleBook(ArrayList<Book> list) {
        this.bookList = list;
    }

    public boolean isEmpty() {
        return bookList.isEmpty();
    }

    public ArrayList<Book> getBooks() {
        return bookList;
    }
}
