package com.techverse.rental.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
 

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageService {

	  @Value("${azure.storage.account-name}")
	    private String storageAccountName;

	    @Value("${azure.storage.container-string}")
	    private String container_string;

	    @Value("${azure.storage.container-name}")
	    private String containerName;
 
 
 

  
  public String uploadFileOnAzure(MultipartFile file) {
      try {
          // Construct Azure Blob Storage URL
          String blobServiceUrl = "https://satyaprofilestorage.blob.core.windows.net";
          String blobContainerUrl = String.format("%s/%s", blobServiceUrl, containerName);

          // Get the original file extension
          String originalFileName = file.getOriginalFilename();
          String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

          // Generate a unique name using timestamp and UUID
          String uniqueBlobName = Instant.now().toEpochMilli() + "_" + UUID.randomUUID().toString() + fileExtension;

          // Create BlobClient with connection string
          BlobClientBuilder blobClientBuilder = new BlobClientBuilder().connectionString(container_string)
                  .containerName(containerName).blobName(uniqueBlobName);

          // Upload the file to Azure Blob Storage with the unique blob name
          try (InputStream inputStream = file.getInputStream()) {
              blobClientBuilder.buildClient().upload(inputStream, file.getSize(), true);
          }

          // Create a SAS token that's valid for 1 hour (adjust duration as needed)
          // Create a SAS token without expiration time
          OffsetDateTime expiryTime = OffsetDateTime.of(2099, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

          
          // Assign read permissions to the SAS token
          BlobSasPermission sasPermission = new BlobSasPermission().setReadPermission(true);

          // Set the start time for the SAS token (optional)
          OffsetDateTime startTime = OffsetDateTime.now().minusMinutes(5);

          BlobServiceSasSignatureValues sasSignatureValues = new BlobServiceSasSignatureValues(expiryTime, sasPermission)
                  .setStartTime(startTime);

          // Generate SAS token for the blob
          String sasToken = blobClientBuilder.buildClient().generateSas(sasSignatureValues);

          // Return the URL of the uploaded file with the SAS token
          String fileUrlWithSas = String.format("%s/%s?%s", blobContainerUrl, uniqueBlobName, sasToken);
          return fileUrlWithSas;
      } catch (IOException e) {
          e.printStackTrace();
          return null;
      }
  }
 
  
}

