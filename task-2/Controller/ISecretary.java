package Controller;

import Model.IDocument;

public interface ISecretary {
    void addDocument(IDocument doc) throws Exception;
    IDocument getDocumentByAuthor(String author) throws Exception;
    IDocument getDocumentByTitle(String title) throws Exception;
}
