import java.util.HashMap;
import java.util.Map;

public interface Downloader {
    byte[] download(String url);
}

public class SimpleDownloader implements Downloader {
    @Override
    public byte[] download(String url) {
        System.out.println("Downloading file from: " + url);
        return new byte[0];
    }
}

public class ProxyDownloader implements Downloader {
    private final Downloader realDownloader;
    private final Map<String, byte[]> cache = new HashMap<>();

    public ProxyDownloader(Downloader realDownloader) {
        this.realDownloader = realDownloader;
    }

    @Override
    public byte[] download(String url) {
        if (cache.containsKey(url)) {
            System.out.println("Returning cached data for: " + url);
            return cache.get(url);
        }
        System.out.println("Cache miss. Using real downloader...");
        byte[] data = realDownloader.download(url);
        cache.put(url, data);
        return data;
    }
}

public class PageRenderer {
    private final Downloader downloader;

    public PageRenderer(Downloader downloader) {
        this.downloader = downloader;
    }

    public void renderHomePage() {
        System.out.println("Rendering Home Page...");
        downloader.download("http://example.com/home_banner");
    }

    public void renderProfilePage() {
        System.out.println("Rendering Profile Page...");
        downloader.download("http://example.com/user_avatar");
    }
}

public class ClientExample {
    public static void main(String[] args) {
        Downloader simple = new SimpleDownloader();
        Downloader proxy = new ProxyDownloader(simple);

        PageRenderer simpleRenderer = new PageRenderer(simple);
        PageRenderer cachedRenderer = new PageRenderer(proxy);

        System.out.println("=== Simple Rendering ===");
        simpleRenderer.renderHomePage();
        simpleRenderer.renderProfilePage();

        System.out.println("\n=== Cached Rendering ===");
        cachedRenderer.renderHomePage();
        cachedRenderer.renderHomePage();
        cachedRenderer.renderProfilePage();
    }
}
