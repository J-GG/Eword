package com.eword.general;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public final void testRandomString() {
        String stringM10 = StringUtils.randomString(-10);
        String string0 = StringUtils.randomString(0);
        String string10 = StringUtils.randomString(10);
        String string25 = StringUtils.randomString(25);

        assertEquals(0, stringM10.length());
        assertEquals(0, string0.length());
        assertEquals(10, string10.length());
        assertEquals(25, string25.length());
    }

    @Test
    public final void testSha256() {
        String text1 = "sampletext";
        String text1SHA256Expected = "a5871e22ae2dc0dcffdaebcffa9d12ab2278e22feaa5cbe2891eba56d52678f5";

        String text2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sodales lectus ac sollicitudin laoreet.";
        String text2SHA256Expected = "9b5ef1e6ff7d2e666a702c49cb5a51e9ddb6abc13e08516c4e3a3eca2a6ce691";

        String text1SHA256 = StringUtils.sha256(text1);
        String text2SHA256 = StringUtils.sha256(text2);

        assertEquals(64, text1SHA256.length());
        assertEquals(64, text2SHA256.length());
        assertEquals(text1SHA256Expected, text1SHA256);
        assertEquals(text2SHA256Expected, text2SHA256);
    }
}
