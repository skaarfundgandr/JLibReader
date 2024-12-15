import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Image;

import javax.imageio.ImageIO;

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

    public ArrayList<Image> getCoverImages() {
        ArrayList<Image> coverImages = new ArrayList<>();

        try {
            for (Book book : bookList) {
                Image coverImage = ImageIO.read(book.getCoverImage().getInputStream());
                if (coverImage != null) {
                    coverImages.add(coverImage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coverImages;
    }

    public HashMap<Image, String> getTitleAndCover() {
        HashMap<Image, String> titlesAndCovers = new HashMap<>();

        try {
            for (Book book : bookList) {
                Image cover = ImageIO.read(book.getCoverImage().getInputStream());
                String title = book.getTitle();

                if (cover != null) {
                    titlesAndCovers.put(cover, title);
                } else {
                    titlesAndCovers.put(null, title);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return titlesAndCovers;
    }

    public ArrayList<Book> getBooks() {
        return bookList;
    }
}
