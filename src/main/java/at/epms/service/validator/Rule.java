package at.epms.service.validator;

import at.epms.entity.Severity;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import java.util.List;

public abstract class Rule {
    public static final Severity severity = null;
    public static final String TITLE = "";
    public static final String DESCRIPTION = "";
    public static final String DETAILS = "";

    public static boolean ruleBroken(BpmnModelInstance modelInstance){
        return true;
    }

    public static List<ModelElementInstance> getEffectedElements(BpmnModelInstance modelInstance){
        return null;
    }
}
