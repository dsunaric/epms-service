package at.epms.service.impl;

import at.epms.service.SuggestionService;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.stereotype.Service;


@Service("SuggestionService")
public class SuggestionServiceImpl implements SuggestionService {


    @Override
    public void applyRules(BpmnModelInstance bpmnModelInstance) {
        // TODO implement rule applying
    }
}
