CREATE TABLE IF NOT EXISTS CATEGORIES
(
    category_id   BIGINT GENERATED BY DEFAULT AS IDENTITY,
    category_name VARCHAR(20),
    CONSTRAINT cat_pk PRIMARY KEY (category_id),
    CONSTRAINT cat_name UNIQUE (category_name)
    );

CREATE TABLE IF NOT EXISTS COMPILATIONS
(
    compilation_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    title          VARCHAR(100),
    pinned         BOOLEAN,
    CONSTRAINT com_pk PRIMARY KEY (compilation_id)
    );

CREATE TABLE IF NOT EXISTS USERS
(
    user_id   BIGINT GENERATED BY DEFAULT AS IDENTITY,
    email     VARCHAR(200),
    user_name VARCHAR(200),
    CONSTRAINT user_pk PRIMARY KEY (user_id),
    CONSTRAINT user_email UNIQUE (email)
    );

CREATE TABLE IF NOT EXISTS LOCATIONS
(
    location_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    lat         INTEGER,
    lon         INTEGER,
    CONSTRAINT loc_pk PRIMARY KEY (location_id)
    );

CREATE TABLE IF NOT EXISTS EVENTS
(
    event_id       BIGINT GENERATED BY DEFAULT AS IDENTITY,
    initiator_id   BIGINT,
    title          VARCHAR(120),
    annotation     VARCHAR(2000),
    category_id    BIGINT,
    description    VARCHAR(7000),
    created_on     TIMESTAMP,
    event_date     TIMESTAMP,
    published_on   TIMESTAMP DEFAULT NULL,
    state          VARCHAR(20),
    paid           BOOLEAN,
    part_limit     INT,
    req_moderation BOOLEAN,
    CONSTRAINT event_pk PRIMARY KEY (event_id),
    CONSTRAINT event_user_fk FOREIGN KEY (initiator_id) REFERENCES USERS,
    CONSTRAINT event_category_fk FOREIGN KEY (category_id) REFERENCES CATEGORIES
    );

CREATE TABLE IF NOT EXISTS EVENT_LOCATIONS
(
    location_id BIGINT,
    event_id    BIGINT,
    CONSTRAINT loc_event_pk PRIMARY KEY (location_id, event_id),
    CONSTRAINT loc_user_fk FOREIGN KEY (location_id) REFERENCES LOCATIONS ON DELETE CASCADE,
    CONSTRAINT loc_event_fk FOREIGN KEY (event_id) REFERENCES EVENTS ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS REQUESTS
(
    request_id   BIGINT GENERATED BY DEFAULT AS IDENTITY,
    requester_id BIGINT,
    event_id     BIGINT,
    created      TIMESTAMP,
    status       varchar(20),
    CONSTRAINT req_pk PRIMARY KEY (request_id),
    CONSTRAINT req_user_fk FOREIGN KEY (requester_id) REFERENCES USERS,
    CONSTRAINT req_event_fk FOREIGN KEY (event_id) REFERENCES EVENTS
    );

CREATE TABLE IF NOT EXISTS COMPILATION_EVENTS
(
    compilation_id BIGINT,
    event_id       BIGINT,
    CONSTRAINT com_event_pk PRIMARY KEY (compilation_id, event_id),
    CONSTRAINT com_user_fk FOREIGN KEY (compilation_id) REFERENCES COMPILATIONS ON DELETE CASCADE,
    CONSTRAINT com_event_fk FOREIGN KEY (event_id) REFERENCES EVENTS ON DELETE CASCADE
    );