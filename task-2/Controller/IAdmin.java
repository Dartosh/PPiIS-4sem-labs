package Controller;

import Model.ICatalog;
import Model.IDocument;

public interface IAdmin {
    void removeDocument(IDocument document) throws Exception;
    ICatalog createCatalog(String catalogName);
    void moveDocument(IDocument document, ICatalog fromCatalog, ICatalog toCatalog) throws Exception;

}
