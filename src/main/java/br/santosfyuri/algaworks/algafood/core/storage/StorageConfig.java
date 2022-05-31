package br.santosfyuri.algaworks.algafood.core.storage;

import br.santosfyuri.algaworks.algafood.domain.service.PhotoStorageService;
import br.santosfyuri.algaworks.algafood.infrastructure.storage.LocalPhotoStorageService;
import br.santosfyuri.algaworks.algafood.infrastructure.storage.S3PhotoStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "algafood.storage.type", havingValue = "S3")
    public AmazonS3 amazonS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(storageProperties.getS3().getIdAccessKey(),
                storageProperties.getS3().getSecretAccessKey());
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }

    @Bean
    public PhotoStorageService photoStorageService() {
        if (StorageProperties.StorageType.S3.equals(storageProperties.getType())) {
            return new S3PhotoStorageService();
        }
        return new LocalPhotoStorageService();
    }
}
