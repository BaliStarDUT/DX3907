package top.hunaner.lol.controller.heros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import top.hunaner.lol.service.storage.StorageService;
import top.hunaner.lol.service.storage.impl.StorageFileNotFoundException;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by James Yang on 2017/7/17 0017 下午 7:20.
 */
@Controller
@RequestMapping(value = "herosassets")
public class HerosAssetsController {
    private StorageService storageService;

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value = "/data/files",method=RequestMethod.GET)
    public ResponseEntity<Model> getAll(Model model){
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(HerosAssetsController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
        return  new ResponseEntity(model, HttpStatus.OK);
    }

    @RequestMapping(value = "/files",method=RequestMethod.GET)
    public String listUploadedFiles(Model model) throws IOException {
//        model.addAttribute("files", storageService
//                .loadAll()
//                .map(path ->
//                        MvcUriComponentsBuilder
//                                .fromMethodName(HerosAssetsController.class, "serveFile", path.getFileName().toString())
//                                .build().toString())
//                .collect(Collectors.toList()));

        model.addAttribute("lolimages", storageService
                .loadAll()
                .map(path -> "/resources/image/champions/"+path.getFileName().toString())
                .collect(Collectors.toList()));
        return "heros/heros_resources";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/files/{filename:.+}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }
}
