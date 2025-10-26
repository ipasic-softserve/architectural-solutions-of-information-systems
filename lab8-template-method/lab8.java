abstract class EntityUpdater {

    public final void updateEntity() {
        Object entity = getEntity();
        if (!validate(entity)) {
            onValidationFailed(entity);
            return;
        }
        sendUpdateRequest(entity);
        Object response = buildResponse(entity);
        sendResponse(response);
    }

    protected abstract Object getEntity();

    protected abstract boolean validate(Object entity);

    protected abstract void sendUpdateRequest(Object entity);

    protected abstract Object buildResponse(Object entity);

    protected void sendResponse(Object response) {
        System.out.println("Response sent: " + response);
    }

    protected void onValidationFailed(Object entity) {
        System.out.println("Validation failed for entity: " + entity);
    }
}

class ProductUpdater extends EntityUpdater {

    @Override
    protected Object getEntity() {
        System.out.println("Fetching Product...");
        return new Object();
    }

    @Override
    protected boolean validate(Object entity) {
        System.out.println("Validating Product...");
        return false;
    }

    @Override
    protected void onValidationFailed(Object entity) {
        super.onValidationFailed(entity);
        notifyAdminInMessenger(entity);
    }

    private void notifyAdminInMessenger(Object entity) {
        System.out.println("Notifying admin about invalid Product...");
    }

    @Override
    protected void sendUpdateRequest(Object entity) {
        System.out.println("Sending Product update request...");
    }

    @Override
    protected Object buildResponse(Object entity) {
        return "Product Update Response: 400 Bad Request";
    }
}

class UserUpdater extends EntityUpdater {

    @Override
    protected Object getEntity() {
        System.out.println("Fetching User...");
        return new Object();
    }

    @Override
    protected boolean validate(Object entity) {
        System.out.println("Validating User (email field cannot change)...");
        return true;
    }

    @Override
    protected void sendUpdateRequest(Object entity) {
        System.out.println("Sending User update request...");
    }

    @Override
    protected Object buildResponse(Object entity) {
        return "User Update Response: 200 OK";
    }
}

class OrderUpdater extends EntityUpdater {

    @Override
    protected Object getEntity() {
        System.out.println("Fetching Order...");
        return new Object();
    }

    @Override
    protected boolean validate(Object entity) {
        System.out.println("Validating Order...");
        return true;
    }

    @Override
    protected void sendUpdateRequest(Object entity) {
        System.out.println("Sending Order update request...");
    }

    @Override
    protected Object buildResponse(Object entity) {
        return "{ code: 200, status: 'OK', order: {...} }";
    }
}

public class Main {
    public static void main(String[] args) {
        EntityUpdater productUpdater = new ProductUpdater();
        EntityUpdater userUpdater = new UserUpdater();
        EntityUpdater orderUpdater = new OrderUpdater();

        System.out.println("Product Update");
        productUpdater.updateEntity();

        System.out.println("\nUser Update");
        userUpdater.updateEntity();

        System.out.println("\nOrder Update");
        orderUpdater.updateEntity();
    }
}
