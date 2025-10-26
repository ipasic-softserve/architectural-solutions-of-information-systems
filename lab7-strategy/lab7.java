interface DeliveryStrategy {
    double calculateCost(double orderAmount);
}

class SelfPickupStrategy implements DeliveryStrategy {
    public double calculateCost(double orderAmount) {
        return 0;
    }
}

class ExternalServiceDeliveryStrategy implements DeliveryStrategy {
    public double calculateCost(double orderAmount) {
        return orderAmount * 0.1 + 5;
    }
}

class OwnDeliveryServiceStrategy implements DeliveryStrategy {
    public double calculateCost(double orderAmount) {
        return orderAmount * 0.05 + 3;
    }
}

class DeliveryContext {
    private DeliveryStrategy strategy;

    public void setStrategy(DeliveryStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(double orderAmount) {
        return strategy.calculateCost(orderAmount);
    }
}

public class Main {
    public static void main(String[] args) {
        DeliveryContext context = new DeliveryContext();
        double orderAmount = 6.99;

        context.setStrategy(new SelfPickupStrategy());
        System.out.println("Self pickup: " + context.executeStrategy(orderAmount));

        context.setStrategy(new ExternalServiceDeliveryStrategy());
        System.out.println("External service: " + context.executeStrategy(orderAmount));

        context.setStrategy(new OwnDeliveryServiceStrategy());
        System.out.println("Own delivery: " + context.executeStrategy(orderAmount));
    }
}
