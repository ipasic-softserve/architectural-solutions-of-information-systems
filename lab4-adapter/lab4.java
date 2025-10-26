public interface Notification {
    void send(String title, String message);
}

public class EmailNotification implements Notification {
    private String adminEmail;

    public EmailNotification(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Override
    public void send(String title, String message) {
        System.out.println("Sent email with title '" + title + "' to '" + adminEmail + "' that says '" + message + "'.");
    }
}

class SlackService {
    private String login;
    private String apiKey;
    private String chatId;

    public SlackService(String login, String apiKey, String chatId) {
        this.login = login;
        this.apiKey = apiKey;
        this.chatId = chatId;
    }

    public void sendSlackMessage(String message) {
        System.out.println("Sent Slack message to chat '" + chatId + "': " + message);
    }
}

public class SlackNotificationAdapter implements Notification {
    private SlackService slackService;

    public SlackNotificationAdapter(String login, String apiKey, String chatId) {
        this.slackService = new SlackService(login, apiKey, chatId);
    }

    @Override
    public void send(String title, String message) {
        slackService.sendSlackMessage(title + ": " + message);
    }
}

class SMSService {
    private String phone;
    private String sender;

    public SMSService(String phone, String sender) {
        this.phone = phone;
        this.sender = sender;
    }

    public void sendSMS(String message) {
        System.out.println("Sent SMS from '" + sender + "' to '" + phone + "': " + message);
    }
}

public class SMSNotificationAdapter implements Notification {
    private SMSService smsService;

    public SMSNotificationAdapter(String phone, String sender) {
        this.smsService = new SMSService(phone, sender);
    }

    @Override
    public void send(String title, String message) {
        smsService.sendSMS(title + ": " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Notification email = new EmailNotification("admin@example.com");
        email.send("Server Alert", "CPU usage is 90%");

        Notification slack = new SlackNotificationAdapter("userLogin", "apiKey123", "chatIdXYZ");
        slack.send("Server Alert", "CPU usage is 90%");

        Notification sms = new SMSNotificationAdapter("+380501234567", "ServerBot");
        sms.send("Server Alert", "CPU usage is 90%");
    }
}
