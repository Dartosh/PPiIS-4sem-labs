package Controller;

import Model.IDocument;

public interface IWriter {
    IDocument createDocument(String title, String text);
    void updateDocument(IDocument doc, String title, String text) throws Exception;
}
