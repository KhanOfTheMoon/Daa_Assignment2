package org.example.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.Flushable;
import java.io.IOException;

public final class CsvWriter implements Closeable, Flushable {
    private final BufferedWriter bw;
    public CsvWriter(String path) throws IOException { this.bw = new BufferedWriter(new FileWriter(path)); }
    public void header(String... cols) throws IOException { writeRow(cols); }
    public void row(Object... cols) throws IOException {
        String[] s = new String[cols.length];
        for (int i = 0; i < cols.length; i++) s[i] = String.valueOf(cols[i]);
        writeRow(s);
    }
    private void writeRow(String... cols) throws IOException {
        bw.write(String.join(",", cols));
        bw.write("\n");
    }
    @Override public void flush() throws IOException { bw.flush(); }
    @Override public void close() throws IOException { bw.close(); }
}