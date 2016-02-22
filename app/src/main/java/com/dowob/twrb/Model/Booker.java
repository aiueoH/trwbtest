package com.dowob.twrb.Model;

import com.twrb.core.URLConnectionBooker;
import com.twrb.core.book.BookResult;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.List;

public class Booker {
    /**
     * @param from
     * @param to
     * @param date
     * @param no
     * @param qty
     * @param personId
     * @return BookResult, {code, get in time, station and post office pickup date, convenience store pickup time, internet puckup time}
     * @throws IOException
     */
    public AbstractMap.SimpleEntry<Result, List<String>> book(String from, String to, Calendar date, String no, int qty, String personId) {
        com.twrb.core.Booker booker = new URLConnectionBooker();
        try {
            AbstractMap.SimpleEntry<BookResult, List<String>> result = booker.book(from, to, date, no, qty, personId);
            return new AbstractMap.SimpleEntry<>(bookResultToResult(result.getKey()), result.getValue());
        } catch (IOException e) {
            return new AbstractMap.SimpleEntry<>(Result.IO_EXCEPTION, null);
        }
    }

    private Result bookResultToResult(BookResult bookResult) {
        if (bookResult.equals(BookResult.OK)) return Result.OK;
        if (bookResult.equals(BookResult.NO_SEAT)) return Result.NO_SEAT;
        if (bookResult.equals(BookResult.OUT_TIME)) return Result.OUT_TIME;
        if (bookResult.equals(BookResult.NOT_YET_BOOK)) return Result.NOT_YET_BOOK;
        if (bookResult.equals(BookResult.WRONG_STATION)) return Result.WRONG_STATION;
        if (bookResult.equals(BookResult.WRONG_DATA)) return Result.WRONG_DATA;
        if (bookResult.equals(BookResult.WRONG_NO)) return Result.WRONG_NO;
        if (bookResult.equals(BookResult.OVER_QUOTA)) return Result.OVER_QUOTA;
        if (bookResult.equals(BookResult.FULL_UP)) return Result.FULL_UP;
        if (bookResult.equals(BookResult.IO_EXCEPTION)) return Result.IO_EXCEPTION;
        if (bookResult.equals(BookResult.CANNOT_GET_PRONOUNCE)) return Result.CANNOT_GET_PRONOUNCE;
        return Result.UNKNOWN;
    }

    public enum Result {
        OK,
        UNKNOWN,
        NO_SEAT,
        OUT_TIME,
        NOT_YET_BOOK,
        WRONG_STATION,
        WRONG_DATA,
        WRONG_NO,
        OVER_QUOTA,
        FULL_UP,
        IO_EXCEPTION,
        CANNOT_GET_PRONOUNCE
    }
}