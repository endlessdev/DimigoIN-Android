package us.narin.dimigoin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Seungwoo on 2016. 1. 4..
 */
public class FileModel {

    @SerializedName("bf_source")
    String fileName;
    @SerializedName("bf_datetime")
    String postTime;
    @SerializedName("bf_type")
    Integer fileType;
    @SerializedName("bf_file")
    String filePath;
    @SerializedName("bf_download")
    Integer downloadCount;

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostTime() {
        return postTime;
    }

}
