package com.buildlogger;

import com.buildlogger.exception.InvalidBuildLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Map;

/**
 * Created by Swaroop Pallapothu on 26 Mar, 2021
 */
@RunWith(JUnit4.class)
public class BuildLoggerServiceTest {

    BuildLoggerService buildLoggerService;

    @Before
    public void setBuildLoggerService() throws InvalidBuildLog {
        String buildLogAsString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                "1233456,2345,in_asia,BlueTeam,ProjectDate,2221s\n" +
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
        this.buildLoggerService = new BuildLoggerService(buildLogAsString);
    }

    @Test
    public void check_getNumberOfUniqueCustomerIdsForEachContract() {
        Map<Integer, Integer> contractCustomers = buildLoggerService.getNumberOfUniqueCustomerIdsForEachContract();
        Assert.assertEquals(contractCustomers.size(), 2);
        Assert.assertEquals(contractCustomers.get(2345).compareTo(3), 0);
    }

    @Test
    public void check_getNoOfUniqueCustomerIdsForEachGeoZone() {
        Map<String, Integer> geoZoneCustomers = buildLoggerService.getNoOfUniqueCustomerIdsForEachGeoZone();
        Assert.assertEquals(geoZoneCustomers.size(), 4);
        Assert.assertEquals(geoZoneCustomers.get("us_west").compareTo(2), 0);
    }

    @Test
    public void check_getAverageBuildDurationForEachGeoZone() {
        Map<String, Double> geoZoneBuildAvg = buildLoggerService.getAverageBuildDurationForEachGeoZone();
        Assert.assertEquals(geoZoneBuildAvg.size(), 4);
        Assert.assertEquals(geoZoneBuildAvg.get("in_asia").compareTo(2221d), 0);
    }

    @Test
    public void check_getUniqueCustomerIdsForEachGeoZone() {
        Map<String, List<Integer>> geoZoneBuildAvg = buildLoggerService.getUniqueCustomerIdsForEachGeoZone();
        Assert.assertEquals(geoZoneBuildAvg.size(), 4);
        Assert.assertEquals(geoZoneBuildAvg.get("us_west").size(), 2);
    }

}