package com.sergiofraga.helbikedataimport.station;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Listener for the import stations job
 */
@Component
public class StationJobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(StationJobCompletionNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;
    private int stationCount;

    @Autowired
    public StationJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Prints the result once the job is completed
     *
     * @param jobExecution representation of the execution of a job
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! [IMPORTING STATIONS] JOB FINISHED ! Time to verify the results");

            jdbcTemplate.query("SELECT COUNT(*) FROM stations", (rs, row) -> stationCount = rs.getInt(1));

            log.info(String.format("%s stations have been added to the database", stationCount));
        }
    }
}