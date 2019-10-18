package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;



/**
 * Represents a Customer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Customer extends Person {

    private static int idCount = 0;
    private int id;


    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        id = ++idCount;
    }

    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags, String id) {
        super(name, phone, email, address, tags);
        this.id = Integer.parseInt(id);
    }

    public int getIdCount() {
        return idCount;
    }

    public void setIdCount(int idCount) {
        Customer.idCount = idCount;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Returns a string representation of the customer, with identity fields visible to the user.
     *
     * @return string representation of customer
     */

    @Override
    public String toString() {
        StringBuilder customerBuilder = new StringBuilder();
        customerBuilder.append(" Customer stats: \n")
                .append(" id: ")
                .append(getId())
                .append(super.toString());
        return customerBuilder.toString();
    }
}
