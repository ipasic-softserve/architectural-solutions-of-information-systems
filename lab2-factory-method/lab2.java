interface SocialNetwork {
    void connect();
    void publish(String message);
}

class Facebook implements SocialNetwork {
    private final String login;
    private final String password;

    public Facebook(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void connect() {
        System.out.printf("Connecting to Facebook with login: %s and password: %s%n", login, password);
    }

    @Override
    public void publish(String message) {
        System.out.printf("Publishing message to Facebook: %s%n", message);
    }
}

class LinkedIn implements SocialNetwork {
    private final String email;
    private final String password;

    public LinkedIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void connect() {
        System.out.printf("Connecting to LinkedIn with email: %s and password: %s%n", email, password);
    }
    
    @Override
    public void publish(String message) {
        System.out.printf("Publishing message to LinkedIn: %s%n", message);
    }
}

abstract class SocialNetworkPoster {
    public void post(String message) {
        SocialNetwork network = createNetwork();
        network.connect();
        network.publish(message);
    }

    protected abstract SocialNetwork createNetwork();
}

class FacebookPoster extends SocialNetworkPoster {
    private final String login;
    private final String password;

    public FacebookPoster(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    protected SocialNetwork createNetwork() {
        return new Facebook(login, password);
    }
}

class LinkedInPoster extends SocialNetworkPoster {
    private final String email;
    private final String password;

    public LinkedInPoster(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    protected SocialNetwork createNetwork() {
        return new LinkedIn(email, password);
    }
}

public class Main {
    public static void main(String[] args) {
        SocialNetworkPoster facebookPoster = new FacebookPoster("john_doe", "12345");
        facebookPoster.post("Hello Facebook!");

        SocialNetworkPoster linkedInPoster = new LinkedInPoster("john.doe@example.com", "67890");
        linkedInPoster.post("Hello LinkedIn!");
    }
}
