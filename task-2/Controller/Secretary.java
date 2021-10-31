package Controller;

import Model.ICatalog;
import Model.IDocument;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class Secretary implements ISecretary {
    private final ICatalog catalog;

    public Secretary(ICatalog catalog) {
        if (catalog == null) {
            throw new NullPointerException("Catalog is empty!");
        }

        this.catalog = catalog;
    }

    @Override
    public void addDocument(IDocument doc) throws Exception {
        if (doc == null) {
            throw new NullPointerException("Document is empty");
        }
        if (catalog.getDocumentList().contains(doc)) {
            throw new Exception("Duplicate!");
        }

        Thread thread = new Thread(() -> catalog.getDocumentList().add(doc));
        thread.start();
        thread.join();
    }

    @Override
    public IDocument getDocumentByAuthor(String author) throws Exception {
        return getDocument((e) -> e.getAuthor().equals(author));
    }

    @Override
    public IDocument getDocumentByTitle(String title) throws Exception {
        return getDocument((e) -> e.getTitle().equals(title));
    }

    private IDocument getDocument(Predicate<IDocument> predicate) throws Exception {
        AtomicReference<IDocument> doc = new AtomicReference<>();

        Thread thread = new Thread(() -> doc.set(catalog.getDocumentList()
                .stream().filter(predicate)
                .findAny().orElseGet(() -> null)));
        thread.start();
        thread.join();

        return doc.get();
    }
}
