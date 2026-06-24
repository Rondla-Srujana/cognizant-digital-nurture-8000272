package com.example;
public class Service {
 private Repository repo;
 public Service(Repository repo) { this.repo = repo; }
 public String processData() { return "Processed " + repo.getData(); }
}