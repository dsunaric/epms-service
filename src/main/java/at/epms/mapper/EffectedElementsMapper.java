package at.epms.mapper;

import at.epms.api.gui.v1.AppliedRule;
import at.epms.api.gui.v1.EffectedElement;
import org.camunda.bpm.model.bpmn.instance.Event;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EffectedElementsMapper {

    public default EffectedElement mapEffectedElement(ModelElementInstance modelElementInstance){
        EffectedElement element = new EffectedElement();
        if(modelElementInstance instanceof Task){
            element.setId(((Task) modelElementInstance).getId());
            element.setType(EffectedElement.TypeEnum.TASK);
        } else if(modelElementInstance instanceof Event){
            element.setId(((Event) modelElementInstance).getId());
            element.setType(EffectedElement.TypeEnum.EVENT);
        } else {
            throw new IllegalArgumentException("Effected element has to be Task or event!");
        }
        element.setName(((FlowNode) modelElementInstance).getName());
        return element;
    };

    public List<EffectedElement> mapEffectedElements(List<ModelElementInstance> modelElementInstances);

    public default AppliedRule mapAppliedRule(List<ModelElementInstance> modelElementInstances, String title, String description, String details){
        AppliedRule appliedRule = new AppliedRule();
        appliedRule.setEffectedElements(mapEffectedElements(modelElementInstances));
        appliedRule.setDescription(description);
        appliedRule.setTitle(title);
        appliedRule.setDetails(details);
        return appliedRule;
    };

}