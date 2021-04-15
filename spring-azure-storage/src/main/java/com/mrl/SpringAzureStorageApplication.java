package com.mrl;

import com.mrl.service.BlobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAzureStorageApplication implements CommandLineRunner {

	@Autowired
	private BlobService blobService;

	public static void main(String[] args) {
		SpringApplication.run(SpringAzureStorageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		blobService.listBlobs("assets-images");

		// list all blobs in the specified container (after azure-blob://), including
		// any sub-folder
		blobService.listBlobsByPattern("azure-blob://assets-images/**");

		// list all blobs in all containers
		blobService.listBlobsByPattern("azure-blob://*/*");

	}

}
