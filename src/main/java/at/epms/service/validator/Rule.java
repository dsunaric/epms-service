package at.epms.service.validator;

import at.epms.entity.Severity;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;

import java.util.List;

public abstract class Rule {
    public static final Severity severity = null;

    public static boolean ruleBroken(BpmnModelInstance modelInstance){
        return true;
    }

    public static <T extends BpmnModelElementInstance> List<T> getEffectedElements(BpmnModelInstance modelInstance){
        return null;
    }
}
