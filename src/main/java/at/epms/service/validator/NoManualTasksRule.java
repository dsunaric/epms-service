package at.epms.service.validator;

import at.epms.entity.Severity;
import at.epms.entity.TaskType;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BoundaryEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.ManualTask;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NoManualTasksRule extends Rule {

    public static final Severity severity = null;
    public static final String TITLE = "No manual Tasks are allowed";
    public static final String DESCRIPTION =
        "Manual Tasks should not be part of an executable BPMN diagram";
    public static final String DETAILS =
        "The Returned Effected Elements represent a list of manual tasks in the given diagram";

    private static Logger logger = LoggerFactory.getLogger(NoManualTasksRule.class);

    public static List<ModelElementInstance> getEffectedElements(BpmnModelInstance modelInstance){
        return modelInstance.getModelElementsByType(modelInstance.getModel().getType(ManualTask.class)).stream().toList();
    }


}
