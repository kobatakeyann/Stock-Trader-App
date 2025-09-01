package com.suu.hppa.stocktradarapp.presentation.cli.helper.table;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TableMaker<T extends TableRenderable> {

	public String build(List<TableColmunSpec> headercols, List<T> records) {
		int contentTotalWidth = this.calculateTotalWidth(headercols);

		return String.join(
				"\n",
				this.buildBorderLine(contentTotalWidth),
				this.buildHeaderLine(headercols),
				this.buildSeparatorLine(headercols),
				this.buildContentLines(records, headercols),
				this.buildBorderLine(contentTotalWidth)
		);
	}

	private int calculateTotalWidth(List<TableColmunSpec> columns) {
		return columns.stream()
				.mapToInt(col -> col.contentCharLength() + 2) // +2 for paddings of each column
				.sum() + columns.size() - 1; // between columns
	}

	private String buildBorderLine(int contentTotalWidth) {
		return "|" + "=".repeat(contentTotalWidth) + "|";
	}

	private String buildHeaderLine(List<TableColmunSpec> columns) {
		return columns.stream()
				.map(
						col -> String
								.format(" " + "%-" + col.contentCharLength() + "s", col.colName())
								+ " "
				)
				.collect(Collectors.joining("|", "|", "|"));
	}

	private String buildSeparatorLine(List<TableColmunSpec> columns) {
		return columns.stream()
				.map(col -> "-".repeat(col.contentCharLength() + 2)) // +2 for paddings of each col
				.collect(Collectors.joining("+", "|", "|"));
	}

	private String buildContentLines(List<T> records, List<TableColmunSpec> columns) {
		return records.stream()
				.map(
						record -> columns.stream()
								.map(col -> {
									String content = col.wrapContentIfOverWidth(
											record.getField(col.colName())
									);
									return col.formatContent(content);
								})
								.collect(Collectors.joining("|", "|", "|"))
				)
				.collect(Collectors.joining("\n"));
	}
}
