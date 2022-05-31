package br.santosfyuri.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
public interface PhotoStorageService {

    void storage(NewPhoto newPhoto);

    void delete(String fileName);

    default void toReplace(String fileName, NewPhoto newPhoto) {
        this.storage(newPhoto);
        if (Objects.nonNull(fileName)) {
            this.delete(fileName);
        }
    }

    RetrievedPhoto toRetrieve(String fileName);

    default String generateFileName(String originalName) {
        return UUID.randomUUID().toString() + "_" + originalName;
    }

    @Getter
    @Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
    class NewPhoto {

        private String fileName;
        private InputStream inputStream;
        private String contentType;
    }

    @Getter
    @Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
    class RetrievedPhoto {

        private InputStream inputStream;
        private String url;

        public boolean hasURL() {
            return Objects.nonNull(url);
        }

        public boolean hasInputStream() {
            return Objects.nonNull(inputStream);
        }
    }
}
