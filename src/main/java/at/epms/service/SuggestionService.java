package at.epms.service;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public interface SuggestionService {

    public void applyRules(BpmnModelInstance bpmnModelInstance);
}
