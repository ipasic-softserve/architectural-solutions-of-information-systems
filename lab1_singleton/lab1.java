import java.util.List;

enum StorageType {
    LOCAL,
    S3
}

interface IStorage {
    void uploadFile(String filePath, String data);
    String downloadFile(String filePath);
    void deleteFile(String filePath);
    List<String> listFiles(String directory);
}

class LocalStorage implements IStorage {
    @Override
    public void uploadFile(String filePath, String data) {}

    @Override
    public String downloadFile(String filePath) { return 'local file'; }

    @Override
    public void deleteFile(String filePath) {}

    @Override
    public List<String> listFiles(String directory) { return []; }
}

class AmazonS3Storage implements IStorage {
    @Override
    public void uploadFile(String filePath, String data) {}

    @Override
    public String downloadFile(String filePath) { return 's3 file'; }

    @Override
    public void deleteFile(String filePath) {}

    @Override
    public List<String> listFiles(String directory) { return []; }
}

class FileManager {
    private static FileManager instance;
    private final IStorage storage;

    private FileManager(StorageType storageType) {
        switch (storageType) {
            case LOCAL:
                this.storage = new LocalStorage();
                break;
            case S3:
                this.storage = new AmazonS3Storage();
                break;
            default:
                throw new IllegalArgumentException("Unknown storage type: " + storageType);
        }
    }

    public static synchronized FileManager getInstance(StorageType storageType) {
        if (instance == null) {
            instance = new FileManager(storageType);
        }
        return instance;
    }

    public void upload(String filePath, String data) {
        storage.uploadFile(filePath, data);
    }

    public String download(String filePath) {
        return storage.downloadFile(filePath);
    }

    public void remove(String filePath) {
        storage.deleteFile(filePath);
    }

    public List<String> list(String directory) {
        return storage.listFiles(directory);
    }
}

public class Main {
    public static void main(String[] args) {
        StorageType userStorageType = StorageType.S3;
        FileManager fileManager = FileManager.getInstance(userStorageType);

        fileManager.upload("data.txt", "Hello from S3");
        fileManager.list("");
    }
}
