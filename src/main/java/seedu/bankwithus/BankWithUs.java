package seedu.bankwithus;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BankWithUs {

    public static final String FILE_PATH = "data/save.txt";

    private Storage storage;
    private Ui ui;
    private AccountList accounts;

    /**
     * Creates a new instance of BankWithUs. Initialises storage, ui and
     * accounts. Should load data into accounts too.
     *
     * @param filePath the filepath. Should be data/save.txt by default
     * @throws IOException thrown when something goes really, really wrong
     */
    public BankWithUs(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            accounts = new AccountList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
            try {
                storage.createNewFile();
            } catch (IOException ioE) {
                ui.showIOError();
                throw ioE;
            }
            accounts = new AccountList();
        }
    }

    /**
     * exit the programme, save the data and show farewell message
     *
     * @throws IOException throw error if the data cannot be saved
     */
    private void exit(String filePath) throws IOException {
        try {
            storage.saveToFile(accounts);
        } catch (IOException e) {
            ui.showIOError();
            throw e;
        }
        ui.showFarewellMessage();
    }

    public void run() {

    }

    public static void main(String[] args) {
        try {
            new BankWithUs(FILE_PATH).run();
        } catch (IOException e) {
            return;
        }
    }
}