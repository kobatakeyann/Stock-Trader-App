package com.suu.hppa.stocktradarapp.presentation.cli.helper.table;

public record TableColmunSpec(
        String colName,
        int contentCharLength,
        Alignment alignment) {

    public enum Alignment {
        LEFT, RIGHT,
    }

    public String wrapContentIfOverWidth(String content) {
        if (content == null) content = "";
        if (content.length() > this.contentCharLength) {
            return content.substring(0, contentCharLength - 3) + "..."; // 3 blanks for '...'
        }
        return content;
    }

    public String formatContent(String content) {
        if (content == null) content = "";
        String formatted = switch (this.alignment) {
            case LEFT -> String.format("%-" + this.contentCharLength + "s", content);
            case RIGHT -> String.format("%" + this.contentCharLength + "s", content);
        };
        // add padding on both sides
        return " " + formatted + " ";
    }
}
