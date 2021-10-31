import Controller.*;
import Model.Catalog;
import Model.ICatalog;
import Model.IDocument;

public class Main {

    public static void main(String[] args) {
        ICatalog fromCatalog = new Catalog("First catalog");
        ICatalog toCatalog = new Catalog("Second catalog");
        IWriter writer = new Writer("Klintsov Antony");
        IDocument tempDocument = null;
        ISecretary secretary = new Secretary(fromCatalog);
        IAdmin admin = new Admin(toCatalog);

        try {
            secretary.addDocument(writer.createDocument("PPiIS Additional task",
                    "Lorem ipsum dolor sit amet."));
            secretary.addDocument(writer.createDocument("PPiIS Lab 1",
                    "Lorem ipsum dolor sit amet."));
            secretary.addDocument(writer.createDocument("PPiIS Lab 2",
                    "Lorem ipsum dolor sit amet."));

            System.out.println("Before:");
            fromCatalog.getDocumentList().forEach(System.out::println);

            tempDocument = secretary.getDocumentByTitle("PPiIS Additional task");
            admin.moveDocument(tempDocument, fromCatalog, toCatalog);

            System.out.println("\n\nResult:");
            fromCatalog.getDocumentList().forEach(System.out::println);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
