package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Document implements IDocument{
    private static int flag;
    private String title;
    private LocalDate createDate;
    private String author;
    private String text;

    public Document(String title, LocalDate createDate, String author, String text) {
        this.title = title;
        this.createDate = createDate;
        this.author = author;
        this.text = text;
    }

    Document() {
        this.title = "Title " + flag;
        this.createDate = LocalDate.now();
        this.author = "Author " + flag;
        this.text = "Text" + flag++;
    }

    @Override
    public String toString() {
        return "Document {\n" +
                "Title: " + title + "\n" +
                "Date of creation: " + createDate + "\n" +
                "Author: " + author + "\n" +
                "Text: " + text +
                "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Document temp = (Document) obj;
        return Objects.equals(this.title, temp.title)
                && Objects.equals(this.createDate, temp.createDate)
                && Objects.equals(this.author, temp.author)
                && Objects.equals(this.text, temp.text);
    }

    @Override
    public int hashCode() {
        int res = title == null ? 0 : title.hashCode();
        res = 29 * res + createDate.hashCode();
        res = 29 * res + author.hashCode();
        res = 29 * res + text.hashCode();
        return res;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
