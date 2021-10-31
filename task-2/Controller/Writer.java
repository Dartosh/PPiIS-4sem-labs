package Controller;

import Model.Document;
import Model.IDocument;
import java.time.LocalDate;

public class Writer implements IWriter{
    private final String author;

    public Writer(String author) {
        this.author = author;
    }

    @Override
    public IDocument createDocument(String title, String text) {
        return new Document(title, LocalDate.now(), author, text);
    }

    @Override
    public void updateDocument(IDocument doc, String title, String text) throws Exception {
        if (doc == null) {
            throw new NullPointerException("Document is empty!");
        }

        Thread thread = new Thread(() -> {
            Document temp = (Document) doc;
            temp.setTitle(title);
            temp.setText(text);
        });
        thread.start();
        thread.join();
    }
}
