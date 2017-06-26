--liquibase formatted sql

--changeset rnkoaa:1
CREATE SCHEMA IF NOT EXISTS todocqrs;

CREATE TABLE IF NOT EXISTS todocqrs.domain_event (
  id         BIGINT PRIMARY KEY NOT NULL,
  aggregate_id VARCHAR(32)           NOT NULL,
  event_details  JSONB          NOT NULL,
  created_on TIMESTAMP,
  updated_at TIMESTAMP,
  version    INTEGER
  );