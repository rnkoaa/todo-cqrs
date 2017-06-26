--liquibase formatted sql

--changeset rnkoaa:1
CREATE TABLE IF NOT EXISTS domain_event (
  id         BIGSERIAL PRIMARY KEY NOT NULL,
  aggregate_id VARCHAR(32)           NOT NULL,
  event_details  JSONB          NOT NULL
  );