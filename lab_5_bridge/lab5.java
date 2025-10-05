public class Product {
    private String name;
    private String description;
    private String image;
    private int id;

    public Product(String name, String description, String image, int id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.id = id;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public int getId() { return id; }
}


public interface Renderer {
    String renderTitle(String title);
    String renderContent(String content);
    String renderProduct(Product product);
}

public class HTMLRenderer implements Renderer {
    @Override
    public String renderTitle(String title) {
        return "<h1>" + title + "</h1>";
    }

    @Override
    public String renderContent(String content) {
        return "<p>" + content + "</p>";
    }

    @Override
    public String renderProduct(Product product) {
        return "<div class='product'>" +
               "<h2>" + product.getName() + "</h2>" +
               "<p>" + product.getDescription() + "</p>" +
               "<img src='" + product.getImage() + "' />" +
               "<span>ID: " + product.getId() + "</span>" +
               "</div>";
    }
}

public class JsonRenderer implements Renderer {
    @Override
    public String renderTitle(String title) {
        return "{\"title\": \"" + title + "\"}";
    }

    @Override
    public String renderContent(String content) {
        return "{\"content\": \"" + content + "\"}";
    }

    @Override
    public String renderProduct(Product product) {
        return "{ \"name\": \"" + product.getName() + "\", " +
               "\"description\": \"" + product.getDescription() + "\", " +
               "\"image\": \"" + product.getImage() + "\", " +
               "\"id\": \"" + product.getId() + "\" }";
    }
}

public class XmlRenderer implements Renderer {
    @Override
    public String renderTitle(String title) {
        return "<title>" + title + "</title>";
    }

    @Override
    public String renderContent(String content) {
        return "<content>" + content + "</content>";
    }

    @Override
    public String renderProduct(Product product) {
        return "<product>" +
               "<name>" + product.getName() + "</name>" +
               "<description>" + product.getDescription() + "</description>" +
               "<image>" + product.getImage() + "</image>" +
               "<id>" + product.getId() + "</id>" +
               "</product>";
    }
}

public abstract class Page {
    protected Renderer renderer;

    public Page(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String render();
}

public class SimplePage extends Page {
    private String title;
    private String content;

    public SimplePage(Renderer renderer, String title, String content) {
        super(renderer);
        this.title = title;
        this.content = content;
    }

    @Override
    public String render() {
        return renderer.renderTitle(title) + renderer.renderContent(content);
    }
}

public class ProductPage extends Page {
    private Product product;

    public ProductPage(Renderer renderer, Product product) {
        super(renderer);
        this.product = product;
    }

    @Override
    public String render() {
        return renderer.renderProduct(product);
    }
}

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Laptop", "High-end gaming laptop", "laptop.png", 101);

        Renderer htmlRenderer = new HTMLRenderer();
        Renderer jsonRenderer = new JsonRenderer();
        Renderer xmlRenderer = new XmlRenderer();

        Page simpleHtml = new SimplePage(htmlRenderer, "Welcome", "This is a simple page.");
        Page productJson = new ProductPage(jsonRenderer, product);
        Page simpleXml = new SimplePage(xmlRenderer, "Hello", "XML content example.");

        System.out.println(simpleHtml.render());
        System.out.println(productJson.render());
        System.out.println(simpleXml.render());
    }
}
