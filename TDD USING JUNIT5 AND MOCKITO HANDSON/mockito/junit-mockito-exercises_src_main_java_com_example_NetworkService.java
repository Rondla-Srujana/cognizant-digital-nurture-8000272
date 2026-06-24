package com.example;
public class NetworkService {
 private NetworkClient client;
 public NetworkService(NetworkClient c) { this.client = c; }
 public String connectToServer() { return "Connected to " + client.connect(); }
}