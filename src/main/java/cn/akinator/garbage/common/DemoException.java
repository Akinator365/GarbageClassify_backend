package cn.akinator.garbage.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoException extends Exception {
    public DemoException(String message) {
        super(message);
        log.error(message);
    }
}
