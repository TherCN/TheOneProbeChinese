package mcjty.theoneprobe.api;

/**
 * Represent a style for text. This style is configurable by the user and used server-side.
 * Use it like you would use a TextFormatting in your strings. i.e.:
 * probeInfo.text(TextStyleClass.ERROR + "Error! World will explode in 5 seconds!");
 */
public enum TextStyleClass {
    MODNAME("m", "Mod名称"),         // Name of the mod
    NAME("n", "名称"),               // Name of the block or entity
    INFO("i", "信息"),               // General info, neutral
    INFOIMP("I", "重要信息"),   // General info, important
    WARNING("w", "警告"),         // Warning, something is not ready (not mature), or missing stuff
    ERROR("e", "错误"),             // Error, bad situation, out of power, things like that
    OBSOLETE("O", "过时"),       // Obsolete, deprecated, old information
    LABEL("l", "标签"),             // A label, use the 'context' code to set the same as the style that follows
    OK("o", "Ok"),                   // Status ok
    PROGRESS("p", "进度");       // Progress rendering in case the bar is not used

    private final String code;
    private final String readableName;

    TextStyleClass(String code, String readableName) {
        this.code = code;
        this.readableName = readableName;
    }

    public String getCode() {
        return code;
    }

    public String getReadableName() {
        return readableName;
    }

    @Override
    public String toString() {
        return "{=" + code + "=}";
    }
}
