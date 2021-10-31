package Model;

import java.util.ArrayList;
import java.util.List;

public class Catalog implements ICatalog{
    private static int flag;
    private final String name;
    private final List<IDocument> documentList;
    private final List<ICatalog> catalogList;

    public Catalog(String name) {
        this.name = name;
        this.documentList = new ArrayList<>();
        this.catalogList = new ArrayList<>();
    }

    Catalog() {
        this.name = Integer.toString(flag++);
        this.documentList = new ArrayList<>();
        this.catalogList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<IDocument> getDocumentList() {
        return documentList;
    }

    public List<ICatalog> getCatalogList() {
        return catalogList;
    }

    @Override
    public String toString() {
        return "Catalog{\n" +
                "Catalog name: " + name + "\n" +
                "Number of documents: " + documentList.size() + "\n" +
                "Number of catalogs: " + catalogList.size() +
                "\n}";
    }
}
