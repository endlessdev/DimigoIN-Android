package us.narin.dimigoin.model;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */
public class MarketModel {

    String marketThumbnailUrl;
    Integer marketNumber;
    Boolean marketIsSell;
    String marketSubject;
    String marketAuthor;
    String marketDate;
    Integer marketViewCount;
    Integer marketLikeCount;

    public MarketModel(String marketThumbnailUrl, Integer marketNumber, Boolean marketIsSell, String marketSubject, String marketAuthor, String marketDate, Integer marketViewCount, Integer marketLikeCount){
        this.marketThumbnailUrl = marketThumbnailUrl;
        this.marketNumber = marketNumber;
        this.marketIsSell = marketIsSell;
        this.marketSubject = marketSubject;
        this.marketAuthor = marketAuthor;
        this.marketDate = marketDate;
        this.marketViewCount = marketViewCount;
        this.marketLikeCount = marketLikeCount;
    }

    public Boolean getMarketIsSell() {
        return marketIsSell;
    }

    public Integer getMarketLikeCount() {
        return marketLikeCount;
    }

    public Integer getMarketNumber() {
        return marketNumber;
    }

    public Integer getMarketViewCount() {
        return marketViewCount;
    }

    public String getMarketAuthor() {
        return marketAuthor;
    }

    public String getMarketDate() {
        return marketDate;
    }

    public String getMarketSubject() {
        return marketSubject;
    }

    public String getMarketThumbnailUrl() {
        return marketThumbnailUrl;
    }
}
