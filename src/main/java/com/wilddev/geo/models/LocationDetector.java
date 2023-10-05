package com.wilddev.geo.models;

public interface LocationDetector {

    Location detect(String ip) throws Exception;
}
