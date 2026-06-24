package com.example;
public class FileService {
 private FileReader reader;
 private FileWriter writer;
 public FileService(FileReader r, FileWriter w) { this.reader = r; this.writer = w; }
 public String processFile() { return "Processed " + reader.read(); }
}