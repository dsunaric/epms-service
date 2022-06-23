package at.epms.service.validator;

import at.epms.entity.Severity;
import at.epms.entity.TaskType;
import at.epms.service.impl.SuggestionServiceImpl;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BoundaryEvent;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Event;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NoTwoTasksWithSameExecutorRule extends Rule {

    public static final Severity severity = null;
    public static final String TITLE = "No two consecutive Tasks should have the same executor";
    public static final String DESCRIPTION =
        "Two task that are executed one after the other can be merged if they have the same enitity executing the Task. " +
        "In case of User tasks this means the same usergroup is executing this task. Automated Tasks that follow " +
        "each other should also always be merged to minimize flownodes.";
    public static final String DETAILS =
        "The Returned Effected Elements represent a list of tasks that can be merged with its direct successor." +
        "In case that three or more tasks can be merged, this algorithm will return every Task that can be merged" +
        "with its successor on its own. Therefore if \"task1\" , \"task2\" and \"task3\" can be merged all together, " +
        "this algorithm will \"task2\" and \"task3\" as effected elements";

    private static Logger logger = LoggerFactory.getLogger(NoTwoTasksWithSameExecutorRule.class);

    public static List<ModelElementInstance> getEffectedElements(BpmnModelInstance modelInstance){
        List<ModelElementInstance> effectedElements = new ArrayList<>();

        Collection<ModelElementInstance> taskInstances = modelInstance.getModelElementsByType(modelInstance.getModel().getType(Task.class));
        for (ModelElementInstance taskInstance: taskInstances){
            Task task = (Task) taskInstance;
            if(hasAttachedBoundaryEvent(task,modelInstance)){
                continue;
            }
            List<SequenceFlow> outgoingFlows = task.getOutgoing().stream().toList();
            if(outgoingFlows.size() == 1){
                FlowNode successor = outgoingFlows.get(0).getTarget();
                if(successor instanceof Task){
                    if(hasAttachedBoundaryEvent((Task) successor,modelInstance)){
                        continue;
                    }
                    if(hasSameExecutionType(task, (Task) successor)){
                        effectedElements.add(taskInstance);
                    }
                }
            }


        }
        return effectedElements;
    }

    private static boolean hasAttachedBoundaryEvent(Task task, BpmnModelInstance modelInstance) {
        Collection<BoundaryEvent> boundaryEvents = modelInstance.getModelElementsByType(BoundaryEvent.class);
        if (!boundaryEvents.isEmpty()) {
            for (BoundaryEvent event : boundaryEvents) {
                if (event.getAttachedTo().getName().equals(task.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sees if two tasks have the same kind of execution e.g. if they are both automatable or if they
     * both have the same candidate groups/users
     * @param task1
     * @param task2
     * @return
     */
    private static boolean hasSameExecutionType(Task task1, Task task2){
        ModelElementType type1 = task1.getElementType();
        ModelElementType type2 = task2.getElementType();
        if(type1.getTypeName().equals(type2.getTypeName()) ){
            //Is user tasks -> check if mergable
            if(TaskType.fromString(type1.getTypeName()) == TaskType.USER_TASK){
                if(((UserTask) task1).getCamundaCandidateGroups().equals(((UserTask) task2).getCamundaCandidateGroups())
                 && ((UserTask) task1).getCamundaCandidateUsers().equals(((UserTask) task2).getCamundaCandidateUsers())){
                    return true;
                }
            }
            // tasks can be merged
            return true;
        }
        if ((isAutomatable(task1) && isAutomatable(task2)) ){
            return true;
        }
        return  false;
    }

    private static boolean isAutomatable(Task task){
        return TaskType.fromString(task.getElementType().getTypeName()) == TaskType.SERVICE_TASK
            || TaskType.fromString(task.getElementType().getTypeName()) == TaskType.SEND_TASK;
    }
}
