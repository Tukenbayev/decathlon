package enums;

public enum InputFileExtension {
    CSV, XLS, XLSX;

    public static InputFileExtension get(String extension) {
        return valueOf(extension.toUpperCase());
    }
}
