package Controller;

import Model.Catalog;
import Model.ICatalog;
import Model.IDocument;

public class Admin implements IAdmin{
    private final ICatalog catalog;

    public Admin(ICatalog catalog) {
        if (catalog == null) {
            throw new NullPointerException("Catalog is empty!");
        }

        this.catalog = catalog;
    }

    @Override
    public void removeDocument(IDocument doc) throws Exception{
        if (doc == null) {
            throw new NullPointerException("Document is empty!");
        }

        Thread thread = new Thread(() -> catalog.getDocumentList().remove(doc));
        thread.start();
        thread.join();
    }

    @Override
    public ICatalog createCatalog(String catalogName) {
        return new Catalog(catalogName);
    }

    @Override
    public void moveDocument(IDocument document, ICatalog fromCatalog, ICatalog toCatalog) throws Exception{
        if (fromCatalog == null || toCatalog == null) {
            throw new NullPointerException("There are no such catalog!");
        }
        if (toCatalog.getDocumentList().contains(document)) {
            throw new Exception("Duplicate!");
        }

        Thread thread = new Thread(() -> fromCatalog.getDocumentList().remove(document));
        thread.start();
        thread.join();
        thread = new Thread(() -> toCatalog.getDocumentList().add(document));
        thread.start();
        thread.join();
    }
}
