package org.example.ipreportgenerator.model;

import java.time.Instant;

public record Log(Instant timestamp, long bytes, int status, String ip) {}
