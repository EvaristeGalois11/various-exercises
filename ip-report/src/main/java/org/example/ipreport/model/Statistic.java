package org.example.ipreport.model;

public record Statistic(
    String ip, long requests, double percentageRequests, long bytes, double percentageBytes) {}
