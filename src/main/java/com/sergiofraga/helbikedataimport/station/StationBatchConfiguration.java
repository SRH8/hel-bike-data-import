package com.sergiofraga.helbikedataimport.station;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Batch configuration for the import stations job
 */
@Configuration
public class StationBatchConfiguration {

    /**
     * Initialises a FlatFileItemReader for reading stations from a source file
     *
     * @return FlatFileItemReader reader
     */
    @Bean
    public FlatFileItemReader<Station> stationReader() {
        BeanWrapperFieldSetMapper<Station> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Station.class);

        return new FlatFileItemReaderBuilder<Station>()
                .name("stationReader")
                .linesToSkip(1)
                .resource(new ClassPathResource("input/Helsingin_ja_Espoon_kaupunkipyöräasemat_avoin.csv"))
                .delimited()
                .names(Station.fields())
                .fieldSetMapper(beanWrapperFieldSetMapper)
                .build();
    }

    /**
     * Initialises an item writer for writing stations to a database
     *
     * @param dataSource database connection
     * @return JdbcBatchItemWriter writer
     */
    @Bean
    public JdbcBatchItemWriter<Station> stationWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Station>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO stations (fid, id, nimi, namn, name, osoite, adress, kaupunki, stad, operaattor, kapasiteet, x, y)" +
                        " VALUES (:fid, :id, :nimi, :namn, :name, :osoite, :adress, :kaupunki, :stad, :operaattor, :kapasiteet, :x, :y)")
                .dataSource(dataSource)
                .build();
    }

    /**
     * Initialises a ValidatingItemProcessor for processing and validating stations
     *
     * @return ValidatingItemProcessor validator
     */
    @Bean
    public ValidatingItemProcessor<Station> stationValidator() {
        ValidatingItemProcessor<Station> stationValidator = new ValidatingItemProcessor<>();
        stationValidator.setValidator(new StationValidator());
        stationValidator.setFilter(true);

        return stationValidator;
    }
}