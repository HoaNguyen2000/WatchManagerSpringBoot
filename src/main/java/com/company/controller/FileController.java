//package com.company.controller;
//
//
//import com.company.services.FileStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Path;
//import java.util.stream.Stream;
//
//@RestController
//@RequestMapping("api/v1/uploads")
//public class FileController {
//
//
//    @Autowired
//    private FileStorageService fileStorageService;
//
//    @GetMapping(path = "/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//        Resource file = fileStorageService.load(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//
//    @PostMapping
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        try {
//            fileStorageService.saveImage(file);
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(message);
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//        }
//    }
//    @GetMapping("abc")
//    public ResponseEntity<Stream<Path>> uploadFile() {
//            return ResponseEntity.status(HttpStatus.OK).body(fileStorageService.loadAll());
//
//    }
//}