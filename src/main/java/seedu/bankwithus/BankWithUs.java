package seedu.bankwithus;

import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.bankwithus.exceptions.CommandNotFoundException;

public class BankWithUs {

    public static final String FILE_PATH = "data/save.txt";
    public boolean isExitEntered = false;
    private Storage storage;
    private Ui ui;
    private AccountList accountList;
    private Parser parser;

    /**
     * Creates a new instance of BankWithUs. Initialises storage, ui and
     * accounts. Should load data into accounts too.
     *
     * @param filePath the filepath. Should be data/save.txt by default
     * @throws IOException thrown when something goes really, really wrong
     */
    public BankWithUs(String filePath) throws IOException {
        // Main object instantiations
        ui = new Ui();
        storage = new Storage(filePath);

        // Ui stuff
        ui.createScanner();
        ui.greet();

        // Initialising accountList
        try {
            accountList = new AccountList(storage.load(), this);
        } catch (FileNotFoundException e) {
            // If savefile not created
            ui.showFileNotFoundError();
            try {
                storage.createNewFile();
            } catch (IOException ioE) {
                ui.showIOError();
                throw ioE;
            }
            accountList = new AccountList(this);
        }
        parser = new Parser(this);
    }

    public AccountList getAccountList() {
        return accountList;
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Exit the programme, save the data and show farewell message
     *
     * @throws IOException throw error if the data cannot be saved
     */
    public void exit() throws IOException {
        isExitEntered = true;
        ui.showFarewellMessage();
        ui.closeScanner();
        try {
            storage.saveToFile(accountList);
        } catch (IOException e) {
            ui.showIOError();
            throw e;
        }
    }

    /**
     * The main command and output loop. Takes in user input line by line
     * and gives it to the parser to execute the command.
     *
     * @throws IOException if something goes wrong while exiting the program
     */
    public void run() throws IOException {
        while (!isExitEntered) {
            String line = ui.getNextLine();
            try {
                parser.parseUserInput(line);
            } catch (CommandNotFoundException e) {
                ui.showCommandNotFoundError();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new BankWithUs(FILE_PATH).run();
        } catch (IOException e) {
            return;
        }
    }
}
