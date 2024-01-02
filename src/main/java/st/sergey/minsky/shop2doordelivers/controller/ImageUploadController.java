package st.sergey.minsky.shop2doordelivers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import st.sergey.minsky.shop2doordelivers.model.Image;
import st.sergey.minsky.shop2doordelivers.payload.response.MessageResponse;
import st.sergey.minsky.shop2doordelivers.service.ImageUploadService;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageUploadController {
    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {

        imageUploadService.uploadImageToUser(file, principal);
        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @PostMapping("/{productId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToProduct(@PathVariable("productId") String productId,
                                                             @RequestParam("file")
                                                             MultipartFile file) throws IOException {
        imageUploadService.uploadImageToProduct(file, Long.parseLong(productId));
        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @GetMapping("/profileImage")
    public ResponseEntity<Image> getImageForUser(Principal principal) {
        Image userImage = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }

    @GetMapping("/{productId}/image")
    public ResponseEntity<Image> getImageToProduct(@PathVariable("productId") Long productId) {
        Image productImage = imageUploadService.getImageToProduct(productId);
        return new ResponseEntity<>(productImage, HttpStatus.OK);
    }

}
