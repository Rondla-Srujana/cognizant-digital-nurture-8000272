// ============================================================
// Exercise 2: Factory Method Pattern
// File: Ex2_Factory_Method.java
// ============================================================

interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() { System.out.println("Opening Word Document."); }
}

class PdfDocument implements Document {
    public void open() { System.out.println("Opening PDF Document."); }
}

class ExcelDocument implements Document {
    public void open() { System.out.println("Opening Excel Document."); }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new ExcelDocument(); }
}

public class Ex2_Factory_Method {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory  = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        Document doc1 = wordFactory.createDocument();
        Document doc2 = pdfFactory.createDocument();
        Document doc3 = excelFactory.createDocument();

        doc1.open();
        doc2.open();
        doc3.open();
    }
}
