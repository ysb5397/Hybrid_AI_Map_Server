package com.ysb5397.hybrid_map._global.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;

public class TimeFormatter {

    // OO년 OO월 OO일 OO시 OO분 OO초 로 포맷팅 하는 정적 메서드
    public static String getTime(Timestamp originTime) {
        return DateFormatUtils.format(originTime, "YYYY년 MM월 dd일 HH시 mm분 ss초");
    }
}
