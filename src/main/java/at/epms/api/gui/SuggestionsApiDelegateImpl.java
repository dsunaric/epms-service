package at.epms.api.gui;

import at.epms.api.gui.v1.AppliedRule;
import at.epms.api.gui.v1.SuggestionsApi;
import at.epms.api.gui.v1.SuggestionsApiDelegate;
import at.epms.mapper.FileMapper;
import at.epms.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.Response;
import java.util.List;


@Component
public class SuggestionsApiDelegateImpl implements SuggestionsApiDelegate {

    @Autowired
    protected SuggestionService suggestionService;

    @Autowired
    protected FileMapper fileMapper;

    @Override
    public ResponseEntity<List<AppliedRule>> postSuggestions(MultipartFile processModel){
        try{
            return ResponseEntity.ok(
                suggestionService.applyRules(fileMapper.multipartFileToBpmnModelInstance(processModel)));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
