package dhl.edi.module.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FTPConnection {
	
	private static Logger logger = Logger.getLogger(FTPConnection.class);
	private FTPClient ftpClient = null;
	
	public void connect(String host, int port, String userName, String password) throws Exception
	{
		int reply=0;
		
		ftpClient = new FTPClient();
		//ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		
		try {
			ftpClient.connect(host, port);
			reply = ftpClient.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) 
			{
				logger.error("Exception in connecting to FTP Server");
			}
			
			logger.info("Try to login to ftp successfully: " + host);
			
			//boolean loginResult = ftpClient.login("anonymous", "anonymous");
			boolean loginResult = ftpClient.login(userName, password);
			if(loginResult == false)
			{
				logger.error("Login to FTP is failed");
				throw new Exception("Login to FTP is failed");
			}
			
	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	        //ftpClient.enterLocalPassiveMode();
	        
	        logger.info("Login to ftp successfully: " + host);
	        
		} catch (SocketException e) {
			throw e;
			
		} catch (IOException e) {
			throw e;
		}        
        
	}
	
	public void uploadFile(File file, String remotePath, String remoteFileName) throws Exception
	{
		boolean uploadResult = false;
		InputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(file);
			uploadResult = ftpClient.storeFile(remotePath + remoteFileName, inputStream);

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally{
			try {
				if(inputStream!=null)
					inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (uploadResult == false)
		{
			logger.error("Upload file to FTP is failed");
			throw new Exception("Upload file to FTP is failed");
		} 
		
			
	}
	
	public int downloadFile(String localFilePath, String localFileName, String remotePath, String remoteFileName)
	{
		FileOutputStream fos = null;
		boolean downloadResult = false;
		
		try {
			File localFolder = new File(localFilePath);

			if(localFolder.exists()!=true){
				
				logger.debug("Create local in folder" + localFilePath);			
				localFolder.mkdirs();
			}
			
			fos = new FileOutputStream(FilenameUtils.concat(localFilePath,localFileName));
			downloadResult = ftpClient.retrieveFile(remotePath+remoteFileName, fos);
								
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
			return 0;
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			return 0;
		} finally {
			try {
				if(fos!=null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(downloadResult==false)
		{
			logger.error("Download file from FTP is failed");
			return 0;
		}else
			return 1;
		
		
	}
	
	/*
	 * Download all files in the remote path
	 */
	public ArrayList<File> downloadFiles(String remotePath, String localPath) throws IOException{
		
		ArrayList<String> allFileList = this.listFiles(remotePath);
		int downloadResult = 0;
		File downloadedFile = null;
		ArrayList<File> downloadFileList = new ArrayList<File>();
		
		for(String tempFileName:allFileList){
			
			downloadResult = this.downloadFile(localPath, tempFileName, remotePath, tempFileName);
			
			if(downloadResult==1){
				
				downloadedFile = new File(localPath, tempFileName);
				downloadFileList.add(downloadedFile);
			}
			
		}
		
		return downloadFileList;
	}
	
	/*
	 * download file by prefix of file name(ex, "SHIP")  
	 */
	public ArrayList<File> downloadFileByPrefix(String remotePath, String prefix, String localPath) throws IOException
	{
		ArrayList<String> allFileList = this.listFiles(remotePath);
		logger.debug("file amount in the ftp: " + allFileList.size());
		
		//get ship in file list
		ArrayList<String> filteredFileList = this.filterFileName(allFileList, prefix);
		ArrayList<File> downloadFileList = new ArrayList<File>();
		int downloadResult = 0;
		File downloadedFile = null;
		
		//download files
		for(String tempFileName:filteredFileList){
			
			downloadResult = this.downloadFile(localPath, tempFileName, remotePath, tempFileName);
			
			if(downloadResult==1){
				
				downloadedFile = new File(localPath, tempFileName);
				downloadFileList.add(downloadedFile);
			}
			
		}

		return downloadFileList;
	}
	
	private ArrayList filterFileName(ArrayList fileNameList, String prefix)
	{
		ArrayList filteredList = new ArrayList();
		Iterator iterator = fileNameList.iterator();
		String temp = null;
		
		while(iterator.hasNext())
		{
			 temp = (String) iterator.next();
			 
			 if (temp.startsWith(prefix))
				 filteredList.add(temp);
		}
		
		return filteredList;
	}
	
	public boolean moveFile(String sourcePath, String sourceFileName, String destnationPath, String destFileName) throws IOException{
		
		String from = sourcePath + sourceFileName;
		String to = destnationPath +  destFileName;
		boolean renameResult = false;
		
		try {
			renameResult = ftpClient.rename(from, to);
			
		} catch (IOException e) {
			throw e;
		}

		return renameResult;
	}
	
	public boolean deleteFile(String filePath, String fileName) throws IOException{
		
		String filePathName = filePath + fileName;
		boolean result = false;

		result = ftpClient.deleteFile(filePathName);

		return result;
	}
	
	public void disConnect()
	{
		if(ftpClient!=null && ftpClient.isConnected())
		{
			try {
				//ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			
			
		}
	}
	
	public ArrayList<String> listFiles(String directory) throws IOException
	{
		ArrayList<String> fileNameList = new ArrayList<String>();
		
		FTPFile[] files = null;
		
		//filter file size must be larger than 0
		FTPFileFilter fileSizeFilter = new FTPFileFilter() {
			 
		    @Override
		    public boolean accept(FTPFile ftpFile) {
		 
		        return ftpFile.getSize() > 0;
		 
		    }
		};
		
		files = ftpClient.listFiles(directory, fileSizeFilter);
					
        for (FTPFile file : files) {

            fileNameList.add(file.getName());
        }
        
        return fileNameList;
		
	}
	
	/*
	 * �T�{��w�ؿ�O�_����w�ɮ�
	 * return 1: �O
	 * return 0: �_
	 */
	public int isFileExisted(String directory, String fileName){
			
		String pathName = null;
		pathName = directory + fileName;
		String[] result = null;
		
		try {
			logger.debug("isFileExisted: " + pathName);
			result = ftpClient.listNames(pathName);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
		
		if(result==null) //�ؿ�L��w�ɮ�
			return 0;
		else if(result!=null && result.length==0) //�ؿ�L��w�ɮ�
			return 0;
		else if(result.length>=0) //�ؿ��w�ɮ�
			return 1;
		else
			return 0;
			
	}
	

}
