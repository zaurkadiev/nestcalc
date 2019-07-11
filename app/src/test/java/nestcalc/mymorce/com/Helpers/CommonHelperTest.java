package nestcalc.mymorce.com.Helpers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import nestcalc.mymorce.com.Storage.Record;

import static org.junit.Assert.*;

/**
 * Created by user on 04/02/2018.
 */
public class CommonHelperTest {
    @Test
    public void cropList() throws Exception {
        List<Record> list = new ArrayList<>();
        list.add(new Record());
        list.add(new Record());
        Record r1 = new Record();
        list.add(r1);
        Record r2 = new Record();
        list.add(r2);
        Record r3 = new Record();
        list.add(r3);

        List<Record> exp = new ArrayList<>();
        exp.add(r1);
        exp.add(r2);
        exp.add(r3);
        List<Record> res = CommonHelper.cropList(list, 3);

        assertEquals(exp, res);
    }

    @Test
    public void isError() throws Exception {
        String arg = "Infinity";
        boolean expected = false;
        boolean actual = CommonHelper.isError(arg);
        assertEquals(expected, actual);
    }

    @Test
    public void isInfinity() throws Exception {
        String arg = "Infinity";
        boolean expected = true;
        boolean actual = CommonHelper.isInfinity(arg);
        assertEquals(expected, actual);
    }

}