package com.spring.basics.services.interfaces;

import java.util.Map;

public interface TemplateProcessor {
    String getProcessedTemplate(Map<String, String> params, String ftl);
}