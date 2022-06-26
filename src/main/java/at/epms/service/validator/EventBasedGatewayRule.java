package at.epms.service.validator;

import at.epms.entity.Severity;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.InclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.ManualTask;
import org.camunda.bpm.model.bpmn.instance.ParallelGateway;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventBasedGatewayRule extends Rule {

    public static final Severity severity = null;
    public static final String TITLE = "No manual Tasks are allowed";
    public static final String DESCRIPTION =
        "Manual Tasks should not be part of an executable BPMN diagram";
    public static final String DETAILS =
        "The Returned Effected Elements represent a list of manual tasks in the given diagram";

    private static Logger logger = LoggerFactory.getLogger(EventBasedGatewayRule.class);

    public static List<ModelElementInstance> getEffectedElements(BpmnModelInstance modelInstance) {
        List<ModelElementInstance> effectedElements = new ArrayList<>();

        Collection<ModelElementInstance> paralellgateways = modelInstance.getModelElementsByType(modelInstance.getModel().getType(ParallelGateway.class));
        for (ModelElementInstance paralellgatewayinstance : paralellgateways) {
            ParallelGateway paralellgateway = (ParallelGateway) paralellgatewayinstance;
            List<SequenceFlow> outgoingFlows = paralellgateway.getOutgoing().stream().toList();
            for (SequenceFlow flow : outgoingFlows) {
                if (flow.getTarget() instanceof ExclusiveGateway
                    || flow.getTarget() instanceof InclusiveGateway) {
                    effectedElements.add(paralellgatewayinstance);
                    break;
                }
            }
        }

        return effectedElements;
    }
}
