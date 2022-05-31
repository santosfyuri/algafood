package br.santosfyuri.algaworks.algafood.infrastructure.storage;

import br.santosfyuri.algaworks.algafood.core.storage.StorageProperties;
import br.santosfyuri.algaworks.algafood.domain.service.PhotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3PhotoStorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void storage(NewPhoto newPhoto) {
        try {
            String filePath = getFilePath(newPhoto.getFileName());

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(storageProperties.getS3().getBucket(),
                    filePath, newPhoto.getInputStream(),
                    objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
        } catch (Exception ex) {
            throw new StorageException("Não foi possível enviar o arquivo para a Amazon S3", ex);
        }
    }

    private String getFilePath(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getDirectory(), fileName);
    }

    @Override
    public void delete(String fileName) {
        try {
            String filePath = getFilePath(fileName);

            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(), filePath);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir o arquivo na Amazon S3", e);
        }
    }

    @Override
    public RetrievedPhoto toRetrieve(String fileName) {
        String filePath = getFilePath(fileName);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), filePath);
        return RetrievedPhoto.create()
                .url(url.toString())
                .build();
    }
}
