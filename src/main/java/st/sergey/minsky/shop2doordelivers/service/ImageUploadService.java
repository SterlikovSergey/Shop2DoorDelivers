package st.sergey.minsky.shop2doordelivers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import st.sergey.minsky.shop2doordelivers.exception.ImageNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.ProductNotFoundException;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;
import st.sergey.minsky.shop2doordelivers.model.Image;
import st.sergey.minsky.shop2doordelivers.model.Product;
import st.sergey.minsky.shop2doordelivers.model.User;
import st.sergey.minsky.shop2doordelivers.repository.ImageRepository;
import st.sergey.minsky.shop2doordelivers.repository.ProductRepository;
import st.sergey.minsky.shop2doordelivers.repository.UserRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
public class ImageUploadService {
    public static final Logger LOG = LoggerFactory.getLogger(ImageUploadService.class);
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            LOG.error("Cannot decompress Bytes");
        }
        return outputStream.toByteArray();
    }

    public Image uploadImageToUser(MultipartFile file, Principal principal) throws IOException {
        User user = getUserByPrincipal(principal);
        LOG.info("Uploading image profile to User {}", user.getUsername());

        Image userProfileImage = imageRepository.findByUserId(user.getId())
                .orElseThrow(() -> new UserNotFoundException("Username not found with userId " + user.getId()
                ));
        if (!ObjectUtils.isEmpty(userProfileImage)) {
            imageRepository.delete(userProfileImage);
        }
        return imageRepository.save(
                Image
                        .builder()
                        .userId(user.getId())
                        .imageBytes(compressBytes(file.getBytes()))
                        .name(file.getOriginalFilename())
                        .build()
        );
    }

    public Image uploadImageToProduct(MultipartFile file, Long productId) throws IOException {
        Product product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product " +
                        "not found with id " + productId));

        LOG.info("Uploading image to Product {}", product.getId());
        return imageRepository.save(
                Image
                        .builder()
                        .productId(product.getId())
                        .imageBytes(file.getBytes())
                        .imageBytes(compressBytes(file.getBytes()))
                        .name(file.getOriginalFilename())
                        .build()

        );
    }

    public Image getImageToUser(Principal principal) {
        User user = getUserByPrincipal(principal);

        Image imageModel = imageRepository.findByUserId(user.getId()).orElse(null);
        if (!ObjectUtils.isEmpty(imageModel)) {
            imageModel.setImageBytes(decompressBytes(imageModel.getImageBytes()));
        }
        return imageModel;
    }

    public Image getImageToProduct(Long productId) {
        Image imageModel = imageRepository.findByProductId(productId)
                .orElseThrow(() -> new ImageNotFoundException("Cannot find image to Product: " + productId));
        if (!ObjectUtils.isEmpty(imageModel)) {
            imageModel.setImageBytes(decompressBytes(imageModel.getImageBytes()));
        }
        return imageModel;
    }

    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            LOG.error("Cannot compress Bytes");
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return (User) userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Username not found with username " + username));

    }

    private <T> Collector<T, ?, T> toSingleProductCollector() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}
