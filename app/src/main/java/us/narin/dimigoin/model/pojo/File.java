package us.narin.dimigoin.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Seungwoo on 2016. 1. 4..
 */
public class File {

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


    public String getFileName() {
        return fileName;
    }

    public String getPostTime() {
        return postTime;
    }

    public Integer getFileType() {
        return fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

}
