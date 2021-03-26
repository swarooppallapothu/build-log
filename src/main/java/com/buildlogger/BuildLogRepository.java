package com.buildlogger;

import com.buildlogger.domain.BuildLog;
import com.buildlogger.exception.InvalidBuildLog;
import com.buildlogger.parser.BuildLogDetailsParser;

import java.util.List;

/**
 * Created by Swaroop Pallapothu on 26 Mar, 2021
 */
public class BuildLogRepository {

    private final String buildLogAsString;
    private final List<BuildLog> buildLogDetails;

    public BuildLogRepository(String buildLogAsString) throws InvalidBuildLog {
        this.buildLogAsString = buildLogAsString;
        this.buildLogDetails = BuildLogDetailsParser.getInstance().convertToBuildLogs(buildLogAsString);
    }

    public String getBuildLogAsString() {
        return buildLogAsString;
    }

    public List<BuildLog> getBuildLogDetails() {
        return buildLogDetails;
    }
}
