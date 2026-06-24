// ============================================================
// Exercise 6: Proxy Pattern
// File: Ex6_Proxy_Image.java
// ============================================================

interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer();
    }

    private void loadFromRemoteServer() {
        System.out.println("Loading image from remote server: " + filename);
    }

    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;

    ProxyImage(String filename) { this.filename = filename; }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // lazy init
        }
        realImage.display();
    }
}

public class Ex6_Proxy_Image {
    public static void main(String[] args) {
        Image img = new ProxyImage("photo.jpg");

        System.out.println("First call:");
        img.display(); // loads + displays

        System.out.println("\nSecond call (cached):");
        img.display(); // only displays
    }
}
