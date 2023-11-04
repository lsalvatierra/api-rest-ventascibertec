package pe.edu.cibertec.apirestventascibertec.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.apirestventascibertec.model.response.ResponseFile;
import pe.edu.cibertec.apirestventascibertec.service.FileService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/files")
public class FileController {

    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseFile> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        fileService.save(files);
        return ResponseEntity.status(HttpStatus.OK)
                .body( ResponseFile.builder().message("Los archivos fueron cargados correctamente al servidor").build());
    }

}
