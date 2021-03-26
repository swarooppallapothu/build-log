package com.buildlogger.parser;

import com.buildlogger.domain.BuildLog;
import com.buildlogger.exception.InvalidBuildLog;
import com.buildlogger.util.ApplicationUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Swaroop Pallapothu on 26 Mar, 2021
 */
public class BuildLogDetailsParser {

    private static BuildLogDetailsParser instance;

    private BuildLogDetailsParser() {

    }

    public static BuildLogDetailsParser getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BuildLogDetailsParser();
        }
        return instance;
    }

    public List<BuildLog> convertToBuildLogs(String logDetails) throws InvalidBuildLog {
        if (Objects.isNull(logDetails) || logDetails.isEmpty()) {
            throw new InvalidBuildLog("Log details should not empty!");
        }

        List<BuildLog> buildLogDetails = Arrays.stream(logDetails
                .split("\\r?\\n"))
                .filter(line -> Objects.nonNull(line) && !line.trim().isEmpty())
                .map(this::convertToBuildLog)
                .collect(Collectors.toList());
        return buildLogDetails;
    }

    public BuildLog convertToBuildLog(String logLine) {
        BuildLog buildLog = new BuildLog();
        String[] tuples = logLine.split(",");
        for (int ind = 0; ind < tuples.length; ind++) {
            switch (ind) {
                case 0:
                    buildLog.setCustomerId(ApplicationUtils.parseNumber(tuples[ind]));
                    break;
                case 1:
                    buildLog.setContractId(ApplicationUtils.parseNumber(tuples[ind]));
                    break;
                case 2:
                    buildLog.setGeoZone(tuples[ind]);
                    break;
                case 3:
                    buildLog.setTeamCode(tuples[ind]);
                    break;
                case 4:
                    buildLog.setProjectCode(tuples[ind]);
                    break;
                case 5:
                    buildLog.setBuildDuration(ApplicationUtils.parseNumber(ApplicationUtils.parseString(tuples[ind]).replace("s", "")));
                    break;

            }
        }
        return buildLog;
    }


}
