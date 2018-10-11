package com.app.data.input;

class InputDataException extends Exception {

    public static final String UNSUPPORTED_FILE_FORMAT = "Unsupported file format";
    public static final String MALFORMED_DATA = "Invalid Json/Xml form";

    InputDataException(String message) {
        super(message);
    }
}
