package com.suu.hppa.summer_training.presentation.cli.viewmodel;

public record TableColumn(
        String name,
        int contentLength,
        Alignment alignment) {

    public enum Alignment {
        LEFT, RIGHT,
    }

    public String wrapContentIfOverWidth(String content) {
        if (content == null) content = "";
        if (content.length() > this.contentLength) {
            return content.substring(0, contentLength - 3) + "..."; // 3 blanks for '...'
        }
        return content;
    }

    public String formatContent(String content) {
        if (content == null) content = "";
        String formatted = switch (this.alignment) {
            case LEFT -> String.format("%-" + this.contentLength + "s", content);
            case RIGHT -> String.format("%" + this.contentLength + "s", content);
        };
        // add padding on both sides
        return " " + formatted + " ";
    }
}
