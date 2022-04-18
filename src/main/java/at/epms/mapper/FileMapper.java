package at.epms.mapper;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Mapper
public abstract class FileMapper {

    public BpmnModelInstance multipartFileToBpmnModelInstance(MultipartFile processModel){
        File f = new File("src/main/resources/process.tmp");

        try (OutputStream os = new FileOutputStream(f)) {
            os.write(processModel.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }

        return Bpmn.readModelFromFile(f);
    }
}
