interface OrderFormMediator {
    void dateChanged(String newDate);
    void pickupOptionChanged(boolean isPickup);
    void recipientOptionChanged(boolean isOtherRecipient);
    void timeSlotSelected(String timeSlot);
}

class FlowerOrderMediator implements OrderFormMediator {
    private DatePicker datePicker;
    private TimeSlotSelector timeSlotSelector;
    private RecipientSection recipientSection;
    private PickupCheckbox pickupCheckbox;

    public void setComponents(
            DatePicker datePicker,
            TimeSlotSelector timeSlotSelector,
            RecipientSection recipientSection,
            PickupCheckbox pickupCheckbox
    ) {
        this.datePicker = datePicker;
        this.timeSlotSelector = timeSlotSelector;
        this.recipientSection = recipientSection;
        this.pickupCheckbox = pickupCheckbox;
    }

    @Override
    public void dateChanged(String newDate) {
        System.out.println("update available time slots based on date");
    }

    @Override
    public void pickupOptionChanged(boolean isPickup) {
        System.out.println("enable/disable delivery fields depending on pickup");
    }

    @Override
    public void recipientOptionChanged(boolean isOtherRecipient) {
        System.out.println("show/hide recipient name and phone fields");
    }

    @Override
    public void timeSlotSelected(String timeSlot) {
        System.out.println("handle time slot selection logic");
    }
}

interface Component {
    void setMediator(OrderFormMediator mediator);
}

class DatePicker implements Component {
    private OrderFormMediator mediator;

    @Override
    public void setMediator(OrderFormMediator mediator) {
        this.mediator = mediator;
    }

    public void onDateSelected(String date) {
        mediator.dateChanged(date);
    }
}

class TimeSlotSelector implements Component {
    private OrderFormMediator mediator;

    @Override
    public void setMediator(OrderFormMediator mediator) {
        this.mediator = mediator;
    }

    public void updateAvailableSlots(String date) {
        System.out.println("updating time slots for date: " + date);
    }
}

class RecipientSection implements Component {
    private OrderFormMediator mediator;
    private boolean isOtherRecipient;
    private String recipientName;
    private String recipientPhone;

    @Override
    public void setMediator(OrderFormMediator mediator) {
        this.mediator = mediator;
    }

    public void onOtherRecipientChanged(boolean isOtherRecipient) {
        this.isOtherRecipient = isOtherRecipient;
        mediator.recipientOptionChanged(isOtherRecipient);
    }

    public void setRecipientInfo(String name, String phone) {
        this.recipientName = name;
        this.recipientPhone = phone;
    }
}

class PickupCheckbox implements Component {
    private OrderFormMediator mediator;
    private boolean isPickup;

    @Override
    public void setMediator(OrderFormMediator mediator) {
        this.mediator = mediator;
    }

    public void onPickupChanged(boolean isPickup) {
        this.isPickup = isPickup;
        mediator.pickupOptionChanged(isPickup);
    }
}

public class MediatorDemo {
    public static void main(String[] args) {
        FlowerOrderMediator mediator = new FlowerOrderMediator();

        DatePicker datePicker = new DatePicker();
        TimeSlotSelector timeSlotSelector = new TimeSlotSelector();
        RecipientSection recipientSection = new RecipientSection();
        PickupCheckbox pickupCheckbox = new PickupCheckbox();

        mediator.setComponents(datePicker, timeSlotSelector, recipientSection, pickupCheckbox);

        datePicker.setMediator(mediator);
        timeSlotSelector.setMediator(mediator);
        recipientSection.setMediator(mediator);
        pickupCheckbox.setMediator(mediator);

        datePicker.onDateSelected("2025-10-27");
        recipientSection.onOtherRecipientChanged(true);
        pickupCheckbox.onPickupChanged(true);
    }
}
