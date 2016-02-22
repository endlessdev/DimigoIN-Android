package us.narin.dimigoin.util;

import android.content.Context;
import android.content.res.Resources;

import java.util.Date;

import us.narin.dimigoin.R;

public class TimeStamp {

    Context mContext;

    public TimeStamp(Context mContext) {
        this.mContext = mContext;
    }

    public enum Times {

        SEC(60), MIN(60), HOUR(24), DAY(30), MONTH(12);
        private int timeValue;

        Times(int timeValue) {
            this.timeValue = timeValue;
        }

        public int getTimeValue() {
            return timeValue;
        }

    }

    public String getTimes(Date dateValue) {
        long curTime = System.currentTimeMillis();
        long regTime = dateValue.getTime();


        long diffTime = (curTime - regTime) / 1000;
        String dateMsg;

        Resources resString = mContext.getResources();

        if (diffTime < Times.SEC.getTimeValue())
            dateMsg = resString.getString(R.string.timestamp_now);
        else if ((diffTime /= Times.SEC.getTimeValue()) < Times.MIN.getTimeValue())
            dateMsg = diffTime + resString.getString(R.string.timestamp_minute);
        else if ((diffTime /= Times.MIN.getTimeValue()) < Times.HOUR.getTimeValue())
            dateMsg = diffTime + resString.getString(R.string.timestamp_hour);
        else if ((diffTime /= Times.HOUR.getTimeValue()) < Times.DAY.getTimeValue())
            dateMsg = (diffTime) + resString.getString(R.string.timestamp_day);
        else if ((diffTime /= Times.DAY.getTimeValue()) < Times.MONTH.getTimeValue())
            dateMsg = (diffTime) + resString.getString(R.string.timestamp_month);
        else
            dateMsg = (diffTime) + resString.getString(R.string.timestamp_year);
        return dateMsg;
    }

}