package at.epms.service.impl;

import at.epms.api.gui.v1.AppliedRule;
import at.epms.mapper.EffectedElementsMapper;
import at.epms.mapper.FileMapper;
import at.epms.service.SuggestionService;
import at.epms.service.validator.NoManualTasksRule;
import at.epms.service.validator.NoTwoTasksWithSameExecutorRule;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("SuggestionService")
public class SuggestionServiceImpl implements SuggestionService {

    private Logger logger = LoggerFactory.getLogger(SuggestionServiceImpl.class);



    @Autowired
    protected EffectedElementsMapper effectedElementsMapper;

    @Override
    public List<AppliedRule> applyRules(BpmnModelInstance bpmnModelInstance) {
        ArrayList<AppliedRule> appliedRules = new ArrayList<>();
        logger.debug(bpmnModelInstance.getModel().getModelName());

        appliedRules.add(effectedElementsMapper.mapAppliedRule(
                NoTwoTasksWithSameExecutorRule.getEffectedElements(bpmnModelInstance),
                NoTwoTasksWithSameExecutorRule.TITLE,
                NoTwoTasksWithSameExecutorRule.DESCRIPTION,
                NoTwoTasksWithSameExecutorRule.DETAILS
                ));

        appliedRules.add(effectedElementsMapper.mapAppliedRule(
            NoManualTasksRule.getEffectedElements(bpmnModelInstance),
            NoManualTasksRule.TITLE,
            NoManualTasksRule.DESCRIPTION,
            NoManualTasksRule.DETAILS
        ));


        return appliedRules;
    }
}
