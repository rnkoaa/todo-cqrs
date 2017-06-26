--liquibase formatted sql

--changeset rnkoaa:1
CREATE SCHEMA IF NOT EXISTS todocqrs;

CREATE TABLE IF NOT EXISTS todocqrs.domain_event (
  id            BIGSERIAL PRIMARY KEY NOT NULL,
  aggregate_id  VARCHAR(40)           NOT NULL,
  event_details JSONB                 NOT NULL,
  event_type    VARCHAR(256)          NOT NULL,
  created_on    TIMESTAMP
);