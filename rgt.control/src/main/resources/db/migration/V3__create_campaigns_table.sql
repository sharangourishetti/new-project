CREATE TABLE campaigns (
    uuid CHAR(36) PRIMARY KEY,
    public_id VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    client_id BIGINT NOT NULL,
    campaign_type_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('Active','Paused','Draft') NOT NULL DEFAULT 'Active',
    total_leads INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_campaign_client
        FOREIGN KEY (client_id) REFERENCES clients(id),

    CONSTRAINT fk_campaign_type
        FOREIGN KEY (campaign_type_id) REFERENCES campaign_type(id),

    CONSTRAINT uq_client_campaign UNIQUE (client_id, name)
);
