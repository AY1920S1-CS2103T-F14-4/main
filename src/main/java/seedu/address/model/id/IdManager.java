package seedu.address.model.id;

import seedu.address.model.person.Customer;
import seedu.address.model.person.Driver;
import seedu.address.model.task.Task;

/**
 * Keeps a record of the last unique id used by {@link Task}, {@link Customer} and {@link Driver}.
 */
public class IdManager {

    //all unique ids start with 1
    private static int lastTaskId = 0;
    private static int lastCustomerId = 0;
    private static int lastDriverId = 0;

    public static int getLastTaskId() {
        return lastTaskId;
    }

    public static void setLastTaskId(int lastTaskId) {
        lastTaskId = lastTaskId;
    }

    public static int getLastCustomerId() {
        return lastCustomerId;
    }

    public static void setLastCustomerId(int lastCustomerId) {
        lastCustomerId = lastCustomerId;
    }

    public static int getLastDriverId() {
        return lastDriverId;
    }

    public static void setLastDriverId(int lastDriverId) {
        lastDriverId = lastDriverId;
    }

    public static void lastTaskIdPlusOne() {
        lastTaskId++;
    }

    public static void lastCustomerIdPlusOne() {
        lastCustomerId++;
    }

    public static void lastDriverIdPlusOne() {
        lastDriverId++;
    }
}
