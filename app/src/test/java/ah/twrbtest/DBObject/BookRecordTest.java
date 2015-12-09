package ah.twrbtest.DBObject;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Wei on 2015/12/9.
 */
public class BookRecordTest {

    @Test
    public void testIsBookable() throws Exception {
        Calendar c, today;
        BookRecord br;
        c = Calendar.getInstance();
        today = Calendar.getInstance();
        br = new BookRecord();

        c.set(2015, 1, 20);
        br.setGetInDate(c.getTime());

        today.set(2015, 1, 1, 0, 0);
        assertFalse(BookRecord.isBookable(br, today));

        today.set(2015, 1, 5, 0, 0);
        assertFalse(BookRecord.isBookable(br, today));

        today.set(2015, 1, 5, BookRecord.MID_NIGHT_H, BookRecord.MID_NIGHT_M - 1);
        assertFalse(BookRecord.isBookable(br, today));

        today.set(2015, 1, 5, BookRecord.MID_NIGHT_H, BookRecord.MID_NIGHT_M);
        assertTrue(BookRecord.isBookable(br, today));

        today.set(2015, 1, 6, 0, 0);
        assertTrue(BookRecord.isBookable(br, today));

        today.set(2015, 1, 7, 0, 0);
        assertTrue(BookRecord.isBookable(br, today));
    }
}