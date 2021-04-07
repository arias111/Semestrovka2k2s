package com.spring.basics.services.interfaces;

import java.io.Writer;
import java.util.Map;

public interface TemplateResolver {
    String process(String name, Map<String, String> root);
}