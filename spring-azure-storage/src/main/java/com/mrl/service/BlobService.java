package com.mrl.service;

import java.io.IOException;

import com.azure.spring.autoconfigure.storage.resource.AzureStorageResourcePatternResolver;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlobService {

    @Autowired
    private BlobServiceClient blobServiceClient;

    public void listBlobs(String containerName) {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        log.info("Listing blobs...");
        for (BlobItem blobItem : containerClient.listBlobs()) {
            log.info("\t{}", blobItem.getName());
        }
    }

    /**
     * Pattern search, the searchPattern should start with azure-blob:// or
     * azure-file://. Such as azure-blob://*\/*, it means list all blobs in all
     * containers; azure-blob://demo-container/**, it means list all blobs in the
     * demo-container container, including any sub-folder
     * 
     * @param searchPattern
     */
    public void listBlobsByPattern(String searchPattern) {
        AzureStorageResourcePatternResolver storageResourcePatternResolver = new AzureStorageResourcePatternResolver(
                blobServiceClient);

        log.info("Listing blobs with search pattern [{}]...", searchPattern);
        try {
            Resource[] resources = storageResourcePatternResolver.getResources(searchPattern);
            for (Resource resource : resources) {
                log.info("\t File name:{}, URI: {}", resource.getFilename(), resource.getURI());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        // Resource resource =
        // storageResourcePatternResolver.getResource(searchLocation);
    }

}
