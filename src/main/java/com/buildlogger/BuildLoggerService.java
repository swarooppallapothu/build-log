package com.buildlogger;

import com.buildlogger.domain.BuildLog;
import com.buildlogger.exception.InvalidBuildLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Swaroop Pallapothu on 26 Mar, 2021
 */
public class BuildLoggerService {

    private final BuildLogRepository buildLogRepository;

    public BuildLoggerService(String buildLog) throws InvalidBuildLog {
        this.buildLogRepository = new BuildLogRepository(buildLog);
    }

    public Map<Integer, Integer> getNumberOfUniqueCustomerIdsForEachContract() {

        return this.buildLogRepository.getBuildLogDetails()
                .stream()
                .collect(Collectors.groupingBy(BuildLog::getContractId, Collectors.mapping(BuildLog::getCustomerId,
                        Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    public Map<String, Integer> getNoOfUniqueCustomerIdsForEachGeoZone() {

        return this.buildLogRepository.getBuildLogDetails()
                .stream()
                .collect(Collectors.groupingBy(BuildLog::getGeoZone, Collectors.mapping(BuildLog::getCustomerId,
                        Collectors.collectingAndThen(Collectors.toSet(), Set::size))));
    }

    public Map<String, Double> getAverageBuildDurationForEachGeoZone() {

        return this.buildLogRepository.getBuildLogDetails()
                .stream()
                .collect(Collectors.groupingBy(BuildLog::getGeoZone, Collectors.averagingInt(BuildLog::getBuildDuration)));
    }

    public Map<String, List<Integer>> getUniqueCustomerIdsForEachGeoZone() {

        return this.buildLogRepository.getBuildLogDetails()
                .stream()
                .collect(Collectors.groupingBy(BuildLog::getGeoZone, Collectors.mapping(BuildLog::getCustomerId,
                        Collectors.collectingAndThen(Collectors.toSet(), ArrayList::new))));
    }

}
