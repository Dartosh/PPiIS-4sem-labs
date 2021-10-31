package Model;

import java.time.LocalDate;

public interface IDocument {
    String getTitle();
    LocalDate getCreateDate();
    String getAuthor();
    String getText();
}
