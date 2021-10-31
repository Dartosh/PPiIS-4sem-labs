package Model;

import java.util.List;

public interface ICatalog {
    String getName();
    List<IDocument> getDocumentList();
    List<ICatalog> getCatalogList();
}
