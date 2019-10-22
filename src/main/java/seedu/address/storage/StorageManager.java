package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.legacy.ReadOnlyAddressBook;
import seedu.address.model.task.TaskManager;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    private JsonManagerStorage jsonManagerStorage;

    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    public StorageManager(JsonManagerStorage jsonManagerStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.jsonManagerStorage = jsonManagerStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Managers methods ===============================

    @Override
    public Path getManagerFilePath() {
        return jsonManagerStorage.getManagerFilePath();
    }

    @Override
    public Optional<CentralManager> readManager() throws DataConversionException, IOException {
        return readManager(jsonManagerStorage.getManagerFilePath());
    }

    @Override
    public Optional<CentralManager> readManager(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return jsonManagerStorage.readManager(filePath);
    }

    @Override
    public void saveManager(CentralManager centralManager) throws IOException {
        saveManager(centralManager, jsonManagerStorage.getManagerFilePath());
    }

    @Override
    public void saveManager(CentralManager centralManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        jsonManagerStorage.saveManager(centralManager, filePath);
    }

    // Includes saving task manager
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, TaskManager taskManager) throws IOException {
        saveAddressBook(addressBook, taskManager, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, TaskManager taskManager, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, taskManager, filePath);
    }

}
