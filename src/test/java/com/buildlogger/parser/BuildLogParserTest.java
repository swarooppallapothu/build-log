package com.buildlogger.parser;

import com.buildlogger.domain.BuildLog;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Created by Swaroop Pallapothu on 26 Mar, 2021
 */
@RunWith(JUnit4.class)
public class BuildLogParserTest extends TestCase {

    BuildLogDetailsParser buildLogDetailsParser;
    String buildLogAsString;

    @Before
    public void before() {
        buildLogDetailsParser = BuildLogDetailsParser.getInstance();
        buildLogAsString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
    }

    @Test
    public void test_convertToBuildLogs() throws Exception {
        List<BuildLog> buildLogs = buildLogDetailsParser.convertToBuildLogs(buildLogAsString);
        Assert.assertFalse(buildLogs.isEmpty());
        Assert.assertEquals(buildLogs.size(), 5);
    }

    @Test
    public void test_checkBuildDuration() throws Exception {
        List<BuildLog> buildLogs = buildLogDetailsParser.convertToBuildLogs(buildLogAsString);
        Assert.assertEquals(buildLogs.get(2).getBuildDuration(), 4322);
    }

}