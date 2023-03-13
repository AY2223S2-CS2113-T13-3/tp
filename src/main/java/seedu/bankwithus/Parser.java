package seedu.bankwithus;

import seedu.bankwithus.exceptions.CommandNotFoundException;

public class Parser {

    private BankWithUs bwu;

    public Parser(BankWithUs bwu) {
        this.bwu = bwu;
    }

    public void parseUserInput(String input) throws CommandNotFoundException {
        // Split input by space
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";

        switch (command) {
        case "exit":
            bwu.isExitEntered = true;
            break;
        default:
            throw new CommandNotFoundException();
        }
    }

}