package com.sergiofraga.helbikedataimport.journey;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Batch configuration for the import journeys job
 */
@Configuration
public class JourneyBatchConfiguration {

    @Value("input/2021-0*.csv")
    private Resource[] inputResources;

    /**
     * Initialises a FlatFileItemReader for reading journeys from a source file
     *
     * @return FlatFileItemReader reader
     */
    @Bean
    public FlatFileItemReader<Journey> journeyReader() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(Journey.fields());

        BeanWrapperFieldSetMapper<Journey> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Journey.class);

        DefaultLineMapper<Journey> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        FlatFileItemReader<Journey> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setLineMapper(defaultLineMapper);

        return reader;
    }

    /**
     * Initialises a MultiResourceItemReader which reads items from multiple resources sequentially
     *
     * @return MultiResourceItemReader reader
     */
    @Bean
    public MultiResourceItemReader<Journey> multiResourceJourneyReader() {
        MultiResourceItemReader<Journey> multiResourceItemReader = new MultiResourceItemReader<>();
        multiResourceItemReader.setResources(inputResources);
        multiResourceItemReader.setDelegate(journeyReader());

        return multiResourceItemReader;
    }

    /**
     * Initialises a JdbcBatchItemWriter for writing journeys to a database
     *
     * @param dataSource database connection
     * @return JdbcBatchItemWriter writer
     */
    @Bean
    public JdbcBatchItemWriter<Journey> journeyWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Journey>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO journeys (departure_date, return_date, departure_station_id, departure_station_name, return_station_id, return_station_name, covered_distance_m, duration_s)" +
                        " VALUES (:departureDate, :returnDate, :departureStationId, :departureStationName, :returnStationId, :returnStationName, :distanceCoveredM, :durationS)")
                .dataSource(dataSource)
                .build();
    }

    /**
     * Initialises a ValidatingItemProcessor for processing and validating journeys
     *
     * @return ValidatingItemProcessor validator
     */
    @Bean
    public ValidatingItemProcessor<Journey> journeyValidator() {
        ValidatingItemProcessor<Journey> journeyValidator = new ValidatingItemProcessor<>();
        journeyValidator.setValidator(new JourneyValidator());
        journeyValidator.setFilter(true);

        return journeyValidator;
    }
}