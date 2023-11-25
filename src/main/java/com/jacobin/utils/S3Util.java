package com.jacobin.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Util {

	private static final String AWS_ACCESS_KEY = System.getenv("CLOUDCUBE_ACCESS_KEY_ID");
	private static final String AWS_SECRET_KEY = System.getenv("CLOUDCUBE_SECRET_ACCESS_KEY");
	private static final String AWS_REGION = System.getenv("CLOUDCUBE_REGION");
	public static final String AWS_BUCKET = System.getenv("CLOUDCUBE_BUCKET");
	public static final String AWS_URL_FOLDER = System.getenv("CLOUDCUBE_URL_FOLDER");
	
	public static void uploadFile(String fileName, InputStream inputStream) 
			throws S3Exception, AwsServiceException, SdkClientException, IOException {
		
		AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider
				.create(AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY));
		
		S3Client client = S3Client.builder()
				.credentialsProvider(credentialsProvider)
				.region(Region.of(AWS_REGION))
				.build();
		
		PutObjectRequest req = PutObjectRequest.builder()
				.bucket(AWS_BUCKET)
				.key(fileName)
				.acl("public-read")
				.build();
		
		client.putObject(req, RequestBody.fromInputStream(inputStream, inputStream.available()));
	}
	
	public static void deleteFile(String fileName)
            throws S3Exception, AwsServiceException, SdkClientException {
		
		AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider
				.create(AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY));
		
		S3Client client = S3Client.builder()
				.credentialsProvider(credentialsProvider)
				.region(Region.of(AWS_REGION))
				.build();

        DeleteObjectRequest req = DeleteObjectRequest.builder()
				.bucket(AWS_BUCKET)
				.key(fileName)
				.build();

        client.deleteObject(req);
    }
	
	public static String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		int beginIndex = contentDisposition.indexOf("filename=") + 10;
		int endIndex = contentDisposition.length() - 1;
		return contentDisposition.substring(beginIndex, endIndex);
	}
}
