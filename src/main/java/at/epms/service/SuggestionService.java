package at.epms.service;

import at.epms.api.gui.v1.AppliedRule;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.List;

public interface SuggestionService {

    public List<AppliedRule> applyRules(BpmnModelInstance bpmnModelInstance);
}
